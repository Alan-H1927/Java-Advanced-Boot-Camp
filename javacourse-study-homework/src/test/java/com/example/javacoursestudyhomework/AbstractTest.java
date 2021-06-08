package com.example.javacoursestudyhomework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-08
 */
@SpringBootTest
public abstract class AbstractTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
}
