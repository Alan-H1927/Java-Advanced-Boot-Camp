package com.study.week5.spring.parser;


import com.study.week5.spring.bean.Student;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-01
 */
public class SpringStudentDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Student.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        System.out.println("SpringStudentDefinitionParser id--->" + id);
        String name = element.getAttribute("name");
        System.out.println("SpringStudentDefinitionParser name--->" + name);
        String sex = element.getAttribute("sex");
        System.out.println("SpringStudentDefinitionParser sex--->" + sex);
        if (StringUtils.hasText(id)) {
            builder.addPropertyValue("id", id);
        }
        if (StringUtils.hasText(name)) {
            builder.addPropertyValue("name", name);
        }
        if (StringUtils.hasText(sex)) {
            builder.addPropertyValue("sex", sex);
        }
    }
}
