package com.study.id;

import java.util.UUID;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-14
 */
public class UserIdUtil {
    private UserIdUtil() {
    }

    private static final String PREFIX = "U";

    public static String generateId() {
        return PREFIX + UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(generateId());
    }
}
