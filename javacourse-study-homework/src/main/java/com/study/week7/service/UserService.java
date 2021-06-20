package com.study.week7.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.week7.entity.User;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-20
 */

public interface UserService extends IService<User> {

    User getUserByIdInMaster(String id);

    User getUserByIdInSlave(String id);
}
