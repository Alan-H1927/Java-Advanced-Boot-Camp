package com.study.week5.spring.manualwired;


import com.study.week5.spring.bean.ManualSpringBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-05
 */
@Configuration
public class ManualSpringBeanConfig {

    @Bean
    public ManualSpringBean getSpringBean() {
        ManualSpringBean springBean = new ManualSpringBean();
        springBean.setId("1");
        springBean.setName("ManualSpringBean");
        return springBean;
    }
}
