package com.study.autowired;

import com.study.bean.SchoolInfo;
import com.study.bean.StudentInfo;
import com.study.config.SpringBootSchoolConfiguration;
import com.study.prop.SpringBootSchoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-05
 */
@Configuration
@Import(SpringBootSchoolConfiguration.class)
@EnableConfigurationProperties(SpringBootSchoolProperties.class)
public class SpringBootSchoolAutowired {

    @Autowired
    SpringBootSchoolProperties springBootSchoolProperties;

    @Autowired
    SpringBootSchoolConfiguration springBootSchoolConfiguration;

    @Bean
    public SchoolInfo createSchoolInfo() {
        return new SchoolInfo(springBootSchoolConfiguration.name + "--->"
                + "，id--->" + springBootSchoolProperties.getId()
                + "，name--->" + springBootSchoolProperties.getName());
    }
}
