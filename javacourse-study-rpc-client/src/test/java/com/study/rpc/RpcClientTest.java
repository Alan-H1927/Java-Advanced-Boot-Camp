package com.study.rpc;

import com.study.facade.GoodService;
import com.study.facade.OrderService;
import com.study.proxy.ClientProxy;
import org.junit.jupiter.api.Test;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-03
 */
public class RpcClientTest {

    @Test
    public void clientTest() {
        GoodService goodService = ClientProxy.create(GoodService.class, "good", "1.0", 2);
        System.out.println("RpcClientTest GoodService--->" + goodService.count());

        OrderService orderService = ClientProxy.create(OrderService.class, "order", "1.0", 3);
        System.out.println("RpcClientTest OrderService--->" + orderService.getOrder());

        goodService = ClientProxy.create(GoodService.class, "good", "2.0", 2);
        System.out.println("2 RpcClientTest GoodService--->" + goodService.count());

        orderService = ClientProxy.create(OrderService.class, "order", "1.0", 3);
        System.out.println("2 RpcClientTest OrderService--->" + orderService.getOrder());
    }
}
