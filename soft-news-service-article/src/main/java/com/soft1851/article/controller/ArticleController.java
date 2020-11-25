package com.soft1851.article.controller;

import com.soft1851.api.BaseController;
import com.soft1851.api.controller.article.ArticleControllerApi;
import com.soft1851.article.service.ArticleService;
import com.soft1851.enums.ArticleCoverType;
import com.soft1851.enums.ArticleReviewStatus;
import com.soft1851.enums.YesOrNo;
import com.soft1851.pojo.Category;
import com.soft1851.pojo.bo.NewArticleBO;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author zhao
 * @className ArticleController
 * @Description TODO
 * @Date 2020/11/24
 * @Version 1.0
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController extends BaseController implements ArticleControllerApi {
    private final ArticleService articleService;

    @Override
    public GraceResult createArticle(@Valid NewArticleBO newArticleBO, BindingResult result) {
        System.out.println(newArticleBO);
        System.out.println(result);
        //判断BindingResult是否保存错误的验证信息，如果有，则直接return
        if (result.hasErrors()) {
            Map<String, String> errorHap = getErrors(result);
            return GraceResult.errorMap(errorHap);
        }
        // 判断文章封面类型，单图必填，纯文字则设置为空
        if (newArticleBO.getArticleType().equals(ArticleCoverType.ONE_IMAGE.type)) {
            if (StringUtils.isBlank(newArticleBO.getArticleCover())) {
                return GraceResult.errorCustom(ResponseStatusEnum.ARTICLE_COVER_NOT_EXIST_ERROR);
            }
        } else if (newArticleBO.getArticleType().equals(ArticleCoverType.WORDS.type)) {
            newArticleBO.setArticleCover("");
        }

        //判断分类id是否存在
        String allCatJson = redis.get(REDIS_ALL_CATEGORY);
        Category temp = null;
        if (StringUtils.isBlank(allCatJson)) {
            return GraceResult.errorCustom(ResponseStatusEnum.SYSTEM_OPERATION_ERROR);
        } else {
            List<Category> categories = JsonUtil.jsonToList(allCatJson, Category.class);
            assert categories != null;
            for (Category c : categories) {
                if (c.getId().equals(newArticleBO.getCategoryId())) {
                    temp = c;
                    break;
                }
            }
            if (temp == null) {
                return GraceResult.errorCustom(ResponseStatusEnum.ARTICLE_CATEGORY_NOT_EXIST_ERROR);
            }
        }
        System.out.println(newArticleBO.toString());
        articleService.createArticle(newArticleBO, temp);
        return GraceResult.ok();
    }

    @Override
    public GraceResult doReview(String articleId, Integer passOrNot) {
        Integer pendingStatus;
        if(passOrNot == YesOrNo.YES.type) {
            pendingStatus = ArticleReviewStatus.SUCCESS.type;
        } else if (YesOrNo.No.type.equals(passOrNot)) {
            pendingStatus = ArticleReviewStatus.FAILED.type;
        } else {
            return GraceResult.errorCustom(ResponseStatusEnum.ARTICLE_REVIEW_ERROR);
        }
        articleService.updateArticleStatus(articleId,pendingStatus);
        return GraceResult.ok();
    }
}

