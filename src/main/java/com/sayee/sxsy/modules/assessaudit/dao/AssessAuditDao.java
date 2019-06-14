/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessaudit.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.assessaudit.entity.AssessAudit;

/**
 * 评估鉴定审批DAO接口
 * @author lyt
 * @version 2019-06-13
 */
@MyBatisDao
public interface AssessAuditDao extends CrudDao<AssessAudit> {
	
}