package com.soft1851.api.controller.user;

import com.soft1851.pojo.bo.RegistLoginBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author zhao
 * @className PassportControllerApi
 * @Description 通行证Api
 * @Date 2020/11/15
 * @Version 1.0
 **/
@Api(value = "用户注册登录",tags = {"用户注册登录的controller"})
@RequestMapping("passport")
public interface PassportControllerApi {
    /**
     * 发送短信
     * @param mobile 手机号
     * @param request request
     * @return GraceResult
     */
    @ApiOperation(value = "获得验证码",notes = "获得短信验证码", httpMethod = "GET")
    @GetMapping("/smsCode")
    GraceResult getCode(@RequestParam String mobile, HttpServletRequest request);

    /**
     * 一键登录注册接口
     * @param registLoginBO 注册登录入参
     * @param result        校验结果
     * @param request       request
     * @param response      response
     * @return GraceResult
     */
    @ApiOperation(value = "一键注册登录接口",notes = "一键注册登录接口",httpMethod = "POST")
    @PostMapping("/sign")
    GraceResult doSign(@RequestBody @Valid RegistLoginBO registLoginBO,
                       BindingResult result,
                       HttpServletRequest request,
                       HttpServletResponse response);
}
