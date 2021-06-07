package com.study.week5.single;

/**
 * 懒汉式，线程不安全
 *
 * @author me-ht
 * @date 2021-06-07
 */
public class Single3 {
    private static Single3 instance;

    private Single3() {
    }

    public static Single3 getInstance() {
        if (instance == null) {
            instance = new Single3();
        }
        return instance;
    }
}
