package com.soft1851.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author zhao
 * @className UpdateUserInfoBO
 * @Description TODO
 * @Date 2020/11/17
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserInfoBO {
    @NotBlank(message = "用户ID不能为空")
    private String id;

    @NotBlank(message = "用户昵称不能为空")
    @Length(max = 12, message = "用户昵称不能超过12位")
    private String nickname;

    @NotBlank(message = "用户头像不能为空")
    private String face;

    @NotBlank(message = "真实姓名不能为空")
    private String realname;

    @Email
    @NotBlank(message = "邮件不能为空")
    private String email;

    @NotNull(message = "请选择一个性别")
    @Min(value = 0,message = "性别选择不正确")
    @Max(value = 1,message = "性别选择不正确")
    private Integer sex;

    /**
     * 前端日期字符串传到后端，转换为Date类型
     */
    @NotNull(message = "请选择生日")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotBlank(message = "请选择所在省份")
    private String province;

    @NotBlank(message = "请选择所在城市")
    private String city;

    @NotBlank(message = "请选择所在地区")
    private String district;
}
