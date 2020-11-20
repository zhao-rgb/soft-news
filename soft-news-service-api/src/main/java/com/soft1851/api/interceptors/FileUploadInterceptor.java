package com.soft1851.api.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author zhao
 * @className FileUploadInterceptor
 * @Description TODO
 * @Date 2020/11/19
 * @Version 1.0
 **/
public class FileUploadInterceptor extends BaseInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        File file= (File) request.getAttribute("file");
        return verifyUploadFile(file);
    }
}

