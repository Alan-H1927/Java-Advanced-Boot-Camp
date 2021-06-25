package com.study.shardingsphere;

import com.study.AbstractTest;
import com.study.entity.ShardingSphereUser;
import com.study.id.UserIdUtil;
import com.study.service.ShardingSphereUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * //ShardingSphere测试
 *
 * @author me-ht
 * @date 2021-06-25
 */
public class ShardingSphereTest extends AbstractTest {

    @Autowired
    private ShardingSphereUserService shardingSphereUserService;

    /**
     * 读写分离测试
     */
    @Test
    public void readAndWrite() {
        List<ShardingSphereUser> list = shardingSphereUserService.list();
        logger.info("读取从库数据size is [{}]", list.size());
        ShardingSphereUser shardingSphereUser = new ShardingSphereUser();
        shardingSphereUser.setUserId(UserIdUtil.generateId());
        shardingSphereUser.setUserUsername("u_username");
        shardingSphereUser.setUserPassword("u_password");
        shardingSphereUser.setUserEmail("u_username@126.com");
        shardingSphereUser.setUserPhoneNumber("1234567");
        shardingSphereUser.setCreatePerson("sys");
        shardingSphereUser.setUpdatePerson("sys");
        System.out.println("开始saveOne");
        shardingSphereUserService.saveOne(shardingSphereUser);
        List<ShardingSphereUser> masterList = shardingSphereUserService.listOnlyByMaster();
        logger.info("saveOne完成,读取主库数据size is [{}]", masterList.size());
        List<ShardingSphereUser> listAfter = shardingSphereUserService.list();
        logger.info("读取从库数据size is [{}]", listAfter.size());
    }
}
