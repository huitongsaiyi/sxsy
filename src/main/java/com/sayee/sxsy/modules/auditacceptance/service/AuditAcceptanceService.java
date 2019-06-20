/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.auditacceptance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.mediateapplyinfo.dao.MediateApplyInfoDao;
import com.sayee.sxsy.modules.mediateapplyinfo.entity.MediateApplyInfo;
import com.sayee.sxsy.modules.mediateapplyinfo.service.MediateApplyInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.auditacceptance.dao.AuditAcceptanceDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 审核受理Service
 * @author zhangfan
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class AuditAcceptanceService extends CrudService<AuditAcceptanceDao, AuditAcceptance> {
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private MediateApplyInfoService mediateApplyInfoService;

	public AuditAcceptance get(String id) {
		AuditAcceptance auditAcceptance = super.get(id);

		return super.get(id);
	}
	
	public List<AuditAcceptance> findList(AuditAcceptance auditAcceptance) {
		//获取当前登陆用户
		auditAcceptance.setUser(UserUtils.getUser());
		return super.findList(auditAcceptance);
	}
	
	public Page<AuditAcceptance> findPage(Page<AuditAcceptance> page, AuditAcceptance auditAcceptance) {
		//获取当前登陆用户
		auditAcceptance.setUser(UserUtils.getUser());
		return super.findPage(page, auditAcceptance);
	}
	
	@Transactional(readOnly = false)
	public void save(HttpServletRequest request, AuditAcceptance auditAcceptance) {
		if(StringUtils.isBlank(auditAcceptance.getCreateBy().getId())){
			//判断主键ID是否为空
			auditAcceptance.preInsert();
			auditAcceptance.setAuditAcceptanceId(auditAcceptance.getId());
			//将主键ID设为UUID
			dao.insert(auditAcceptance);
			auditAcceptance.getMediateApplyInfo().setAuditAcceptanceId(auditAcceptance.getAuditAcceptanceId());
			auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().setAuditAcceptanceId(auditAcceptance.getAuditAcceptanceId());
			auditAcceptance.getMediateApplyInfo().setApplyType("1");
			auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().setApplyType("2");
			mediateApplyInfoService.save(auditAcceptance.getMediateApplyInfo());
			mediateApplyInfoService.save(auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo());
//			this.savefj(request, auditAcceptance);
		}else{//如果不为空进行更新
			//修改表
			auditAcceptance.preUpdate();
			auditAcceptance.getMediateApplyInfo().preUpdate();
			auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().preUpdate();
			dao.update(auditAcceptance);
			if(StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getAuditAcceptanceId()) || "".equals(auditAcceptance.getMediateApplyInfo().getAuditAcceptanceId())){
				auditAcceptance.getMediateApplyInfo().setAuditAcceptanceId(auditAcceptance.getAuditAcceptanceId());
				auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().setAuditAcceptanceId(auditAcceptance.getAuditAcceptanceId());
			}
			if(StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getApplyType()) || "".equals(auditAcceptance.getMediateApplyInfo().getApplyType())){
				auditAcceptance.getMediateApplyInfo().setApplyType("1");
				auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().setApplyType("2");
			}
			mediateApplyInfoService.save(auditAcceptance.getMediateApplyInfo());
			mediateApplyInfoService.save(auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo());
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

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request, AuditAcceptance auditAcceptance){
		String files = request.getParameter("files");
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files4");
		String files5 = request.getParameter("files5");
		String files6 = request.getParameter("files6");
		String files7 = request.getParameter("files7");
		String files8 = request.getParameter("files8");
		String files9 = request.getParameter("files9");
		String files10 = request.getParameter("files10");
		String files11 = request.getParameter("files11");
		String files12 = request.getParameter("files12");
		String files13 = request.getParameter("files13");
		String files14 = request.getParameter("files14");
		String files15 = request.getParameter("files15");
		String files16 = request.getParameter("files16");
		String files17 = request.getParameter("files17");
		String files18 = request.getParameter("files18");
		String files19 = request.getParameter("files19");
		String acceId1 = IdGen.uuid();
		String acceId2 = IdGen.uuid();
		String acceId3 = IdGen.uuid();
		String acceId4 = IdGen.uuid();
		String acceId5 = IdGen.uuid();
		String acceId6 = IdGen.uuid();
		String acceId7 = IdGen.uuid();
		String acceId8 = IdGen.uuid();
		String acceId9 = IdGen.uuid();
		String acceId10 = IdGen.uuid();
		String acceId11 = IdGen.uuid();
		String acceId12 = IdGen.uuid();
		String acceId13 = IdGen.uuid();
		String acceId14 = IdGen.uuid();
		String acceId15 = IdGen.uuid();
		String acceId16 = IdGen.uuid();
		String acceId17 = IdGen.uuid();
		String acceId18 = IdGen.uuid();
		String acceId19 = IdGen.uuid();
		String acceId20 = IdGen.uuid();
		String itemId1 = auditAcceptance.getAuditAcceptanceId();
		String itemId2 = auditAcceptance.getAuditAcceptanceId();
		String itemId3 = auditAcceptance.getAuditAcceptanceId();
		String itemId4 = auditAcceptance.getAuditAcceptanceId();
		String itemId5 = auditAcceptance.getAuditAcceptanceId();
		String itemId6 = auditAcceptance.getAuditAcceptanceId();
		String itemId7 = auditAcceptance.getAuditAcceptanceId();
		String itemId8 = auditAcceptance.getAuditAcceptanceId();
		String itemId9 = auditAcceptance.getAuditAcceptanceId();
		String itemId10 = auditAcceptance.getAuditAcceptanceId();
		String itemId11 = auditAcceptance.getAuditAcceptanceId();
		String itemId12 = auditAcceptance.getAuditAcceptanceId();
		String itemId13 = auditAcceptance.getAuditAcceptanceId();
		String itemId14 = auditAcceptance.getAuditAcceptanceId();
		String itemId15 = auditAcceptance.getAuditAcceptanceId();
		String itemId16 = auditAcceptance.getAuditAcceptanceId();
		String itemId17 = auditAcceptance.getAuditAcceptanceId();
		String itemId18= auditAcceptance.getAuditAcceptanceId();
		String itemId19 = auditAcceptance.getAuditAcceptanceId();
		String itemId20 = auditAcceptance.getAuditAcceptanceId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		String fjtype3 = request.getParameter("fjtype3");
		String fjtype4 = request.getParameter("fjtype4");
		String fjtype5 = request.getParameter("fjtype5");
		String fjtype6 = request.getParameter("fjtype6");
		String fjtype7 = request.getParameter("fjtype7");
		String fjtype8 = request.getParameter("fjtype8");
		String fjtype9 = request.getParameter("fjtype9");
		String fjtype10 = request.getParameter("fjtype10");
		String fjtype11 = request.getParameter("fjtype11");
		String fjtype12 = request.getParameter("fjtype12");
		String fjtype13 = request.getParameter("fjtype13");
		String fjtype14 = request.getParameter("fjtype14");
		String fjtype15 = request.getParameter("fjtype15");
		String fjtype16 = request.getParameter("fjtype16");
		String fjtype17 = request.getParameter("fjtype17");
		String fjtype18 = request.getParameter("fjtype18");
		String fjtype19 = request.getParameter("fjtype19");
		String fjtype20 = request.getParameter("fjtype20");
		preOperativeConsentService.save1(acceId1,itemId1,files,fjtype1);
		preOperativeConsentService.save1(acceId2,itemId2,files1,fjtype2);
		preOperativeConsentService.save1(acceId3,itemId3,files2,fjtype3);
		preOperativeConsentService.save1(acceId4,itemId4,files3,fjtype4);
		preOperativeConsentService.save1(acceId5,itemId5,files4,fjtype5);
		preOperativeConsentService.save1(acceId6,itemId6,files5,fjtype6);
		preOperativeConsentService.save1(acceId7,itemId7,files6,fjtype7);
		preOperativeConsentService.save1(acceId8,itemId8,files7,fjtype8);
		preOperativeConsentService.save1(acceId9,itemId9,files8,fjtype9);
		preOperativeConsentService.save1(acceId10,itemId10,files9,fjtype10);
		preOperativeConsentService.save1(acceId11,itemId11,files10,fjtype11);
		preOperativeConsentService.save1(acceId12,itemId12,files11,fjtype12);
		preOperativeConsentService.save1(acceId13,itemId13,files12,fjtype13);
		preOperativeConsentService.save1(acceId14,itemId14,files13,fjtype14);
		preOperativeConsentService.save1(acceId15,itemId15,files14,fjtype15);
		preOperativeConsentService.save1(acceId16,itemId16,files15,fjtype16);
		preOperativeConsentService.save1(acceId17,itemId17,files16,fjtype17);
		preOperativeConsentService.save1(acceId18,itemId18,files17,fjtype18);
		preOperativeConsentService.save1(acceId19,itemId19,files18,fjtype19);
		preOperativeConsentService.save1(acceId20,itemId20,files19,fjtype20);
	}
}