/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.major.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.major.entity.MajorInfo;

/**
 * 重大事件DAO接口
 * @author zhangfan
 * @version 2019-12-09
 */
@MyBatisDao
public interface MajorInfoDao extends CrudDao<MajorInfo> {
	
}