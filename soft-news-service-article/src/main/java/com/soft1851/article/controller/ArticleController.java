package com.soft1851.article.controller;

import com.soft1851.api.BaseController;
import com.soft1851.api.controller.article.ArticleControllerApi;
import com.soft1851.article.service.ArticleService;
import com.soft1851.enums.ArticleCoverType;
import com.soft1851.enums.ArticleReviewStatus;
import com.soft1851.enums.YesOrNo;
import com.soft1851.pojo.Category;
import com.soft1851.pojo.bo.NewArticleBO;
import com.soft1851.pojo.vo.AppUserVo;
import com.soft1851.pojo.vo.ArticleDetailVO;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.utils.IpUtil;
import com.soft1851.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private final RestTemplate restTemplate;

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

    @Override
    public GraceResult delete(String userId, String articleId) {
        articleService.deleteArticle(userId,articleId);
        return GraceResult.ok();
    }

    @Override
    public GraceResult withdraw(String userId, String articleId) {
        articleService.withdrawArticle(userId,articleId);
        return GraceResult.ok();
    }

    @Override
    public GraceResult detail(String articleId) {
        ArticleDetailVO detailVo = articleService.queryDetail(articleId);
        Set<String> idSet = new HashSet<>();
        idSet.add(detailVo.getPublishUserId());
        List<AppUserVo> publisherList = getPublisherList(idSet);
        if(!publisherList.isEmpty()){
            detailVo.setPublishUserName(publisherList.get(0).getNickname());
        }
        detailVo.setReadCounts(getCountsFromRedis(REDIS_ARTICLE_READ_COUNTS+":"+articleId));
        return GraceResult.ok(detailVo);
    }

    /**
     * 发起远程调用，获得用户的基本信息
     *
     * @param idSet id集合
     * @return List<AppUserVO>
     */
    private List<AppUserVo> getPublisherList(Set<String> idSet) {
        String userServerUrlExecute = "http://localhost:8003/user/queryByIds?userIds=" + JsonUtil.objectToJson(idSet);
        ResponseEntity<GraceResult> responseEntity
                = restTemplate.getForEntity(userServerUrlExecute, GraceResult.class);
        GraceResult bodyResult = responseEntity.getBody();
        List<AppUserVo> publisherList = null;
        assert bodyResult != null;
        if (bodyResult.getStatus() == 200) {
            String userJson = JsonUtil.objectToJson(bodyResult.getData());
            publisherList = JsonUtil.jsonToList(userJson, AppUserVo.class);
        }
        return publisherList;
    }

    /**
     * 从redis根据key读取阅读量
     *
     * @param key key
     * @return value
     */
    private Integer getCountsFromRedis(String key) {
        String countsStr = redis.get(key);
        if (StringUtils.isBlank(countsStr)) {
            countsStr = "0";
        }
        return Integer.valueOf(countsStr);
    }


    @Override
    public GraceResult readArticle(String articleId, HttpServletRequest request) {
        String userIp = IpUtil.getRequestIp(request);
        // 设置针对当前用户ip的永久存在的key，存入到redis，表示该ip的用户已经阅读过了，无法累加阅读量
        redis.setnx(REDIS_ALREADY_READ + ":" + articleId + ":" + userIp,userIp);
        redis.increment(REDIS_ARTICLE_READ_COUNTS + ":"+ articleId,1);
        return GraceResult.ok();
    }
}

