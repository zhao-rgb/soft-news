package com.soft1851.enums;

/**
 * @author zhao
 * @className ArticleCoverType
 * @Description 文章封面类型 枚举
 * @Date 2020/11/24
 * @Version 1.0
 **/
public enum ArticleCoverType {
    //
    ONE_IMAGE(1, "单图"),
    WORDS(2, "纯文字");

    public final Integer type;
    public final String value;

    ArticleCoverType(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

}
