package com.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.entity.ShardingSphereUser;

import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-23
 */
public interface ShardingSphereUserMapper extends BaseMapper<ShardingSphereUser> {
    /**
     * 插入一条记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int insertOne(ShardingSphereUser record);

    /**
     * 从主库获取所有用户
     */
    List<ShardingSphereUser> selectAllOnlyByMaster();

    /**
     * 获取所有用户
     */
    List<ShardingSphereUser> selectAll();

    /**
     * 删除全表
     */
    void truncate();

    /**
     * 删除全表
     */
    void deleteAll();

    /**
     * 修改
     */
    int updateOne(ShardingSphereUser user);
}
