package com.study.service.impl;

import com.study.entity.ShardingSphereUser;
import com.study.mapper.ShardingSphereUserMapper;
import com.study.service.ShardingSphereUserService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-23
 */
@Service
public class ShardingSphereUserServiceImpl implements ShardingSphereUserService {

    @Autowired
    private ShardingSphereUserMapper shardingSphereUserMapper;

    @Override
    public List<ShardingSphereUser> list() {
        return shardingSphereUserMapper.selectAll();
    }

    @Override
    public List<ShardingSphereUser> listOnlyByMaster() {
        // 强制路由主库
        HintManager.getInstance().setMasterRouteOnly();
        List<ShardingSphereUser> shardingSphereUsers = shardingSphereUserMapper.selectAllOnlyByMaster();
        HintManager.clear();
        return shardingSphereUsers;
    }

    @Override
    public String saveOne(ShardingSphereUser user) {
        user.setUserStatus("1");
        shardingSphereUserMapper.insert(user);
        return "保存成功";
    }

    @Override
    public void deleteAll() {
        shardingSphereUserMapper.deleteAll();
    }

    @Override
    public void truncate() {
        shardingSphereUserMapper.truncate();
    }
}
