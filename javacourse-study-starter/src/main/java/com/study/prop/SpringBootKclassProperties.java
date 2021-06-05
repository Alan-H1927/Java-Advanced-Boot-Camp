package com.study.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-05
 */
@ConfigurationProperties(prefix = "spring.kclass")
public class SpringBootKclassProperties {
    private String id = "1";
    private String name = "kclass";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
