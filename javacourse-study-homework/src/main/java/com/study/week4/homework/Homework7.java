package com.study.week4.homework;

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
public class Homework7 {

    private static int sum = 0;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        long start = System.currentTimeMillis();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, () -> {
            int result = sum;

            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);

            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        });

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        //这是得到的返回值
        new MyThread(cyclicBarrier).start();
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

        private CyclicBarrier cyclicBarrier;

        public MyThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            int temp = sum();
            System.out.println("线程计算的结果为：" + temp);
            sum = temp;
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
