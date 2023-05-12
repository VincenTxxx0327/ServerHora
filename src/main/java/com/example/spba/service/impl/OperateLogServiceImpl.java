package com.example.spba.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spba.domain.entity.Member;
import com.example.spba.domain.entity.OperateLog;
import com.example.spba.dao.OperateLogMapper;
import com.example.spba.service.MemberService;
import com.example.spba.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements OperateLogService
{

    @Autowired
    private MemberService memberService;

    @Override
    public void save(Long adminId, String url, String method, String params, String ip) {
        Member member = memberService.getById(adminId);
        OperateLog log = new OperateLog();
        log.setAdminId(adminId.intValue());
        log.setUsername(member.getLogin_code());
        log.setUrl(url);
        log.setMethod(method);
        log.setParams(params);
        log.setIp(ip);
        this.save(log);
    }

    @Override
    public Page<HashMap> getList(Page page, HashMap params) {
        return this.baseMapper.getList(page, params);
    }
}
