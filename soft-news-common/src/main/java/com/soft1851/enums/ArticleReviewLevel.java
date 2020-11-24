package com.soft1851.enums;

/**
 * @author Administrator
 */

public enum ArticleReviewLevel {
    //文章
    PASS("PASS", "自动审核通过"),
    BLOCK("BLOCK", "自动审核不通过"),
    REVIEW("REVIEW", "建议人工复审");

    public final String type;
    public final String value;

    ArticleReviewLevel(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
