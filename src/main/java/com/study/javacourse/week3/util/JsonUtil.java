package com.study.javacourse.week3.util;

import com.alibaba.fastjson.JSONObject;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public class JsonUtil {
    private JsonUtil() {
    }

    public static String beanToString(Object obj) {
        return JSONObject.toJSONString(obj);
    }
}
