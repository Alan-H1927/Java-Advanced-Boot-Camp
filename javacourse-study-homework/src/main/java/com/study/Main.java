package com.study;


import com.study.week5.spring.autowired.WebInfo;
import com.study.week7.spring.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.study.**.mapper")
@Import({DynamicDataSourceConfig.class})
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Autowired
    private WebInfo webInfo;

    public static void main(String[] args) {
        logger.info("::--->Main start<---::");
        SpringApplication.run(Main.class, args);
        logger.info("::--->Main end<---::");
    }

    @Bean
    public void printInfo() {
        System.out.println(webInfo.getName());
    }

}
