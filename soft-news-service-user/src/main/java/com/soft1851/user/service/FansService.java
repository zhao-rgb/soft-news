package com.soft1851.user.service;

/**
 * @author zhao
 * @className FansService
 * @Description FansService接口
 * @Date 2020/11/26
 * @Version 1.0
 **/
public interface FansService {

    /**
     * 查询当前用户是否关注作者
     * @param writerId 作者id
     * @param fanId    粉丝id
     * @return boolean
     */
    boolean isMeFollowThisWriter(String writerId,String fanId);

    /**
     * 关注作者,成为粉丝
     * @param writerId 作者id
     * @param fanId    粉丝id
     */
    void follow(String writerId,String fanId);
}
