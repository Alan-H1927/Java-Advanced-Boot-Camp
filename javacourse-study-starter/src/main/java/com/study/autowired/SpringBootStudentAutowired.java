package com.study.autowired;

import com.study.bean.StudentInfo;
import com.study.config.SpringBootStudentConfiguration;
import com.study.prop.SpringBootStudentProperties;
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
@Import(SpringBootStudentConfiguration.class)
@EnableConfigurationProperties(SpringBootStudentProperties.class)
public class SpringBootStudentAutowired {

    @Autowired
    SpringBootStudentProperties springBootStudentProperties;

    @Autowired
    SpringBootStudentConfiguration springBootStudentConfiguration;

    @Bean
    public StudentInfo createStudentInfo() {
        return new StudentInfo(springBootStudentConfiguration.name + "--->"
                + "，id--->" + springBootStudentProperties.getId()
                + "，name--->" + springBootStudentProperties.getName()
                + "，sex--->" + springBootStudentProperties.getSex());
    }
}
