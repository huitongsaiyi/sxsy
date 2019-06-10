/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.auditacceptance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.auditacceptance.dao.AuditAcceptanceDao;

/**
 * 审核受理Service
 * @author zhangfan
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class AuditAcceptanceService extends CrudService<AuditAcceptanceDao, AuditAcceptance> {

	public AuditAcceptance get(String id) {
		return super.get(id);
	}
	
	public List<AuditAcceptance> findList(AuditAcceptance auditAcceptance) {
		return super.findList(auditAcceptance);
	}
	
	public Page<AuditAcceptance> findPage(Page<AuditAcceptance> page, AuditAcceptance auditAcceptance) {
		return super.findPage(page, auditAcceptance);
	}
	
	@Transactional(readOnly = false)
	public void save(AuditAcceptance auditAcceptance) {
		super.save(auditAcceptance);
	}
	
	@Transactional(readOnly = false)
	public void delete(AuditAcceptance auditAcceptance) {
		super.delete(auditAcceptance);
	}
	
}