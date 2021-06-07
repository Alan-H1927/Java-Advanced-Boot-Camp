package com.study.week4.homework;


import com.study.thread.CommonThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-30
 */
public class Homework14 {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        //这是得到的返回值
        ExecutorService executorService = CommonThreadPool.get();
        //子线程启动
        Future<Integer> future = executorService.submit(new MyThread());

        int result = 0;
        int i = 0;
        while (!future.isDone()) {
            System.out.println("循环" + i++);
            result = future.get();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
        executorService.shutdown();
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    static class MyThread implements Callable<Integer> {

        @Override
        public Integer call() {
            return sum();
        }
    }
}
