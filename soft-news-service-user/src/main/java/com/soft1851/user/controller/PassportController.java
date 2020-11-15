package com.soft1851.user.controller;

import com.soft1851.api.BaseController;
import com.soft1851.api.controller.user.PassportControllerApi;
import com.soft1851.result.GraceResult;
import com.soft1851.utils.IpUtil;
import com.soft1851.utils.SmsUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhao
 * @className PassportController
 * @Description 通行证接口
 * @Date 2020/11/15
 * @Version 1.0
 **/
@RestController
public class PassportController extends BaseController implements PassportControllerApi {
    @Resource
    private SmsUtil smsUtil;

    @Override
    public GraceResult getCode(String mobile, HttpServletRequest request) {
        // 获得用户ip
        String userIp = IpUtil.getRequestIp(request);
        // 根据用户的ip进行限制，限制用户在60秒内只能获得一次验证码
        redis.setnx60s(MOBILE_SMSCODE + ":" + userIp, userIp);
        // 生成随机验证码并且发送短信
        String random = (int) ((Math.random() * 9 + 1)* 100000) + "";
        System.out.println(random);
        smsUtil.sendSms(mobile, random);
        // 把验证码存入redis，用于后续进行验证
        redis.set(MOBILE_SMSCODE + ":" + mobile, random, 30 * 60);
        return GraceResult.ok();
    }
}
