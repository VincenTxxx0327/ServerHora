package com.example.spba.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spba.domain.entity.TrackEvent;

import java.util.HashMap;
import java.util.List;

public interface TrackEventService extends IService<TrackEvent>
{
    public void saveEvent(TrackEvent trackEvent);

    public void saveEvents(List<TrackEvent> trackEvents);

    public Page<HashMap> getList(Page page, HashMap params);
}
