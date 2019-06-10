/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.auditacceptance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ComplaintMainDao complaintMainDao;

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
		if(StringUtils.isBlank(auditAcceptance.getCreateBy().getId())){
			//判断主键ID是否为空
			auditAcceptance.preInsert();
			auditAcceptance.setAuditAcceptanceId(auditAcceptance.getId());
			//将主键ID设为UUID
			dao.insert(auditAcceptance);
		}else{//如果不为空进行更新
			//修改表
			auditAcceptance.preUpdate();
			dao.update(auditAcceptance);
		}
		//修改主表信息 因为处理的是  主表事由信息的  对主表信息进行修改即可
//		ComplaintMain complaintMain=reportRegistration.getComplaintMain();
//		complaintMain.preUpdate();
//		complaintMain.setComplaintMainId(reportRegistration.getComplaintMainId());
//		complaintMainDao.update(complaintMain);
//		super.save(auditAcceptance);
		if ("yes".equals(auditAcceptance.getComplaintMain().getAct().getFlag())){
			List<Act> list = actTaskService.todoList(auditAcceptance.getComplaintMain().getAct());

			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(auditAcceptance.getNextLinkMan());
			var.put("forensics_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(auditAcceptance.getComplaintMain().getAct().getTaskId(), auditAcceptance.getComplaintMain().getAct().getProcInsId(), auditAcceptance.getComplaintMain().getAct().getComment(), auditAcceptance.getComplaintMain().getCaseNumber(), var);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(AuditAcceptance auditAcceptance) {
		super.delete(auditAcceptance);
	}
	
}