package com.soft1851.api;

import com.soft1851.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhao
 * @className BaseController
 * @Description TODO
 * @Date 2020/11/15
 * @Version 1.0
 **/
public class BaseController {
    /**
     * 这里要声明为public、要不子类不能使用
     */
    @Autowired
    public RedisOperator redis;

    public static final String MOBILE_SMSCODE = "mobile:smscode";
    public static final String REDIS_USER_TOKEN = "redis_user_token";
    public static final String REDIS_USER_INFO = "redis_user_info";
    public static final String REDIS_ADMIN_TOKEN = "redis_admin_token";
    public static final String REDIS_ALL_CATEGORY = "redis_all_category" ;


    public static final String REDIS_WRITER_FANS_COUNTS = "redis_writer_fans_counts";
    public static final String REDIS_MY_FOLLOW_COUNTS = "redis_my_follow_counts";
    public static final String REDIS_ARTICLE_READ_COUNTS = "redis_article_read_counts";
    public static final String REDIS_ALREADY_READ = "redis_already_read";

    
    public static final Integer COMMON_START_PAGE = 1;
    public static final Integer COMMON_PAGE_SIZE = 10;

    public static final Integer COOKIE_MONTH = 30*24*60*60;
    public static final Integer COOKIE_DELETE = 0;

    @Value("${website.domain-name}")
    public String DOMAIN_NAME;

    /**
     * 获取BO中的错误信息，可以通过统一的异常处理返回给客户端
     * @param result 入参
     */
    public Map<String,String> getErrors(BindingResult result) {
        Map<String,String> map = new HashMap<>(16);
        List<FieldError> errorList = result.getFieldErrors();
        for(FieldError error : errorList){
            // 发送验证错误的时候所对应的某个属性
            String field = error.getField();
            // 验证的错误信息
            String msg = error.getDefaultMessage();
            map.put(field,msg);
        }
        return map;
    }

    /**
     * 设置cookie
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param maxAge
     */
    public void setCookie(HttpServletRequest request,
                          HttpServletResponse response,
                          String cookieName,
                          String cookieValue,
                          Integer maxAge) {
        try{
            cookieValue = URLEncoder.encode(cookieValue,"utf-8");
            setCookieValue(request,response,cookieName,cookieValue,maxAge);
            Cookie cookie = new Cookie(cookieName,cookieValue);
            cookie.setMaxAge(maxAge);
            cookie.setDomain(DOMAIN_NAME);
            cookie.setPath("/");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setCookieValue(HttpServletRequest request,
                               HttpServletResponse response,
                               String cookieName,
                               String cookieValue,
                               Integer maxAge) {
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(DOMAIN_NAME);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     * @param request 请求
     * @param response 响应
     * @param cookieName cookie名称
     */
    public void deleteCookie(HttpServletRequest request,
                               HttpServletResponse response,
                               String cookieName) {
        try {
            String deleteValue = URLEncoder.encode("","utf-8");
            setCookieValue(request,response,cookieName,deleteValue,COOKIE_DELETE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
