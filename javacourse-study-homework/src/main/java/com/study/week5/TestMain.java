package com.study.week5;


import com.study.week5.spring.bean.Kclass;
import com.study.week5.spring.bean.ManualSpringBean;
import com.study.week5.spring.bean.School;
import com.study.week5.spring.bean.Student;
import com.study.week5.spring.manualwired.ManualSpringBeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-01
 */
public class TestMain {

    public static void main(String[] args) {
        //注解
        annotationConfigBean();
        //自定义schema
        cSchema();
    }

    private static void annotationConfigBean() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(ManualSpringBeanConfig.class);

        ManualSpringBean annotationSpringBean = annotationConfigApplicationContext.getBean(ManualSpringBean.class);
        System.out.println("annotationSpringBean--->" + annotationSpringBean.getId() + ", " + annotationSpringBean.getName());
    }

    private static void cSchema() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("week5/spring-cbean.xml");

        Student student = applicationContext.getBean(Student.class);
        System.out.println("student--->" + student.getId() + ", " + student.getName() + ", " + student.getSex());

        School school = applicationContext.getBean(School.class);
        System.out.println("school--->" + school.getId() + ", " + school.getName());

        Kclass kclass = applicationContext.getBean(Kclass.class);
        System.out.println("kclass--->" + kclass.getId() + ", " + kclass.getName());
    }


}
