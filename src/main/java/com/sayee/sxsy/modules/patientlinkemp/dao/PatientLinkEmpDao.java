/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.patientlinkemp.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;

import java.util.List;

/**
 * 患方联系人DAO接口
 * @author gbq
 * @version 2019-06-13
 */
@MyBatisDao
public interface PatientLinkEmpDao extends CrudDao<PatientLinkEmp> {

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     * @param entity
     * @return
     */
    public List<PatientLinkEmp> findSignList(PatientLinkEmp entity);
	
}