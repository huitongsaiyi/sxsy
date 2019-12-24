package com.sayee.sxsy.api.user.service;

import com.sayee.sxsy.api.user.dao.UserApiDao;
import com.sayee.sxsy.api.user.entity.UserApiEntity;
import com.sayee.sxsy.api.user.entity.UserInfo;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author www.donxon.com
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
        userApiDao.update(userApiEntity);
    }
    public UserInfo getUserInfoByOpenId(String openId){
        return userApiDao.getUserInfoByOpenId(openId);
    }
    public void changeStatu(Map map){
        userApiDao.changeStatu(map);
    }
}
