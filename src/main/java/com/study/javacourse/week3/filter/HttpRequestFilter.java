package com.study.javacourse.week3.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.client.methods.HttpGet;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public interface HttpRequestFilter {
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);

    void filter(HttpGet httpGet);
}
