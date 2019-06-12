/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessapply.entity;

import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 评估申请Entity
 * @author zhangfan
 * @version 2019-06-11
 */
public class AssessApply extends DataEntity<AssessApply> {
	
	private static final long serialVersionUID = 1L;
	private String assessApplyId;		// 申请表主键
	private String complaintMainId;		// 关联主键
	private ComplaintMain complaintMain;  //主表
	private User user;  //当前登录人员
	private User sqEmployee;		// 申请人员
	private User linkEmployee;		// 下一环节人员
	private String patientApplyer;		// 患方申请人
	private String patientRelation;		// 与患者关系
	private String patientMobile;		// 患方电话
	private String patientName;		// 患者姓名
	private String patientSex;		// 患方性别
	private String patientAge;		// 患方年龄
	private String involveHospital;		// 患方涉及医院
	private String patientApplyMatter;		// 患方申请事项
	private String hospitalApply;		// 申请医院
	private String agent;		// 代理人
	private String hospitalMobile;		// 医方电话
	private String hospitalName;		// 医方姓名
	private String hospitalAge;		// 医方年龄
	private String hospitalSex;		// 医方性别
	private String hospitalApplyMatter;		// 医方申请事项
	private String applyType;		// 申请类型
	private String handlePeople;		// 处理人
	private String handleTime;		// 处理日期
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	
	public AssessApply() {
		super();
	}

	public AssessApply(String id){
		super(id);
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getSqEmployee() {
		return sqEmployee;
	}

	public void setSqEmployee(User sqEmployee) {
		this.sqEmployee = sqEmployee;
	}

	public User getLinkEmployee() {
		return linkEmployee;
	}

	public void setLinkEmployee(User linkEmployee) {
		this.linkEmployee = linkEmployee;
	}

	@Length(min=1, max=32, message="申请表主键长度必须介于 1 和 32 之间")
	public String getAssessApplyId() {
		return assessApplyId;
	}

	public void setAssessApplyId(String assessApplyId) {
		this.assessApplyId = assessApplyId;
	}
	
	@Length(min=0, max=32, message="关联主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=0, max=32, message="患方申请人长度必须介于 0 和 32 之间")
	public String getPatientApplyer() {
		return patientApplyer;
	}

	public void setPatientApplyer(String patientApplyer) {
		this.patientApplyer = patientApplyer;
	}
	
	@Length(min=0, max=1, message="与患者关系长度必须介于 0 和 1 之间")
	public String getPatientRelation() {
		return patientRelation;
	}

	public void setPatientRelation(String patientRelation) {
		this.patientRelation = patientRelation;
	}
	
	@Length(min=0, max=15, message="患方电话长度必须介于 0 和 15 之间")
	public String getPatientMobile() {
		return patientMobile;
	}

	public void setPatientMobile(String patientMobile) {
		this.patientMobile = patientMobile;
	}
	
	@Length(min=0, max=10, message="患者姓名长度必须介于 0 和 10 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Length(min=0, max=1, message="患方性别长度必须介于 0 和 1 之间")
	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	
	@Length(min=0, max=4, message="患方年龄长度必须介于 0 和 4 之间")
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	
	@Length(min=0, max=32, message="患方涉及医院长度必须介于 0 和 32 之间")
	public String getInvolveHospital() {
		return involveHospital;
	}

	public void setInvolveHospital(String involveHospital) {
		this.involveHospital = involveHospital;
	}
	
	@Length(min=0, max=200, message="患方申请事项长度必须介于 0 和 200 之间")
	public String getPatientApplyMatter() {
		return patientApplyMatter;
	}

	public void setPatientApplyMatter(String patientApplyMatter) {
		this.patientApplyMatter = patientApplyMatter;
	}
	
	@Length(min=0, max=32, message="申请医院长度必须介于 0 和 32 之间")
	public String getHospitalApply() {
		return hospitalApply;
	}

	public void setHospitalApply(String hospitalApply) {
		this.hospitalApply = hospitalApply;
	}
	
	@Length(min=0, max=10, message="代理人长度必须介于 0 和 10 之间")
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	@Length(min=0, max=15, message="医方电话长度必须介于 0 和 15 之间")
	public String getHospitalMobile() {
		return hospitalMobile;
	}

	public void setHospitalMobile(String hospitalMobile) {
		this.hospitalMobile = hospitalMobile;
	}
	
	@Length(min=0, max=10, message="医方姓名长度必须介于 0 和 10 之间")
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	@Length(min=0, max=4, message="医方年龄长度必须介于 0 和 4 之间")
	public String getHospitalAge() {
		return hospitalAge;
	}

	public void setHospitalAge(String hospitalAge) {
		this.hospitalAge = hospitalAge;
	}
	
	@Length(min=0, max=1, message="医方性别长度必须介于 0 和 1 之间")
	public String getHospitalSex() {
		return hospitalSex;
	}

	public void setHospitalSex(String hospitalSex) {
		this.hospitalSex = hospitalSex;
	}
	
	@Length(min=0, max=200, message="医方申请事项长度必须介于 0 和 200 之间")
	public String getHospitalApplyMatter() {
		return hospitalApplyMatter;
	}

	public void setHospitalApplyMatter(String hospitalApplyMatter) {
		this.hospitalApplyMatter = hospitalApplyMatter;
	}
	
	@Length(min=0, max=1, message="申请类型长度必须介于 0 和 1 之间")
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	@Length(min=0, max=10, message="处理人长度必须介于 0 和 10 之间")
	public String getHandlePeople() {
		return handlePeople;
	}

	public void setHandlePeople(String handlePeople) {
		this.handlePeople = handlePeople;
	}
	
	@Length(min=0, max=20, message="处理日期长度必须介于 0 和 20 之间")
	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
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