/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessapply.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.assessapply.entity.AssessApply;

/**
 * 评估申请DAO接口
 * @author zhangfan
 * @version 2019-06-11
 */
@MyBatisDao
public interface AssessApplyDao extends CrudDao<AssessApply> {
	
}