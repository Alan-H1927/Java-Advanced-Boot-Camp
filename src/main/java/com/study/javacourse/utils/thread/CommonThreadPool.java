package com.study.javacourse.utils.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-30
 */
public class CommonThreadPool {

    private CommonThreadPool() {
    }

    private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(2, 8, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5), new CommonThreadFactory("Common"));


    public static ThreadPoolExecutor get() {
        return POOL;
    }

    public static void execute(Runnable runnable) {
        POOL.execute(runnable);
    }
}
