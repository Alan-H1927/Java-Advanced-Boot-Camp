package com.study.service;

import com.study.entity.ShardingSphereUser;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-27
 */
public interface XAShardingSphereService {
    /**
     * XA分布式事务
     */
    void insertXA(ShardingSphereUser shardingSphereUser);

    /**
     * XA分布式事务
     */
    void updateXA(ShardingSphereUser shardingSphereUser);
}
