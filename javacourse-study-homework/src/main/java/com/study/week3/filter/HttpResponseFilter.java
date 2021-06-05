package com.study.week3.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public interface HttpResponseFilter {
    void filter(FullHttpResponse response);
}
