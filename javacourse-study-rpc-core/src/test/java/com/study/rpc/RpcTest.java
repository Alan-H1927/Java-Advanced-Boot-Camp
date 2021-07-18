package com.study.rpc;

import com.study.client.NettyClient;
import com.study.server.NettyServer;
import com.study.util.CuratorUtil;
import com.study.util.RpcNodeUtil;
import org.apache.curator.framework.CuratorFramework;
import org.junit.jupiter.api.Test;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class RpcTest {

    @Test
    public void rpcServerTest() {
        NettyServer nettyServer = new NettyServer();
        nettyServer.run();
    }

    @Test
    public void rpcClientTest() throws Exception {
        CuratorFramework client = CuratorUtil.getClient();
        CuratorUtil.deleteNode(RpcNodeUtil.buildProviderNode());
        CuratorUtil.createPersistentNode(RpcNodeUtil.buildProviderNode(),"test");
        System.out.println("--->begin");
        System.out.println(CuratorUtil.getNodeData(RpcNodeUtil.buildProviderNode()));
        System.out.println("--->");
        Thread.sleep(4000);
        System.out.println(CuratorUtil.getNodeData(RpcNodeUtil.buildProviderNode()));
        System.out.println("--->end");

    }
}
