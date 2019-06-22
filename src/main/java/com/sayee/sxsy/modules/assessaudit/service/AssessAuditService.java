/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessaudit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
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
	@Autowired
	private ActTaskService actTaskService;

	public AssessAudit get(String id) {
		return super.get(id);
	}
	
	public List<AssessAudit> findList(AssessAudit assessAudit) {
		assessAudit.setUser(UserUtils.getUser());
		return super.findList(assessAudit);
	}
	
	public Page<AssessAudit> findPage(Page<AssessAudit> page, AssessAudit assessAudit) {
		assessAudit.setUser(UserUtils.getUser());
		return super.findPage(page, assessAudit);
	}
	
	@Transactional(readOnly = false)
	public void save(AssessAudit assessAudit,HttpServletRequest request) {
		if(StringUtils.isBlank(assessAudit.getCreateBy().getId())){
			//判断是否为空
			assessAudit.preInsert();
			assessAudit.setAssessAuditId(assessAudit.getId());		//将主键设为UUID
			dao.insert(assessAudit);
		}else{//如果不为空进行更新
			assessAudit.preUpdate();
			dao.update(assessAudit);
		}
		if ("yes".equals(assessAudit.getComplaintMain().getAct().getFlag())){
			//List<Act> list = actTaskService.todoList(assessApply.getComplaintMain().getAct());
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(assessAudit.getNextLinkMan());
			var.put("evaluation_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(assessAudit.getComplaintMain().getAct().getTaskId(), assessAudit.getComplaintMain().getAct().getProcInsId(), assessAudit.getComplaintMain().getAct().getComment(), assessAudit.getComplaintMain().getCaseNumber(), var);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(AssessAudit assessAudit) {
		super.delete(assessAudit);
	}

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request,AssessAudit assessAudit){
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		String acceId = null;
		String itemId = assessAudit.getAssessAuditId();
		if(StringUtils.isNotBlank(files1)){
			String acceId1=request.getParameter("acceId1");
			if(StringUtils.isNotBlank(acceId1)){
				preOperativeConsentService.updatefj(files1,itemId,fjtype1);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files1,fjtype1);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype1);
		}
		if(StringUtils.isNotBlank(files2)){
			String acceId2=request.getParameter("acceId2");
			if(StringUtils.isNotBlank(acceId2)){
				preOperativeConsentService.updatefj(files2,itemId,fjtype2);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files2,fjtype2);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype2);
		}
	}
}