package com.study.codec;

import com.study.protocol.RpcProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcProtocol rpcProtocol, ByteBuf byteBuf) throws Exception {
        // 1.写入消息的开头的信息标志(int类型)
        byteBuf.writeInt(rpcProtocol.getHEAD());
        // 2.写入消息的长度(int 类型)
        byteBuf.writeInt(rpcProtocol.getContentLength());
        // 3.写入消息的内容(byte[]类型)
        byteBuf.writeBytes(rpcProtocol.getContent());
    }
}
