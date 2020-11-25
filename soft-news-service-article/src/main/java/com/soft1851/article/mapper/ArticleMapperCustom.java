package com.soft1851.article.mapper;

import com.soft1851.my.mapper.MyMapper;
import com.soft1851.pojo.Article;
import org.springframework.stereotype.Repository;

/**
 * @author zhao
 * @className ArticleMapperCustom
 * @Description 自定义文章mapper，个性化功能要求
 * @Date 2020/11/25
 * @Version 1.0
 **/
@Repository
public interface ArticleMapperCustom extends MyMapper<Article> {

    /**
     * 更新文章发布状态：定时->即时发布
     */
    void updateAppointToPublish();
}
