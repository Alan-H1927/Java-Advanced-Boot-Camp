package com.study.week6.h2;


import com.study.AbstractTest;
import com.study.week6.h2.entity.User;
import com.study.week6.h2.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;


/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-14
 */

public class H2UserTest extends AbstractTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}
