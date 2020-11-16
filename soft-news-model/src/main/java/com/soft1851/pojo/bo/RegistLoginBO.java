package com.soft1851.pojo.bo;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author zhao
 * @className RegistLoginBO
 * @Description 注册登录BO类
 * @Date 2020/11/16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistLoginBO {

    /**
     * @Description :@NonNull只校验null值, @NotBlank会同时校验Null和""串
     */
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @NotBlank(message = "短信验证码不能为空")
    private String smsCode;

}
