package com.study.week5.single;

/**
 * 双重校验锁
 *
 * @author me-ht
 * @date 2021-06-07
 */
public class Single5 {
    private volatile static Single5 singleton;

    private Single5() {
    }

    public static Single5 getSingleton() {
        if (singleton == null) {
            synchronized (Single5.class) {
                if (singleton == null) {
                    singleton = new Single5();
                }
            }
        }
        return singleton;
    }
}
