package com.study.server;

import com.study.codec.RpcDecoder;
import com.study.codec.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new RpcEncoder());
        channelPipeline.addLast(new RpcDecoder());
        channelPipeline.addLast(new NettyServerHandler());
    }
}
