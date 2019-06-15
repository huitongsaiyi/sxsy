/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.patientlinkemp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.patientlinkemp.dao.PatientLinkEmpDao;

/**
 * 患方联系人Service
 * @author gbq
 * @version 2019-06-13
 */
@Service
@Transactional(readOnly = true)
public class PatientLinkEmpService extends CrudService<PatientLinkEmpDao, PatientLinkEmp> {

	public PatientLinkEmp get(String id) {
		return super.get(id);
	}
	
	public List<PatientLinkEmp> findList(PatientLinkEmp patientLinkEmp) {
		return super.findList(patientLinkEmp);
	}
	
	public Page<PatientLinkEmp> findPage(Page<PatientLinkEmp> page, PatientLinkEmp patientLinkEmp) {
		return super.findPage(page, patientLinkEmp);
	}
	
	@Transactional(readOnly = false)
	public void save(PatientLinkEmp patientLinkEmp) {
		super.save(patientLinkEmp);
	}
	
	@Transactional(readOnly = false)
	public void delete(PatientLinkEmp patientLinkEmp) {
		super.delete(patientLinkEmp);
	}
	
}