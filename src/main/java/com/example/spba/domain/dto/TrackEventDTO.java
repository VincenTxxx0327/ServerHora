package com.example.spba.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TrackEventDTO
{
    @NotBlank(message = "模块名称不能为空")
    private String module;

    @NotBlank(message = "事件内容不能为空")
    private String content;

    @NotNull(message = "事件类别不能为空")
    private Integer category;

    private Date triggerTime;

    private String pageUrl;

    private String elementId;

    private String sessionId;

    private String extraData;

    public interface Save{}
    public interface Update{}
}
