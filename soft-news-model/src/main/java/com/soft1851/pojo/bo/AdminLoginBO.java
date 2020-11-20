package com.soft1851.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao
 * @className AdminLoginBO
 * @Description 管理员登录BO类
 * @Date 2020/11/20
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLoginBO {
    private String username;
    private String password;
    private String img64;
}
