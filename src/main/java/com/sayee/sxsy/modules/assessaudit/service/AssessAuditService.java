/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessaudit.service;

import java.util.List;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.assessaudit.entity.AssessAudit;
import com.sayee.sxsy.modules.assessaudit.dao.AssessAuditDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 评估鉴定审批Service
 * @author lyt
 * @version 2019-06-13
 */
@Service
@Transactional(readOnly = true)
public class AssessAuditService extends CrudService<AssessAuditDao, AssessAudit> {

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;

	public AssessAudit get(String id) {
		return super.get(id);
	}
	
	public List<AssessAudit> findList(AssessAudit assessAudit) {
		return super.findList(assessAudit);
	}
	
	public Page<AssessAudit> findPage(Page<AssessAudit> page, AssessAudit assessAudit) {
		return super.findPage(page, assessAudit);
	}
	
	@Transactional(readOnly = false)
	public void save(AssessAudit assessAudit) {
		if(StringUtils.isBlank(assessAudit.getAssessAuditId())){	//判断主键ID是否为空
			assessAudit.preInsert();
			assessAudit.setAssessAuditId(assessAudit.getId());		//将主键设为UUID
			dao.insert(assessAudit);
		}else{
			assessAudit.preUpdate();
			dao.update(assessAudit);
		}
//		super.save(assessAudit);
	}
	
	@Transactional(readOnly = false)
	public void delete(AssessAudit assessAudit) {
		super.delete(assessAudit);
	}

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request,AssessAudit assessAudit){
		String files = request.getParameter("files");
		String files1 = request.getParameter("files1");
		String acceId1 = IdGen.uuid();
		String acceId2 = IdGen.uuid();
		String itemId1 = assessAudit.getAssessAuditId();
		String itemId2 = assessAudit.getAssessAuditId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		preOperativeConsentService.save1(acceId1,itemId1,files,fjtype1);
		preOperativeConsentService.save1(acceId2,itemId2,files1,fjtype2);
	}
}