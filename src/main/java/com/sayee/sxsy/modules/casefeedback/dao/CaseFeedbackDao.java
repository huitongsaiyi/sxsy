/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.casefeedback.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.casefeedback.entity.CaseFeedback;

/**
 * 案件反馈DAO接口
 * @author lyt
 * @version 2019-06-20
 */
@MyBatisDao
public interface CaseFeedbackDao extends CrudDao<CaseFeedback> {
	
}