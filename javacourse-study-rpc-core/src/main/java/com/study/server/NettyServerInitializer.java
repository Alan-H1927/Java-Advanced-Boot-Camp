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
        //执行顺序如下
        //1，服务端接收到请求，先解码RpcDecoder
        //2，解码完以后处理业务逻辑NettyServerHandler
        //3，处理完业务逻辑以后，再编码发送出去RpcEncoder
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new RpcEncoder());
        channelPipeline.addLast(new RpcDecoder());
        channelPipeline.addLast(new NettyServerHandler());
    }
}
