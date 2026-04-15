package com.example.spba.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spba.domain.entity.Article;
import com.example.spba.service.impl.ArticleServiceImpl;
import com.example.spba.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/list")
    public R getList(@RequestParam(name = "page", defaultValue = "1") Integer page,
                     @RequestParam(name = "size", defaultValue = "6") Integer size,
                     @RequestParam(name = "category", required = false) String category) {
        HashMap params = new HashMap();
        if (category != null && !category.isEmpty()) {
            params.put("category", category);
        }
        Page<HashMap> pages = new Page<>(page, size);
        Page<HashMap> list = articleService.getList(pages, params);
        return R.success(list);
    }

    @GetMapping("/{id}")
    public R getDetail(@PathVariable("id") Long id) {
        Article article = articleService.getDetail(id);
        if (article == null) {
            return R.error(404, "文章不存在");
        }
        return R.success(article);
    }
}
