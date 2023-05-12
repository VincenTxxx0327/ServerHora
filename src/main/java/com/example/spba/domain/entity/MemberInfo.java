package com.example.spba.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "memberinfo")
public class MemberInfo implements Serializable {
    // 将光标放到类名上，按atl＋enter键
    private static final long serialVersionUID = -2443506562619360420L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联会员表ID
     */
    private Long member_id;

    /**
     * 会员类型 0-普通会员 1-黄金会员 2-铂金会员
     */
    private Long vip_level;

    /**
     * 肖像路径
     */
    private String avatar_url;

    /**
     * 唯一名称 类似于微信名 可通过此找到该会员
     */
    private String sole_name;

    /**
     * 昵称 可为空 不超过16个字或32字符
     */
    private String nick_name;

    /**
     * 真实姓名（若是公司身份则是HR姓名）显示在名片上
     */
    private String real_name;

    /**
     * 身份证
     */
    private String id_card;

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