/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sign.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.gen.entity.GenTableColumn;
import com.sayee.sxsy.modules.medicalofficeemp.dao.MedicalOfficeEmpDao;
import com.sayee.sxsy.modules.patientlinkemp.dao.PatientLinkEmpDao;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
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
	private PatientLinkEmpDao patientLinkEmpDao;

	@Autowired
	private MedicalOfficeEmpDao medicalOfficeEmpDao;
	public SignAgreement get(String id) {
		return super.get(id);
	}
	
	public List<SignAgreement> findList(SignAgreement signAgreement) {
		return super.findList(signAgreement);
	}
	
	public Page<SignAgreement> findPage(Page<SignAgreement> page, SignAgreement signAgreement) {
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
		//对患方 医方联系人 进行保存
		for (PatientLinkEmp patientLinkEmp : signAgreement.getPatientLinkEmpList()){
			if(patientLinkEmp.getId() == null){
				continue;
			}
			if(MediateRecord.DEL_FLAG_NORMAL.equals(patientLinkEmp.getDelFlag())){
				if(StringUtils.isBlank(patientLinkEmp.getPatientLinkEmpId())){
					patientLinkEmp.setRelationId(signAgreement.getSignAgreementId());
					patientLinkEmp.preInsert();
					patientLinkEmp.setPatientLinkEmpId(patientLinkEmp.getId());
					patientLinkEmp.setLinkType("1");
					patientLinkEmpDao.insert(patientLinkEmp);
				}else {
					patientLinkEmp.preUpdate();
					patientLinkEmpDao.update(patientLinkEmp);
				}
			}else{
				patientLinkEmpDao.delete(patientLinkEmp);
			}
		}
		if ("yes".equals(signAgreement.getComplaintMain().getAct().getFlag())){
			//List<Act> list = actTaskService.todoList(assessApply.getComplaintMain().getAct());
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(signAgreement.getNextLinkMan());
			var.put("approval_user",assigness.getLoginName());
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
		if (signAgreement.getMeatterList()!=null && !signAgreement.getMediationList().isEmpty()){
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
}