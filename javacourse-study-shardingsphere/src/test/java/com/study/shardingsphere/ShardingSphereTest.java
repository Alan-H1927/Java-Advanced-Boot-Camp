package com.study.shardingsphere;

import com.study.AbstractTest;
import com.study.entity.ShardingSphereUser;
import com.study.id.UserIdUtil;
import com.study.service.ShardingSphereUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
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
    public void readAndWriteTest() {
        List<ShardingSphereUser> list = shardingSphereUserService.list();
        logger.info("读取从库数据size is [{}]", list.size());
        ShardingSphereUser shardingSphereUser = new ShardingSphereUser();
        shardingSphereUser.setUserId("1");
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

    /**
     * 分库分表测试
     */
    @Test
    public void shardingDatabaseAndTableTest() {
        logger.info("初始化读取数据size is [{}]", shardingSphereUserService.list().size());
        shardingSphereUserService.truncate();
        logger.info("清空表后读取数据size is [{}]", shardingSphereUserService.list().size());
        for (int i = 0; i < 16; i++) {
            ShardingSphereUser shardingSphereUser = new ShardingSphereUser();
            shardingSphereUser.setUserId(String.valueOf(i));
            shardingSphereUser.setUserUsername("u_username");
            shardingSphereUser.setUserPassword("u_password");
            shardingSphereUser.setUserEmail("u_username@126.com");
            shardingSphereUser.setUserPhoneNumber("1234567");
            shardingSphereUser.setCreatePerson("sys");
            shardingSphereUser.setUpdatePerson("sys");
            System.out.println("开始saveOne");
            shardingSphereUserService.saveOne(shardingSphereUser);
        }
        logger.info("插入完成后读取库数据size is [{}]", shardingSphereUserService.list().size());
    }

    /**
     * 读写分离+分库分表测试
     * 根据配置：
     * javacourse-0，javacourse-1参与写操作
     * javacourse-2参与读操作
     * <p>
     * readAndWriteAndShardingDatabaseAndTableTest一共有4个动作，16个库
     * 写操作日志为：c,d
     * 读操作日志为：a,b,e
     *
     * javacourse-0应出现64次（a,c,e）
     * javacourse-1应出现64次（a,c,e）
     * javacourse-2应出现48次（b,d）
     */
    @Test
    public void readAndWriteAndShardingDatabaseAndTableTest() {
        //应出现16次javacourse-0与16次javacourse-1
        logger.info("a，初始化强制走主库数据size is [{}]", shardingSphereUserService.listOnlyByMaster().size());
        //应出现16次javacourse-2
        logger.info("b，初始化读取数据size is [{}]", shardingSphereUserService.list().size());
        //应出现16次javacourse-0与16次javacourse-1
        shardingSphereUserService.truncate();
        //应出现16次javacourse-2
        logger.info("c，清空表后读取数据size is [{}]", shardingSphereUserService.list().size());
        //应出现16次javacourse-0与16次javacourse-1
        for (int i = 0; i < 16; i++) {
            ShardingSphereUser shardingSphereUser = new ShardingSphereUser();
            shardingSphereUser.setUserId(String.valueOf(i));
            shardingSphereUser.setUserUsername("u_username");
            shardingSphereUser.setUserPassword("u_password");
            shardingSphereUser.setUserEmail("u_username@126.com");
            shardingSphereUser.setUserPhoneNumber("1234567");
            shardingSphereUser.setCreatePerson("sys");
            shardingSphereUser.setUpdatePerson("sys");
            System.out.println("开始saveOne");
            logger.info("开始saveOne[{}]次", i);
            shardingSphereUserService.saveOne(shardingSphereUser);
        }
        //应出现16次javacourse-2
        logger.info("d，插入完成后读取库数据size is [{}]", shardingSphereUserService.list().size());
        //应出现16次javacourse-0与16次javacourse-1
        logger.info("e，插入完成后强制走主库数据size is [{}]", shardingSphereUserService.listOnlyByMaster().size());
    }
}
