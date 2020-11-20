package com.soft1851.admin.controller;

import com.soft1851.admin.service.AdminUserService;
import com.soft1851.api.BaseController;
import com.soft1851.api.controller.admin.AdminMsgControllerApi;
import com.soft1851.pojo.AdminUser;
import com.soft1851.pojo.bo.AdminLoginBO;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author zhao
 * @className AdminMsgController
 * @Description TODO
 * @Date 2020/11/20
 * @Version 1.0
 **/
@RestController
public class AdminMsgController extends BaseController implements AdminMsgControllerApi {

    @Autowired
    private AdminUserService adminUserService;

    @Override
    public GraceResult adminLogin(AdminLoginBO adminLoginBO, HttpServletRequest request, HttpServletResponse response) {
        // 查询用户是否存在
        AdminUser admin = adminUserService.queryAdminByUsername(adminLoginBO.getUsername());
        if (admin == null) {
            return GraceResult.errorCustom(ResponseStatusEnum.ADMIN_NOT_EXIT_ERROR);
        }
        // 判断密码是否匹配
        boolean isPwdMatch = BCrypt.checkpw(adminLoginBO.getPassword(),admin.getPassword());
        if (isPwdMatch) {
            doLoginSettings(admin,request,response);
            return GraceResult.ok();
        } else {
            // 密码不匹配
            return GraceResult.errorCustom(ResponseStatusEnum.ADMIN_NOT_EXIT_ERROR);
        }
    }

    /**
     * 用户admin用户登录过后的基本信息设置
     * @param admin 管理员
     * @param request 请求
     * @param response 响应
     */
    private void doLoginSettings(AdminUser admin,HttpServletRequest request, HttpServletResponse response) {
        // 保存token放入redis中
        String token = UUID.randomUUID().toString();
        redis.set(REDIS_ADMIN_TOKEN + ":" + admin.getId(),token);
        // 保存admin登录基本token信息到cookie中
        setCookie(request,response,"aToken",token,COOKIE_MONTH);
        setCookie(request,response,"aId",admin.getId(),COOKIE_MONTH);
        setCookie(request,response,"aName",admin.getAdminName(),COOKIE_MONTH);
    }
}
