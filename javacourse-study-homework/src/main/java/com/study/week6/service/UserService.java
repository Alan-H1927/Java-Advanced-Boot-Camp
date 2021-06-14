package com.study.week6.service;

import com.study.week6.entity.User;

import java.util.List;

/**
 * 用户service
 *
 * @author me-ht
 * @date 2021-06-14
 */
public interface UserService {
    /**
     * 新增单个对象
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * 删除单个对象
     *
     * @param user
     */
    void deleteUser(User user);

    /**
     * 修改单个对象
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询单个对象
     *
     * @return
     */
    User getUser();

    /**
     * 查询对象集合
     *
     * @return
     */
    List<User> listUsers();
}
