package com.study;


import com.study.week5.spring.autowired.WebInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavacourseStudyHomeworkApplication {

	private static final Logger logger = LoggerFactory.getLogger(JavacourseStudyHomeworkApplication.class);

	@Autowired
	private WebInfo webInfo;

	public static void main(String[] args) {
		logger.info("::--->JavacourseStudyHomeworkApplication start<---::");
		SpringApplication.run(JavacourseStudyHomeworkApplication.class, args);
		logger.info("::--->JavacourseStudyHomeworkApplication end<---::");
	}

	@Bean
	public void printInfo(){
		System.out.println(webInfo.getName());
	}

}
