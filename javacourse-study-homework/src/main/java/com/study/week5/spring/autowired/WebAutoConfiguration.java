package com.study.week5.spring.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * //自动装配类
 *
 * @author me-ht
 * @date 2021-06-05
 */
@Configuration
@Import(WebConfig.class)
@EnableConfigurationProperties(WebProperties.class)
public class WebAutoConfiguration {

    @Autowired
    WebProperties webProperties;

    @Autowired
    WebConfig webConfig;

    @Bean
    public WebInfo createAutoInfo() {
        return new WebInfo(webConfig.name + "--->" + webProperties.getName());
    }
}