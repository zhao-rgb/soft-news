package com.soft1851.article.service;

import com.soft1851.pojo.Category;
import com.soft1851.pojo.bo.NewArticleBO;
import com.soft1851.pojo.vo.ArticleDetailVO;

/**
 * @author zhao
 * @className ArticleService
 * @Description TODO
 * @Date 2020/11/24
 * @Version 1.0
 **/
public interface ArticleService {
    /**
     * 发布文章
     * @param newArticleBO 新建文章BO类
     * @param category 分类
     */
    void createArticle(NewArticleBO newArticleBO, Category category);

    /**
     * 更改文章状态
     * @param articleId 文章id
     * @param pendingStatus 发布状态
     */
    void updateArticleStatus(String articleId,Integer pendingStatus);

    /**
     * 更新定时发布为即时发布
     */
    void updateAppointToPublish();

    /**
     * 删除文章
     * @param userId 用户id
     * @param articleId 文章id
     */
    void deleteArticle(String userId, String articleId);

    /**
     * 撤回文章
     * @param userId 用户id
     * @param articleId 文章id
     */
    void withdrawArticle(String userId, String articleId);

    /**
     * 查询文章详情
     * @param articleId 文章id
     * @return ArticleDetailVO
     */
    ArticleDetailVO queryDetail(String articleId);
}
