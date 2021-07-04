package com.study.thread;

import com.study.util.CuratorUtil;
import com.study.util.RpcNodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-02
 */
public class ZkRegisterThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ZkRegisterThread.class);

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                CuratorUtil.createPersistentNode(RpcNodeUtil.buildProviderNode() + "/com.study.provider_" + i, "com.study.provider_" + i);
            }
        } catch (Exception e) {
            logger.error("ZkRegisterThread register error", e);
        }
    }


}
