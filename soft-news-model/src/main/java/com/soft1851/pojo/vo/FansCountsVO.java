package com.soft1851.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao
 * @className FansCountsVo
 * @Description 按性别统计粉丝数统计VO
 * @Date 2020/11/26
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FansCountsVO {
    private Integer manCounts;
    private Integer womanCounts;
}
