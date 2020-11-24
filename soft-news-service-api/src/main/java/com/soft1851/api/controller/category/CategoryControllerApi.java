package com.soft1851.api.controller.category;

import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhao
 * @className CategoryControllerApi
 * @Description TODO
 * @Date 2020/11/24
 * @Version 1.0
 **/
@Api(value = "文章类型的controller", tags = {"文章类型的controller"})
@RequestMapping("category")
public interface CategoryControllerApi {

    /**
     * 查询文章分类
     * @return GraceResult
     */
    @GetMapping("/getAll")
    @ApiOperation(value = "查询文章分类", notes = "查询文章分类", httpMethod = "GET")
    GraceResult getAll();
}
