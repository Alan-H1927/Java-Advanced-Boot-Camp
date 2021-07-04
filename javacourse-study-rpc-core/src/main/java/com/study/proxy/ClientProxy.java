package com.study.proxy;

import com.google.common.base.Joiner;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 代理
 *
 * @author me-ht
 * @date 2021-07-03
 */
public class ClientProxy {

    private static final ConcurrentHashMap<String, Object> PROXY_CACHE = new ConcurrentHashMap<>();

    public static <T> T create(Class<?> cls, String group, String version, Integer weight) {
        String key = Joiner.on(":").join(cls.getName(), group, version, weight);
        if (isExist(key)) {
            return getFromCache(key);
        }
        Object result;
        try {
            result = createByProxy(cls, group, version, weight);
            PROXY_CACHE.put(key, result);
        } catch (Exception e) {
            throw new RuntimeException("invoke proxy fail", e);
        }
        return (T) result;
    }

    private static boolean isExist(String key) {
        return PROXY_CACHE.containsKey(key);
    }

    private static <T> T getFromCache(String key) {
        return (T) PROXY_CACHE.get(key);
    }

    private static <T> T createByProxy(Class<?> cls, String group, String version, Integer weight) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(cls)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(cls, group, version, weight)))
                .make()
                .load(ClientProxy.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }
}
