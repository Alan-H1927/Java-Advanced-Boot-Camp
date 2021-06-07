package com.study.week5.single;

/**
 * 静态内部类
 *
 * @author me-ht
 * @date 2021-06-07
 */
public class Single6 {
    private static class SingletonHolder {
        private static final Single6 INSTANCE = new Single6();
    }

    private Single6() {
    }

    public static Single6 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
