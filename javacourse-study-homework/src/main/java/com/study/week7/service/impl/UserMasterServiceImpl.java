package com.study.week7.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.week7.multidatasourceconfig.MultiDataSource;
import com.study.week7.multidatasourceconfig.DataSourceNames;
import com.study.week7.entity.User;
import com.study.week7.mapper.UserMapper;
import com.study.week7.service.UserService;
import org.springframework.stereotype.Service;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-20
 */
@Service
public class UserMasterServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUserByIdInMaster(String id) {
        return this.baseMapper.selectById(id);
    }

    @MultiDataSource(name = DataSourceNames.SLAVE)
    @Override
    public User getUserByIdInSlave(String id) {
        return this.baseMapper.selectById(id);
    }
}
