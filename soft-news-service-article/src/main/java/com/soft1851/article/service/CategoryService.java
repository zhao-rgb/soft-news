package com.soft1851.article.service;

import com.soft1851.pojo.Category;

import java.util.List;

/**
 * @author zhao
 * @className CategoryService
 * @Description TODO
 * @Date 2020/11/24
 * @Version 1.0
 **/
public interface CategoryService {
    /**
     * 查询所有文章分类
     * @return
     */
    List<Category> selectAll();
}
