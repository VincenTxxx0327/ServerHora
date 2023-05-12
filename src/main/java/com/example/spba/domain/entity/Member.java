package com.example.spba.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Member implements Serializable {
    // 将光标放到类名上，按atl＋enter键
    private static final long serialVersionUID = -2643105155807511497L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 直接上级id
     */
    private Long parent_id;

    /**
     * 会员类型 0-普通会员 1-黄金会员 2-铂金会员
     */
    private Long vip_level;

    /**
     * 用户名
     */
    private String login_code;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String role_ids;

    /**
     * 加密随机码
     */
    private String safe;

    /**
     * QQOpenID
     */
    private String openid_qq;

    /**
     * 微博OpenID
     */
    private String openid_wb;

    /**
     * 微信OpenID（微信登录使用）
     */
    private String openid_wx;

    /**
     * 微信OpenID（微信支付使用）
     */
    private String openid_wxpay;

    /**
     * 最后登录时间
     */
    private Date login_time;

    /**
     * 会员过期时间
     */
    private Date expire_time;

    /**
     * 状态：-1-未激活 0-正常 1-封号
     */
    private Long active;

    /**
     * 激活时间，导入或封号时反馈显示用
     */
    private Date active_time;
//    /** 更新时间 */
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private Date updateTime;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 是否导入： 0-正常 1-导入
     */
    private Long import_code;

}