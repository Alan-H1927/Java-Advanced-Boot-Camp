package com.study.week4.homework;

import com.study.thread.CommonThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author me-ht
 * @date 2021-05-30
 */
public class Homework13 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ExecutorService executorService = CommonThreadPool.get();
        AtomicInteger sumResult = new AtomicInteger();
        //子线程启动
        CompletableFuture.supplyAsync(new MyThread(), executorService).whenComplete((result, e) -> {
            //执行线程执行完以后的操作。
            sumResult.set(result);
        }).exceptionally((e) -> {
            throw new RuntimeException();
        });

        int result = 0;
        int i = 0;
        while (result == 0) {
            System.out.println("循环" + i++);
            result = sumResult.get();
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

    static class MyThread implements Supplier<Integer> {
        @Override
        public Integer get() {
            return sum();
        }
    }
}
