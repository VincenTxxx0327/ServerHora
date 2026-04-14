package com.example.spba.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spba.domain.dto.TrackEventDTO;
import com.example.spba.domain.entity.TrackEvent;
import com.example.spba.service.TrackEventService;
import com.example.spba.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Validated
@RestController
public class TrackEventController
{
    @Autowired
    private TrackEventService trackEventService;

    @PostMapping("/track/event")
    public R reportEvent(@Validated @RequestBody TrackEventDTO form, HttpServletRequest request) {
        TrackEvent trackEvent = new TrackEvent();
        BeanUtils.copyProperties(form, trackEvent);
        if (trackEvent.getTriggerTime() == null) {
            trackEvent.setTriggerTime(new Date());
        }
        trackEvent.setUserAgent(request.getHeader("User-Agent"));
        trackEvent.setIp(getClientIp(request));
        trackEventService.saveEvent(trackEvent);
        return R.success();
    }

    @PostMapping("/track/events")
    public R reportEvents(@Validated @RequestBody List<TrackEventDTO> formList, HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String ip = getClientIp(request);
        Date now = new Date();
        List<TrackEvent> trackEvents = new ArrayList<>();
        for (TrackEventDTO form : formList) {
            TrackEvent trackEvent = new TrackEvent();
            BeanUtils.copyProperties(form, trackEvent);
            if (trackEvent.getTriggerTime() == null) {
                trackEvent.setTriggerTime(now);
            }
            trackEvent.setUserAgent(userAgent);
            trackEvent.setIp(ip);
            trackEvents.add(trackEvent);
        }
        trackEventService.saveEvents(trackEvents);
        return R.success();
    }

    @GetMapping("/track/events")
    public R getTrackEventList(String module, Integer category, String start, String end,
                               @RequestParam(name = "page", defaultValue = "1") Integer page,
                               @RequestParam(name = "size", defaultValue = "15") Integer size) {
        HashMap where = new HashMap();
        where.put("module", module);
        where.put("category", category);
        where.put("start", start);
        where.put("end", end);

        Page<HashMap> pages = new Page<>(page, size);
        Page<HashMap> list = trackEventService.getList(pages, where);

        return R.success(list);
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}
