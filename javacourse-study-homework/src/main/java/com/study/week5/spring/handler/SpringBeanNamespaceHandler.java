package com.study.week5.spring.handler;


import com.study.week5.spring.parser.SpringKclassDefinitionParser;
import com.study.week5.spring.parser.SpringSchoolDefinitionParser;
import com.study.week5.spring.parser.SpringStudentDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-01
 */
public class SpringBeanNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        System.out.println("-->" + getClass().getName());
        registerBeanDefinitionParser("student", new SpringStudentDefinitionParser());
        registerBeanDefinitionParser("school", new SpringSchoolDefinitionParser());
        registerBeanDefinitionParser("kclass", new SpringKclassDefinitionParser());
    }
}
