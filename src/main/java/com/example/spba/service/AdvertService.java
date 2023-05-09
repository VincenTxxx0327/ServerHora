package com.example.spba.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spba.domain.entity.Advert;

import java.util.HashMap;

/**
 * Author: VincenT
 * Date: 2023/4/29 13:45
 * Contact: vincentli_tech@qq.com
 * Purpose: 此类用于..
 */
public interface AdvertService extends IService<Advert> {

    /**
     * 获取通用广告列表（分页）
     * @param page
     * @param params
     * @return
     */
    public Page<HashMap> getList(Page page, HashMap params);

    /**
     * 获取拥有某角色的广告列表（分页）
     * @param roleId
     * @return
     */
    public Page<HashMap> getListByRole(Integer roleId, Page page, HashMap params);
}
