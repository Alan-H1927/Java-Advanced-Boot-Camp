package com.study.client;

import com.alibaba.fastjson.JSON;
import com.study.api.RpcResponse;
import com.study.protocol.RpcProtocol;
import com.study.util.ConvertUtil;
import com.study.util.CuratorUtil;
import com.study.util.RpcNodeUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    private CountDownLatch countDownLatch;

    private RpcResponse response;

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void setResponse(RpcResponse response) {
        this.response = response;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 用于获取服务端发来的数据信息
        RpcProtocol rpcProtocol = (RpcProtocol) msg;
        String content = new String(rpcProtocol.getContent(), CharsetUtil.UTF_8);
        logger.info("Netty client receive message:");
        logger.info("Message length: [{}]", rpcProtocol.getContentLength());
        logger.info("Message content: [{}]", content);
        response = JSON.parseObject(content, RpcResponse.class);
        logger.info("Message response: [{}]", content);
        countDownLatch.countDown();
    }

    /**
     * 阻塞等待结果后返回
     *
     * @return 后台服务器响应
     * @throws InterruptedException exception
     */
    RpcResponse getResponse() throws InterruptedException {
        countDownLatch.await();
        return response;
    }
}
