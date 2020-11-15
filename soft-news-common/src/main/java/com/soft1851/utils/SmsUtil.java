package com.soft1851.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.soft1851.utils.extend.AliyunResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhao
 * @className SmsUtil
 * @Description 短信工具类
 * @Date 2020/11/15
 * @Version 1.0
 **/
@Component
public class SmsUtil {
    @Resource
    public AliyunResource aliyunResource;
    public void sendSms(String mobile, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",aliyunResource.getAccessKeyId(),aliyunResource.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId","cn-hangzhou");
        request.putQueryParameter("PhoneNumbers",mobile);
        request.putQueryParameter("SignName","龙龙的直播");
        request.putQueryParameter("TemplateCode","SMS_205397647");
        request.putQueryParameter("TemplateParam","{\"code\":\"" + code + "\"}");

        try{
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            System.err.println(e.getMessage());
        }
    }
}
