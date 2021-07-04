package com.study.proxy;

import com.google.common.base.Joiner;
import com.study.api.RpcRequest;
import com.study.api.RpcResponse;
import com.study.api.ServiceInfo;
import com.study.client.NettyClient;
import com.study.discovery.ClientSubscribe;
import com.study.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-03
 */
public class RpcInvocationHandler implements InvocationHandler, MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RpcInvocationHandler.class);

    private Class<?> serviceClass;
    private String group;
    private String version;
    private Integer weight;

    public RpcInvocationHandler(Class<?> serviceClass, String group, String version, Integer weight) {
        this.serviceClass = serviceClass;
        this.group = group;
        this.version = version;
        this.weight = weight;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        return doInvoke(serviceClass, method, args);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
        return doInvoke(serviceClass, method, objects);
    }

    private Object doInvoke(Class<?> service, Method method, Object[] params) {
        logger.info("client begin invoke");
        //订阅
        ClientSubscribe.getInstance().subscribe();
        Map<String, List<ServiceInfo>> providersCache = ClientSubscribe.getInstance().getProvidersCache();
        String url = null;
        for (Map.Entry<String, List<ServiceInfo>> m : providersCache.entrySet()) {
            if (m.getKey().equals(Joiner.on(":").join(service.getName(), group, version, weight))) {
                List<ServiceInfo> value = m.getValue();
                url = value.get(0).getUrl();
            }
        }
        if (url == null) {
            throw new RuntimeException("无法找到该地址");
        }
        //获取url
        //构造请求参数
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setUrl(url);
        rpcRequest.setServiceClass(service.getName());
        rpcRequest.setMethod(method.getName());
        rpcRequest.setArgv(params);
        rpcRequest.setGroup(group);
        rpcRequest.setVersion(version);
        rpcRequest.setWeight(weight);

        //根据url请求
        NettyClient nettyClient = new NettyClient();
        RpcResponse rpcResponse = nettyClient.invoke(rpcRequest);
        return JsonUtil.convertJsonToObject(rpcResponse.getResult().toString());
    }
}
