package com.soft1851.admin.service;

import com.soft1851.pojo.mo.FriendLinkMO;

/**
 * @author zhao
 * @className FriendLinkService
 * @Description FriendLinkService接口
 * @Date 2020/11/24
 * @Version 1.0
 **/
public interface FriendLinkService {

    /**
     * 新增或者更新友情链接
     *
     * @param friendLinkMO 入参
     */
    void saveOrUpdateFriendLink(FriendLinkMO friendLinkMO);
}
