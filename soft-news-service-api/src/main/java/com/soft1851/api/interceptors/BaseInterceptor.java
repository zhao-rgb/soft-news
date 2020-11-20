package com.soft1851.api.interceptors;

import com.soft1851.exception.GraceException;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @author zhao
 * @className BaseInterceptor
 * @Description 基础拦截器
 * @Date 2020/11/17
 * @Version 1.0
 **/
public class BaseInterceptor {

    @Autowired
    private RedisOperator redis;

    public static final String REDIS_USER_TOKEN = "redis_user_token";
    public static final String REDIS_ADMIN_TOKEN = "redis_admin_token";

    public boolean verifyUserIdToken(String id, String token, String redisKeyPrefix) {
        if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(token)) {
            String uniqueToken = redis.get(redisKeyPrefix + ":" + id);
            if (StringUtils.isBlank(uniqueToken)) {
                GraceException.display(ResponseStatusEnum.UN_LOGIN);
                return false;
            } else {
                if (!uniqueToken.equals(token)) {
                    GraceException.display(ResponseStatusEnum.TICKET_INVALID);
                    return false;
                }
            }
        } else {
            GraceException.display(ResponseStatusEnum.UN_LOGIN);
            return false;
        }

        // false: 请求被拦截，被驳回，验证出现问题;true: 请求在经过验证校验以后，确认是可以放行的
        return true;
    }


    public boolean verifyUploadFile(File file) throws Exception {
        if(file != null) {
            // 获得文件上传的名称
            String fileName = file.getName();
            // 判断文件名不能为空
            if (StringUtils.isNotBlank(fileName)) {
                // 分割文件名
                String[] fileNameArr = fileName.split("\\.");
                // 获得后缀
                String suffix = fileNameArr[fileNameArr.length - 1];
                // 判断后缀符合我们的预定义规范
                if (!"png".equalsIgnoreCase(suffix) &&
                        !"jpg".equalsIgnoreCase(suffix) &&
                        !"jpeg".equalsIgnoreCase(suffix)
                ) {
                    GraceException.display(ResponseStatusEnum.FILE_FORMATTER_FAILD);
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

}