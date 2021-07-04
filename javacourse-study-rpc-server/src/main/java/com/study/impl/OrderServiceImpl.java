package com.study.impl;


import com.study.annotation.ProviderService;
import com.study.facade.OrderService;


/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-02
 */
@ProviderService(service = "com.study.facade.OrderService", group = "order", version = "1.0", weight = 3)
public class OrderServiceImpl implements OrderService {

    @Override
    public String getOrder() {
        return "this is getOrder";
    }
}
