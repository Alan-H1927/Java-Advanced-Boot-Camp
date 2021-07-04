package com.study.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存服务提供者
 *
 * @author me-ht
 * @date 2021-07-03
 */
public enum ProviderCache {
    /**
     *
     */
    CACHE;

    private static final Map<String, Object> PROVIDER_CACHE = new HashMap<>();

    public Object get(String key) {
        return PROVIDER_CACHE.get(key);
    }

    public void put(String key, Object value) {
        PROVIDER_CACHE.put(key, value);
    }

    public int size() {
        return PROVIDER_CACHE.size();
    }

    public Map<String, Object> getAll() {
        return PROVIDER_CACHE;
    }
}
