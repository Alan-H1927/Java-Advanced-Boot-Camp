package com.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavacourseStudyCourseApplication {

    private static final Logger logger = LoggerFactory.getLogger(JavacourseStudyCourseApplication.class);

    public static void main(String[] args) {
        logger.info("::--->JavacourseStudyCourseApplication start<---::");
        SpringApplication.run(JavacourseStudyCourseApplication.class, args);
        logger.info("::--->JavacourseStudyCourseApplication end<---::");
    }

}
