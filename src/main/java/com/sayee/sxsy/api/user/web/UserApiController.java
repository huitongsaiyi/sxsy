package com.sayee.sxsy.api.user.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.HttpRequest;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.complain.entity.ComplaintApi;
import com.sayee.sxsy.api.main.entity.MainApi;
import com.sayee.sxsy.api.main.service.MainApiService;
import com.sayee.sxsy.api.mediate.entity.Mediate;
import com.sayee.sxsy.api.mediate.service.MediateApiService;
import com.sayee.sxsy.api.user.entity.Communicate;
import com.sayee.sxsy.api.user.entity.IdCardApiEntity;
import com.sayee.sxsy.api.user.entity.UserApiEntity;
import com.sayee.sxsy.api.user.entity.UserInfo;
import com.sayee.sxsy.api.user.service.IdCardApiService;
import com.sayee.sxsy.api.user.service.UserApiService;
import com.sayee.sxsy.common.security.Digests;
import com.sayee.sxsy.common.utils.Encodes;
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
    @Autowired
    private MediateApiService mediateApiService;
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
                        Map map=new HashMap();
                        map.put("wechatUserId",userInfo.getWechatUserId());
                        R r=new R();
                        r.put("RtnCode",0);
                        r.put("RtnMsg","success");
                        r.put("RtnData",map);
                        return r;
                    }else {
                        Map map=new HashMap();
                        UserApiEntity userApiEntity = new UserApiEntity();
                        userApiEntity.setOpenId(openId);
                        userApiEntity.preInsert();
                        String wechatUserId = userApiEntity.getId();
                        userApiEntity.setWechatUserId(wechatUserId);
                        userApiEntity.setUserType(0);
                        userApiEntity.setCreateDate(new Date());
                        userApiEntity.setUpdateDate(new Date());
                        userApiService.save(userApiEntity);
                        UserInfo userInfo1=new UserInfo();
                        userInfo1.setWechatUserId(wechatUserId);
                        map.put("userInfo",userInfo1);
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
            if(userType==1){
                //医院用户
                int complaintToYtwCount=0;
                int complaintCount=0;
                int complaintToOthersCount=0;
                List<ComplaintApi> dataList3=mediateApiService.complaintListForHos(wechatUserId);
                for(int i=0;i<dataList3.size();i++){
                    String handleWay=dataList3.get(i).getHandleWay();
                    if(handleWay.equals("2")){
                        complaintToYtwCount++;
                    }else if(handleWay.equals("0")){
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
                List<Mediate> dataList1=mediateApiService.mediateCommenFindList(wechatUserId);
                int mediateCountForYtw=0;
                int assessCount= 0;
                int assessInfoCount=0;
                for(int i=0;i<dataList1.size();i++){
                    String actName=dataList1.get(i).getActName();
                    if(actName.equals("报案登记")||actName.equals("审核受理")||actName.equals("调查取证")||actName.equals("质证调解")){
                        mediateCountForYtw++;
                    }else if(actName.equals("评估鉴定")||actName.equals("达成调解")||actName.equals("签署协议")||actName.equals("履行协议")){
                        assessCount++;
                    }else{
                        assessInfoCount++;
                    }
                }
                map.put("mediateCountForYtw",mediateCountForYtw);
                map.put("assessCount",assessCount);
                map.put("assessInfoCount",assessInfoCount);
            }else{
                int consultCount=userApiService.getConsultCount(wechatUserId);
                int complaintCount=0;
                int complaintToYtwCount=0;
                List<ComplaintApi> dataList2=mediateApiService.getComplaintList(wechatUserId);
                for(int i=0;i<dataList2.size();i++){
                    String handleWay=dataList2.get(i).getHandleWay();
                    if(handleWay.equals("2")){
                        complaintToYtwCount++;
                    }else{
                        complaintCount++;
                    }
                }
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
        String wechatUserId=jsonObject.getString("wechatUserId");
        //获取用户信息
        UserInfo userInfo=userApiService.getUserInfoByUserId(wechatUserId);
        userInfo.setAvatarUrl(jsonObject.getString("avatarUrl"));
        userInfo.setNickName(jsonObject.getString("nickName"));
        int userType=userInfo.getUserType();
        //String wechatUserId=userApiEntity.getWechatUserId();
        //userApiEntity.setUserType(userType);
        Map map=new HashMap();
        map.put("userInfo",userInfo);
        if(userType==1){
            //医院用户
            int complaintToYtwCount=0;
            int complaintCount=0;
            int complaintToOthersCount=0;
            List<ComplaintApi> dataList3=mediateApiService.complaintListForHos(wechatUserId);
            for(int i=0;i<dataList3.size();i++){
                String handleWay=dataList3.get(i).getHandleWay();
                if(handleWay.equals("2")){
                    complaintToYtwCount++;
                }else if(handleWay.equals("0")){
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
            List<Mediate> dataList1=mediateApiService.mediateCommenFindList(wechatUserId);
            int mediateCountForYtw=0;
            int assessCount= 0;
            int assessInfoCount=0;
            for(int i=0;i<dataList1.size();i++){
                String actName=dataList1.get(i).getActName();
                if(actName.equals("报案登记")||actName.equals("审核受理")||actName.equals("调查取证")||actName.equals("质证调解")){
                    mediateCountForYtw++;
                }else if(actName.equals("评估鉴定")||actName.equals("达成调解")||actName.equals("签署协议")||actName.equals("履行协议")){
                    assessCount++;
                }else{
                    assessInfoCount++;
                }
            }
            map.put("mediateCountForYtw",mediateCountForYtw);
            map.put("assessCount",assessCount);
            map.put("assessInfoCount",assessInfoCount);
        }else{
            int consultCount=userApiService.getConsultCount(wechatUserId);
            int complaintCount=0;
            int complaintToYtwCount=0;
            List<ComplaintApi> dataList2=mediateApiService.getComplaintList(wechatUserId);
            for(int i=0;i<dataList2.size();i++){
                String handleWay=dataList2.get(i).getHandleWay();
                if(handleWay.equals("2")){
                    complaintToYtwCount++;
                }else{
                    complaintCount++;
                }
            }
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
        int userType=Integer.valueOf(jsonObject.getString("userType"));
        Map userMap=userApiService.getUser(accountNumber);
        String wechatUserId=jsonObject.getString("wechatUserId");
        if(null!=userMap){
            boolean checkPassword=validatePassword(password,userMap.get("password").toString());
            if(checkPassword){
                try {
                    UserApiEntity userApiEntity = JSON.toJavaObject(jsonObject, UserApiEntity.class);
                    userApiEntity.setSysUserId(userMap.get("id").toString());
                    userApiEntity.setUserType(userType);
                    userApiEntity.setWechatUserId(wechatUserId);
                    userApiService.organizationBind(userApiEntity);
                    R r=new R();
                    r.put("RtnCode",0);
                    r.put("RtnMsg","success");
                    r.put("RtnData","");
                    return r;
                }catch(Exception e){
                    R r=new R();
                    r.put("RtnCode",3);
                    r.put("RtnMsg","绑定失败");
                    r.put("RtnData","");
                    return r;
                }
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
    /*机构解绑*/
    @RequestMapping("organizationclear")
    @ResponseBody
    public R organizationClear(@RequestBody JSONObject jsonObject){
        String wechatUserId=jsonObject.getString("wechatUserId");
        String sysUserid=null;
        int userType=0;
        UserApiEntity userApiEntity = new UserApiEntity();
        userApiEntity.setWechatUserId(wechatUserId);
        userApiEntity.setSysUserId(sysUserid);
        userApiEntity.setUserType(userType);
        userApiService.organizationBind(userApiEntity);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData","");
        return r;
    }
    @RequestMapping("changestatu")
    @ResponseBody
    public R changeStatu(@RequestBody JSONObject jsonObject){
        String wechatUserid=jsonObject.getString("wechatUserid");
        String statu=jsonObject.getString("statu");
        Map map=new HashMap<>();
        map.put("uid",wechatUserid);
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
    @Autowired
    private IdCardApiService idCardApiService;
    /*实名认证*/
    @ResponseBody
    @RequestMapping("certificate")
    public R certificate(@RequestBody JSONObject jsonObject){
        String idNumber=jsonObject.getString("idNumber");
        String realName=jsonObject.getString("realName");
        String wechatUserId=jsonObject.getString("wechatUserId");
        String tel=jsonObject.getString("tel");
        String cardA=jsonObject.getString("cardA");
        String cardB=jsonObject.getString("cardB");
        String cardHand=jsonObject.getString("cardHand");
        int certificateMark=1;
        if(null==wechatUserId||wechatUserId.isEmpty()){
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","用户id不能为空");
            r.put("RtnData","");
            return r;
        }else if(null==realName||realName.isEmpty()){
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","真实姓名不能为空");
            r.put("RtnData","");
            return r;
        }else if(null==idNumber||idNumber.isEmpty()){
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","身份证号码不能为空");
            r.put("RtnData","");
            return r;
        }else{
            String regex = "\\d{15}(\\d{2}[0-9xX])?";
            if(idNumber.matches(regex)){
                userApiService.certificate(wechatUserId,idNumber,realName,tel,certificateMark);
                IdCardApiEntity idCardApiEntity=new IdCardApiEntity();
                idCardApiEntity.preInsert();
                idCardApiEntity.setCardId(idCardApiEntity.getId());
                idCardApiEntity.setCardA(cardA);
                idCardApiEntity.setCardB(cardB);
                idCardApiEntity.setCardHand(cardHand);
                idCardApiEntity.setWechatUserId(wechatUserId);
                idCardApiEntity.setCreateDate(new Date());
                idCardApiEntity.setUpdateDate(new Date());
                try {
                    idCardApiService.save(idCardApiEntity);
                }catch(Exception e){
                }
                R r=new R();
                r.put("RtnCode",0);
                r.put("RtnMsg","success");
                r.put("RtnData","");
                return r;
            }else{
                R r=new R();
                r.put("RtnCode",1);
                r.put("RtnMsg","身份证号码无效");
                r.put("RtnData","");
                return r;
            }
        }

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
