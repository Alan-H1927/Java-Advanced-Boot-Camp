package com.study.javacourse.week3.handler;

import com.study.javacourse.week3.bean.UrlBean;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public enum HandlerFactory {
    /**
     *
     */
    INSTANCE;

    public ChannelInboundHandlerAdapter create(HandlerEnum handlerEnum, UrlBean urlBean) {
        switch (handlerEnum) {
            case NETTY:
                return null;
            case HTTPCLIENT:
                return new HttpClientHandler(urlBean);
            default:
                return new DefaultHandler();
        }
    }
}

