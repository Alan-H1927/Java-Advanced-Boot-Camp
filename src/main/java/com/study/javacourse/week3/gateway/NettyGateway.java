package com.study.javacourse.week3.gateway;

import com.study.javacourse.week3.bean.UrlBean;
import com.study.javacourse.week3.handler.HandlerEnum;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public class NettyGateway {

    private static final Logger logger = LoggerFactory.getLogger(NettyGateway.class);

    private static final int PORT = 8080;

    private HandlerEnum handlerEnum;

    private List<UrlBean> urls;

    public NettyGateway(HandlerEnum handlerEnum, List<UrlBean> urls) {
        this.handlerEnum = handlerEnum;
        this.urls = urls;
    }

    public void run() {
        logger.info("NettyGateway begin");
        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new NettyGatewayInitializer(handlerEnum,urls));
            Channel ch = b.bind(PORT).sync().channel();
            logger.info("开启NettyGateway服务器，监听地址和端口为 http://127.0.0.1:{}", PORT);
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("NettyGateway exception", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        logger.info("NettyGateway end");
    }
}
