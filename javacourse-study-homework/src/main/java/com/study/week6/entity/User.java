package com.study.week6.entity;

import lombok.Data;

/**
 * //用户
 *
 * @author me-ht
 * @date 2021-06-14
 */
@Data
public class User extends BaseEntity {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户账号
     */
    private String userUsername;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户性别（0，女；1，男）
     */
    private String userSex;
    /**
     * 用户年龄
     */
    private int userAge;
    /**
     * 用户邮箱
     */
    private String userEmail;
    /**
     * 用户手机号
     */
    private String userPhoneNumber;
    /**
     * 用户身份证号
     */
    private String userIdCard;
    /**
     * 状态（1，正常;2，冻结）
     */
    private String userStatus;

}
