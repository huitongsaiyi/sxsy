/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.nestigateeividence.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.respondentinfo.dao.RespondentInfoDao;
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
	@Autowired
	private RespondentInfoDao respondentInfoDao;

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
	public void  save(InvestigateEvidence investigateEvidence, HttpServletRequest request) {
		//医方调查笔录实体类
		InvestigateEvidence yinvestigateEvidence=investigateEvidence.getInvestigateEvidence();
		//调查人实体类
		RespondentInfo d1=investigateEvidence.getRespondentInfo();
		RespondentInfo d2=investigateEvidence.getRespondentInfo2();
		RespondentInfo d3 = investigateEvidence.getRespondentInfo3();
		RespondentInfo d4 = investigateEvidence.getRespondentInfo4();
		//从前台获取到的附件
		String files=request.getParameter("files");
		String files1=request.getParameter("filse1");
		String files3=request.getParameter("filse3");
		String files4=request.getParameter("filse4");
		//附件的主键
        String acceId1 = IdGen.uuid();
        String acceId2 = IdGen.uuid();
        String acceId3 = IdGen.uuid();
        String acceId4 = IdGen.uuid();
        //从前台获取附件类型
        String fjtype4 = request.getParameter("fjtype4");
        String fjtype3 = request.getParameter("fjtype3");
        String fjtype2 = request.getParameter("fjtype2");
        String fjtype1 = request.getParameter("fjtype1");
        //itemId1关联调查取证表的主键
        String itemId1=investigateEvidence.getInvestigateEvidenceId();
        String itemId2=yinvestigateEvidence.getInvestigateEvidenceId();
		if(StringUtils.isBlank(investigateEvidence.getCreateBy().getId())){
			//判断主键ID是否为空
			investigateEvidence.preInsert();
			investigateEvidence.setInvestigateEvidenceId(investigateEvidence.getId());
			//将主键设为uuid  患方信息
			 String investigateType1=request.getParameter("investigateType");
			 String investigateType2=request.getParameter("investigateType2");
			 investigateEvidence.setInvestigateType(investigateType1);
			 //investigateEvidence.setComplaintMainId(investigateEvidence.getComplaintMain().getComplaintMainId());
			 dao.insert(investigateEvidence);
			//保存调查人1
			this.respondent(d1,investigateEvidence.getInvestigateEvidenceId());
			//保存调查人2
			this.respondent(d2,investigateEvidence.getInvestigateEvidenceId());

			//保存医方信息
//			InvestigateEvidence yinvestigateEvidence=investigateEvidence.getInvestigateEvidence();
			yinvestigateEvidence.setInvestigateType(investigateType2);
			yinvestigateEvidence.setComplaintMainId(investigateEvidence.getComplaintMainId());
			yinvestigateEvidence.setInvestigateEvidenceId(IdGen.uuid());
			dao.insert(yinvestigateEvidence);
			//保存调查人1
//			RespondentInfo d3 = investigateEvidence.getRespondentInfo3();
			this.respondent(d3,investigateEvidence.getInvestigateEvidenceId());
			//保存调查人2
//			RespondentInfo d4 = investigateEvidence.getRespondentInfo4();
			this.respondent(d4,investigateEvidence.getInvestigateEvidenceId());

			//保存附件

			//附件表的主键都设为UUID

			if (StringUtils.isNotBlank(files)){

				preOperativeConsentService.save1(acceId1, itemId1, files, fjtype1);
			}
			if (StringUtils.isNotBlank(files1)){

				preOperativeConsentService.save1(acceId2, itemId1, files1, fjtype2);
			}
			if (StringUtils.isNotBlank(files3)){

				preOperativeConsentService.save1(acceId3, itemId2, files3, fjtype3);
			}
			if (StringUtils.isNotBlank(files3)){

				//保存附件
				preOperativeConsentService.save1(acceId4, itemId2, files4, fjtype4);
			}

		}else{
			//如果不为空进行更新
			//修改调查取证表
			investigateEvidence.preUpdate();//更新者
			//更新患方笔录
			dao.update(investigateEvidence);

			//更新患方调查人1信息
			this.respondent(d1,investigateEvidence.getInvestigateEvidenceId());

			//更新患方调查人2信息
			this.respondent(d2,investigateEvidence.getInvestigateEvidenceId());
			//更新医方b笔录信息
			yinvestigateEvidence.preUpdate();
			dao.update(yinvestigateEvidence);
			//更新医方调查人1的信息
			d3.preUpdate();
			this.respondent(d3,yinvestigateEvidence.getInvestigateEvidenceId());
			//更新医方调查人2的信息
			d4.preUpdate();
			this.respondent(d4,yinvestigateEvidence.getInvestigateEvidenceId());
			//更新附件
			//先判断由前台传过来的file是否为"",如果是，将附件表进行物理删除，如果不是就更新
			if(StringUtils.isBlank(files)){
				preOperativeConsentService.delefj(investigateEvidence.getInvestigateEvidenceId(),"3");
			}else {
                preOperativeConsentService.delefj(investigateEvidence.getInvestigateEvidenceId(),"3");
                preOperativeConsentService.save1(acceId1, itemId1, files, fjtype1);

			}
			if(StringUtils.isBlank(files1)){
				preOperativeConsentService.delefj(investigateEvidence.getInvestigateEvidenceId(),"4");
			}else{
                preOperativeConsentService.delefj(investigateEvidence.getInvestigateEvidenceId(),"4");
                preOperativeConsentService.save1(acceId2, itemId1, files1, fjtype2);
			}
			if (StringUtils.isBlank(files3)){
				preOperativeConsentService.delefj(yinvestigateEvidence.getInvestigateEvidenceId(),"5");
			}else{
                preOperativeConsentService.delefj(yinvestigateEvidence.getInvestigateEvidenceId(),"5");
                preOperativeConsentService.save1(acceId3, itemId2, files3, fjtype3);
			}
			if (StringUtils.isBlank(files4)){
				preOperativeConsentService.delefj(yinvestigateEvidence.getInvestigateEvidenceId(),"6");
			} else {
                preOperativeConsentService.delefj(yinvestigateEvidence.getInvestigateEvidenceId(),"6");
                preOperativeConsentService.save1(acceId4, itemId2, files4, fjtype4);
			}
		}
		//修改主表信息 因为处理的是  主表事由信息的  对主表信息进行修改即可

//		ComplaintMain complaintMain =investigateEvidence.getComplaintMain();
////		complaintMain.preUpdate();
////		complaintMain.setComplaintMainId(investigateEvidence.getComplaintMainId());
////		complaintMainDao.update(complaintMain);

		//super.save(investigateEvidence);
		if("yes".equals(investigateEvidence.getComplaintMain().getAct().getFlag())){
			//获取待办列表
			 List<Act> list = actTaskService.todoList(investigateEvidence.getComplaintMain().getAct());
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness=UserUtils.get(investigateEvidence.getNextLinkMan());
			var.put("mediation_user",assigness.getLoginName());
		//执行流程
			actTaskService.complete(investigateEvidence.getComplaintMain().getAct().getTaskId(),investigateEvidence.getComplaintMain().getAct().getProcInsId(),investigateEvidence.getComplaintMain().getAct().getComment(),investigateEvidence.getComplaintMain().getCaseNumber(),var);
		}


    }
	
	@Transactional(readOnly = false)
	public void delete(InvestigateEvidence investigateEvidence) {
		super.delete(investigateEvidence);
	}
	//对调查人信息进行处理
	@Transactional(readOnly = false)
	public void respondent(RespondentInfo respondentInfo,String relation) {
		//对调查人信息进程处理，如果有主键 说明是 进行修改方法； 如果没有主键 在看看有没有保存的年龄等信息，，如果有则insert，否则不处理
		if (StringUtils.isBlank(respondentInfo.getRespondentId())){//主键不空  则进行修改
			respondentInfo.setRespondentId(IdGen.uuid());
		}
		respondentInfo.setInvestigationEvidenceId(relation);
		if(StringUtils.isNotBlank(respondentInfo.getRespondentAge())){
			respondentInfoService.save(respondentInfo);
		}
	}
	//查询调查人
	public void respondent(InvestigateEvidence investigateEvidence){
		//遍历数据 拿到患方主键 查找调查人
		List<RespondentInfo> respondentInfo=respondentInfoDao.getL(investigateEvidence.getInvestigateEvidenceId());
		for (RespondentInfo r: respondentInfo) {
			if (investigateEvidence.getRespondentInfo()==null){
				investigateEvidence.setRespondentInfo(r);
			}else {
				investigateEvidence.setRespondentInfo2(r);
			}
		}
		//查找医方调查人
		List<RespondentInfo> YrespondentInfo=respondentInfoDao.getL(investigateEvidence.getInvestigateEvidence().getInvestigateEvidenceId());
		for (RespondentInfo yf: YrespondentInfo) {
			if (investigateEvidence.getRespondentInfo3()==null){
				investigateEvidence.setRespondentInfo3(yf);
			}else {
				investigateEvidence.setRespondentInfo4(yf);
			}
		}
		//return  investigateEvidence;
	}
}