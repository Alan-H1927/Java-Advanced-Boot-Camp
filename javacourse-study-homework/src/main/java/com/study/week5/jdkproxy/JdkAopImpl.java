package com.study.week5.jdkproxy;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-30
 */
public class JdkAopImpl implements JdkAop {

    @Override
    public void aop(String action) {
        System.out.println(action);
    }
}
