package com.study.week3.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.client.methods.HttpGet;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("X-Netty-Request", "soul");
    }

    @Override
    public void filter(HttpGet httpGet) {
        httpGet.setHeader("X-Netty-Request", "soul");
    }
}
