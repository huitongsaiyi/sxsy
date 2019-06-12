/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.nestigateeividence.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.respondentinfo.entity.RespondentInfo;
import com.sayee.sxsy.modules.respondentinfo.service.RespondentInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.nestigateeividence.entity.InvestigateEvidence;
import com.sayee.sxsy.modules.nestigateeividence.dao.InvestigateEvidenceDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 调查取证Service
 * @author gbq
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class InvestigateEvidenceService extends CrudService<InvestigateEvidenceDao, InvestigateEvidence> {
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ComplaintMainDao complaintMainDao;
	@Autowired
	private RespondentInfoService respondentInfoService;
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;

	public InvestigateEvidence get(String id) {
		return super.get(id);
	}
	
	public List<InvestigateEvidence> findList(InvestigateEvidence investigateEvidence) {
		return super.findList(investigateEvidence);
	}
	
	public Page<InvestigateEvidence> findPage(Page<InvestigateEvidence> page, InvestigateEvidence investigateEvidence) {
		//获取当前登陆用户
		investigateEvidence.setUser(UserUtils.getUser());
		Page<InvestigateEvidence> a=super.findPage(page, investigateEvidence);
		return super.findPage(page, investigateEvidence);
	}
	
	@Transactional(readOnly = false)
	public void save(InvestigateEvidence investigateEvidence, HttpServletRequest request) {
		InvestigateEvidence yinvestigateEvidence=investigateEvidence.getInvestigateEvidence();
		RespondentInfo d1=investigateEvidence.getRespondentInfo();
		RespondentInfo d2=investigateEvidence.getRespondentInfo2();
		RespondentInfo d3 = investigateEvidence.getRespondentInfo3();
		RespondentInfo d4 = investigateEvidence.getRespondentInfo4();
		//从前台获取到的附件
		String files=request.getParameter("files");
		String files1=request.getParameter("filse1");
		String files3=request.getParameter("filse3");
		String files4=request.getParameter("filse4");
		if(investigateEvidence.getCreateBy()==null){
			//判断主键ID是否为空
			investigateEvidence.preInsert();
			investigateEvidence.setInvestigateEvidenceId(investigateEvidence.getId());
			//将主键设为uuid  患方信息
			 String investigateType1=request.getParameter("investigateType");
			 String investigateType2=request.getParameter("investigateType2");
			 investigateEvidence.setInvestigateType(investigateType1);
			 investigateEvidence.setComplaintMainId(investigateEvidence.getComplaintMain().getComplaintMainId());
			dao.insert(investigateEvidence);
			//保存调查人1
//			RespondentInfo d1=investigateEvidence.getRespondentInfo();
			d1.setRespondentId(IdGen.uuid());
			d1.setInvestigationEvidenceId(investigateEvidence.getInvestigateEvidenceId());
			this.saveD1(d1);
			//保存调查人2
//			RespondentInfo d2=investigateEvidence.getRespondentInfo2();
			d2.setRespondentId(IdGen.uuid());
			d2.setInvestigationEvidenceId(investigateEvidence.getInvestigateEvidenceId());
			this.saveD1(d2);
			//保存医方信息
//			InvestigateEvidence yinvestigateEvidence=investigateEvidence.getInvestigateEvidence();
			yinvestigateEvidence.setInvestigateType(investigateType2);
			yinvestigateEvidence.setComplaintMainId(investigateEvidence.getComplaintMain().getComplaintMainId());
			yinvestigateEvidence.setInvestigateEvidenceId(IdGen.uuid());
			dao.insert(yinvestigateEvidence);
			//保存调查人1
//			RespondentInfo d3 = investigateEvidence.getRespondentInfo3();
			d3.setRespondentId(IdGen.uuid());
			d3.setInvestigationEvidenceId(yinvestigateEvidence.getInvestigateEvidenceId());
			this.saveD1(d3);
			//保存调查人2
//			RespondentInfo d4 = investigateEvidence.getRespondentInfo4();
			d4.setRespondentId(IdGen.uuid());
			d4.setInvestigationEvidenceId(yinvestigateEvidence.getInvestigateEvidenceId());
			this.saveD1(d4);

			//保存附件

			//附件表的主键都设为UUID
			String acceId1 = IdGen.uuid();
			String acceId2 = IdGen.uuid();
			String acceId3 = IdGen.uuid();
			String acceId4 = IdGen.uuid();
			//itemId1关联调查取证表的主键
			String itemId1=investigateEvidence.getInvestigateEvidenceId();
			String itemId2=yinvestigateEvidence.getInvestigateEvidence().getInvestigateEvidenceId();
			//从前台获取附件类型
			String fjtype1 = request.getParameter("fjtype1");
			String fjtype2 = request.getParameter("fjtype2");
			String fjtype3 = request.getParameter("fjtype3");
			String fjtype4 = request.getParameter("fjtype4");
			//保存附件
			preOperativeConsentService.save1(acceId1, itemId1, files, fjtype1);
			preOperativeConsentService.save1(acceId2, itemId1, files1, fjtype2);
			preOperativeConsentService.save1(acceId3, itemId2, files3, fjtype3);
			preOperativeConsentService.save1(acceId4, itemId2, files4, fjtype4);
		}else{
			//如果不为空进行更新
			//修改调查取证表
			investigateEvidence.preUpdate();//更新者
			//更新患方笔录
			dao.update(investigateEvidence);

			//更新患方调查人1信息
			d1.preUpdate();
			this.updateD(d1);
			//更新患方调查人2信息
			d2.preUpdate();
			this.updateD(d2);
			//更新医方b笔录信息
			yinvestigateEvidence.preUpdate();
			dao.update(yinvestigateEvidence);
			//更新医方调查人1的信息
			d3.preUpdate();
			this.updateD(d3);
			//更新医方调查人2的信息
			d4.preUpdate();
			this.updateD(d4);
			//更新附件
			//先判断由前台传过来的file是否为"",如果是，将附件表进行物理删除，如果不是就更新
			if(files == ""){
				preOperativeConsentService.delefj(investigateEvidence.getInvestigateEvidenceId(),"3");
			}else if(files !=""){
				preOperativeConsentService.updatefj(files,investigateEvidence.getInvestigateEvidenceId(),"3");
			}
			if(files1 == ""){
				preOperativeConsentService.delefj(investigateEvidence.getInvestigateEvidenceId(),"4");
			}else if(files1 !=""){
				preOperativeConsentService.updatefj(files1,investigateEvidence.getInvestigateEvidenceId(),"4");
			}
			if (files3 == ""){
				preOperativeConsentService.delefj(yinvestigateEvidence.getInvestigateEvidence().getInvestigateEvidenceId(),"5");
			}else if (files3 != ""){
				preOperativeConsentService.updatefj(files3,yinvestigateEvidence.getInvestigateEvidence().getInvestigateEvidenceId(),"5");
			}
			if (files4 == ""){
				preOperativeConsentService.delefj(yinvestigateEvidence.getInvestigateEvidence().getInvestigateEvidenceId(),"6");
			} else if (files4 !="") {
				preOperativeConsentService.updatefj(files4,yinvestigateEvidence.getInvestigateEvidence().getInvestigateEvidenceId(),"6");
			}
		}
		//修改主表信息 因为处理的是  主表事由信息的  对主表信息进行修改即可
		ComplaintMain complaintMain =investigateEvidence.getComplaintMain();
		complaintMain.preUpdate();
		complaintMain.setComplaintMainId(investigateEvidence.getComplaintMainId());
		complaintMainDao.update(complaintMain);

		//super.save(investigateEvidence);
		if("yes".equals(investigateEvidence.getComplaintMain().getAct().getFlag())){
			//获取待办列表
			 List<Act> list = actTaskService.todoList(investigateEvidence.getComplaintMain().getAct());
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness=UserUtils.get(investigateEvidence.getNextLinkMan());
			var.put("check_user",assigness.getLoginName());
		//执行流程
			actTaskService.complete(investigateEvidence.getComplaintMain().getAct().getTaskId(),investigateEvidence.getComplaintMain().getAct().getProcInsId(),investigateEvidence.getComplaintMain().getAct().getComment(),investigateEvidence.getComplaintMain().getCaseNumber(),var);
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(InvestigateEvidence investigateEvidence) {
		super.delete(investigateEvidence);
	}

	@Transactional(readOnly = false)
	public void saveD1(RespondentInfo respondentInfo) {
         respondentInfoService.save(respondentInfo);
	}
	public void updateD(RespondentInfo respondentInfo){
		respondentInfoService.save(respondentInfo);
	}
}