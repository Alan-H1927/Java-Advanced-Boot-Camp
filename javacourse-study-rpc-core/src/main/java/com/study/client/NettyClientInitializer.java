package com.study.client;

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
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel socketChannel) {
        //执行顺序如下
        //1，客户端发送请求，先编码RpcEncoder
        //2，编码完发送请求以后会收到服务端的返回，所以要解码RpcDecoder
        //3，解码完以后要处理业务逻辑NettyClientHandler
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new RpcDecoder());
        channelPipeline.addLast(new RpcEncoder());
        channelPipeline.addLast("clientHandler",new NettyClientHandler());
    }
}
