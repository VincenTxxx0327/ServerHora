package com.example.spba.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spba.domain.entity.Member;
import com.example.spba.domain.entity.MemberInfo;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;

public interface MemberInfoService extends IService<MemberInfo> {


    /**
     * 获取管理员列表（分页）
     *
     * @param page
     * @param params
     * @return
     */
    public Page<HashMap> getList(Page page, HashMap params);

    /**
     * 根据条件获取详情
     *
     * @param params
     * @return
     */
    public HashMap getInfo(HashMap params);

    /**
     * 检查登录名是否重复
     *
     * @param username
     * @return
     * @Param
     */
    public Boolean checkUsername(@NotBlank String username);


}