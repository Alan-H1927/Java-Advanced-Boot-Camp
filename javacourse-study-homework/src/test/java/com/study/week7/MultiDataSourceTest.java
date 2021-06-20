package com.study.week7;

import com.study.AbstractTest;
import com.study.week7.entity.User;
import com.study.week7.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-20
 */
public class MultiDataSourceTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Test
    public void testDataSource() {
        User user1 = userService.getUserByIdInMaster("1");
        System.out.println("使用数据源1：" + user1.toString());
        System.out.println("===");
        User user2 = userService.getUserByIdInSlave("2");
        System.out.println("使用数据源2：" + user2.toString());
    }
}
