/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.surgicalconsentbook.entity;

import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;


import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 术前同意书见证管理Entity
 * @author gbq
 * @version 2019-05-31
 */
public class PreOperativeConsent extends DataEntity<PreOperativeConsent> {
	
	private static final long serialVersionUID = 1L;
	private String id;						//主键id
	private String surgicalConsentId;		// 手术同意书编号
	private String operationType;		// 手术专业
	private Date witnessTime;		// 见证时间
	private String witnessLocations;		// 见证地点
	private String hospital;		// 见证医院
	private String department;		// 科室
	private String otherDoctors;		// 其他医方人员
	private String patient;		// 患者
	private String operationClient;
	private String operationName;
	private Date operationDate;
	private String postoperativeVisit;
	private String isDangerous;
	private String compensationAmount;
	private String affectedParty;		// 其他患方家属
	private String medicalSide;		// 主管医生
	private String insured;		// 是否投保(1.是，2.否)
	private String policyNo;		// 保单号
	private String witness;		// 见证人
	private String recordMan;		// 记录人
	private String witnessContent;		// 见证内容
	private String officeName;		// 医院
	private String departmentName;		// 科室
	private String witnessName;		// 科室
	private String recordManName;		// 科室
//	private String files;          //医方附件
//	private String hFiles;   //患方附件
	private User user;   //当前登录人
	private List<String> list;//驻卫健委工作站人员list

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public PreOperativeConsent() {
		super();
	}

	public PreOperativeConsent(String id){
		super(id);
	}

//	public String getFiles() {
//		return files;
//	}
//
//	public void setFiles(String files) {
//		this.files = files;
//	}
//
//	public String gethFiles() {
//		return hFiles;
//	}
//
//	public void sethFiles(String hFiles) {
//		this.hFiles = hFiles;
//	}

	public String getWitnessName() {
		return witnessName;
	}

	public void setWitnessName(String witnessName) {
		this.witnessName = witnessName;
	}

	public String getRecordManName() {
		return recordManName;
	}

	public void setRecordManName(String recordManName) {
		this.recordManName = recordManName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getOtherDoctors() {
		return otherDoctors;
	}

	public void setOtherDoctors(String otherDoctors) {
		this.otherDoctors = otherDoctors;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getOperationClient() {
		return operationClient;
	}

	public void setOperationClient(String operationClient) {
		this.operationClient = operationClient;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getPostoperativeVisit() {
		return postoperativeVisit;
	}

	public void setPostoperativeVisit(String postoperativeVisit) {
		this.postoperativeVisit = postoperativeVisit;
	}

	public String getIsDangerous() {
		return isDangerous;
	}

	public void setIsDangerous(String isDangerous) {
		this.isDangerous = isDangerous;
	}

	public String getCompensationAmount() {
		return compensationAmount;
	}

	public void setCompensationAmount(String compensationAmount) {
		this.compensationAmount = compensationAmount;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Length(min=1, max=64, message="手术同意书编号长度必须介于 1 和 64 之间")
	public String getSurgicalConsentId() {
		return surgicalConsentId;
	}

	public void setSurgicalConsentId(String surgicalConsentId) {
		this.surgicalConsentId = surgicalConsentId;
	}
	

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}


	public Date getWitnessTime() {
		return witnessTime;
	}

	public void setWitnessTime(Date witnessTime) {
		this.witnessTime = witnessTime;
	}

	@Length(min=1, max=64, message="见证地点长度必须介于 1 和 64 之间")
	public String getWitnessLocations() {
		return witnessLocations;
	}

	public void setWitnessLocations(String witnessLocations) {
		this.witnessLocations = witnessLocations;
	}
	
	@Length(min=1, max=64, message="患方人员长度必须介于 1 和 64 之间")
	public String getAffectedParty() {
		return affectedParty;
	}

	public void setAffectedParty(String affectedParty) {
		this.affectedParty = affectedParty;
	}
	
	@Length(min=1, max=64, message="医方人员长度必须介于 1 和 64 之间")
	public String getMedicalSide() {
		return medicalSide;
	}

	public void setMedicalSide(String medicalSide) {
		this.medicalSide = medicalSide;
	}
	

	public String getInsured() {
		return insured;
	}

	public void setInsured(String insured) {
		this.insured = insured;
	}
	

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	
	@Length(min=1, max=32, message="见证人长度必须介于 1 和 32 之间")
	public String getWitness() {
		return witness;
	}

	public void setWitness(String witness) {
		this.witness = witness;
	}
	
	@Length(min=1, max=32, message="记录人长度必须介于 1 和 32 之间")
	public String getRecordMan() {
		return recordMan;
	}

	public void setRecordMan(String recordMan) {
		this.recordMan = recordMan;
	}
	
	public String getWitnessContent() {
		return witnessContent;
	}

	public void setWitnessContent(String witnessContent) {
		this.witnessContent = witnessContent;
	}
	
}