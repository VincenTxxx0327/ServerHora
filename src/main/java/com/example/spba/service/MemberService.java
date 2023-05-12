package com.example.spba.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spba.domain.entity.Member;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;

public interface MemberService extends IService<Member>
{

    /**
     * 登录验证
     * @param params
     * @return
     */
    public HashMap checkLogin(HashMap params);

    /**
     * 获取管理员列表（分页）
     * @param page
     * @param params
     * @return
     */
    public Page<HashMap> getList(Page page, HashMap params);

    /**
     * 根据条件获取详情
     * @param params
     * @return
     */
    public HashMap getInfo(HashMap params);

    /**
     * 检查登录名是否重复
     * @Param
     * @return
     *
     * @param username*/
    public Boolean checkUsername(@NotBlank String username);

    /**
     * 获取拥有某角色的管理员列表
     * @param roleId
     * @return
     */
    public List<HashMap> getRoleAdminAll(Integer roleId);

    /**
     * 获取管理员的权限列表
     * @param adminId
     * @return
     */
    public List<HashMap> getPermissionList(Integer adminId);

}