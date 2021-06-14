package com.study.week5.jdkproxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-30
 */
public class ProxyTest {

    @Test
    public void test() {
        JdkAopImpl jdkAopImpl = new JdkAopImpl();
        System.out.println("不使用动态代理：");
        jdkAopImpl.aop(ProxyTest.class.getSimpleName());
        System.out.println("---分割线---");

        JdkProxy jdkProxy = new JdkProxy(jdkAopImpl);
        ClassLoader classLoader = jdkAopImpl.getClass().getClassLoader();
        Class<?>[] interfaces = jdkAopImpl.getClass().getInterfaces();
        JdkAop jdkAop = (JdkAop) Proxy.newProxyInstance(classLoader, interfaces, jdkProxy);
        System.out.println("使用动态代理：");
        jdkAop.aop(ProxyTest.class.getSimpleName());
    }

}
