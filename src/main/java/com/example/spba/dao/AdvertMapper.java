package com.example.spba.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spba.domain.entity.Advert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * Author: VincenT
 * Date: 2023/4/29 22:16
 * Contact: vincentli_tech@qq.com
 * Purpose: 此类用于..
 */
@Mapper
public interface AdvertMapper extends BaseMapper<Advert> {

    public Page<HashMap> getList(Page page, @Param("params") HashMap params);
    public Page<HashMap> getListByRole(Integer roleId, Page page, @Param("params") HashMap params);

}
