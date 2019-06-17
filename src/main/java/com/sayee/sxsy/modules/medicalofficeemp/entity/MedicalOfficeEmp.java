/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.medicalofficeemp.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 医方人员机构关联信息Entity
 * @author gbq
 * @version 2019-06-14
 */
public class MedicalOfficeEmp extends DataEntity<MedicalOfficeEmp> {
	
	private static final long serialVersionUID = 1L;
	private String medicalOfficeEmpId;		// 主键
	private String medicalOfficeName;		// 医疗机构名称
	private String medicalOfficeAddress;		// 地址
	private String legalRepresentative;		// 法定代表人
	private String medicalOfficePost;		// 职务
	private String medicalOfficeAgent;		// 委托代理人
	private String medicalOfficeSex;		// 性别
	private String medicalOfficeIdcard;		// 身份证号码
	private String medicalOfficeCompany;		// 单位及职务
	private String relationId;		// 关联主键
	private String medicalOfficeMobile;		// 联系方式
	
	public MedicalOfficeEmp() {
		super();
	}

	public MedicalOfficeEmp(String id){
		super(id);
	}

	@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getMedicalOfficeEmpId() {
		return medicalOfficeEmpId;
	}

	public void setMedicalOfficeEmpId(String medicalOfficeEmpId) {
		this.medicalOfficeEmpId = medicalOfficeEmpId;
	}
	
	@Length(min=0, max=100, message="医疗机构名称长度必须介于 0 和 100 之间")
	public String getMedicalOfficeName() {
		return medicalOfficeName;
	}

	public void setMedicalOfficeName(String medicalOfficeName) {
		this.medicalOfficeName = medicalOfficeName;
	}
	
	@Length(min=0, max=100, message="地址长度必须介于 0 和 100 之间")
	public String getMedicalOfficeAddress() {
		return medicalOfficeAddress;
	}

	public void setMedicalOfficeAddress(String medicalOfficeAddress) {
		this.medicalOfficeAddress = medicalOfficeAddress;
	}
	
	@Length(min=0, max=20, message="法定代表人长度必须介于 0 和 20 之间")
	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	
	@Length(min=0, max=20, message="职务长度必须介于 0 和 20 之间")
	public String getMedicalOfficePost() {
		return medicalOfficePost;
	}

	public void setMedicalOfficePost(String medicalOfficePost) {
		this.medicalOfficePost = medicalOfficePost;
	}
	
	@Length(min=0, max=32, message="委托代理人长度必须介于 0 和 32 之间")
	public String getMedicalOfficeAgent() {
		return medicalOfficeAgent;
	}

	public void setMedicalOfficeAgent(String medicalOfficeAgent) {
		this.medicalOfficeAgent = medicalOfficeAgent;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getMedicalOfficeSex() {
		return medicalOfficeSex;
	}

	public void setMedicalOfficeSex(String medicalOfficeSex) {
		this.medicalOfficeSex = medicalOfficeSex;
	}
	
	@Length(min=0, max=20, message="身份证号码长度必须介于 0 和 20 之间")
	public String getMedicalOfficeIdcard() {
		return medicalOfficeIdcard;
	}

	public void setMedicalOfficeIdcard(String medicalOfficeIdcard) {
		this.medicalOfficeIdcard = medicalOfficeIdcard;
	}
	
	@Length(min=0, max=200, message="单位及职务长度必须介于 0 和 200 之间")
	public String getMedicalOfficeCompany() {
		return medicalOfficeCompany;
	}

	public void setMedicalOfficeCompany(String medicalOfficeCompany) {
		this.medicalOfficeCompany = medicalOfficeCompany;
	}
	
	@Length(min=0, max=32, message="关联主键长度必须介于 0 和 32 之间")
	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	@Length(min=0, max=15, message="联系方式长度必须介于 0 和 15 之间")
	public String getMedicalOfficeMobile() {
		return medicalOfficeMobile;
	}

	public void setMedicalOfficeMobile(String medicalOfficeMobile) {
		this.medicalOfficeMobile = medicalOfficeMobile;
	}
	
}