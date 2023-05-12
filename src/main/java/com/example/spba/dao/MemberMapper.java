package com.example.spba.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spba.domain.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    public Page<HashMap> getList(Page page, @Param("params") HashMap params);

    public HashMap getInfo(HashMap params);

    public List<HashMap> getRoleAdminAll(Integer roleId);
}