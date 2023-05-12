package com.example.spba.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spba.dao.MemberInfoMapper;
import com.example.spba.dao.MemberMapper;
import com.example.spba.domain.entity.MemberInfo;
import com.example.spba.service.MemberInfoService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;

/**
 * Author: VincenT
 * Date: 2023/5/11 23:21
 * Contact: vincentli_tech@qq.com
 * Purpose: 此类用于..
 */
@Service
public class MemberInfoServiceImpl extends ServiceImpl<MemberInfoMapper, MemberInfo> implements MemberInfoService {
    @Override
    public Page<HashMap> getList(Page page, HashMap params) {
        return this.baseMapper.getList(page, params);
    }

    @Override
    public HashMap getInfo(HashMap params) {
        return this.baseMapper.getInfo(params);
    }

    @Override
    public Boolean checkUsername(@NotBlank String username) {
        HashMap<String, Object> where = new HashMap<>();
        where.put("login_code", username);
        return getInfo(where) != null;
    }
}
