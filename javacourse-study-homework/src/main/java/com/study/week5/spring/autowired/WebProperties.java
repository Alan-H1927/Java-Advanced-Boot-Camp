package com.study.week5.spring.autowired;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-05
 */
@ConfigurationProperties(prefix = "web")
public class WebProperties {

    private String name = "web";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
