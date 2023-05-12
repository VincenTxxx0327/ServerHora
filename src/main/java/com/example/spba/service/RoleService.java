package com.example.spba.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spba.domain.entity.Role;

import java.util.HashMap;
import java.util.List;

public interface RoleService extends IService<Role>
{

    /**
     * 获取角色列表（分页）
     * @param page
     * @param params
     * @return
     */
    public Page<HashMap> getList(Page page, HashMap params);

    /**
     * 验证角色
     * @param role_ids 当前包含角色
     * @return 是否超级管理员
     */
    public Boolean checkRoleSuper(String role_ids);

    /**
     * 获取角色列表
     * @param params
     * @return
     */
    public List<Role> getAll(HashMap params);
}