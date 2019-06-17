/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessinfo.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.assessinfo.entity.AssessInfo;

/**
 * 案件评价DAO接口
 * @author lyt
 * @version 2019-06-17
 */
@MyBatisDao
public interface AssessInfoDao extends CrudDao<AssessInfo> {
	
}