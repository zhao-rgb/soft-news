package com.soft1851.admin.service;

import com.soft1851.pojo.AdminUser;
import com.soft1851.pojo.bo.NewAdminBO;
import com.soft1851.utils.PageGridResult;

/**
 * @author zhao
 * @className AdminUserService
 * @Description TODO
 * @Date 2020/11/20
 * @Version 1.0
 **/
public interface AdminUserService {

    /**
     * 获得管理员用户信息
     * @param username 用户名
     * @return AdminUser
     */
    AdminUser queryAdminByUserName(String username);

    /**
     * 新增管理员
     * @param newAdminBO BO入参
     */
    void createAdminUser(NewAdminBO newAdminBO);

    /**
     * 分页查询管理员列表
     * @param page
     * @param pageSize
     * @return
     */
    PageGridResult queryAdminList(Integer page, Integer pageSize);

    /**
     * 修改指定管理员的faceId
     * @param username 用户名
     * @param faceId faceId
     */
    void updateAdmin(String username,String faceId);
}
