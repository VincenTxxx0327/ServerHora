package com.example.spba.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spba.dao.TrackEventMapper;
import com.example.spba.domain.entity.TrackEvent;
import com.example.spba.service.TrackEventService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TrackEventServiceImpl extends ServiceImpl<TrackEventMapper, TrackEvent> implements TrackEventService
{
    @Override
    public void saveEvent(TrackEvent trackEvent) {
        this.save(trackEvent);
    }

    @Override
    public void saveEvents(List<TrackEvent> trackEvents) {
        this.saveBatch(trackEvents);
    }

    @Override
    public Page<HashMap> getList(Page page, HashMap params) {
        return this.baseMapper.getList(page, params);
    }
}
