package com.study.week3.handler;


import com.study.week3.bean.UrlBean;
import com.study.week3.filter.HeaderHttpRequestFilter;
import com.study.week3.filter.HeaderHttpResponseFilter;
import com.study.week3.filter.HttpRequestFilter;
import com.study.week3.filter.HttpResponseFilter;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public abstract class AbstractHandler extends ChannelInboundHandlerAdapter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected HttpRequestFilter requestFilter = new HeaderHttpRequestFilter();

    protected HttpResponseFilter responseFilter = new HeaderHttpResponseFilter();

    protected String buildUrl(UrlBean urlBean) {
        return "http://" + urlBean.getIp() + ":" + urlBean.getPort();
    }
}
