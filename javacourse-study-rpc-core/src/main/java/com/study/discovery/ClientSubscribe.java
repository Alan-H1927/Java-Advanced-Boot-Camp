package com.study.discovery;

import com.study.api.ServiceInfo;
import com.study.util.CuratorUtil;
import com.study.util.RpcNodeUtil;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 服务订阅
 *
 * @author me-ht
 * @date 2021-07-02
 */
public class ClientSubscribe {

    private static final Logger logger = LoggerFactory.getLogger(ClientSubscribe.class);

    private static final Map<String, List<ServiceInfo>> SUBSCRIBE_CACHE = new HashMap<>();

    private ClientSubscribe() {
    }

    private static final ClientSubscribe INSTANCE = new ClientSubscribe();

    public static ClientSubscribe getInstance() {
        return INSTANCE;
    }

    public void subscribe() {
        ServiceDiscovery<ServiceInfo> serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceInfo.class)
                .client(CuratorUtil.getClient())
                .basePath(RpcNodeUtil.buildProviderNode())
                .build();

        try {
            serviceDiscovery.start();

            cacheProvider(serviceDiscovery);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void cacheProvider(ServiceDiscovery<ServiceInfo> serviceDiscovery) throws Exception {
        Collection<String> serviceNames = serviceDiscovery.queryForNames();
        for (String serviceName : serviceNames) {
            Collection<ServiceInstance<ServiceInfo>> instances = serviceDiscovery.queryForInstances(serviceName);
            for (ServiceInstance<ServiceInfo> instance : instances) {
                String url = "http://" + instance.getAddress() + ":" + instance.getPort();
                ServiceInfo serviceInfo = instance.getPayload();
                serviceInfo.setId(instance.getId());
                serviceInfo.setUrl(url);

                List<ServiceInfo> providerList = SUBSCRIBE_CACHE.getOrDefault(instance.getName(), new ArrayList<>());
                providerList.add(serviceInfo);
                SUBSCRIBE_CACHE.put(instance.getName(), providerList);
                logger.info("订阅服务[{}]", instance.getName());
            }
        }
        logger.info("服务订阅完成");
    }

    public Map<String, List<ServiceInfo>> getProvidersCache() {
        return SUBSCRIBE_CACHE;
    }

    public String getUrl(String key) {
        List<ServiceInfo> serviceInfos = SUBSCRIBE_CACHE.get(key);
        return serviceInfos.get(0).getUrl();
    }

}
