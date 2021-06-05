package com.study.utils.properties;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-05
 */
public class ResourceUtil {
    private ResourceUtil() {
    }

    private final static MyResourceBundleControl ctl = new MyResourceBundleControl();

    private static ResourceBundle getBundle(String pro) {
        return ResourceBundle.getBundle(pro, Locale.getDefault(), ctl);
    }

    /**
     * 读取conf.properties
     *
     * @param key
     * @return value
     */
    public static String getConf(String key) {
        String string = "";
        try {
            string = getBundle("application").getString(key);
        } catch (Exception e) {
            e.printStackTrace();
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
