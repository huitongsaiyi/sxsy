/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.proposal.entity;

import com.google.common.collect.Lists;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.List;

/**
 * 意见书Entity
 * @author gbq
 * @version 2019-06-13
 */
public class Proposal extends DataEntity<Proposal> {
	
	private static final long serialVersionUID = 1L;
	private String proposalId;		// 主键
	private String assessAppraisalId;		// 评估鉴定主键
	private String treatmentSummary;		// 诊疗概要
	private String patientThink;		// 患方认为
	private String doctorThink;		// 医方认为
	private String analysisOpinion;		// 分析意见 关联&ldquo;类型&rdquo;表的主键 ，多个用逗号分开
	private String diagnosis;		// 诊断
	private String treatment;		// 治疗
	private String other;		// 其他
	private String conclusion;		// 结论 关联&ldquo;类型&rdquo;表主键 多个用逗号分开
	private String proposalCode;//意见书编码


	@Length(min=1, max=30, message="意见书编码长度必须介于 1 和 30 之间")
	public String getProposalCode() {
		return proposalCode;
	}

	public void setProposalCode(String proposalCode) {
		this.proposalCode = proposalCode;
	}

	public Proposal() {
		super();
	}

	public Proposal(String id){
		super(id);
	}

	@Length(min=0, max=32, message="主键长度必须介于 0 和 32 之间")
	public String getProposalId() {
		return proposalId;
	}

	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}
	
	@Length(min=0, max=32, message="评估鉴定主键长度必须介于 0 和 32 之间")
	public String getAssessAppraisalId() {
		return assessAppraisalId;
	}

	public void setAssessAppraisalId(String assessAppraisalId) {
		this.assessAppraisalId = assessAppraisalId;
	}
	@Length(min=1, max=10000, message="诊疗概要长度必须介于 1 和 10000 之间")
	public String getTreatmentSummary() {
		return treatmentSummary;
	}

	public void setTreatmentSummary(String treatmentSummary) {
		this.treatmentSummary = treatmentSummary;
	}
	
	@Length(min=1, max=500, message="患方认为长度必须介于 1 和 500 之间")
	public String getPatientThink() {
		return patientThink;
	}

	public void setPatientThink(String patientThink) {
		this.patientThink = patientThink;
	}
	
	@Length(min=1, max=500, message="医方认为长度必须介于 1 和 500 之间")
	public String getDoctorThink() {
		return doctorThink;
	}

	public void setDoctorThink(String doctorThink) {
		this.doctorThink = doctorThink;
	}
	
	@Length(min=1, max=200, message="分析意见长度必须介于 1 和 200 之间")
	public String getAnalysisOpinion() {
		return analysisOpinion;
	}

	public void setAnalysisOpinion(String analysisOpinion) {
		this.analysisOpinion = analysisOpinion;
	}
	
	@Length(min=1, max=200, message="诊断长度必须介于 1 和 200 之间")
	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	@Length(min=1, max=200, message="治疗长度必须介于 1 和 200 之间")
	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	
	@Length(min=1, max=200, message="其他长度必须介于 1 和 200 之间")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	@Length(min=1, max=200, message="结论长度必须介于 0 和 200 之间")
	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
}