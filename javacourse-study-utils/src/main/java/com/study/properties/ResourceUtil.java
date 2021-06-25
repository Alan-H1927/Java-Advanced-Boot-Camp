package com.study.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-05
 */
public class ResourceUtil {

    private static final Logger logger = LoggerFactory.getLogger(ResourceUtil.class);

    private ResourceUtil() {
    }

    private final static MyResourceBundleControl CTL = new MyResourceBundleControl();

    private static ResourceBundle getBundle(String properties) {
        return ResourceBundle.getBundle(properties, Locale.getDefault(), CTL);
    }

    /**
     * 读取properties文件
     *
     * @param key
     * @return value
     */
    public static String getConf(String key) {
        return getConf("application", key);
    }

    /**
     * 读取properties文件
     *
     * @param key
     * @return value
     */
    public static String getConf(String properties, String key) {
        String string = "";
        try {
            string = getBundle(properties).getString(key);
        } catch (Exception e) {
            logger.error("ResourceUtil getConf error", e);
        }
        return string;
    }

    /**
     * 重载控制器
     */
    private static class MyResourceBundleControl extends ResourceBundle.Control {
        /**
         * 如果在加载配置文件中时隔一秒钟文件内容将重新读取
         */
        @Override
        public long getTimeToLive(String baseName, Locale locale) {
            return 1000;
        }

        @Override
        public boolean needsReload(String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle, long loadTime) {
            return true;
        }
    }

}
