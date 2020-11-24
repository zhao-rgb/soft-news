package com.soft1851.article.controller;

import com.soft1851.api.BaseController;
import com.soft1851.api.controller.category.CategoryControllerApi;
import com.soft1851.article.service.CategoryService;
import com.soft1851.pojo.Category;
import com.soft1851.result.GraceResult;
import com.soft1851.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhao
 * @className CategoryController
 * @Description TODO
 * @Date 2020/11/24
 * @Version 1.0
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController extends BaseController implements CategoryControllerApi {
    private final CategoryService categoryService;

    @Override
    public GraceResult getAll() {
        //先从redis中查询，如果有，则返回，如果为空，则查询数据库后先放缓存再返回。
        String allCategoryJson = redis.get(REDIS_ALL_CATEGORY);
        List<Category> categoryList;
        if (StringUtils.isBlank(allCategoryJson)) {
            //如果redis没有数据，则从数据库中查询所有文章分类
            categoryList = categoryService.selectAll();
            //存入redis
            redis.set(REDIS_ALL_CATEGORY, JsonUtil.objectToJson(categoryList));
        } else {
            // 否则，redis有数据，则直接转换为list返回, 保证减少数据库压力
            categoryList = JsonUtil.jsonToList(allCategoryJson, Category.class);
        }
        return GraceResult.ok(categoryList);

    }
}
