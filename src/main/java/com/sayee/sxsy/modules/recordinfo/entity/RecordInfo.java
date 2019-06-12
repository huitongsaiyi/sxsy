/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.recordinfo.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 笔录Entity
 * @author 逯洋涛
 * @version 2019-06-11
 */
public class RecordInfo extends DataEntity<RecordInfo> {
	
	private static final long serialVersionUID = 1L;
	private String recordId;		// 笔录主键
	private String relationId;		// 关联模块主键
	private String startTime;		// 开始时间
	private String endTime;		// 结束时间
	private String recordAddress;		// 地点
	private String cause;		// 事由
	private String host;		// 主持人
	private String noteTaker;		// 记录人
	private String patient;		// 患方
	private String doctor;		// 医方
	private String otherParticipants;		// 其他参加人员
	private String recordContent;		// 笔录内容
	private String moduleType;		// 模块类型 1调解会议 2评估会议
	private String type;		// 类型 1患方笔录 2 医方笔录
	private RecordInfo yrecordInfo;		//医方笔录

	public RecordInfo() {
		super();
	}

	public RecordInfo(String id){
		super(id);
	}

	public void setYrecordInfo(RecordInfo yrecordInfo) {
		this.yrecordInfo = yrecordInfo;
	}

	public RecordInfo getYrecordInfo() {
		return yrecordInfo;
	}

	@Length(min=0, max=32, message="笔录主键长度必须介于 0 和 32 之间")
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	@Length(min=0, max=32, message="关联模块主键长度必须介于 0 和 32 之间")
	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	@Length(min=0, max=20, message="开始时间长度必须介于 0 和 20 之间")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Length(min=0, max=20, message="结束时间长度必须介于 0 和 20 之间")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=100, message="地点长度必须介于 0 和 100 之间")
	public String getrecordAddress() {
		return recordAddress;
	}

	public void setrecordAddress(String recordAddress) {
		this.recordAddress = recordAddress;
	}
	
	@Length(min=0, max=500, message="事由长度必须介于 0 和 500 之间")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
	@Length(min=0, max=32, message="主持人长度必须介于 0 和 32 之间")
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	@Length(min=0, max=32, message="记录人长度必须介于 0 和 32 之间")
	public String getNoteTaker() {
		return noteTaker;
	}

	public void setNoteTaker(String noteTaker) {
		this.noteTaker = noteTaker;
	}
	
	@Length(min=0, max=32, message="患方长度必须介于 0 和 32 之间")
	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}
	
	@Length(min=0, max=32, message="医方长度必须介于 0 和 32 之间")
	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	@Length(min=0, max=100, message="其他参加人员长度必须介于 0 和 100 之间")
	public String getOtherParticipants() {
		return otherParticipants;
	}

	public void setOtherParticipants(String otherParticipants) {
		this.otherParticipants = otherParticipants;
	}
	
	public String getRecordContent() {
		return recordContent;
	}

	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}
	
	@Length(min=0, max=1, message="模块类型 1调解会议 2评估会议长度必须介于 0 和 1 之间")
	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	
	@Length(min=0, max=1, message="类型 1患方笔录 2 医方笔录长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}