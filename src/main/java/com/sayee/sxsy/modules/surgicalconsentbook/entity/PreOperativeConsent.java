/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.surgicalconsentbook.entity;

import org.hibernate.validator.constraints.Length;


import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.Date;

/**
 * 术前同意书见证管理Entity
 * @author gbq
 * @version 2019-05-31
 */
public class PreOperativeConsent extends DataEntity<PreOperativeConsent> {
	
	private static final long serialVersionUID = 1L;
	private String id;						//主键id
	private String surgicalConsentId;		// 手术同意书编号
	private String operationType;		// 手术类型
	private Date witnessTime;		// 见证时间
	private String witnessLocations;		// 见证地点
	private String affectedParty;		// 患方人员
	private String medicalSide;		// 医方人员
	private String insured;		// 是否投保(1.是，2.否)
	private String policyNo;		// 保单号
	private String witness;		// 见证人
	private String recordMan;		// 记录人
	private String witnessContent;		// 见证内容
//	private String files;          //医方附件
//	private String hFiles;   //患方附件
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
	
	@Length(min=1, max=20, message="见证人长度必须介于 1 和 20 之间")
	public String getWitness() {
		return witness;
	}

	public void setWitness(String witness) {
		this.witness = witness;
	}
	
	@Length(min=1, max=20, message="记录人长度必须介于 1 和 20 之间")
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