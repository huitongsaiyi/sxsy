/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessappraisal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.medicalofficeemp.dao.MedicalOfficeEmpDao;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.dao.PatientLinkEmpDao;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.proposal.entity.Proposal;
import com.sayee.sxsy.modules.proposal.service.ProposalService;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.recordinfo.service.RecordInfoService;
import com.sayee.sxsy.modules.respondentinfo.service.RespondentInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;

import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.assessappraisal.entity.AssessAppraisal;
import com.sayee.sxsy.modules.assessappraisal.dao.AssessAppraisalDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 评估鉴定Service
 * @author gbq
 * @version 2019-06-13
 */
@Service
@Transactional(readOnly = true)
public class AssessAppraisalService extends CrudService<AssessAppraisalDao, AssessAppraisal> {
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private MedicalOfficeEmpDao medicalOfficeEmpDao;//医方信息Dao
	@Autowired
	private PatientLinkEmpDao patientLinkEmpDao;//患方联系人，患方信息Dao
	@Autowired
	private RespondentInfoService respondentInfoService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private RecordInfoService recordInfoService;//笔录业务层
	@Autowired
	private ProposalService proposalService;
	public AssessAppraisal get(String id) {
  //患方信息
		AssessAppraisal assessAppraisal=super.get(id);
		assessAppraisal.setPatientLinkEmps1(patientLinkEmpDao.findList(new PatientLinkEmp(assessAppraisal.getAssessAppraisalId())));
		return super.get(id);
	}
	public AssessAppraisal gety(String id){
		//医方信息
		AssessAppraisal assessAppraisal=super.get(id);
		assessAppraisal.setMedicalofficeempList2(medicalOfficeEmpDao.findList(new MedicalOfficeEmp(assessAppraisal.getAssessAppraisalId())));
		return super.get(id);
	}
	public AssessAppraisal getLianXi(String id){
		//联系人信息
		AssessAppraisal assessAppraisal=super.get(id);
		assessAppraisal.setPatientLinkEmps(patientLinkEmpDao.findList(new PatientLinkEmp(assessAppraisal.getAssessAppraisalId())));
		return super.get(id);
	}
	public List<AssessAppraisal> findList(AssessAppraisal assessAppraisal)
	{
		//获取当前登陆用户
		assessAppraisal.setUser(UserUtils.getUser());
		return super.findList(assessAppraisal);
	}
	
	public Page<AssessAppraisal> findPage(Page<AssessAppraisal> page, AssessAppraisal assessAppraisal) {
		//获取当前登陆用户
		assessAppraisal.setUser(UserUtils.getUser());
		return super.findPage(page, assessAppraisal);
	}
	
	@Transactional(readOnly = false)
	public void save(AssessAppraisal assessAppraisal, HttpServletRequest request) {
		//笔录实体类
		RecordInfo huanf = assessAppraisal.getRecordInfo1();
		RecordInfo yif = assessAppraisal.getRecordInfo2();
		//患方关联人员类型 1 患者 2 联系人
		String linkType1 = request.getParameter("linkType1");
		String linkType2 = request.getParameter("linkType2");
		if(StringUtils.isBlank(assessAppraisal.getCreateBy().getId())){
			assessAppraisal.preInsert();
			assessAppraisal.setAssessAppraisalId(assessAppraisal.getId());
			dao.insert(assessAppraisal);
		}else{
			//更新评估鉴定主表
			assessAppraisal.preUpdate();
			dao.update(assessAppraisal);

		}
		//保存患方笔录
		huanf.preInsert();
		huanf.setRecordId(huanf.getId());
		huanf.setRelationId(assessAppraisal.getAssessAppraisalId());
		huanf.setType("1");
		recordInfoService.save(huanf);

		//保存医方笔录
		yif.preInsert();
		yif.setRecordId(yif.getId());
		yif.setRelationId(assessAppraisal.getAssessAppraisalId());
		yif.setType("2");
		recordInfoService.save(yif);
		//保存附件
		this.fj(assessAppraisal,request);
		//保存患方信息
        for(PatientLinkEmp patientLinkEmp: assessAppraisal.getPatientLinkEmps()){
            if (patientLinkEmp.getId() == null){
                continue;
            }
            if(patientLinkEmp.DEL_FLAG_NORMAL.equals(patientLinkEmp.getDelFlag())){
                if(StringUtils.isBlank(patientLinkEmp.getPatientLinkEmpId())){
                    patientLinkEmp.preInsert();
                    patientLinkEmp.setRelationId(assessAppraisal.getAssessAppraisalId());
                    patientLinkEmp.setPatientLinkEmpId(patientLinkEmp.getId());
                    patientLinkEmp.setLinkType(linkType1);
                    patientLinkEmpDao.insert(patientLinkEmp);
                }else{
                    patientLinkEmp.preUpdate();
                    patientLinkEmpDao.update(patientLinkEmp);
                }
            }else{
                patientLinkEmpDao.delete(patientLinkEmp);
            }
        }
        //保存联系人信息
        for (PatientLinkEmp patientLinkEmp1n1: assessAppraisal.getPatientLinkEmps1()) {
            if(patientLinkEmp1n1.getId() == null){
                continue;
            }
            if (patientLinkEmp1n1.DEL_FLAG_NORMAL.equals(patientLinkEmp1n1.getDelFlag())){
                if(StringUtils.isBlank(patientLinkEmp1n1.getPatientLinkEmpId())){
                    patientLinkEmp1n1.preInsert();
                    patientLinkEmp1n1.setRelationId(assessAppraisal.getAssessAppraisalId());
                    patientLinkEmp1n1.setPatientLinkEmpId(patientLinkEmp1n1.getId());
                    patientLinkEmp1n1.setLinkType(linkType2);
                    patientLinkEmpDao.insert(patientLinkEmp1n1);
                }else{
                    patientLinkEmp1n1.preUpdate();
                    patientLinkEmpDao.update(patientLinkEmp1n1);
                }
            }else{
                patientLinkEmpDao.delete(patientLinkEmp1n1);
            }
        }
        //保存医方联系人
        for (MedicalOfficeEmp medicalOfficeEmp:assessAppraisal.getMedicalofficeempList2()) {
            if(medicalOfficeEmp.getId() == null){
                continue;
            }
            if(medicalOfficeEmp.DEL_FLAG_NORMAL.equals(medicalOfficeEmp.getDelFlag())){
                if(StringUtils.isBlank(medicalOfficeEmp.getMedicalOfficeEmpId())){
                    medicalOfficeEmp.preInsert();
                    medicalOfficeEmp.setMedicalOfficeEmpId(medicalOfficeEmp.getId());
                    medicalOfficeEmp.setRelationId(assessAppraisal.getAssessAppraisalId());
                    medicalOfficeEmpDao.insert(medicalOfficeEmp);
                }else{
                    medicalOfficeEmp.preUpdate();
                    medicalOfficeEmpDao.update(medicalOfficeEmp);
                }
            }else{
                medicalOfficeEmpDao.delete(medicalOfficeEmp);
            }
        }
        //保存意见书
		     this.submissions(assessAppraisal);

//		if ("yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())){
//
//			Map<String,Object> var=new HashMap<String, Object>();
//			var.put("pass","0");
//			User assigness= UserUtils.get(assessAppraisal.getNextLinkMan());
//			var.put("apply_user",assigness.getLoginName());
//			// 执行流程
//			actTaskService.complete(assessAppraisal.getComplaintMain().getAct().getTaskId(), assessAppraisal.getComplaintMain().getAct().getProcInsId(), assessAppraisal.getComplaintMain().getAct().getComment(), assessAppraisal.getComplaintMain().getCaseNumber(), var);
//
//
//		}
	}


     //保存意见书
	public void submissions(AssessAppraisal assessAppraisal){
		StringBuffer string=new StringBuffer();
		Proposal proposal=assessAppraisal.getProposal();
		proposal.setProposalId(IdGen.uuid());
		//保存意见
		if(proposal.getTypeInfosList()!=null && !proposal.getTypeInfosList().isEmpty()){
			for(TypeInfo typeInfo : proposal.getTypeInfosList()){
				if(StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		proposal.setAnalysisOpinion(String.valueOf(string));
		string.setLength(0);
		//保存结论
		if(proposal.getTypeInfosList2()!=null && !proposal.getTypeInfosList2().isEmpty()){
			for (TypeInfo typeInfo : proposal.getTypeInfosList2()) {
				if(StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		proposal.setConclusion(String.valueOf(string));
		proposalService.save(proposal);
	}
//保存附件
	public void fj(AssessAppraisal assessAppraisal,HttpServletRequest request){
	//从前台获取到的附件
	String files1 = request.getParameter("files1");
	String files2 = request.getParameter("files2");
	String files3 = request.getParameter("files3");
	String files4 = request.getParameter("files4");
	String files5 = request.getParameter("files5");
	String files6 = request.getParameter("files6");
	String files7 = request.getParameter("files7");
	//附件的主键
	String acceId1 = IdGen.uuid();
	String acceId2 = IdGen.uuid();
	String acceId3 = IdGen.uuid();
	String acceId4 = IdGen.uuid();
	String acceId5 = IdGen.uuid();
	String acceId6 = IdGen.uuid();
	String acceId7 = IdGen.uuid();
	//从前台获取到的附件类型
	String fjtype1 = request.getParameter("fjtype1");
	String fjtype2 = request.getParameter("fjtype2");
	String fjtype3 = request.getParameter("fjtype3");
	String fjtype4 = request.getParameter("fjtype4");
	String fjtype5 = request.getParameter("fjtype5");
	String fjtype6 = request.getParameter("fjtype6");
	String fjtype7 = request.getParameter("fjtype7");
	//itemId1关联评估鉴定表的主键
	String itemId = assessAppraisal.getAssessAppraisalId();
//保存附件
	if(StringUtils.isNotBlank(files1)){
		preOperativeConsentService.save1(acceId1,itemId,files1,fjtype1);
	}
	if(StringUtils.isNotBlank(files2)){
		preOperativeConsentService.save1(acceId2,itemId,files2,fjtype2);
	}
	if(StringUtils.isNotBlank(files3)){
		preOperativeConsentService.save1(acceId3,itemId,files3,fjtype3);
	}
	if(StringUtils.isNotBlank(files4)){
		preOperativeConsentService.save1(acceId4,itemId,files4,fjtype4);
	}
	if(StringUtils.isNotBlank(files5)){
		preOperativeConsentService.save1(acceId5,itemId,files5,fjtype5);
	}
	if(StringUtils.isNotBlank(files6)){
		preOperativeConsentService.save1(acceId6,itemId,files6,fjtype6);
	}
	if (StringUtils.isNotBlank(files7)){
		preOperativeConsentService.save1(acceId7,itemId,files7,fjtype7);
	}
	//更新附件
	if(StringUtils.isBlank(files1)){
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"16");
	}else{
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"16");
		preOperativeConsentService.save1(acceId1,itemId,files1,fjtype1);
	}
	if(StringUtils.isBlank(files2)){
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"17");
	}else{
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"17");
		preOperativeConsentService.save1(acceId2,itemId,files2,fjtype2);
	}
	if(StringUtils.isBlank(files3)){
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"18");
	}else{
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"18");
		preOperativeConsentService.save1(acceId3,acceId3,files3,fjtype3);
	}
	if(StringUtils.isBlank(files4)){
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"19");
	}else{
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"19");
		preOperativeConsentService.save1(acceId4,itemId,files4,fjtype4);
	}
	if(StringUtils.isBlank(files5)){
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"20");
	}else{
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"20");
		preOperativeConsentService.save1(acceId5,itemId,files5,fjtype5);
	}
	if(StringUtils.isBlank(files6)){
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"21");
	}else{
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"21");
		preOperativeConsentService.save1(acceId6,itemId,files6,fjtype6);
	}
	if(StringUtils.isBlank(files7)){
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"22");
	}else{
		preOperativeConsentService.delefj(assessAppraisal.getAssessAppraisalId(),"22");
		preOperativeConsentService.save1(acceId7,itemId,files7,fjtype7);
	}
}
	@Transactional(readOnly = false)
	public void delete(AssessAppraisal assessAppraisal) {
		super.delete(assessAppraisal);
	}
	
}