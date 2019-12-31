package com.sayee.sxsy.api.user.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.HttpRequest;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.main.entity.MainApi;
import com.sayee.sxsy.api.main.service.MainApiService;
import com.sayee.sxsy.api.user.entity.Communicate;
import com.sayee.sxsy.api.user.entity.UserApiEntity;
import com.sayee.sxsy.api.user.entity.UserInfo;
import com.sayee.sxsy.api.user.service.UserApiService;
import com.sayee.sxsy.common.security.Digests;
import com.sayee.sxsy.common.utils.Encodes;
import com.sayee.sxsy.modules.sys.service.SystemService;
import org.activiti.engine.impl.json.JsonObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 */
@Controller
@RequestMapping("${adminPath}/api")
public class UserApiController{
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    @Autowired
    private UserApiService userApiService;
    @Autowired
    private MainApiService mainApiService;
    /*登录*/
    @RequestMapping("wxlogin")
    @ResponseBody
    public R wxLogin(@RequestBody JSONObject jsonObject){
        String code=jsonObject.getString("code");
        if(null==code||code.isEmpty()){
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","code不能为空");
            r.put("RtnData","");
            return r;
        }else{
            MainApi mainApi=mainApiService.getMainInfo();
            if(null==mainApi){
                R r=new R();
                r.put("RtnCode",2);
                r.put("RtnMsg","内容未配置");
                r.put("RtnData","");
                return r;
            }else{
                String logApi="https://api.weixin.qq.com/sns/jscode2session?";
                //String appid="wx5c31f4c601318ad7";
                //String secret="9629a3f3389bd0484028f668ed9bf929";
                String appid=mainApi.getAppId();
                String secret=mainApi.getAppSecret();
                String js_code=code;
                String param = "appid="+appid+"&secret="+secret+"&js_code="+js_code+"&grant_type=authorization_code";
                try{
                    String sr = HttpRequest.sendPost(logApi, param);
                    JSONObject json=JSON.parseObject(sr);
                    String openId=json.getString("openid");
                    UserInfo userInfo=userApiService.getUserInfoByOpenId(openId);
                    if(null!=userInfo){
                        int userType=userInfo.getUserType();//1医院用户 2 医调委用户 3 普通用户
                        String wechatUserId =userInfo.getWechatUserId();
                        Map map=new HashMap();
                        map.put("userInfo",userInfo);
                        int complaintCount=0;
                        int complaintToYtwCount=0;
                        int complaintToOthersCount=0;
                        Integer[] mediateInfoCount;
                        if(userType==1){
                            //医院用户
                            mediateInfoCount=userApiService.getMediateInfoCount(wechatUserId,userType);
                            for(int count:mediateInfoCount){
                                if(count==2){
                                    complaintToYtwCount++;
                                }else if(count==0){
                                    complaintCount++;
                                }else{
                                    complaintToOthersCount++;
                                }
                            }
                            map.put("complaintCount",complaintCount);
                            map.put("complaintToYtwCount",complaintToYtwCount);
                            map.put("complaintToOthersCount",complaintToOthersCount);
                        }else if(userType ==2){
                            //医调委用户
                            int mediateCountForYtw=userApiService.getMediateCountForYtw(wechatUserId);
                            int assessCount= userApiService.getAssessCount(wechatUserId);
                            int assessInfoCount=userApiService.getAssessInfoCount(wechatUserId);
                            map.put("mediateCountForYtw",mediateCountForYtw);
                            map.put("assessCount",assessCount);
                            map.put("assessInfoCount",assessInfoCount);
                        }else{
                            mediateInfoCount=userApiService.getMediateInfoCount(wechatUserId,userType);
                            for(int count:mediateInfoCount){
                                if(count==2){
                                    complaintToYtwCount++;
                                }else{
                                    complaintCount++;
                                }
                            }
                            int consultCount=userApiService.getConsultCount(wechatUserId);
                            map.put("consultCount",consultCount);
                            map.put("complaintCount",complaintCount);
                            map.put("complaintToYtwCount",complaintToYtwCount);
                        }
                        R r=new R();
                        r.put("RtnCode",0);
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
                        UserInfo userInfo1=new UserInfo();
                        userInfo1.setWechatUserId(wechatUserId);
                        R r = new R();
                        r.put("RtnCode", 0);
                        r.put("RtnMsg", "success");
                        r.put("RtnData", userInfo1);
                        return r;
                    }
                }catch(Exception e){
                    R r = new R();
                    r.put("RtnCode", 3);
                    r.put("RtnMsg", "error");
                    r.put("RtnData","");
                    return r;
                }
            }
        }
    }
    /*获取用户信息*/
    @ResponseBody
    @RequestMapping("getuserinfo")
    public R getUserInfo(@RequestBody JSONObject jsonObject){
        String wechatUserId=jsonObject.getString("wechatUserId");
        if(!(null==wechatUserId||wechatUserId.isEmpty())){
            UserInfo userInfo=userApiService.getUserInfoByUserId(wechatUserId);
            int userType=userInfo.getUserType();//1医院用户 2 医调委用户 3 普通用户
            Map map=new HashMap();
            map.put("userInfo",userInfo);
            int complaintCount=0;
            int complaintToYtwCount=0;
            int complaintToOthersCount=0;
            Integer[] mediateInfoCount;
            if(userType==1){
                //医院用户
                mediateInfoCount=userApiService.getMediateInfoCount(wechatUserId,userType);
                for(int count:mediateInfoCount){
                    if(count==2){
                        complaintToYtwCount++;
                    }else if(count==0){
                        complaintCount++;
                    }else{
                        complaintToOthersCount++;
                    }
                }
                map.put("complaintCount",complaintCount);
                map.put("complaintToYtwCount",complaintToYtwCount);
                map.put("complaintToOthersCount",complaintToOthersCount);
            }else if(userType ==2){
                //医调委用户
                int mediateCountForYtw=userApiService.getMediateCountForYtw(wechatUserId);
                int assessCount= userApiService.getAssessCount(wechatUserId);
                int assessInfoCount=userApiService.getAssessInfoCount(wechatUserId);
                map.put("mediateCountForYtw",mediateCountForYtw);
                map.put("assessCount",assessCount);
                map.put("assessInfoCount",assessInfoCount);
            }else{
                mediateInfoCount=userApiService.getMediateInfoCount(wechatUserId,userType);
                for(int count:mediateInfoCount){
                    if(count==2){
                        complaintToYtwCount++;
                    }else{
                        complaintCount++;
                    }
                }
                int consultCount=userApiService.getConsultCount(wechatUserId);
                map.put("consultCount",consultCount);
                map.put("complaintCount",complaintCount);
                map.put("complaintToYtwCount",complaintToYtwCount);
            }
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "success");
            r.put("RtnData", map);
            return r;
        }else{
            R r = new R();
            r.put("RtnCode", 1);
            r.put("RtnMsg", "微信用户id不能为空!");
            r.put("RtnData", "");
            return r;
        }
    }
    /*用户信息修改账号绑定*/
    @RequestMapping("changeuserinfo")
    @ResponseBody
    public R changeUserInfo(@RequestBody JSONObject jsonObject){
        UserApiEntity userApiEntity=JSON.toJavaObject(jsonObject,UserApiEntity.class);
        int userType=null==userApiEntity.getUserType()?0:userApiEntity.getUserType();
        String wechatUserId=userApiEntity.getWechatUserId();
        userApiEntity.setUserType(userType);
        Map map=new HashMap();
        map.put("userInfo",userApiEntity);
        int complaintCount=0;
        int complaintToYtwCount=0;
        int complaintToOthersCount=0;
        Integer[] mediateInfoCount;
        if(userType==1){
            //医院用户
            mediateInfoCount=userApiService.getMediateInfoCount(wechatUserId,userType);
            for(int count:mediateInfoCount){
                if(count==2){
                    complaintToYtwCount++;
                }else if(count==0){
                    complaintCount++;
                }else{
                    complaintToOthersCount++;
                }
            }
            map.put("complaintCount",complaintCount);
            map.put("complaintToYtwCount",complaintToYtwCount);
            map.put("complaintToOthersCount",complaintToOthersCount);
        }else if(userType ==2){
            //医调委用户
            int mediateCountForYtw=userApiService.getMediateCountForYtw(wechatUserId);
            int assessCount= userApiService.getAssessCount(wechatUserId);
            int assessInfoCount=userApiService.getAssessInfoCount(wechatUserId);
            map.put("mediateCountForYtw",mediateCountForYtw);
            map.put("assessCount",assessCount);
            map.put("assessInfoCount",assessInfoCount);
        }else{
            mediateInfoCount=userApiService.getMediateInfoCount(wechatUserId,userType);
            for(int count:mediateInfoCount){
                if(count==2){
                    complaintToYtwCount++;
                }else{
                    complaintCount++;
                }
            }
            int consultCount=userApiService.getConsultCount(wechatUserId);
            map.put("consultCount",consultCount);
            map.put("complaintCount",complaintCount);
            map.put("complaintToYtwCount",complaintToYtwCount);
        }
        try {
            userApiService.update(userApiEntity);
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "success");
            r.put("RtnData", map);
            return r;
        }catch (Exception e){
            R r = new R();
            r.put("RtnCode", 1);
            r.put("RtnMsg", "修改失败");
            r.put("RtnData", "");
            return r;
        }
    }

    /*机构绑定*/
    @RequestMapping("organizationbind")
    @ResponseBody
    public R organizationBind(@RequestBody JSONObject jsonObject){
        String accountNumber=jsonObject.getString("accountNumber");
        String password=jsonObject.getString("password");
        Map userMap=userApiService.getUser(accountNumber);
        if(null!=userMap){
            boolean checkPassword=validatePassword(password,userMap.get("password").toString());
            if(checkPassword){
                UserApiEntity userApiEntity=JSON.toJavaObject(jsonObject,UserApiEntity.class);
                userApiEntity.setSysUserId(userMap.get("id").toString());
                userApiService.update(userApiEntity);
                R r=new R();
                r.put("RtnCode",0);
                r.put("RtnMsg","success");
                r.put("RtnData","");
                return r;
            }else{
                R r=new R();
                r.put("RtnCode",2);
                r.put("RtnMsg","密码验证失败!");
                r.put("RtnData","");
                return r;
            }
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","没有找到该用户!");
            r.put("RtnData","");
            return r;
        }

    }
    @RequestMapping("changestatu")
    @ResponseBody
    public R changeStatu(@RequestBody JSONObject jsonObject){
        String uid=jsonObject.getString("wechatUserid");
        String statu=jsonObject.getString("statu");
        Map map=new HashMap<>();
        map.put("uid",uid);
        map.put("statu",statu);
        userApiService.changeStatu(map);
        R r=new R();
        r.put("RtnCode",0);
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
        String passwordEntrypt=entryptPassword(password);
        String creditCode=jsonObject.getString("creditCode");
        String organizationImage=jsonObject.getString("organizationImage");
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData","");
        return r;
    }
    /*修改密码*/
    @RequestMapping("changepassword")
    @ResponseBody
    public R changePassword(@RequestBody JSONObject jsonObject){
        String wechatUserId=jsonObject.getString("wechatUserId");
        String sysUserId=userApiService.getSysUserId(wechatUserId);
        //String sysUserId=jsonObject.getString("sysUserId");
        String oldPassword=jsonObject.getString("oldPassword");
        String newPassword=jsonObject.getString("newPassword");
        String password=userApiService.getUserById(sysUserId);
        if(null==password||password.isEmpty()){
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","没有找到该用户!");
            r.put("RtnData","");
            return r;
        }else{
            boolean checkPassword=validatePassword(oldPassword,password);
            if(checkPassword){
                try{
                    userApiService.savePassword(sysUserId,entryptPassword(newPassword));
                    R r=new R();
                    r.put("RtnCode",0);
                    r.put("RtnMsg","密码修改成功!");
                    r.put("RtnData","");
                    return r;
                }catch (Exception e){
                    R r=new R();
                    r.put("RtnCode",3);
                    r.put("RtnMsg","修改失败!");
                    r.put("RtnData","");
                    return r;
                }
            }else{
                R r=new R();
                r.put("RtnCode",2);
                r.put("RtnMsg","旧密码错误!");
                r.put("RtnData","");
                return r;
            }
        }
    }
    /*通讯录*/
    @ResponseBody
    @RequestMapping("communicatelist")
    public R communicationList(){
        List<Communicate> communicateList= userApiService.getCommunicateList();
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",communicateList);
        return r;
    }
    public static String entryptPassword(String plainPassword) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
    }
    public static boolean validatePassword(String plainPassword, String password) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0,16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
    }
}
