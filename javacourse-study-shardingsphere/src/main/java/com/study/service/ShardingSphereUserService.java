package com.study.service;

import com.study.entity.ShardingSphereUser;

import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-23
 */
public interface ShardingSphereUserService {
    /**
     * 获取所有用户信息
     */
    List<ShardingSphereUser> list();
    /**
     * 获取所有用户信息，强制走主库
     */
    List<ShardingSphereUser> listOnlyByMaster();
    /**
     * 单个保存用户信息
     *
     * @param user
     */
    String saveOne(ShardingSphereUser user);

    /**
     * 删除全表
     *
     *
     */
    void truncate();
    /**
     * 删除全表
     *
     *
     */
    void deleteAll();
}
