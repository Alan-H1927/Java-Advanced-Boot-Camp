package com.study.week5.single;

/**
 * 饿汉式
 *
 * @author me-ht
 * @date 2021-06-07
 */
public class Single2 {

    private static final Single2 INSTANCE = new Single2();

    private Single2() {
    }

    public static Single2 getInstance() {
        return INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}
