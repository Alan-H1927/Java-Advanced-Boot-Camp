package com.study.rpc;

import com.study.server.NettyServer;
import org.junit.jupiter.api.Test;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-03
 */
public class RpcServerTest {

    @Test
    public void serverTest(){
        NettyServer nettyServer = new NettyServer();
        nettyServer.run();
    }
}
