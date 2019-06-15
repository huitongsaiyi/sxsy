/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.medicalofficeemp.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;

/**
 * 医方人员机构关联信息DAO接口
 * @author gbq
 * @version 2019-06-14
 */
@MyBatisDao
public interface MedicalOfficeEmpDao extends CrudDao<MedicalOfficeEmp> {
	
}