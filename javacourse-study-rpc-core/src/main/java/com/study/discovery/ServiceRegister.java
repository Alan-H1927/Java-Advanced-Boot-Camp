package com.study.discovery;

import com.google.common.base.Joiner;
import com.study.api.ServiceInfo;
import com.study.util.CuratorUtil;
import com.study.util.RpcNodeUtil;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.List;

/**
 * 服务注册
 *
 * @author me-ht
 * @date 2021-07-02
 */
public class ServiceRegister {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRegister.class);

    public void registerService(String service, String group, String version, Integer port, List<String> tag, Integer weight) throws Exception {
        ServiceInfo provider = new ServiceInfo(service, service, group, version, weight);

        ServiceInstance<ServiceInfo> instance = ServiceInstance.<ServiceInfo>builder()
                .name(Joiner.on(":").join(service, group, version, weight))
                .port(port)
                .address(InetAddress.getLocalHost().getHostAddress())
                .payload(provider)
                .build();

        JsonInstanceSerializer<ServiceInfo> serializer = new JsonInstanceSerializer<>(ServiceInfo.class);

        ServiceDiscovery<ServiceInfo> discovery = ServiceDiscoveryBuilder.builder(ServiceInfo.class)
                .client(CuratorUtil.getClient())
                .basePath(RpcNodeUtil.buildProviderNode())
                .thisInstance(instance)
                .serializer(serializer)
                .build();

        discovery.start();
        logger.info("开始注册服务: [{}]:[{}]:[{}]:[{}]", service, group, version, weight);
    }
}
