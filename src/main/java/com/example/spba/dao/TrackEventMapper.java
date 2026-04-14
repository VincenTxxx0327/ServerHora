package com.example.spba.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spba.domain.entity.TrackEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

@Mapper
public interface TrackEventMapper extends BaseMapper<TrackEvent>
{
    public Page<HashMap> getList(Page page, @Param("params") HashMap params);
}
