package com.soft1851.enums;

/**
 * @author Administrator
 */

public enum ArticleReviewLevel {
    //    0：未通过。
    //    1：通过：
    //    2：已冻结。
    UNPASS("0", "未通过"),
    PASS("1", "通过"),
    FROZEN("2", "已冻结");

    public final String type;
    public final String value;

    ArticleReviewLevel(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
