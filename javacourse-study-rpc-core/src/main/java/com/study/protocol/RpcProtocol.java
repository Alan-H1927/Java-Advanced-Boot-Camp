package com.study.protocol;

import java.util.Arrays;

/**
 * 自定义RPC协议
 * 协议开始标志+报文长度+内容
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class RpcProtocol {

    private final int HEAD = ProtocolConstant.HEAD;

    private int contentLength;

    private byte[] content;

    public RpcProtocol() {
    }

    public RpcProtocol(int contentLength, byte[] content) {
        this.contentLength = contentLength;
        this.content = content;
    }

    public int getHEAD() {
        return HEAD;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RpcProtocol{" +
                "HEAD=" + HEAD +
                ", contentLength=" + contentLength +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
