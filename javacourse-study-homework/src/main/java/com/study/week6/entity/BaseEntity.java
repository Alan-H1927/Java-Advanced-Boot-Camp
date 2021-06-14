package com.study.week6.entity;

import lombok.Data;

import java.util.Date;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-14
 */
@Data
public class BaseEntity {
    /**
     * 创建人
     */
    protected String createPerson;
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * 创建机器IP
     */
    protected String createMachine;
    /**
     * 修改人
     */
    protected String updatePerson;
    /**
     * 修改时间
     */
    protected Date updateTime;
    /**
     * 修改机器IP
     */
    protected String updateMachine;
}
