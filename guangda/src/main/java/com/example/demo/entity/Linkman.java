package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

/**
 * linkman
 * @author 
 */
@Data
public class Linkman extends LinkmanKey implements Serializable {
    /**
     * 准客户号码
     */
    private Integer precustomerno;

    /**
     * 联系人顺序号
     */
    private Integer linkorderno;
    /**
     * 联系人类型
     */
    private String linktype;

    /**
     * 姓名
     */
    private String linkman;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门
     */
    private String depart;

    /**
     * 职位
     */
    private String post;

    private static final long serialVersionUID = 1L;

}