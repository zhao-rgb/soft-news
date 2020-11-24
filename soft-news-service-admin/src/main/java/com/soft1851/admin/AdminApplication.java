package com.soft1851.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhao
 * @className Application
 * @Description TODO
 * @Date 2020/11/20
 * @Version 1.0
 **/
@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = "com.soft1851.admin.mapper")
@ComponentScan(basePackages = {"com.soft1851", "org.n3r.idworker"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
