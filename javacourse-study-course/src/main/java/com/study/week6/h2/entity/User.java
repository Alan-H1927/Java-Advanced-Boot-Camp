package com.study.week6.h2.entity;

import lombok.Data;

/**
 * //用户
 *
 * @author me-ht
 * @date 2021-06-14
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
