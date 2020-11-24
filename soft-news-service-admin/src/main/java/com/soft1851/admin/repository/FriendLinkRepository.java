package com.soft1851.admin.repository;

import com.soft1851.pojo.mo.FriendLinkMO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author zhao
 * @className FriendLinkRepository
 * @Description 友链操作
 * @Date 2020/11/24
 * @Version 1.0
 **/
public interface FriendLinkRepository extends MongoRepository<FriendLinkMO,String> {
    /**
     * 根据是否删除字段查询所有友链
     *
     * @param isDelete 是否删除
     * @return List
     */
    List<FriendLinkMO> getAllByIsDelete(Integer isDelete);
}
