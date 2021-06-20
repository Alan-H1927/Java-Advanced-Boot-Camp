package com.study.week7.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-14
 */
@Data
@ToString
public class BaseEntity {
    /**
     * 创建人
     */
    @TableField(value = "create_person")
    protected String createPerson;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    protected Date createTime;
    /**
     * 创建机器IP
     */
    @TableField(value = "create_machine")
    protected String createMachine;
    /**
     * 修改人
     */
    @TableField(value = "update_person")
    protected String updatePerson;
    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    protected Date updateTime;
    /**
     * 修改机器IP
     */
    @TableField(value = "update_machine")
    protected String updateMachine;
}
