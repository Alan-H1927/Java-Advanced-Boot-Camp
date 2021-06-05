package com.study.week5.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-30
 */
public class JdkProxy implements InvocationHandler {

    private JdkAop jdkAop;

    public JdkProxy(JdkAop jdkAop) {
        this.jdkAop = jdkAop;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入JDK动态代理");
        List<Object> newArgs = new ArrayList<>();
        for (Object s : args) {
            newArgs.add("new--->" + s);
        }
        return method.invoke(jdkAop, newArgs.toArray());
    }


}
