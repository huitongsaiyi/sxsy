/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.patientlinkemp.entity;

import org.hibernate.validator.constraints.Length;


import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 患方联系人Entity
 * @author gbq
 * @version 2019-06-13
 */
public class PatientLinkEmp extends DataEntity<PatientLinkEmp> {
	
	private static final long serialVersionUID = 1L;
	private String patientLinkEmpId;		// 主键
	private String patientLinkName;		// 姓名
	private String patientLinkSex;		// 性别
	private String patientLinkAge;		// 年龄
	private String patientLinkWorkUnit;		// 工作单位
	private String patientLinkPost;		// 职务
	private String patientLinkMobile;		// 联系方式
	private String patientRelation;		// 与患者关系
	private String idNumber;		// 身份证号
	private String patientLinkAddress;		// 住址
	private String respondentIdentity;		// 被调查人身份
	private String relationId;		// 关联主键
	private String linkType;		// 患方关联人员类型 1 患者 2 联系人(签署协议中的代理人)
	
	public PatientLinkEmp() {
		super();
	}

	public PatientLinkEmp(String id){
		super(id);
	}

	@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getPatientLinkEmpId() {
		return patientLinkEmpId;
	}

	public void setPatientLinkEmpId(String patientLinkEmpId) {
		this.patientLinkEmpId = patientLinkEmpId;
	}
	
	@Length(min=0, max=10, message="姓名长度必须介于 0 和 10 之间")
	public String getPatientLinkName() {
		return patientLinkName;
	}

	public void setPatientLinkName(String patientLinkName) {
		this.patientLinkName = patientLinkName;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getPatientLinkSex() {
		return patientLinkSex;
	}

	public void setPatientLinkSex(String patientLinkSex) {
		this.patientLinkSex = patientLinkSex;
	}
	
	@Length(min=0, max=4, message="年龄长度必须介于 0 和 4 之间")
	public String getPatientLinkAge() {
		return patientLinkAge;
	}

	public void setPatientLinkAge(String patientLinkAge) {
		this.patientLinkAge = patientLinkAge;
	}
	
	@Length(min=0, max=20, message="工作单位长度必须介于 0 和 20 之间")
	public String getPatientLinkWorkUnit() {
		return patientLinkWorkUnit;
	}

	public void setPatientLinkWorkUnit(String patientLinkWorkUnit) {
		this.patientLinkWorkUnit = patientLinkWorkUnit;
	}
	
	@Length(min=0, max=10, message="职务长度必须介于 0 和 10 之间")
	public String getPatientLinkPost() {
		return patientLinkPost;
	}

	public void setPatientLinkPost(String patientLinkPost) {
		this.patientLinkPost = patientLinkPost;
	}
	
	@Length(min=0, max=15, message="联系方式长度必须介于 0 和 15 之间")
	public String getPatientLinkMobile() {
		return patientLinkMobile;
	}

	public void setPatientLinkMobile(String patientLinkMobile) {
		this.patientLinkMobile = patientLinkMobile;
	}
	
	@Length(min=0, max=1, message="与患者关系长度必须介于 0 和 1 之间")
	public String getPatientRelation() {
		return patientRelation;
	}

	public void setPatientRelation(String patientRelation) {
		this.patientRelation = patientRelation;
	}
	
	@Length(min=0, max=20, message="身份证号长度必须介于 0 和 20 之间")
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	@Length(min=0, max=200, message="住址长度必须介于 0 和 200 之间")
	public String getPatientLinkAddress() {
		return patientLinkAddress;
	}

	public void setPatientLinkAddress(String patientLinkAddress) {
		this.patientLinkAddress = patientLinkAddress;
	}
	
	@Length(min=0, max=10, message="被调查人身份长度必须介于 0 和 10 之间")
	public String getRespondentIdentity() {
		return respondentIdentity;
	}

	public void setRespondentIdentity(String respondentIdentity) {
		this.respondentIdentity = respondentIdentity;
	}
	
	@Length(min=0, max=32, message="关联主键长度必须介于 0 和 32 之间")
	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	@Length(min=0, max=1, message="患方关联人员类型 1 患者 2 联系人(签署协议中的代理人)长度必须介于 0 和 1 之间")
	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	
}