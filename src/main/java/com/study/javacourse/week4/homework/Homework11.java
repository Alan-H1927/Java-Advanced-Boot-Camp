package com.study.javacourse.week4.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author me-ht
 * @date 2021-05-30
 */
public class Homework11 {
    private static final String NAME = Homework11.class.getName();


    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        //这是得到的返回值
        //参数为线程数
        CyclicBarrier barrier = new CyclicBarrier(2);
        Map<String, Integer> map = new HashMap<>();
        new MyThread(map, barrier).start();

        try {
            //也阻塞,并且当阻塞数量达到指定数目时同时释放
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        int result = map.get(NAME);

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
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

    static class MyThread extends Thread {

        private Map<String, Integer> map;

        private CyclicBarrier barrier;

        public MyThread(Map<String, Integer> map, CyclicBarrier barrier) {
            this.map = map;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            map.put(NAME, sum());
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
