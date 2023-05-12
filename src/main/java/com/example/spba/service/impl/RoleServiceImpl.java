package com.example.spba.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spba.dao.RoleMapper;
import com.example.spba.domain.entity.Role;
import com.example.spba.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Page<HashMap> getList(Page page, HashMap params) {
        return this.baseMapper.getList(page, params);
    }

    @Override
    public Boolean checkRoleSuper(String role_ids) {
        HashMap<String, Object> where = new HashMap<>();
        where.put("root", role_ids);
        List<Role> roles = this.baseMapper.getAll(where);

        return roles.size() > 0;
    }

    @Override
    public List<Role> getAll(HashMap params) {
        return this.baseMapper.getAll(params);
    }
}
