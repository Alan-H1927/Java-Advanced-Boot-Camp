package com.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.entity.ShardingSphereUser;
import com.study.mapper.ShardingSphereUserMapper;
import com.study.service.ShardingSphereUserService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-23
 */
@Service
public class ShardingSphereUserServiceImpl extends ServiceImpl<ShardingSphereUserMapper, ShardingSphereUser> implements ShardingSphereUserService {

    @Override
    public List<ShardingSphereUser> list() {
        return this.baseMapper.selectAll();
    }

    @Override
    public List<ShardingSphereUser> listOnlyByMaster() {
        // 强制路由主库
        HintManager.getInstance().setMasterRouteOnly();
        List<ShardingSphereUser> shardingSphereUsers = this.baseMapper.selectAllOnlyByMaster();
        HintManager.clear();
        return shardingSphereUsers;
    }

    @Override
    public String saveOne(ShardingSphereUser user) {
        user.setUserStatus("1");
        this.baseMapper.insertOne(user);
        return "保存成功";
    }

    @Override
    public void deleteAll() {
        this.baseMapper.deleteAll();
    }

    @Override
    public void truncate() {
        this.baseMapper.truncate();
    }
}
