package com.example.spba.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spba.domain.entity.Article;

import java.util.HashMap;

public interface ArticleService extends IService<Article> {

    Page<HashMap> getList(Page page, HashMap params);

    Article getDetail(Long id);
}
