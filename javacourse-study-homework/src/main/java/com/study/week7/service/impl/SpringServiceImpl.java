package com.study.week7.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.week7.spring.MultiDataSource;
import com.study.week7.spring.DataSourceNames;
import com.study.week7.entity.SpringUser;
import com.study.week7.mapper.SpringUserMapper;
import com.study.week7.service.SpringUserService;
import org.springframework.stereotype.Service;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-20
 */
@Service
public class SpringServiceImpl extends ServiceImpl<SpringUserMapper, SpringUser> implements SpringUserService {

    @Override
    public SpringUser getUserByIdInMaster(String id) {
        return this.baseMapper.selectById(id);
    }

    @MultiDataSource(name = DataSourceNames.SLAVE1)
    @Override
    public SpringUser getUserByIdInSlave1(String id) {
        return this.baseMapper.selectById(id);
    }

    @MultiDataSource(name = DataSourceNames.SLAVE2)
    @Override
    public SpringUser getUserByIdInSlave2(String id) {
        return this.baseMapper.selectById(id);
    }
}
