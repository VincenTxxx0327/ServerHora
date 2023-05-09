package com.example.spba.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spba.dao.AdvertMapper;
import com.example.spba.domain.entity.Advert;
import com.example.spba.service.AdvertService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Author: VincenT
 * Date: 2023/4/29 13:48
 * Contact: vincentli_tech@qq.com
 * Purpose: 此类用于..
 */
@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, Advert> implements AdvertService {
    @Override
    public Page<HashMap> getList(Page page, HashMap params) {
        return this.baseMapper.getList(page, params);
    }

    @Override
    public Page<HashMap> getListByRole(Integer roleId, Page page, HashMap params) {
        return this.baseMapper.getListByRole(roleId, page, params);
    }
}
