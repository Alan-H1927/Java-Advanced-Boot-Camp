package com.study.id;

import java.util.UUID;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-15
 */
public class OrderIdUtil {
    private OrderIdUtil() {
    }

    private static final String PREFIX = "O";

    public static String generateId() {
        return PREFIX + UUID.randomUUID().toString().replaceAll("-", "");
    }
}
