package com.study.week5.hikari;

import org.junit.jupiter.api.Test;


/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-05
 */
public class HikariTest {

    @Test
    public void test() {
        String sqlCount = "SELECT count(1) FROM user";
        //查询总数
        int i = HikariUtil.executeCount(sqlCount, null);
        System.out.println("直接查询以后的数--->" + i);

        //插入
        String nameInsert = "name" + System.currentTimeMillis();
        String sqlInsert = "INSERT INTO user  (NAME) VALUES ('" + nameInsert + "');";

        HikariUtil.executeUpdate(sqlInsert, null);
        System.out.println("插入以后的数--->" + HikariUtil.executeCount(sqlCount, null));

//        //修改
//        String nameUpdate = "name" + System.currentTimeMillis();
//        String sqlUpdate = "UPDATE user SET NAME = '" + nameUpdate + "' WHERE id=1;";
//        HikariUtil.executeUpdate(sqlUpdate, null);
//        System.out.println("修改以后的数--->" + HikariUtil.executeCount(sqlCount, null));
//
//        //删除
//        String sqlDelete = "DELETE FROM user WHERE id IN (SELECT a.id FROM (SELECT * FROM user ORDER BY id DESC LIMIT 1) AS a );";
//        HikariUtil.executeUpdate(sqlDelete, null);
//        System.out.println("删除以后的数--->" + HikariUtil.executeCount(sqlCount, null));
    }
}
