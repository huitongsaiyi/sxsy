/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.entity;

import org.hibernate.validator.constraints.Length;
import com.sayee.sxsy.modules.sys.entity.User;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 纠纷调解Entity
 * @author lyt
 * @version 2019-06-04
 */
public class ComplaintMain extends DataEntity<ComplaintMain> {
	
	private static final long serialVersionUID = 1L;
	private String complaintMainId;		// 主键
	private String caseNumber;		// 案件编号
	private String patientName;		// 患者姓名
	private String patientSex;		// 患者性别 字典
	private String patientAge;		// 患者年龄
	private String patientCard;		// 患者身份证
	private String patientMobile;		// 患方联系电话
	private String involveHospital;		// 涉及医院
	private String hospitalLevel;		// 医院级别
	private String hospitalGrade;		// 医院等级
	private String involveDepartment;		// 涉及科室
	private String involveEmployee;		// 涉及人员
	private String procInsId;		// 流程实例id
	private User createById;		// create_by
	private User updateById;		// update_by
	
	public ComplaintMain() {
		super();
	}

	public ComplaintMain(String id){
		super(id);
	}

	@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=0, max=20, message="案件编号长度必须介于 0 和 20 之间")
	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	@Length(min=0, max=20, message="患者姓名长度必须介于 0 和 20 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Length(min=0, max=1, message="患者性别 字典长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=20, message="患者身份证长度必须介于 0 和 20 之间")
	public String getPatientCard() {
		return patientCard;
	}

	public void setPatientCard(String patientCard) {
		this.patientCard = patientCard;
	}
	
	@Length(min=0, max=10, message="患方联系电话长度必须介于 0 和 10 之间")
	public String getPatientMobile() {
		return patientMobile;
	}

	public void setPatientMobile(String patientMobile) {
		this.patientMobile = patientMobile;
	}
	
	@Length(min=0, max=32, message="涉及医院长度必须介于 0 和 32 之间")
	public String getInvolveHospital() {
		return involveHospital;
	}

	public void setInvolveHospital(String involveHospital) {
		this.involveHospital = involveHospital;
	}
	
	@Length(min=0, max=10, message="医院级别长度必须介于 0 和 10 之间")
	public String getHospitalLevel() {
		return hospitalLevel;
	}

	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}
	
	@Length(min=0, max=10, message="医院等级长度必须介于 0 和 10 之间")
	public String getHospitalGrade() {
		return hospitalGrade;
	}

	public void setHospitalGrade(String hospitalGrade) {
		this.hospitalGrade = hospitalGrade;
	}
	
	@Length(min=0, max=32, message="涉及科室长度必须介于 0 和 32 之间")
	public String getInvolveDepartment() {
		return involveDepartment;
	}

	public void setInvolveDepartment(String involveDepartment) {
		this.involveDepartment = involveDepartment;
	}
	
	@Length(min=0, max=32, message="涉及人员长度必须介于 0 和 32 之间")
	public String getInvolveEmployee() {
		return involveEmployee;
	}

	public void setInvolveEmployee(String involveEmployee) {
		this.involveEmployee = involveEmployee;
	}
	
	@Length(min=0, max=32, message="流程实例id长度必须介于 0 和 32 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	public User getCreateById() {
		return createById;
	}

	public void setCreateById(User createById) {
		this.createById = createById;
	}
	
	public User getUpdateById() {
		return updateById;
	}

	public void setUpdateById(User updateById) {
		this.updateById = updateById;
	}
	
}