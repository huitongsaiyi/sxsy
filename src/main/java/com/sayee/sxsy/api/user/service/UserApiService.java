package com.sayee.sxsy.api.user.service;

import com.sayee.sxsy.api.user.dao.UserApiDao;
import com.sayee.sxsy.api.user.entity.Communicate;
import com.sayee.sxsy.api.user.entity.UserApiEntity;
import com.sayee.sxsy.api.user.entity.UserInfo;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 */
@Service
public class UserApiService extends CrudService<UserApiDao, UserApiEntity> {
    @Autowired
    private UserApiDao userApiDao;
    public void save(UserApiEntity userApiEntity){
        userApiDao.insert(userApiEntity);
    }
    public void update(UserApiEntity userApiEntity){
        userApiDao.regist(userApiEntity);
    }
    public UserInfo getUserInfoByOpenId(String openId){
        return userApiDao.getUserInfoByOpenId(openId);
    }
    public void changeStatu(Map map){
        userApiDao.changeStatu(map);
    }
    public Map getUser(String loginName){
       return userApiDao.getUser(loginName);
    }
    public String getUserById(String sysUserId){
        return userApiDao.getUserById(sysUserId);
    }
    public String getSysUserId(String wechatUserId){
        return userApiDao.getSysUserId(wechatUserId);
    }
    public UserInfo getUserInfoByUserId(String wechatUserId){
        return userApiDao.getUserInfoByUserId(wechatUserId);
    }
    public void savePassword(String sysUserId,String newPassword){
        Map map=new HashMap();
        map.put("sysUserId",sysUserId);
        map.put("newPassword",newPassword);
        userApiDao.savePassword(map);
    }
    /*投诉统计*/
    public Integer[] getMediateInfoCount(String wechatUserId,Integer userType){
        if(userType==1){
            return userApiDao.getMediateInfoCountForHospital(wechatUserId);
        }else{
            return userApiDao.getMediateInfoCount(wechatUserId);
        }
    }
    public Integer getMediateCountForYtw(String wechatUserId){
        return userApiDao.getMediateCountForYtw(wechatUserId);
    }
    public Integer getAssessCount(String wechatUserId){
        return userApiDao.getAssessCount(wechatUserId);
    }
    public Integer getAssessInfoCount(String wechatUserId){
        return userApiDao.getAssessInfoCount(wechatUserId);
    }
    /*咨询统计*/
    public Integer getConsultCount(String wechatUserId){
        return userApiDao.getConsultCount(wechatUserId);
    }

    public List<Communicate> getCommunicateList(){
        return userApiDao.getCommunicateList();
    }
}
