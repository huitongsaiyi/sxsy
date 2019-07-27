/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.program.entity;

import com.sayee.sxsy.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;
import com.sayee.sxsy.modules.sys.entity.User;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 调解程序Entity
 * @author zhangfan
 * @version 2019-07-04
 */
public class MediateProgram extends DataEntity<MediateProgram> {
	
	private static final long serialVersionUID = 1L;
	private String mediateProgramId;		// 调解程序主键
	private String relationId;		// 关联表主键
	private String mediator;		// 调解员
	private String clerk;		// 书记员
	private User user;		// 医调委人员  多人用逗号隔开
	private String patient;		// 患方
	private String doctor;		// 医方
	private User mediatorUser;		// 调解员名称
	private User clerkuser;		// 书记员名称
	private User doctorUser;		// 医方
	private Office doctorOffice;	//医方公司实体类
	private String other;		// 其他
	private String caseInfo;		// 案件
	private String address;		// 地点
	private String meetingTime;		// 会议时间
	private String meetingFrequency;		// 会议次数
    private String doctorAvoid;//医方回避
	private String patientAvoid;//患方回避
	private String patientClear;//患方是否清楚
	private String doctorClear;//医方是否清楚
	private String doctorContent;//医方笔录
	private String patientContent;//患方笔录

	public String getDoctorClear() {
		return doctorClear;
	}

	public void setDoctorClear(String doctorClear) {
		this.doctorClear = doctorClear;
	}

	public String getPatientClear() {
		return patientClear;
	}

	public void setPatientClear(String patientClear) {
		this.patientClear = patientClear;
	}

	public String getPatientAvoid() {
		return patientAvoid;
	}

	public void setPatientAvoid(String patientAvoid) {
		this.patientAvoid = patientAvoid;
	}

	public String getDoctorAvoid() {
		return doctorAvoid;
	}

	public void setDoctorAvoid(String doctorAvoid) {
		this.doctorAvoid = doctorAvoid;
	}

	public MediateProgram() {
		super();
	}

	public MediateProgram(String id){
		super(id);
	}

	public Office getDoctorOffice() {
		return doctorOffice;
	}

	public void setDoctorOffice(Office doctorOffice) {
		this.doctorOffice = doctorOffice;
	}

	public User getMediatorUser() {
		return mediatorUser;
	}

	public void setMediatorUser(User mediatorUser) {
		this.mediatorUser = mediatorUser;
	}

	public User getClerkuser() {
		return clerkuser;
	}

	public void setClerkuser(User clerkuser) {
		this.clerkuser = clerkuser;
	}

	public User getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(User doctorUser) {
		this.doctorUser = doctorUser;
	}

	@Length(min=1, max=32, message="调解程序主键长度必须介于 1 和 32 之间")
	public String getMediateProgramId() {
		return mediateProgramId;
	}

	public void setMediateProgramId(String mediateProgramId) {
		this.mediateProgramId = mediateProgramId;
	}
	
	@Length(min=0, max=32, message="关联表主键长度必须介于 0 和 32 之间")
	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	@Length(min=0, max=32, message="调解员长度必须介于 0 和 32 之间")
	public String getMediator() {
		return mediator;
	}

	public void setMediator(String mediator) {
		this.mediator = mediator;
	}
	
	@Length(min=0, max=32, message="书记员长度必须介于 0 和 32 之间")
	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=100, message="患方长度必须介于 0 和 100 之间")
	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}
	
	@Length(min=0, max=200, message="医方长度必须介于 0 和 200 之间")
	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	@Length(min=0, max=200, message="其他长度必须介于 0 和 200 之间")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	@Length(min=0, max=200, message="案件长度必须介于 0 和 200 之间")
	public String getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(String caseInfo) {
		this.caseInfo = caseInfo;
	}
	
	@Length(min=0, max=100, message="地点长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=20, message="会议时间长度必须介于 0 和 20 之间")
	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}
	
	@Length(min=0, max=4, message="会议次数长度必须介于 0 和 4 之间")
	public String getMeetingFrequency() {
		return meetingFrequency;
	}

	public void setMeetingFrequency(String meetingFrequency) {
		this.meetingFrequency = meetingFrequency;
	}

	public String getDoctorContent() {
		return doctorContent;
	}

	public void setDoctorContent(String doctorContent) {
		this.doctorContent = doctorContent;
	}

	public String getPatientContent() {
		return patientContent;
	}

	public void setPatientContent(String patientContent) {
		this.patientContent = patientContent;
	}
}