package com.study.util;

import com.study.constant.CuratorConstant;
import com.study.constant.RpcConstant;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class CuratorUtil {

    private static final Logger logger = LoggerFactory.getLogger(CuratorUtil.class);

    private CuratorUtil() {
    }

    private static final CuratorFramework CLIENT = create();

    /**
     * 创建客户端
     *
     * @return
     */
    private static CuratorFramework create() {
        //1 重试策略：初试时间为1s 重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        //2 通过工厂创建连接
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(RpcConstant.ZK_URL)
                .connectionTimeoutMs(CuratorConstant.CONNECTION_TIMEOUT)
                .sessionTimeoutMs(CuratorConstant.SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
                .namespace("zk")
                .build();
        //3 开启连接
        client.start();
        System.out.println("ZK连接状态为：" + client.getState());
        return client;
    }

    /**
     * 获取客户端
     *
     * @return
     */
    public static CuratorFramework getClient() {
        return CLIENT;
    }

    /**
     * 创建持久节点
     * Curator默认创建的是持久节点，内容为空
     *
     * @param node
     * @throws Exception
     */
    public static void createPersistentNode(String node, String data) throws Exception {
        CLIENT.create().creatingParentContainersIfNeeded().forPath(node, data.getBytes());
    }

    /**
     * 创建持久顺序节点
     *
     * @param node
     * @throws Exception
     */
    public static void createPersistentSequentialNode(String node, String data) throws Exception {
        CLIENT.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(node, data.getBytes());
    }

    /**
     * 创建临时节点
     *
     * @param node
     * @throws Exception
     */
    public static void createEphemeralNode(String node, String data) throws Exception {
        CLIENT.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(node, data.getBytes());
    }

    /**
     * 创建临时顺序节点
     *
     * @param node
     * @throws Exception
     */
    public static void createEphemeralSequentialNode(String node, String data) throws Exception {
        CLIENT.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(node, data.getBytes());
    }

    /**
     * 判断节点是否存在
     *
     * @param node
     * @return
     * @throws Exception
     */
    public static Stat checkNodeExist(String node) throws Exception {
        return CLIENT.checkExists().forPath(node);
    }

    /**
     * 获取节点数据
     *
     * @param node
     * @return
     * @throws Exception
     */
    public static String getNodeData(String node) throws Exception {
        return new String(CLIENT.getData().forPath(node));
    }

    /**
     * 获取某个节点的所有子节点
     *
     * @param node
     * @return
     * @throws Exception
     */
    public static List<String> getAllChildrenNode(String node) throws Exception {
        return CLIENT.getChildren().forPath(node);
    }

    /**
     * 设置节点数据
     *
     * @param node
     * @param data
     * @throws Exception
     */
    public static void setNodeData(String node, String data) throws Exception {
        CLIENT.setData().forPath(node, data.getBytes());
    }

    /**
     * 删除某节点
     *
     * @param node
     * @throws Exception
     */
    public static void deleteNode(String node) throws Exception {
        CLIENT.delete().forPath(node);
    }

    /**
     * 级联删除某节点
     *
     * @param node
     * @throws Exception
     */
    public static void deleteNodeAndChildrenNode(String node) throws Exception {
        CLIENT.delete().guaranteed().deletingChildrenIfNeeded().forPath(node);
    }
}
