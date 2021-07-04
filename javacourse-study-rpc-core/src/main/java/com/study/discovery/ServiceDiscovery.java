package com.study.discovery;

import com.google.common.base.Joiner;
import com.study.annotation.ProviderService;
import com.study.cache.ProviderCache;
import com.study.constant.NettyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * 服务发现
 *
 * @author me-ht
 * @date 2021-07-02
 */
public class ServiceDiscovery {

    private static final Logger logger = LoggerFactory.getLogger(ServiceDiscovery.class);

    private static final String PACKAGE_BASE = "com";

    public void register() throws IOException, ClassNotFoundException {
        ServiceRegister serviceRegister = new ServiceRegister();

        Class<?>[] classes = getClasses(PACKAGE_BASE);
        for (Class<?> c : classes) {
            ProviderService annotation = c.getAnnotation(ProviderService.class);
            if (annotation == null || c.isInterface()) {
                continue;
            }
            String group = annotation.group();
            String version = annotation.version();
            Integer weight = annotation.weight();
            List<String> tags = Arrays.asList(annotation.tags().split(","));
            String provider = Joiner.on(":").join(annotation.service(), group, version, weight);
            try {
                //服务进缓存
                ProviderCache.CACHE.put(provider, c.newInstance());
                //服务注册，注册的是信息
                serviceRegister.registerService(annotation.service(), group, version, NettyConstant.NETTY_PORT, tags, weight);
            } catch (Exception e) {
                logger.error("[{}] 注册失败", annotation.service(), e);
            }
            logger.info("加载 provider class: " + annotation.service() + ":" + group + ":" + version + " :: " + c.getName());
        }
    }

    private Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[0]);
    }

    private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
