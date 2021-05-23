package com.study.javacourse.week3.gateway;

import com.study.javacourse.week3.bean.UrlBean;
import com.study.javacourse.week3.handler.Handler;
import com.study.javacourse.week3.handler.HandlerEnum;
import com.study.javacourse.week3.handler.HandlerFactory;
import com.study.javacourse.week3.router.ServerRouter;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public class NettyGatewayInitializer extends ChannelInitializer<SocketChannel> {

    private HandlerEnum handlerEnum;

    private List<UrlBean> urls;

    public NettyGatewayInitializer(HandlerEnum handlerEnum, List<UrlBean> urls) {
        this.handlerEnum = handlerEnum;
        this.urls = urls;
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        ServerRouter serverRouter = new ServerRouter();
        ChannelInboundHandlerAdapter channelInboundHandlerAdapter = HandlerFactory.INSTANCE.create(handlerEnum, serverRouter.route(urls));
        p.addLast(channelInboundHandlerAdapter);
    }
}
