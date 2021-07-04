package com.study.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class ConvertUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

    private ConvertUtil() {
    }

    public static String convertByteToString(byte[] data) throws UnsupportedEncodingException {
        return new String(data, StandardCharsets.UTF_8);
    }

    public static byte[] convertStringToByte(String data) throws UnsupportedEncodingException {
        return data.getBytes(StandardCharsets.UTF_8);
    }
}
