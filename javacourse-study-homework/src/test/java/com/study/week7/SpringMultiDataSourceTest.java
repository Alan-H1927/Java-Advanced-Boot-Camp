package com.study.week7;

import com.study.AbstractTest;
import com.study.week7.entity.SpringUser;
import com.study.week7.service.SpringUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * //使用spring进行多数据源配置
 *
 * @author me-ht
 * @date 2021-06-20
 */
public class SpringMultiDataSourceTest extends AbstractTest {

    @Autowired
    private SpringUserService springUserService;

    @Test
    public void testDataSource() {
        SpringUser master = springUserService.getUserByIdInMaster("3");
        System.out.println("使用数据源master：" + master.toString());
        System.out.println("===");
        SpringUser slave1 = springUserService.getUserByIdInSlave1("3");
        System.out.println("使用数据源slave1：" + slave1.toString());
        System.out.println("===");
        SpringUser slave2 = springUserService.getUserByIdInSlave2("6");
        System.out.println("使用数据源slave2：" + slave2.toString());
    }
}
