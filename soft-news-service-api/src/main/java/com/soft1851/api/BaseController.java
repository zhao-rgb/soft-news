package com.soft1851.api;

import com.soft1851.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhao
 * @className BaseController
 * @Description TODO
 * @Date 2020/11/15
 * @Version 1.0
 **/
public class BaseController {
    // 这里要声明为public、要不子类不能使用
    @Autowired
    public RedisOperator redis;

    public static final String MOBILE_SMSCODE = "mobile:smscode";
}
