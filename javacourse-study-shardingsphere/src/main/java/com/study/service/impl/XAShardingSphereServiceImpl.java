package com.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.entity.ShardingSphereUser;
import com.study.mapper.ShardingSphereUserMapper;
import com.study.service.XAShardingSphereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-27
 */
@Service
public class XAShardingSphereServiceImpl extends ServiceImpl<ShardingSphereUserMapper, ShardingSphereUser> implements XAShardingSphereService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertXA(ShardingSphereUser shardingSphereUser) {
        this.baseMapper.insertOne(shardingSphereUser);
    }

    @Override
    public void updateXA(ShardingSphereUser shardingSphereUser) {
        this.baseMapper.updateOne(shardingSphereUser);
    }

}
