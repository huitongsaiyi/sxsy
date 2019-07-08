/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sign.service;

import java.util.*;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.gen.entity.GenTableColumn;
import com.sayee.sxsy.modules.mediate.entity.MediateEvidence;
import com.sayee.sxsy.modules.medicalofficeemp.dao.MedicalOfficeEmpDao;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.dao.PatientLinkEmpDao;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.sign.entity.SignAgreement;
import com.sayee.sxsy.modules.sign.dao.SignAgreementDao;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;

/**
 * 签署协议Service
 * @author zhangfan
 * @version 2019-06-14
 */
@Service
@Transactional(readOnly = true)
public class SignAgreementService extends CrudService<SignAgreementDao, SignAgreement> {
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private PatientLinkEmpDao patientLinkEmpDao;
	@Autowired
	SignAgreementDao signAgreementDao;
	@Autowired
	private MedicalOfficeEmpDao medicalOfficeEmpDao;
	public SignAgreement get(String id) {
		SignAgreement signAgreement=super.get(id);
		//患方 明细查询
		PatientLinkEmp patientLinkEmp=new PatientLinkEmp();
		patientLinkEmp.setRelationId(signAgreement.getSignAgreementId());
		patientLinkEmp.setLinkType("1");
		signAgreement.setPatientLinkEmpList(patientLinkEmpDao.findList(patientLinkEmp));
		//患方代理人
		PatientLinkEmp patientLinkD=new PatientLinkEmp();
		patientLinkD.setRelationId(signAgreement.getSignAgreementId());
		patientLinkD.setLinkType("2");
		signAgreement.setPatientLinkDList(patientLinkEmpDao.findList(patientLinkD));
		//医方人
		MedicalOfficeEmp medicalOfficeEmp=new MedicalOfficeEmp();
		medicalOfficeEmp.setRelationId(signAgreement.getSignAgreementId());
		signAgreement.setMedicalOfficeEmpList(medicalOfficeEmpDao.findList(medicalOfficeEmp));
		return signAgreement;
	}
	
	public List<SignAgreement> findList(SignAgreement signAgreement) {
		//获取当前登陆用户
		signAgreement.setUser(UserUtils.getUser());
		return super.findList(signAgreement);
	}
	
	public Page<SignAgreement> findPage(Page<SignAgreement> page, SignAgreement signAgreement) {
		//获取当前登陆用户
		signAgreement.setUser(UserUtils.getUser());
		return super.findPage(page, signAgreement);
	}
	
	@Transactional(readOnly = false)
	public void save(HttpServletRequest request, SignAgreement signAgreement) {
		//super.save(signAgreement);
		// 保存 多选框，逗号隔开
		this.getCheck(signAgreement);
		//数据保存
		if(StringUtils.isBlank(signAgreement.getCreateBy().getId())){
			//判断主键ID是否为空
			signAgreement.preInsert();
			signAgreement.setSignAgreementId(signAgreement.getId());
			//将主键ID设为UUID
			dao.insert(signAgreement);
		}else{//如果不为空进行更新

			//修改签署协议
			signAgreement.preUpdate();
			dao.update(signAgreement);
		}
		//保存患方
		this.detail(signAgreement,"1");
		//保存患方代理人
		this.detail(signAgreement,"2");
		//保存医方
		this.doctorDetail(signAgreement);
		//保存附件
		this.savefj(signAgreement,request);
		if ("yes".equals(signAgreement.getComplaintMain().getAct().getFlag())){
			//List<Act> list = actTaskService.todoList(assessApply.getComplaintMain().getAct());
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(signAgreement.getNextLinkMan());
			var.put("fulfill_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(signAgreement.getComplaintMain().getAct().getTaskId(), signAgreement.getComplaintMain().getAct().getProcInsId(), signAgreement.getComplaintMain().getAct().getComment(), signAgreement.getComplaintMain().getCaseNumber(), var);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SignAgreement signAgreement) {
		super.delete(signAgreement);
	}


	public void getCheck(SignAgreement signAgreement){
		//调解情况 check
		StringBuffer string=new StringBuffer();
		if (signAgreement.getMediationList()!=null && !signAgreement.getMediationList().isEmpty()){
			for (TypeInfo typeInfo : signAgreement.getMediationList()){
				if (StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		signAgreement.setMediation(String.valueOf(string));
		string.setLength(0);//清空 字符串
		//协议约定事项
		if ( signAgreement.getMeatterList()!=null && !signAgreement.getMeatterList().isEmpty()){
			for (TypeInfo typeInfo : signAgreement.getMeatterList()){
				if (StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		signAgreement.setAgreedMatter(String.valueOf(string));
		string.setLength(0);//清空 字符串
		//履行协议方式
		if (signAgreement.getPerformList()!=null && !signAgreement.getPerformList().isEmpty() ){
			for (TypeInfo typeInfo : signAgreement.getPerformList()){
				if (StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		signAgreement.setPerformAgreementMode(String.valueOf(string));
		string.setLength(0);//清空 字符串
		//协议说明
		if (signAgreement.getAgreementList()!=null && !signAgreement.getAgreementList().isEmpty()){
			for (TypeInfo typeInfo : signAgreement.getAgreementList()){
				if (StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		signAgreement.setAgreementExplain(String.valueOf(string));
	}

	//保存患方联系人
	@Transactional(readOnly = false)
	public void detail(SignAgreement signAgreement,String linkType) {
		//对患方 医方联系人 进行保存
		List<PatientLinkEmp> emp=new ArrayList<PatientLinkEmp>();
		if ("1".equals(linkType)){
			emp=signAgreement.getPatientLinkEmpList();
		}else {
			emp=signAgreement.getPatientLinkDList();
		}
		for (PatientLinkEmp patientLinkEmp : emp){
			if(patientLinkEmp.getId() == null){
				continue;
			}
			if(MediateRecord.DEL_FLAG_NORMAL.equals(patientLinkEmp.getDelFlag()) || "".equals(patientLinkEmp.getDelFlag())){
				if(StringUtils.isBlank(patientLinkEmp.getPatientLinkEmpId())){
					patientLinkEmp.setRelationId(signAgreement.getSignAgreementId());
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
	public void doctorDetail(SignAgreement signAgreement) {
		//对患方 医方联系人 进行保存
		List<MedicalOfficeEmp> emp=new ArrayList<MedicalOfficeEmp>();
		if (signAgreement.getMedicalOfficeEmpList()!=null){
			emp=signAgreement.getMedicalOfficeEmpList();
		}
		for (MedicalOfficeEmp medicalOfficeEmp : emp){
//			if(medicalOfficeEmp.getId() == null){
//				continue;
//			}
			if(MediateRecord.DEL_FLAG_NORMAL.equals(medicalOfficeEmp.getDelFlag()) || "".equals(medicalOfficeEmp.getDelFlag())){
				if(StringUtils.isBlank(medicalOfficeEmp.getMedicalOfficeEmpId())){
					medicalOfficeEmp.setRelationId(signAgreement.getSignAgreementId());
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

	 //保存附件
	public void savefj(SignAgreement signAgreement,HttpServletRequest request){
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files4");
		String acceId = null;
		String itemId = signAgreement.getSignAgreementId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		String fjtype3 = request.getParameter("fjtype3");
		String fjtype4 = request.getParameter("fjtype4");
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

		if(StringUtils.isNotBlank(files3)){
			String acceId3=request.getParameter("acceId3");
			if(StringUtils.isNotBlank(acceId3)){
				preOperativeConsentService.updatefj(files3,itemId,fjtype3);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files3,fjtype3);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype3);
		}

		if(StringUtils.isNotBlank(files4)){
			String acceId4=request.getParameter("acceId4");
			if(StringUtils.isNotBlank(acceId4)){
				preOperativeConsentService.updatefj(files4,itemId,fjtype4);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files4,fjtype4);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype4);
		}
	}
	//查询编号
	public List<SignAgreement> selectAgreementNumber(SignAgreement signAgreement){
	 	List<SignAgreement> signAgreements = signAgreementDao.selectAgreementNumber(signAgreement);
	 	return signAgreements;
	}
}