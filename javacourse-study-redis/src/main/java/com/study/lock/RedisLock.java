package com.study.lock;

import com.study.client.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-18
 */
public enum RedisLock {
    /**
     *
     */
    LOCK;
    /**
     * 锁键
     */
    public static final String LOCK_KEY = "redis_lock";
    /**
     * 锁过期时间
     */
    protected long internalLockLeaseTime = 30000;
    /**
     * 获取锁的超时时间
     */
    private long timeout = 30000;

    /**
     * SET命令的参数
     */
    SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);

    private static final String LOCK_SUCCESS = "OK";

    /**
     * 加锁
     *
     * @param id
     * @return
     */
    public boolean lock(String id) {
        try (Jedis jedis = JedisUtil.getJedis()) {
            long start = System.currentTimeMillis();
            for (; ; ) {
                //SET命令返回OK ，则证明获取锁成功
                String result = jedis.set(LOCK_KEY, id, params);
                if (LOCK_SUCCESS.equals(result)) {
                    System.out.println(id + "获取成功");
                    return true;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= timeout) {
                    System.out.println(id + "过期");
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解锁
     *
     * @param id
     * @return
     */
    public boolean unlock(String id) {
        try (Jedis jedis = JedisUtil.getJedis()) {
            String script =
                    "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                            "   return redis.call('del',KEYS[1]) " +
                            "else" +
                            "   return 0 " +
                            "end";
            Object result = jedis.eval(script, Collections.singletonList(LOCK_KEY),
                    Collections.singletonList(id));
            return "1".equals(result.toString());
        }
    }

}
