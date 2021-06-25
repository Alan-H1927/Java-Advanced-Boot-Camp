package com.study.week7.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * //用户
 *
 * @author me-ht
 * @date 2021-06-14
 */
@Data
@ToString
@TableName(value = "javacourse_user")
public class SpringUser extends SpringBaseEntity {

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;
    /**
     * 用户账号
     */
    @TableField(value = "user_username")
    private String userUsername;
    /**
     * 用户密码
     */
    @TableField(value = "user_password")
    private String userPassword;
    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;
    /**
     * 用户性别（0，女；1，男）
     */
    @TableField(value = "user_sex")
    private String userSex;
    /**
     * 用户年龄
     */
    @TableField(value = "user_age")
    private int userAge;
    /**
     * 用户邮箱
     */
    @TableField(value = "user_email")
    private String userEmail;
    /**
     * 用户手机号
     */
    @TableField(value = "user_phone_number")
    private String userPhoneNumber;
    /**
     * 用户身份证号
     */
    @TableField(value = "user_id_card")
    private String userIdCard;
    /**
     * 状态（1，正常;2，冻结）
     */
    @TableField(value = "user_status")
    private String userStatus;

}
