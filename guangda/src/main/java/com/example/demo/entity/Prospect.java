package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * prospect
 * @author 
 */
@Data
public class Prospect implements Serializable {
    /**
     * 准客户号码
     */
    private Integer precustomerno;

    /**
     * 准客户名称
     */
    private String precustomername;

    /**
     * 投保意向
     */
    private String insureaim;

    /**
     * 证件类型
     */
    private String idtype;

    /**
     * 行业类型
     */
    private String busicategory;

    /**
     * 单位性质
     */
    private String grpnature;

    /**
     * 证件号码
     */
    private String idno;

    /**
     * 单位总人数
     */
    private Integer sumnumpeople;

    /**
     * 预计投保总人数
     */
    private Integer presumpeople;

    /**
     * 单位地址(省)
     */
    private String province;

    /**
     * 单位地址(市)
     */
    private String city;

    /**
     * 单位地址(区/县)
     */
    private String county;

    /**
     * 详细地址
     */
    private String detailaddress;

    /**
     * 客户类型
     */
    private String customertype;

    /**
     * 状态
     */
    private String state;

    /**
     * 星级
     */
    private String customerlevel;

    private List<Linkman> linkmanList;

    private String namePhoneId;

    private static final long serialVersionUID = 1L;

    public Integer getPrecustomerno() {
        return precustomerno;
    }

    public void setPrecustomerno(Integer precustomerno) {
        this.precustomerno = precustomerno;
    }
}