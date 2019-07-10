/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessappraisal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.WordExportUtil;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.medicalofficeemp.dao.MedicalOfficeEmpDao;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.dao.PatientLinkEmpDao;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.proposal.dao.ProposalDao;
import com.sayee.sxsy.modules.proposal.entity.Proposal;
import com.sayee.sxsy.modules.proposal.service.ProposalService;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.recordinfo.dao.RecordInfoDao;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;

import com.sayee.sxsy.modules.respondentinfo.service.RespondentInfoService;
import com.sayee.sxsy.modules.sign.entity.SignAgreement;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;

import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import com.sayee.sxsy.modules.typeinfo.service.TypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.assessappraisal.entity.AssessAppraisal;
import com.sayee.sxsy.modules.assessappraisal.dao.AssessAppraisalDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	private RecordInfoDao recordInfoDao;//笔录
	@Autowired
	private ProposalDao proposalDao;
	@Autowired
	private TypeInfoService typeInfoService;
	public AssessAppraisal get(String id) {
		AssessAppraisal assessAppraisal=super.get(id);
		//患方 明细查询
		PatientLinkEmp patientLinkEmp=new PatientLinkEmp();
		patientLinkEmp.setRelationId(assessAppraisal.getAssessAppraisalId());
		patientLinkEmp.setLinkType("1");
		assessAppraisal.setPatientLinkEmpList(patientLinkEmpDao.findList(patientLinkEmp));
		//患方代理人
		PatientLinkEmp patientLinkD=new PatientLinkEmp();
		patientLinkD.setRelationId(assessAppraisal.getAssessAppraisalId());
		patientLinkD.setLinkType("2");
		assessAppraisal.setPatientLinkDList(patientLinkEmpDao.findList(patientLinkD));
		//医方人
		MedicalOfficeEmp medicalOfficeEmp=new MedicalOfficeEmp();
		medicalOfficeEmp.setRelationId(assessAppraisal.getAssessAppraisalId());
		assessAppraisal.setMedicalOfficeEmpList(medicalOfficeEmpDao.findList(medicalOfficeEmp));
		return assessAppraisal;
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
//从前台获取到的附件
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files4");
		String files5 = request.getParameter("files5");
		String files6 = request.getParameter("files6");
		String files7 = request.getParameter("files7");
		//附件的主键
		String acceId = null;
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
		if(StringUtils.isBlank(assessAppraisal.getCreateBy().getId())){
			assessAppraisal.preInsert();
			assessAppraisal.setAssessAppraisalId(assessAppraisal.getId());
			assessAppraisal.setPatientAge(assessAppraisal.getComplaintMain().getPatientAge());
			assessAppraisal.setPatientName(assessAppraisal.getComplaintMain().getPatientName());
			assessAppraisal.setPatientSex(assessAppraisal.getComplaintMain().getPatientSex());
			if(StringUtils.isBlank(assessAppraisal.getPatientAge())){
				assessAppraisal.setPatientAge("0");
			}
			dao.insert(assessAppraisal);
			//保存患方笔录
			RecordInfo huanf = assessAppraisal.getRecordInfo1();
			huanf.preInsert();
			huanf.setRecordId(IdGen.uuid());
			huanf.setRelationId(assessAppraisal.getAssessAppraisalId());
			huanf.setType("1");
			huanf.setModuleType("2");
			huanf.setPatient(assessAppraisal.getComplaintMain().getPatientName());
			huanf.setDoctor(assessAppraisal.getComplaintMain().getInvolveHospital());
 			huanf.setHost(assessAppraisal.getHost());
			huanf.setNoteTaker(assessAppraisal.getClerk());
			recordInfoDao.insert(huanf);
			//保存医方笔录
			RecordInfo yif = assessAppraisal.getRecordInfo1().getYrecordInfo();
			yif.preInsert();
			yif.setRecordId(IdGen.uuid());
			yif.setRelationId(assessAppraisal.getAssessAppraisalId());
			yif.setType("2");
			yif.setModuleType("2");
			yif.setPatient(assessAppraisal.getComplaintMain().getPatientName());
			yif.setDoctor(assessAppraisal.getComplaintMain().getInvolveHospital());
			yif.setRecordAddress(assessAppraisal.getRecordInfo1().getRecordAddress());
			yif.setHost(assessAppraisal.getHost());
			yif.setNoteTaker(assessAppraisal.getClerk());
			yif.setStartTime(assessAppraisal.getRecordInfo1().getStartTime());
			yif.setEndTime(assessAppraisal.getRecordInfo1().getEndTime());
			recordInfoDao.insert(yif);
			//保存附件
			if(StringUtils.isNotBlank(files1)){
				acceId=IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files1,fjtype1);
			}else if(StringUtils.isNotBlank(files2)){
				acceId=IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files2,fjtype2);
			}else if(StringUtils.isNotBlank(files3)){
				acceId=IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files3,fjtype3);
			}else if(StringUtils.isNotBlank(files4)){
				acceId=IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files4,fjtype4);
			}else if(StringUtils.isNotBlank(files5)){
				acceId=IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files5,fjtype5);
			}else if(StringUtils.isNotBlank(files6)){
				acceId=IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files6,fjtype6);
			}else if(StringUtils.isNotBlank(files7)){
				acceId=IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files7,fjtype7);
			}

		}else{
			//修改患方笔录
			RecordInfo huanf = assessAppraisal.getRecordInfo1();
			huanf.preUpdate();
			huanf.setPatient(assessAppraisal.getComplaintMain().getPatientName());
			huanf.setDoctor(assessAppraisal.getComplaintMain().getInvolveHospital());
			huanf.setHost(assessAppraisal.getHost());
			huanf.setNoteTaker(assessAppraisal.getClerk());
			recordInfoDao.update(huanf);
			//修改医方笔录
			RecordInfo yif = assessAppraisal.getRecordInfo1().getYrecordInfo();
			yif.preUpdate();
			yif.setPatient(assessAppraisal.getComplaintMain().getPatientName());
			yif.setDoctor(assessAppraisal.getComplaintMain().getInvolveHospital());
			yif.setRecordAddress(assessAppraisal.getRecordInfo1().getRecordAddress());
			yif.setHost(assessAppraisal.getHost());
			yif.setNoteTaker(assessAppraisal.getClerk());
			yif.setStartTime(assessAppraisal.getRecordInfo1().getStartTime());
			yif.setEndTime(assessAppraisal.getRecordInfo1().getEndTime());
			recordInfoDao.update(yif);
			//更新评估鉴定主表
			assessAppraisal.preUpdate();
			assessAppraisal.setPatientAge(assessAppraisal.getComplaintMain().getPatientAge());
			assessAppraisal.setPatientName(assessAppraisal.getComplaintMain().getPatientName());
			assessAppraisal.setPatientSex(assessAppraisal.getComplaintMain().getPatientSex());
			dao.update(assessAppraisal);
			//更新附件
			if(StringUtils.isNotBlank(files1)){
				String acceIds=request.getParameter("acceId1");
				if(StringUtils.isNotBlank(acceIds)){
					preOperativeConsentService.updatefj(files1,itemId,fjtype1);
				}else{
					acceId=IdGen.uuid();
					preOperativeConsentService.save1(acceId,itemId,files1,fjtype1);
				}
			}else{
				preOperativeConsentService.delefj(itemId,fjtype1);
			}
			if(StringUtils.isNotBlank(files2)){
				String acceIds=request.getParameter("acceId2");
				if(StringUtils.isNotBlank(acceIds)){
					preOperativeConsentService.updatefj(files2,itemId,fjtype2);
				}else{
					acceId=IdGen.uuid();
					preOperativeConsentService.save1(acceId,itemId,files2,fjtype2);
				}
			}else{
				preOperativeConsentService.delefj(itemId,fjtype2);
			}
			if(StringUtils.isNotBlank(files3)){
				String acceIds=request.getParameter("acceId3");
				if(StringUtils.isNotBlank(acceIds)){
					preOperativeConsentService.updatefj(files3,itemId,fjtype3);
				}else{
					acceId=IdGen.uuid();
					preOperativeConsentService.save1(acceId,itemId,files3,fjtype3);
				}
			}else{
				preOperativeConsentService.delefj(itemId,fjtype3);
			}
			if(StringUtils.isNotBlank(files4)){
				String acceIds=request.getParameter("acceId4");
				if(StringUtils.isNotBlank(acceIds)){
					preOperativeConsentService.updatefj(files4,itemId,fjtype4);
				}else{
					acceId=IdGen.uuid();
					preOperativeConsentService.save1(acceId,itemId,files4,fjtype4);
				}
			}else{
				preOperativeConsentService.delefj(itemId,fjtype4);
			}
			if(StringUtils.isNotBlank(files5)){
				String acceIds=request.getParameter("acceId5");
				if(StringUtils.isNotBlank(acceIds)){
					preOperativeConsentService.updatefj(files5,itemId,fjtype5);
				}else{
					acceId=IdGen.uuid();
					preOperativeConsentService.save1(acceId,itemId,files5,fjtype5);
				}
			}else{
				preOperativeConsentService.delefj(itemId,fjtype5);
			}
			if(StringUtils.isNotBlank(files6)){
				String acceIds=request.getParameter("acceId6");
				if(StringUtils.isNotBlank(acceIds)){
					preOperativeConsentService.updatefj(files6,itemId,fjtype6);
				}else{
					acceId=IdGen.uuid();
					preOperativeConsentService.save1(acceId,itemId,files6,fjtype6);
				}
			}else{
				preOperativeConsentService.delefj(itemId,fjtype6);
			}
			if(StringUtils.isNotBlank(files7)){
				String acceIds=request.getParameter("acceId7");
				if(StringUtils.isNotBlank(acceIds)){
					preOperativeConsentService.updatefj(files7,itemId,fjtype7);
				}else{
					acceId=IdGen.uuid();
					preOperativeConsentService.save1(acceId,itemId,files7,fjtype7);
				}
			}else{
				preOperativeConsentService.delefj(itemId,fjtype7);
			}

		}
		//保存患方信息
		this.detail(assessAppraisal,"1");
		//保存患方代理人
		this.detail(assessAppraisal,"2");
		//保存医方
		this.doctorDetail(assessAppraisal);
		//保存意见书
		this.submissions(assessAppraisal);

		if ("yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())){

			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(assessAppraisal.getNextLinkMan());
			var.put("reach_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(assessAppraisal.getComplaintMain().getAct().getTaskId(), assessAppraisal.getComplaintMain().getAct().getProcInsId(), assessAppraisal.getComplaintMain().getAct().getComment(), assessAppraisal.getComplaintMain().getCaseNumber(), var);
		}
	}


	//保存意见书
	public void submissions(AssessAppraisal assessAppraisal){
		StringBuffer string=new StringBuffer();
		Proposal proposal=assessAppraisal.getProposal();
		//保存意见
		if(assessAppraisal.getTypeInfosList()!=null && !assessAppraisal.getTypeInfosList().isEmpty()){
			for(TypeInfo typeInfo : assessAppraisal.getTypeInfosList()){
				if(StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		proposal.setAnalysisOpinion(String.valueOf(string));
		string.setLength(0);
		//保存结论
		if(assessAppraisal.getTypeInfosList2()!=null && !assessAppraisal.getTypeInfosList2().isEmpty()){
			for (TypeInfo typeInfo : assessAppraisal.getTypeInfosList2()) {
				if(StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		proposal.setConclusion(String.valueOf(string));
		if(StringUtils.isBlank(proposal.getProposalId())){
			proposal.setProposalId(IdGen.uuid());
			proposal.setAssessAppraisalId(assessAppraisal.getAssessAppraisalId());
			proposal.preInsert();
			proposalDao.insert(proposal);
		}else{
			proposal.preUpdate();
			proposalDao.update(proposal);
		}

	}

	@Transactional(readOnly = false)
	public void delete(AssessAppraisal assessAppraisal) {
		super.delete(assessAppraisal);
	}

	//保存患方联系人
	@Transactional(readOnly = false)
	public void detail(AssessAppraisal assessAppraisal,String linkType) {
		//对患方 医方联系人 进行保存
		List<PatientLinkEmp> emp=new ArrayList<PatientLinkEmp>();
		if ("1".equals(linkType)){
			emp=assessAppraisal.getPatientLinkEmpList();
		}else {
			emp=assessAppraisal.getPatientLinkDList();
		}
		for (PatientLinkEmp patientLinkEmp : emp){
			if(patientLinkEmp.getId() == null){
				continue;
			}
			if(MediateRecord.DEL_FLAG_NORMAL.equals(patientLinkEmp.getDelFlag()) || "".equals(patientLinkEmp.getDelFlag())){
				if(StringUtils.isBlank(patientLinkEmp.getPatientLinkEmpId())){
					patientLinkEmp.setRelationId(assessAppraisal.getAssessAppraisalId());
					patientLinkEmp.preInsert();
					patientLinkEmp.setPatientLinkEmpId(patientLinkEmp.getId());
					patientLinkEmp.setLinkType(linkType);
					patientLinkEmp.setDelFlag("0");
					patientLinkEmpDao.insert(patientLinkEmp);
				}else {
					patientLinkEmp.preUpdate();
					patientLinkEmpDao.update(patientLinkEmp);
				}
			}else{
				patientLinkEmpDao.delete(patientLinkEmp);
			}
		}
	}

	//保存医方联系人
	@Transactional(readOnly = false)
	public void doctorDetail(AssessAppraisal assessAppraisal) {
		//对患方 医方联系人 进行保存
		List<MedicalOfficeEmp> emp=new ArrayList<MedicalOfficeEmp>();
		if (assessAppraisal.getMedicalOfficeEmpList()!=null){
			emp=assessAppraisal.getMedicalOfficeEmpList();
		}
		for (MedicalOfficeEmp medicalOfficeEmp : emp){
//			if(medicalOfficeEmp.getId() == null){
//				continue;
//			}
			if(MediateRecord.DEL_FLAG_NORMAL.equals(medicalOfficeEmp.getDelFlag()) || "".equals(medicalOfficeEmp.getDelFlag())){
				if(StringUtils.isBlank(medicalOfficeEmp.getMedicalOfficeEmpId())){
					medicalOfficeEmp.setRelationId(assessAppraisal.getAssessAppraisalId());
					medicalOfficeEmp.preInsert();
					medicalOfficeEmp.setMedicalOfficeEmpId(medicalOfficeEmp.getId());
					medicalOfficeEmp.setDelFlag("0");
					medicalOfficeEmpDao.insert(medicalOfficeEmp);
				}else {
					medicalOfficeEmp.preUpdate();
					medicalOfficeEmpDao.update(medicalOfficeEmp);
				}
			}else{
				medicalOfficeEmpDao.delete(medicalOfficeEmp);
			}
		}
	}
	/*
	 *对 逗号分割的数据进行  处理  然后放入list中
	 * @param
	 */
	public void label(List<TypeInfo> typeInfos,String data){
		if (StringUtils.isNotBlank(data)){//有数据进行 处理
			String[] asplit=data.split(",");
			for (TypeInfo typeInfo:typeInfos) {// 根据类型 拿到 数据
				for (String typeId : asplit) {//数据库中存着 用 逗号 隔开的数据
					if (typeId.equals(typeInfo.getTypeId())){
						typeInfo.setLabel("1");
						break;
					}
				}
			}
		}
	}
	/*
	 * 生成意见书
	 */
	public void exportWord(AssessAppraisal assessAppraisal, String export, HttpServletRequest request, HttpServletResponse response){
		WordExportUtil wordExportUtil = new WordExportUtil();
		assessAppraisal = this.get(assessAppraisal.getAssessAppraisalId());
		List<PatientLinkEmp> patientLinkEmpList = assessAppraisal.getPatientLinkEmpList();//患方
		List<PatientLinkEmp> patientLinkDList = assessAppraisal.getPatientLinkDList();//患方联系人
		List<MedicalOfficeEmp> medicalOfficeEmpList = assessAppraisal.getMedicalOfficeEmpList();//医方
		String analysisOpinion = assessAppraisal.getProposal().getAnalysisOpinion();//分析意见id
		String analysisOpinion1=analysisOpinion.substring(0,32);
		String conclusion = assessAppraisal.getProposal().getConclusion();//结论id
		String conclusion1=conclusion.substring(0,32);
		TypeInfo typeInfo = typeInfoService.get(analysisOpinion1);
		TypeInfo typeInfo1 = typeInfoService.get(conclusion1);
		//String path = request.getSession().getServletContext().getRealPath("/");
		String path = "C:\\a/";
		String modelPath = path;
		String newFileName = "无标题文件.docx";
		Map<String, Object> params = new HashMap<String, Object>();
		if("proposalDis".equals(export)){
			//患方信息
			if(patientLinkEmpList.size()!=0){
				params.put("pName",patientLinkEmpList.get(0).getPatientLinkName());
				if("1".equals(patientLinkEmpList.get(0).getPatientLinkSex())){
					params.put("pSex","男");
				}else{
					params.put("pSex","女");
				}
				params.put("idNumber",patientLinkEmpList.get(0).getIdNumber());
				params.put("pAddress",patientLinkEmpList.get(0).getPatientLinkAddress());
			}
			if(patientLinkDList.size()!=0){
				params.put("cName",patientLinkDList.get(0).getPatientLinkName());
				if("1".equals(patientLinkDList.get(0).getPatientRelation())){
					params.put("r","亲人");
				}else if("2".equals(patientLinkDList.get(0).getPatientRelation())){
					params.put("r","朋友");
				}else{
					params.put("r","代理人");
				}
				params.put("pMobile",patientLinkDList.get(0).getPatientLinkMobile());
			}
			if(medicalOfficeEmpList.size()!=0){
				params.put("hName",medicalOfficeEmpList.get(0).getMedicalOfficeName());
				params.put("agent",medicalOfficeEmpList.get(0).getMedicalOfficeAgent());
				params.put("post",medicalOfficeEmpList.get(0).getMedicalOfficePost());
				params.put("hMobile",medicalOfficeEmpList.get(0).getMedicalOfficeMobile());
			}
			//意见书编码
			params.put("code",assessAppraisal.getProposal().getProposalCode());
			//意见书类型
			if("1".equals(assessAppraisal.getApplyType())){
				params.put("applyType","医疗责任保险事故鉴定");
			}else{
				params.put("applyType","医疗纠纷技术评估");
			}
			//评估时间
			if(StringUtils.isNotBlank(assessAppraisal.getRecordInfo1().getStartTime())){
				params.put("startTime",assessAppraisal.getRecordInfo1().getStartTime());
			}else {
				params.put("startTime","");
			}
			if(StringUtils.isNotBlank(assessAppraisal.getRecordInfo1().getEndTime())){
				params.put("endTime",assessAppraisal.getRecordInfo1().getEndTime());
			}else {
				params.put("endTime","");
			}
			//诊疗概要
			if(StringUtils.isNotBlank(assessAppraisal.getProposal().getTreatmentSummary())){
				params.put("treatmentSummary",assessAppraisal.getProposal().getTreatmentSummary());
			}else{
				params.put("treatmentSummary","");
			}
			//争议要点（患方认为，医方认为）
			if(StringUtils.isNotBlank(assessAppraisal.getProposal().getPatientThink())){
				params.put("patientThink",assessAppraisal.getProposal().getPatientThink());
			}else{
				params.put("patientThink","");
			}
			if(StringUtils.isNotBlank(assessAppraisal.getProposal().getDoctorThink())){
				params.put("doctorThink",assessAppraisal.getProposal().getDoctorThink());
			}else{
				params.put("doctorThink","");
			}
			//分析意见
			if(typeInfo!=null){
				params.put("typeName",typeInfo.getTypeName());
				params.put("content",typeInfo.getContent());
			}else{
				params.put("typeName","");
				params.put("content","");
			}
			//诊断
			if(StringUtils.isNotBlank(assessAppraisal.getProposal().getDiagnosis())){
				params.put("diagnosis",assessAppraisal.getProposal().getDiagnosis());
			}else {
				params.put("diagnosis","");
			}
			//治疗
			if(StringUtils.isNotBlank(assessAppraisal.getProposal().getTreatment())){
				params.put("treatment",assessAppraisal.getProposal().getTreatment());
			}else {
				params.put("treatment","");
			}
			//其他
			if(StringUtils.isNotBlank(assessAppraisal.getProposal().getOther())){
				params.put("other",assessAppraisal.getProposal().getOther());
			}else{
				params.put("other","");
			}
			//结论
			if(typeInfo1!=null){
				params.put("jTypeName",typeInfo1.getTypeName());
				params.put("jContent",typeInfo1.getContent());
			}else{
				params.put("jTypeName","");
				params.put("jContent","");
			}


			path += "/submissions.docx";  //模板文件位置
			modelPath += "/submissions.docx";
			newFileName = "意见书.docx";
		}
		try{
			List<String[]> testList = new ArrayList<String[]>();
			String fileName= new String(newFileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
			wordExportUtil.getWord(path,modelPath,"",params,testList,fileName,response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}