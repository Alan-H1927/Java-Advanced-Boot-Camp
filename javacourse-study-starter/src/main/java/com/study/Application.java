package com.study;

import com.study.bean.KclassInfo;
import com.study.bean.SchoolInfo;
import com.study.bean.StudentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private StudentInfo studentInfo;

    @Autowired
    private SchoolInfo schoolInfo;

    @Autowired
    private KclassInfo kclassInfo;

    public static void main(String[] args) {
        logger.info("::--->Application start<---::");
        SpringApplication.run(Application.class, args);
        logger.info("::--->Application end<---::");
    }

    @Bean
    public void printStudentInfo(){
        System.out.println(studentInfo.getInfo());
    }

    @Bean
    public void printSchoolInfo(){
        System.out.println(schoolInfo.getInfo());
    }

    @Bean
    public void printKclassInfo(){
        System.out.println(kclassInfo.getInfo());
    }
}
