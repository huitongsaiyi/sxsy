/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.medicalofficeemp.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;

import java.util.List;

/**
 * 医方人员机构关联信息DAO接口
 * @author gbq
 * @version 2019-06-14
 */
@MyBatisDao
public interface MedicalOfficeEmpDao extends CrudDao<MedicalOfficeEmp> {

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     * @param entity
     * @return
     */
    public List<MedicalOfficeEmp> findSignList(MedicalOfficeEmp entity);
}