package com.study.codec;

import com.study.protocol.RpcProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {

    private static final Logger logger = LoggerFactory.getLogger(RpcEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcProtocol rpcProtocol, ByteBuf byteBuf) {
        logger.info("进入RpcEncoder");
        // 1.写入消息的开头的信息标志(int类型)
        byteBuf.writeInt(rpcProtocol.getHEAD());
        // 2.写入消息的长度(int 类型)
        byteBuf.writeInt(rpcProtocol.getContentLength());
        // 3.写入消息的内容(byte[]类型)
        byteBuf.writeBytes(rpcProtocol.getContent());
    }
}
