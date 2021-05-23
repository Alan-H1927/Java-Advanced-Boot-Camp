package com.study.javacourse.week3.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("X-Netty-Response", "java-1-nio");
    }
}
