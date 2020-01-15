package com.sayee.sxsy.api.user.dao;

import com.sayee.sxsy.api.user.entity.Communicate;
import com.sayee.sxsy.api.user.entity.UserApiEntity;
import com.sayee.sxsy.api.user.entity.UserInfo;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * @Description
 */
@MyBatisDao
public interface UserApiDao extends CrudDao<UserApiEntity> {
    UserInfo getUserInfoByOpenId(String openId);
    void changeStatu(Map map);
    Map getUser(String loginName);
    String getUserById(String sysUserId);
    void savePassword(Map map);
    Integer[] getMediateInfoCount(String wechatUserId);
    Integer[] getMediateInfoCountForHospital(String wechatUserId);
    Integer getConsultCount(String wechatUserId);
    List<Communicate> getCommunicateList();
    UserInfo getUserInfoByUserId(String wechatUserId);

    Integer getMediateCountForYtw(String wechatUserId);
    Integer getAssessCount(String wechatUserId);
    Integer getAssessInfoCount(String wechatUserId);
    String getSysUserId(String wechatUserId);
    void regist(UserApiEntity userApiEntity);
    void organizationBind(UserApiEntity userApiEntity);
}
