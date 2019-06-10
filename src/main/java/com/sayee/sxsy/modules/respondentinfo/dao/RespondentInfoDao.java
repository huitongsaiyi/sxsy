/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.respondentinfo.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.respondentinfo.entity.RespondentInfo;

/**
 * 被调查人信息DAO接口
 * @author gbq
 * @version 2019-06-10
 */
@MyBatisDao
public interface RespondentInfoDao extends CrudDao<RespondentInfo> {
	
}