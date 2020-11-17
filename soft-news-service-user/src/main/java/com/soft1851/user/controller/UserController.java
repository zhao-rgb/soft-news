package com.soft1851.user.controller;


import com.soft1851.api.BaseController;
import com.soft1851.api.controller.user.UserControllerApi;
import com.soft1851.pojo.AppUser;
import com.soft1851.pojo.bo.UpdateUserInfoBO;
import com.soft1851.pojo.vo.AppUserVo;
import com.soft1851.pojo.vo.UserAccountInfoVo;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author zhao
 * @className UserController
 * @Description TODO
 * @Date 2020/11/14
 * @Version 1.0
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController extends BaseController implements UserControllerApi {

    private final UserService userService;

    @Override
    public GraceResult getUserInfo(String userId) {
        // 0.判断不能为空
        if(StringUtils.isBlank(userId)) {
            return GraceResult.errorCustom(ResponseStatusEnum.UN_LOGIN);
        }
        // 1.根据userId查询用户，调用内部封装方法（复用、扩展方便）
        AppUser user =getUser(userId);
        // 2.设置VO--需要展示的信息
        UserAccountInfoVo accountInfoVo = new UserAccountInfoVo();
        // 3.属性拷贝
        BeanUtils.copyProperties(user,accountInfoVo);
        return GraceResult.ok(accountInfoVo);
    }

    @Override
    public GraceResult updateUserInfo(@Valid UpdateUserInfoBO updateUserInfoBO, BindingResult result) {
        // 判断BindingResult是否保存错误的验证信息，如果有，则直接return
        if(result.hasErrors()) {
            Map<String, String> errorMap = getErrors(result);
            return GraceResult.errorMap(errorMap);
        }
        // 执行更新用户信息操作
        userService.updateUserInfo(updateUserInfoBO);
        return GraceResult.ok();
    }

    @Override
    public GraceResult getUserBasicInfo(String userId) {
        // 0.判断不能为空
        if (StringUtils.isBlank(userId)) {
            return GraceResult.errorCustom(ResponseStatusEnum.UN_LOGIN);
        }
        // 1.查询userId
        AppUser user = getUser(userId);
        // 2.信息脱敏，设置不显示
        AppUserVo userVo = new AppUserVo();
        BeanUtils.copyProperties(user,userVo);
        return GraceResult.ok(userVo);
    }

    private AppUser getUser(String userId){
        // TODO 本方法后续待扩展
        return userService.getUser(userId);

    }
}
