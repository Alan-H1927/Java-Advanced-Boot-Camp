package com.study.client;

import com.alibaba.fastjson.JSON;
import com.study.api.RpcRequest;
import com.study.api.RpcResponse;
import com.study.constant.NettyConstant;
import com.study.protocol.RpcProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 客户端
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class NettyClient {

    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private static final ConcurrentHashMap<String, Channel> CHANNEL_POOL = new ConcurrentHashMap<>();

    public RpcResponse invoke(RpcRequest rpcRequest) {
        logger.info("NettyClient启动");
        //走缓存
        RpcResponse rpcResponse = cacheRpcResponse(rpcRequest);
        if (rpcResponse != null) {
            logger.info("使用缓存结果[{}]", rpcResponse);
            return rpcResponse;
        }

        rpcResponse = new RpcResponse();
        NettyClientHandler handler = new NettyClientHandler();

        handler.setCountDownLatch(new CountDownLatch(1));
        handler.setResponse(rpcResponse);

        Channel channel = createChannel();
        channel.pipeline().replace("clientHandler", "clientHandler", handler);
        RpcResponse response = null;
        try {
            RpcProtocol rpcProtocol = convertProtocol(rpcRequest);
            channel.writeAndFlush(rpcProtocol).sync();
            response = handler.getResponse();
        } catch (InterruptedException e) {
            logger.error("invoke error", e);
        }
        CHANNEL_POOL.put(rpcRequest.getServiceClass(), channel);
        return response;
    }

    private RpcProtocol convertProtocol(RpcRequest rpcRequest) {
        RpcProtocol request = new RpcProtocol();
        String requestJson = JSON.toJSONString(rpcRequest);
        request.setContentLength(requestJson.getBytes(CharsetUtil.UTF_8).length);
        request.setContent(requestJson.getBytes(CharsetUtil.UTF_8));
        return request;
    }

    private Channel createChannel() {
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);
        Channel channel = null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new NettyClientInitializer());
            // 异步链接服务器 同步等待链接成功
            channel = b.connect(NettyConstant.NETTY_HOST, NettyConstant.NETTY_PORT).sync().channel();
            logger.info("开启netty http client，连接地址和端口为 http://127.0.0.1:[{}]", NettyConstant.NETTY_PORT);
        } catch (InterruptedException e) {
            logger.error("NettyClient occur error", e);
        }
        return channel;
    }

    private RpcResponse cacheRpcResponse(RpcRequest rpcRequest) {
        String cacheKey = rpcRequest.getServiceClass();
        if (CHANNEL_POOL.containsKey(cacheKey)) {
            Channel channel = CHANNEL_POOL.get(cacheKey);
            if (!channel.isActive() || !channel.isWritable() || !channel.isOpen()) {
                logger.debug("Channel can't reuse");
            } else {
                try {
                    RpcProtocol rpcProtocol = convertProtocol(rpcRequest);
                    NettyClientHandler handler = new NettyClientHandler();
                    handler.setCountDownLatch(new CountDownLatch(1));
                    channel.pipeline().replace("clientHandler", "clientHandler", handler);
                    channel.writeAndFlush(rpcProtocol).sync();
                    return handler.getResponse();
                } catch (Exception e) {
                    logger.debug("channel reuse send msg failed!");
                    channel.close();
                    CHANNEL_POOL.remove(cacheKey);
                }
                logger.debug("Handler is busy, please user new channel");
            }
        }
        return null;
    }
}
