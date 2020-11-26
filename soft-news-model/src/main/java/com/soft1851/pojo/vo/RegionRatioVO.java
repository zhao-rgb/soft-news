package com.soft1851.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao
 * @className RegionRationVO
 * @Description 按地区粉丝数统计VO
 * @Date 2020/11/26
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionRatioVO {
    private String name;
    private Integer value;
}
