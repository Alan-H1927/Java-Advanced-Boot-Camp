package com.study.javacourse.week3;

import com.study.javacourse.week3.bean.UrlBean;
import com.study.javacourse.week3.gateway.NettyGateway;
import com.study.javacourse.week3.handler.HandlerEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public class TestNettyGateway {

    public static void main(String[] args) {
        List<UrlBean> urls = new ArrayList<>();
        UrlBean urlBean1 = new UrlBean();
        urlBean1.setIp("127.0.0.1");
        urlBean1.setPort(8081);
        UrlBean urlBean2 = new UrlBean();
        urlBean2.setIp("127.0.0.1");
        urlBean2.setPort(8082);
        urls.add(urlBean1);
        urls.add(urlBean2);

        NettyGateway nettyGateway = new NettyGateway(HandlerEnum.HTTPCLIENT, urls);
        nettyGateway.run();
    }
}
