/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaint.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 投诉接待Entity
 * @author zhangfan
 * @version 2019-05-27
 */
public class ComplaintInfo extends DataEntity<ComplaintInfo> {
	
	private static final long serialVersionUID = 1L;
	private String caseNumber;		// 案件编号
	private String visitorName;		// 访客姓名
	private String visitorMobile;		// 访客电话
	private String patientRelation;		// 与患者关系  字典维护
	private String patientName;		// 患者姓名
	private String patientSex;		// 患者性别
	private String patientAge;		// 患者年龄
	private String visitorNumber;		// 来访人数
	private String involveHospital;		// 涉及医院
	private String involveDepartment;		// 涉及科室
	private String complaintId;		// 主键
	private String involveEmployee;		// 涉及人员
	private String summaryOfDisputes;		// 投诉纠纷概要
	private String visitorDate;		// 来访日期
	private String complaintMode;		// 投诉方式
	private String isMajor;		// 是否重大
	private String appeal;		// 诉求
	private String receptionEmployee;		// 接待人员
	private String receptionDate;		// 接待时间
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	
	public ComplaintInfo() {
		super();
	}

	public ComplaintInfo(String id){
		super(id);
	}

	@Length(min=0, max=20, message="案件编号长度必须介于 0 和 20 之间")
	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	@Length(min=0, max=20, message="访客姓名长度必须介于 0 和 20 之间")
	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	
	@Length(min=0, max=15, message="访客电话长度必须介于 0 和 15 之间")
	public String getVisitorMobile() {
		return visitorMobile;
	}

	public void setVisitorMobile(String visitorMobile) {
		this.visitorMobile = visitorMobile;
	}
	
	@Length(min=0, max=1, message="与患者关系  字典维护长度必须介于 0 和 1 之间")
	public String getPatientRelation() {
		return patientRelation;
	}

	public void setPatientRelation(String patientRelation) {
		this.patientRelation = patientRelation;
	}
	
	@Length(min=0, max=20, message="患者姓名长度必须介于 0 和 20 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Length(min=0, max=1, message="患者性别长度必须介于 0 和 1 之间")
	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	
	@Length(min=0, max=4, message="患者年龄长度必须介于 0 和 4 之间")
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	
	@Length(min=0, max=10, message="来访人数长度必须介于 0 和 10 之间")
	public String getVisitorNumber() {
		return visitorNumber;
	}

	public void setVisitorNumber(String visitorNumber) {
		this.visitorNumber = visitorNumber;
	}
	
	@Length(min=0, max=32, message="涉及医院长度必须介于 0 和 32 之间")
	public String getInvolveHospital() {
		return involveHospital;
	}

	public void setInvolveHospital(String involveHospital) {
		this.involveHospital = involveHospital;
	}
	
	@Length(min=0, max=32, message="涉及科室长度必须介于 0 和 32 之间")
	public String getInvolveDepartment() {
		return involveDepartment;
	}

	public void setInvolveDepartment(String involveDepartment) {
		this.involveDepartment = involveDepartment;
	}
	
	@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	
	@Length(min=0, max=32, message="涉及人员长度必须介于 0 和 32 之间")
	public String getInvolveEmployee() {
		return involveEmployee;
	}

	public void setInvolveEmployee(String involveEmployee) {
		this.involveEmployee = involveEmployee;
	}
	
	public String getSummaryOfDisputes() {
		return summaryOfDisputes;
	}

	public void setSummaryOfDisputes(String summaryOfDisputes) {
		this.summaryOfDisputes = summaryOfDisputes;
	}
	
	@Length(min=0, max=10, message="来访日期长度必须介于 0 和 10 之间")
	public String getVisitorDate() {
		return visitorDate;
	}

	public void setVisitorDate(String visitorDate) {
		this.visitorDate = visitorDate;
	}
	
	@Length(min=0, max=1, message="投诉方式长度必须介于 0 和 1 之间")
	public String getComplaintMode() {
		return complaintMode;
	}

	public void setComplaintMode(String complaintMode) {
		this.complaintMode = complaintMode;
	}
	
	@Length(min=0, max=1, message="是否重大长度必须介于 0 和 1 之间")
	public String getIsMajor() {
		return isMajor;
	}

	public void setIsMajor(String isMajor) {
		this.isMajor = isMajor;
	}
	
	public String getAppeal() {
		return appeal;
	}

	public void setAppeal(String appeal) {
		this.appeal = appeal;
	}
	
	@Length(min=0, max=32, message="接待人员长度必须介于 0 和 32 之间")
	public String getReceptionEmployee() {
		return receptionEmployee;
	}

	public void setReceptionEmployee(String receptionEmployee) {
		this.receptionEmployee = receptionEmployee;
	}
	
	@Length(min=0, max=20, message="接待时间长度必须介于 0 和 20 之间")
	public String getReceptionDate() {
		return receptionDate;
	}

	public void setReceptionDate(String receptionDate) {
		this.receptionDate = receptionDate;
	}
	
	@Length(min=0, max=32, message="下一处理环节长度必须介于 0 和 32 之间")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
	
	@Length(min=0, max=32, message="下一环节处理人长度必须介于 0 和 32 之间")
	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}
	
}