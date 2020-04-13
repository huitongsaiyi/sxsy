package com.sayee.sxsy.newModules.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.utils.EhCacheUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoshuya
 * @date 2020/4/10 9:54
 */

@Service
public class SendNoteUtils {

    public ResponsesUtils sendNoteUtlis(String tel, String TemplateCode,String TemplateParam) {
        String sccessKeyId = Global.getSccessKeyId();
        String secert = Global.getSecert();
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", sccessKeyId, secert);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", tel);
        request.putQueryParameter("SignName", "医视界");
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", TemplateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return ResponsesUtils.ok(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    return ResponsesUtils.build(500,"发送失败");
    }
}
