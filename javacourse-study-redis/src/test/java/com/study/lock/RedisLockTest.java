package com.study.lock;

import com.study.client.JedisUtil;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-18
 */
public class RedisLockTest {

    private static int amount = 1000;

    @Test
    public void test() throws InterruptedException {
        System.out.println("redis get =" + JedisUtil.get(RedisLock.LOCK_KEY));
        JedisUtil.del("redis_lock");
        System.out.println("redis get =" + JedisUtil.get(RedisLock.LOCK_KEY));

        int clientCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);
        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        long start = System.currentTimeMillis();
        for (int i = 0; i < clientCount; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String id = UUID.randomUUID().toString().replace("-", "");
                    try {
                        if(RedisLock.LOCK.lock(id)){
                            amount -= 1;
                            System.out.printf("库存减一 amount: %d\n", amount);
                        }
                        countDownLatch.countDown();
                    } finally {
                        RedisLock.LOCK.unlock(id);
                    }
                }
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("执行线程数:" + clientCount + ",总耗时:" + (end - start));
    }
}
