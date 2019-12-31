/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.satisfied.entity;

import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 满意度Entity
 * @author zhangfan
 * @version 2019-12-03
 */
public class SatisfiedDegree extends DataEntity<SatisfiedDegree> {
	
	private static final long serialVersionUID = 1L;
	private String satisfiedId;		// 主键
	private String satisfiedName;		// 申请人姓名
	private String ability;		// 调解能力
	private String attitude;		// 服务态度
	private String meter;		// 仪容仪表
	private String assess;		// 评价
	private String proposal;		// 建议
	private String complaintMainId;		// 主表主键
	private String signAgreementId;		// 签署协议主键
	private ComplaintMain complaintMain;		// 主表
	private String uid;		// 当前人员id

	public SatisfiedDegree() {
		super();
	}

	public SatisfiedDegree(String id){
		super(id);
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	public String getSignAgreementId() {
		return signAgreementId;
	}

	public void setSignAgreementId(String signAgreementId) {
		this.signAgreementId = signAgreementId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getSatisfiedId() {
		return satisfiedId;
	}

	public void setSatisfiedId(String satisfiedId) {
		this.satisfiedId = satisfiedId;
	}
	
	@Length(min=0, max=10, message="申请人姓名长度必须介于 0 和 10 之间")
	public String getSatisfiedName() {
		return satisfiedName;
	}

	public void setSatisfiedName(String satisfiedName) {
		this.satisfiedName = satisfiedName;
	}
	
	@Length(min=0, max=4, message="调解能力长度必须介于 0 和 4 之间")
	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}
	
	@Length(min=0, max=4, message="服务态度长度必须介于 0 和 4 之间")
	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}
	
	@Length(min=0, max=4, message="仪容仪表长度必须介于 0 和 4 之间")
	public String getMeter() {
		return meter;
	}

	public void setMeter(String meter) {
		this.meter = meter;
	}
	
	public String getAssess() {
		return assess;
	}

	public void setAssess(String assess) {
		this.assess = assess;
	}
	
	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	
	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
}