/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.medicalofficeemp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.medicalofficeemp.dao.MedicalOfficeEmpDao;

/**
 * 医方人员机构关联信息Service
 * @author gbq
 * @version 2019-06-14
 */
@Service
@Transactional(readOnly = true)
public class MedicalOfficeEmpService extends CrudService<MedicalOfficeEmpDao, MedicalOfficeEmp> {

	public MedicalOfficeEmp get(String id) {
		return super.get(id);
	}
	
	public List<MedicalOfficeEmp> findList(MedicalOfficeEmp medicalOfficeEmp) {
		return super.findList(medicalOfficeEmp);
	}
	
	public Page<MedicalOfficeEmp> findPage(Page<MedicalOfficeEmp> page, MedicalOfficeEmp medicalOfficeEmp) {
		return super.findPage(page, medicalOfficeEmp);
	}
	
	@Transactional(readOnly = false)
	public void save(MedicalOfficeEmp medicalOfficeEmp) {
		super.save(medicalOfficeEmp);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedicalOfficeEmp medicalOfficeEmp) {
		super.delete(medicalOfficeEmp);
	}
	
}