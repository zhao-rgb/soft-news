package com.soft1851.user.service;

import com.soft1851.pojo.AppUser;

/**
 * @author zhao
 * @className UserService
 * @Description TODO
 * @Date 2020/11/16
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 判断用户是否存在，如果存在返回user信息
     * @param mobile 用户手机号
     * @return AppUser
     */
    AppUser queryMobileIsExist(String mobile);

    /**
     * 创建用户, 新增用户记录到数据库
     * @param mobile 用户手机号
     * @return AppUser
     */
    AppUser createUser(String mobile);

    /**
     * 根据用户主键获得用户信息
     * @param userId 用户id
     * @return AppUser
     */
    public AppUser getUser(String userId);
}
