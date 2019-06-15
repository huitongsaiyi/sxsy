/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.patientlinkemp.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;

/**
 * 患方联系人DAO接口
 * @author gbq
 * @version 2019-06-13
 */
@MyBatisDao
public interface PatientLinkEmpDao extends CrudDao<PatientLinkEmp> {
	
}