package com.study.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Spring boot properties configuration.
 */
@ConfigurationProperties(prefix = "spring.student")
public final class SpringBootStudentProperties {
    private String id = "1";
    private String name = "student";
    private String sex = "male";

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
