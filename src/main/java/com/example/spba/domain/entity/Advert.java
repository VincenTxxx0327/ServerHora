package com.example.spba.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: VincenT
 * Date: 2023/4/29 22:17
 * Contact: vincentli_tech@qq.com
 * Purpose: 此类用于..
 */
@Data
public class Advert implements Serializable {

    private static final long serialVersionUID = -7478956755312715484L;

    /** ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 发布用户名 */
    private String username;

    /** 角色 */
    private String role;

    /** 头像地址 */
    private String avatar;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
