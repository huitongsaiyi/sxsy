/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.mediateapplyinfo.entity;

import com.sayee.sxsy.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 调节申请Entity
 * @author lyt
 * @version 2019-06-19
 */
public class MediateApplyInfo extends DataEntity<MediateApplyInfo> {
	
	private static final long serialVersionUID = 1L;
	private String mediateApplyId;		// 调解申请主键
	private String auditAcceptanceId;		// 审核受理主键
	private String applyer;		// 申请人
	private String patientRelation;		// 与患者关系
	private String patientName;		// 患者姓名
	private String patientSex;		// 患者性别
	private String patientAge;		// 患者年龄
	private String patientMobile;		// 患方电话
	private String involveHospital;		// 涉及医院
	private String summaryOfDisputes;		// 纠纷概要
	private String applyMatter;		// 当事人申请事项
	private String applyHospital;		// 申请医院
	private String agent;		// 代理人
	private String hospitalMobile;		// 医方电话
	private String applyType;		// 申请类型 1患方2医方
	private MediateApplyInfo docMediateApplyInfo;		//医方调解申请信息
	private Office sjOffice; // 涉及医院
	private Office sqOffice; // 申请医院

	public Office getSqOffice() {
		return sqOffice;
	}

	public void setSqOffice(Office sqOffice) {
		this.sqOffice = sqOffice;
	}

	public Office getSjOffice() {
		return sjOffice;
	}

	public void setSjOffice(Office sjOffice) {
		this.sjOffice = sjOffice;
	}

	public MediateApplyInfo() {
		super();
	}

	public MediateApplyInfo(String id){
		super(id);
	}

	public MediateApplyInfo getDocMediateApplyInfo() {
		return docMediateApplyInfo;
	}

	public void setDocMediateApplyInfo(MediateApplyInfo docMediateApplyInfo) {
		this.docMediateApplyInfo = docMediateApplyInfo;
	}

	@Length(min=0, max=32, message="调解申请主键长度必须介于 0 和 32 之间")
	public String getMediateApplyId() {
		return mediateApplyId;
	}

	public void setMediateApplyId(String mediateApplyId) {
		this.mediateApplyId = mediateApplyId;
	}
	
	@Length(min=0, max=32, message="审核受理主键长度必须介于 0 和 32 之间")
	public String getAuditAcceptanceId() {
		return auditAcceptanceId;
	}

	public void setAuditAcceptanceId(String auditAcceptanceId) {
		this.auditAcceptanceId = auditAcceptanceId;
	}
	
	@Length(min=1, max=32, message="申请人长度必须介于 1 和 32 之间")
	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}
	
	@Length(min=1, max=1, message="与患者关系长度必须介于 1 和 1 之间")
	public String getPatientRelation() {
		return patientRelation;
	}

	public void setPatientRelation(String patientRelation) {
		this.patientRelation = patientRelation;
	}
	
	@Length(min=1, max=20, message="患者姓名长度必须介于 1 和 20 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Length(min=1, max=1, message="患者性别长度必须介于 1 和 1 之间")
	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	
	@Length(min=1, max=4, message="患者年龄长度必须介于 1 和 4 之间")
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	
	@Length(min=1, max=20, message="患方电话长度必须介于 1 和 20 之间")
	public String getPatientMobile() {
		return patientMobile;
	}

	public void setPatientMobile(String patientMobile) {
		this.patientMobile = patientMobile;
	}
	
	//@Length(min=1, max=32, message="涉及医院长度必须介于 1 和 32 之间")
	public String getInvolveHospital() {
		return involveHospital;
	}

	public void setInvolveHospital(String involveHospital) {
		this.involveHospital = involveHospital;
	}
	@Length(min=1, max=10000, message="投诉纠纷概要长度必须介于 1 和 10000 之间")
	public String getSummaryOfDisputes() {
		return summaryOfDisputes;
	}

	public void setSummaryOfDisputes(String summaryOfDisputes) {
		this.summaryOfDisputes = summaryOfDisputes;
	}
	@Length(min=1, max=200, message="当事人申请事项长度必须介于 1 和 200 之间")
	public String getApplyMatter() {
		return applyMatter;
	}

	public void setApplyMatter(String applyMatter) {
		this.applyMatter = applyMatter;
	}
	
	//@Length(min=1, max=32, message="申请医院长度必须介于 1 和 32 之间")
	public String getApplyHospital() {
		return applyHospital;
	}

	public void setApplyHospital(String applyHospital) {
		this.applyHospital = applyHospital;
	}
	
	@Length(min=1, max=32, message="代理人长度必须介于 1 和 32 之间")
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	@Length(min=1, max=20, message="医方电话长度必须介于 1 和 20 之间")
	public String getHospitalMobile() {
		return hospitalMobile;
	}

	public void setHospitalMobile(String hospitalMobile) {
		this.hospitalMobile = hospitalMobile;
	}
	
	@Length(min=0, max=1, message="申请类型 1患方2医方长度必须介于 0 和 1 之间")
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
}