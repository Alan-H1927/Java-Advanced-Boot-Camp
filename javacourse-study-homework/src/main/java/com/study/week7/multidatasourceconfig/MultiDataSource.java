package com.study.week7.multidatasourceconfig;

import java.lang.annotation.*;

/**
 * //数据源注解
 *
 * @author me-ht
 * @date 2021-06-20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiDataSource {
    String name() default "";
}
