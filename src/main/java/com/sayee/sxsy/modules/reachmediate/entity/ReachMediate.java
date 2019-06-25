/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.reachmediate.entity;

import com.google.common.collect.Lists;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.List;

/**
 * 达成调解Entity
 * @author lyt
 * @version 2019-06-14
 */
public class ReachMediate extends DataEntity<ReachMediate> {
	
	private static final long serialVersionUID = 1L;
	private String reachMediateId;		// 达成调解主键
	private String complaintMainId;		// 主表主键
	private String reaMediateResult;		// 调解结果  1成功 2 失败
	private String reaSummary;		// 会议总结
	private String reaUserId;		// 医调委人员  多人用逗号隔开
	private String reaPatient;		// 患方
	private String reaDoctor;		// 医方
	private String reaCaseInfo;		// 案件
	private String reaAddress;		// 地点
	private String reaMeetingTime;		// 会议时间
	private String handlePeople;		// 处理人
	private String handleTime;		// 处理时间
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	private User user;        // 当前登录人员
	private User ytwUser;        // 医调委人员  多人用逗号隔开
	private User doctorUser;        // 医方实体类
	private User linkEmployee;		// 下一环节人员
	private ComplaintMain complaintMain;        //关联主表
	private List<MediateRecord> mediateEvidenceList = Lists.newArrayList();     //关联调解志子表
	private RecordInfo recordInfo;      //关联笔录子表
	private Area area;
	private AuditAcceptance auditAcceptance;
	private ReportRegistration reportRegistration;


	public ReachMediate() {
		super();
	}

	public ReachMediate(String id){
		super(id);
	}

	public User getLinkEmployee() {
		return linkEmployee;
	}

	public void setLinkEmployee(User linkEmployee) {
		this.linkEmployee = linkEmployee;
	}

	public AuditAcceptance getAuditAcceptance() {
		return auditAcceptance;
	}

	public void setAuditAcceptance(AuditAcceptance auditAcceptance) {
		this.auditAcceptance = auditAcceptance;
	}

	public ReportRegistration getReportRegistration() {
		return reportRegistration;
	}

	public void setReportRegistration(ReportRegistration reportRegistration) {
		this.reportRegistration = reportRegistration;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public RecordInfo getRecordInfo() {
		return recordInfo;
	}

	public void setRecordInfo(RecordInfo recordInfo) {
		this.recordInfo = recordInfo;
	}

	public User getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(User doctorUser) {
		this.doctorUser = doctorUser;
	}

	public User getYtwUser() {
		return ytwUser;
	}

	public void setYtwUser(User ytwUser) {
		this.ytwUser = ytwUser;
	}

	public List<MediateRecord> getMediateEvidenceList() {
		return mediateEvidenceList;
	}

	public void setMediateEvidenceList(List<MediateRecord> mediateEvidenceList) {
		this.mediateEvidenceList = mediateEvidenceList;
	}

	@Length(min=0, max=32, message="达成调解主键长度必须介于 0 和 32 之间")
	public String getReachMediateId() {
		return reachMediateId;
	}

	public void setReachMediateId(String reachMediateId) {
		this.reachMediateId = reachMediateId;
	}
	
	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=1, max=1, message="调解结果  1成功 2 失败长度必须介于 0 和 1 之间")
	public String getReaMediateResult() {
		return reaMediateResult;
	}

	public void setReaMediateResult(String reaMediateResult) {
		this.reaMediateResult = reaMediateResult;
	}
	
	public String getReaSummary() {
		return reaSummary;
	}

	public void setReaSummary(String reaSummary) {
		this.reaSummary = reaSummary;
	}
	
	@Length(min=1, max=200, message="医调委人员  多人用逗号隔开长度必须介于 1 和 200 之间")
	public String getReaUserId() {
		return reaUserId;
	}

	public void setReaUserId(String reaUserId) {
		this.reaUserId = reaUserId;
	}
	
	@Length(min=1, max=100, message="患方长度必须介于 1 和 100 之间")
	public String getReaPatient() {
		return reaPatient;
	}

	public void setReaPatient(String reaPatient) {
		this.reaPatient = reaPatient;
	}
	
	@Length(min=1, max=200, message="医方长度必须介于 1 和 200 之间")
	public String getReaDoctor() {
		return reaDoctor;
	}

	public void setReaDoctor(String reaDoctor) {
		this.reaDoctor = reaDoctor;
	}
	
	@Length(min=1, max=200, message="案件长度必须介于 1 和 200 之间")
	public String getReaCaseInfo() {
		return reaCaseInfo;
	}

	public void setReaCaseInfo(String reaCaseInfo) {
		this.reaCaseInfo = reaCaseInfo;
	}
	
	@Length(min=1, max=100, message="地点长度必须介于 1 和 100 之间")
	public String getReaAddress() {
		return reaAddress;
	}

	public void setReaAddress(String reaAddress) {
		this.reaAddress = reaAddress;
	}
	
	@Length(min=1, max=20, message="会议时间长度必须介于 1 和 20 之间")
	public String getReaMeetingTime() {
		return reaMeetingTime;
	}

	public void setReaMeetingTime(String reaMeetingTime) {
		this.reaMeetingTime = reaMeetingTime;
	}
	
	@Length(min=0, max=10, message="handle_people长度必须介于 0 和 10 之间")
	public String getHandlePeople() {
		return handlePeople;
	}

	public void setHandlePeople(String handlePeople) {
		this.handlePeople = handlePeople;
	}
	
	@Length(min=0, max=20, message="handle_time长度必须介于 0 和 20 之间")
	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	
	@Length(min=0, max=32, message="next_link长度必须介于 0 和 32 之间")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
	

	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}
	
}