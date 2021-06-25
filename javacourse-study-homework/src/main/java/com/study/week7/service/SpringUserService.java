package com.study.week7.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.week7.entity.SpringUser;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-20
 */

public interface SpringUserService extends IService<SpringUser> {

    SpringUser getUserByIdInMaster(String id);

    SpringUser getUserByIdInSlave1(String id);

    SpringUser getUserByIdInSlave2(String id);
}
