package com.study.util;

import com.study.constant.RpcConstant;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-02
 */
public class RpcNodeUtil {
    private RpcNodeUtil() {
    }

    /**
     * 构建服务提供者节点
     * @return
     */
    public static String buildProviderNode() {
        return RpcConstant.NODE_RPC_BASE + RpcConstant.NODE_RPC_PROVIDER;
    }

    /**
     * 构建服务消费者节点
     * @return
     */
    public static String buildConsumerNode() {
        return RpcConstant.NODE_RPC_BASE + RpcConstant.RPC_BASE_CONSUMER;
    }
}
