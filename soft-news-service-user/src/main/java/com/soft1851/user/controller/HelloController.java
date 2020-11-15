package com.soft1851.user.controller;

import com.soft1851.api.controller.user.HelloControllerApi;
import com.soft1851.result.GraceResult;
import com.soft1851.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhao
 * @className HelloController
 * @Description TODO
 * @Date 2020/11/13
 * @Version 1.0
 **/
@RestController
@Slf4j
public class HelloController implements HelloControllerApi {
    @Autowired
    private RedisOperator redis;

    @Override
    public Object hello() {
        log.info("info: hello");
        return GraceResult.ok("hello");
    }

    @GetMapping("/redis")
    public GraceResult redis() {
        redis.set("age","21");
        return GraceResult.ok(redis.get("age"));
    }
}
