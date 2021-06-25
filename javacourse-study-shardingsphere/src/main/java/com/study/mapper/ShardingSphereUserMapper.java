package com.study.mapper;

import com.study.entity.ShardingSphereUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-23
 */
@Mapper
public interface ShardingSphereUserMapper {
    /**
     * 插入一条记录
     *
     * @param record 实体对象
     * @return 更新条目数
     */
    int insert(ShardingSphereUser record);

    /**
     * 从主库获取所有用户
     */
    List<ShardingSphereUser> selectAllOnlyByMaster();

    /**
     * 获取所有用户
     */
    List<ShardingSphereUser> selectAll();
}
