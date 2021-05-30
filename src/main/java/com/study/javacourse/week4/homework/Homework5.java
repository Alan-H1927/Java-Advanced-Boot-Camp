package com.study.javacourse.week4.homework;

import java.util.HashMap;
import java.util.Map;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-30
 */
public class Homework5 {

    private static final String NAME = Homework5.class.getName();

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        //这是得到的返回值
        Map<String, Integer> map = new HashMap<>();
        new MyThread(map).start();

        int i = 0;
        do {
            System.out.println("循环" + i++);
        } while (map.get(NAME) == null);

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
        }
    }
}
