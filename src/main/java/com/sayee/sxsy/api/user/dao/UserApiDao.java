package com.sayee.sxsy.api.user.dao;

import com.sayee.sxsy.api.user.entity.UserApiEntity;
import com.sayee.sxsy.api.user.entity.UserInfo;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.Map;

/**
 * @author www.donxon.com
 * @Description
 */
@MyBatisDao
public interface UserApiDao extends CrudDao<UserApiEntity> {
    UserInfo getUserInfoByOpenId(String openId);
    void changeStatu(Map map);
}
