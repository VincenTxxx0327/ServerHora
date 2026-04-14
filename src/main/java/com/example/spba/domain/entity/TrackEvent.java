package com.example.spba.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TrackEvent implements Serializable
{
    private static final long serialVersionUID = -8245910654238765432L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String module;

    private String content;

    private Integer category;

    private Date triggerTime;

    private String pageUrl;

    private String elementId;

    private String userAgent;

    private String ip;

    private String sessionId;

    private String extraData;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
