package com.soft1851.enums;

/**
 * @author zhao
 * @className YesOrNo
 * @Description TODO
 * @Date 2020/11/24
 * @Version 1.0
 **/
public enum YesOrNo {
    // 是否
    No(1, "否"),
    YES(1, "是");
    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

}
