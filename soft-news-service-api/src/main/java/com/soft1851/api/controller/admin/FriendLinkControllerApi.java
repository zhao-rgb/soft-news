package com.soft1851.api.controller.admin;

import com.soft1851.pojo.bo.SaveFriendLinkBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author zhao
 * @className FriendLinkService
 * @Description TODO
 * @Date 2020/11/24
 * @Version 1.0
 **/
@Api(value = "首页友情链接维护",tags = {"首页友情链接维护controller"})
@RequestMapping("friendLink")
public interface FriendLinkControllerApi {

    /**
     * 新增或者修改友情链接
     * @param saveFriendLinkBO 入参
     * @param result 校验结果
     * @return GraceResult
     */
    @ApiOperation(value = "新增或者修改友情链接", notes = "新增或者修改友情链接", httpMethod = "POST")
    @PostMapping("/saveOrUpdateFriendLink")
    GraceResult saveOrUpdateFriendLink(@RequestBody @Valid SaveFriendLinkBO saveFriendLinkBO,
                                       BindingResult result);

}
