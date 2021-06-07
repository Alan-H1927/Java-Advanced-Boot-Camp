package com.study.week5.single;

/**
 * 懒汉式，线程安全
 *
 * @author me-ht
 * @date 2021-06-07
 */
public class Single4 {
    private static Single4 instance;

    private Single4() {
    }

    public static synchronized Single4 getInstance() {
        if (instance == null) {
            instance = new Single4();
        }
        return instance;
    }
}
