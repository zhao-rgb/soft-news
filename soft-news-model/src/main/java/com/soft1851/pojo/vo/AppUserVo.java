package com.soft1851.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao
 * @className AppUserVo
 * @Description TODO
 * @Date 2020/11/17
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserVo {

    private String id;

    private String nickname;

    private String face;

    /**
     * 激活状态
     */
    private Integer activeStatus;

    /**
     * 我的关注数
     */
    private Integer myFollowCounts;

    /**
     * 我的粉丝数
     */
    private Integer myFansCounts;
}
