package com.study.week4.homework;

import java.util.HashMap;
import java.util.Map;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author me-ht
 * @date 2021-05-30
 */
public class Homework8 {

    private static final String NAME = Homework8.class.getName();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Map<String, Integer> map = new HashMap<>();

        new MyThread(map).start();

        //这里也是一样
        synchronized (map) {
            //主线程等待
            map.wait();
        }

        //这是得到的返回值
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

        public MyThread(Map<String, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            map.put(NAME, sum());
            //获取对象锁
            synchronized (map) {
                //子线程唤醒
                map.notify();
            }
        }
    }

}
