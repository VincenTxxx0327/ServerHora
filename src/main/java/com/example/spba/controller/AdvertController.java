package com.example.spba.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spba.service.AdvertService;
import com.example.spba.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.HashMap;

/**
 * Author: VincenT
 * Date: 2023/4/29 13:27
 * Contact: vincentli_tech@qq.com
 * Purpose: 此类用于..
 */
@RestController
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @GetMapping("/advert")
    public R getAdvertList(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "7") Integer size){
        HashMap where = new HashMap();
        Page<HashMap> pages = new Page<>(page, size);
        Page<HashMap> list = advertService.getList(pages, where);

        return R.success(list);
    }
    @GetMapping("/advert/{id}")
    public R getAdvertList(@PathVariable("id") @Min(value = 1, message = "参数错误") Integer roleId,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "7") Integer size){
        HashMap where = new HashMap();
        where.put("roleId", roleId);
        Page<HashMap> pages = new Page<>(page, size);
        Page<HashMap> list = advertService.getList(pages, where);

        return R.success(list);
    }
}
