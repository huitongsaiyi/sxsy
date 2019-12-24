package com.sayee.sxsy.api.user.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.HttpRequest;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.user.entity.UserApiEntity;
import com.sayee.sxsy.api.user.entity.UserInfo;
import com.sayee.sxsy.api.user.service.UserApiService;
import com.sayee.sxsy.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 *
 * @author www.donxon.com
 */
@Controller
@RequestMapping("${adminPath}/api")
public class UserApiController{
    @Autowired
    private UserApiService userApiService;
    @RequestMapping("wxlogin")
    @ResponseBody
    public R wxLogin(@RequestBody JSONObject jsonObject){
        String code=jsonObject.getString("code");
        if(null==code||code.isEmpty()){
            R r=new R();
            r.put("RtnCode","1");
            r.put("RtnMsg","error");
            r.put("RtnData","code不能为空");
            return r;
        }else{
            String logApi="https://api.weixin.qq.com/sns/jscode2session?";
            String appid="wx5c31f4c601318ad7";
            String secret="9629a3f3389bd0484028f668ed9bf929";
            String js_code=code;
            String param = "appid="+appid+"&secret="+secret+"&js_code="+js_code+"&grant_type=authorization_code";
            String sr = HttpRequest.sendPost(logApi, param);
            JSONObject json=JSON.parseObject(sr);
            String openId=json.getString("openid");
            UserInfo userInfo=userApiService.getUserInfoByOpenId(openId);
            if(null!=userInfo){
                R r=new R();
                r.put("RtnCode","0");
                r.put("RtnMsg","success");
                r.put("RtnData",userInfo);
                return r;
            }else {
                UserApiEntity userApiEntity = new UserApiEntity();
                userApiEntity.setOpenId(openId);
                userApiEntity.preInsert();
                String wechatUserId = userApiEntity.getId();
                userApiEntity.setWechatUserId(wechatUserId);
                userApiEntity.setCreateDate(new Date());
                userApiEntity.setUpdateDate(new Date());
                userApiService.save(userApiEntity);
                R r = new R();
                r.put("RtnCode", "0");
                r.put("RtnMsg", "success");
                r.put("RtnData", wechatUserId);
                return r;
            }
        }
    }
    /*注册*/
    @RequestMapping("organizationbind")
    @ResponseBody
    public R organizationBind(@RequestBody JSONObject jsonObject){
        UserApiEntity userApiEntity=JSON.toJavaObject(jsonObject,UserApiEntity.class);
        userApiService.update(userApiEntity);
        R r=new R();
        r.put("RtnCode","0");
        r.put("RtnMsg","success");
        r.put("RtnData","");
        return r;
    }
    @RequestMapping("changestatu")
    @ResponseBody
    public R changeStatu(@RequestBody JSONObject jsonObject){
        String uid=jsonObject.getString("uid");
        String statu=jsonObject.getString("statu");
        Map map=new HashMap<>();
        map.put("uid",uid);
        map.put("statu",statu);
        userApiService.changeStatu(map);
        R r=new R();
        r.put("RtnCode","0");
        r.put("RtnMsg","success");
        r.put("RtnData","");
        return r;
    }
    /*机构注册*/
    @RequestMapping("organizationregist")
    @ResponseBody
    public R organizationRegist(@RequestBody JSONObject jsonObject){
        String address=jsonObject.getString("address");
        int organizationType=jsonObject.getInteger("organizationType");
        String accountNumber=jsonObject.getString("accountNumber");
        String password=jsonObject.getString("password");
        String passwordEntrypt=SystemService.entryptPassword(password);
        String creditCode=jsonObject.getString("creditCode");
        String organizationImage=jsonObject.getString("organizationImage");
        R r=new R();
        r.put("RtnCode","0");
        r.put("RtnMsg","success");
        r.put("RtnData","");
        return r;
    }
}
