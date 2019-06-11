/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.nestigateeividence.entity;

import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.respondentinfo.entity.RespondentInfo;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 调查取证Entity
 * @author gbq
 * @version 2019-06-10
 */
public class InvestigateEvidence extends DataEntity<InvestigateEvidence> {

	private static final long serialVersionUID = 1L;
	private String investigateEvidenceId;		// 调查取证主键
	private String complaintMainId;		// 主表主键
	private String startTime;		// 调查开始时间
	private String endTime;		// 调查结束时间
	private String address;		// 调查地点
	private String cause;		// 调查事由
	private String investigator;		// 调查人
	private String noteTaker;		// 调查记录人
	private String content;		// 笔录内容
	private String focus;		// 反应焦点
	private String investigateType;		// 被调查类型 1 患方  2 医方
	private String handlePeople;		// 处理人
	private String handleTime;		// 处理日期
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	private AuditAcceptance auditAcceptance;//保单号
	private ComplaintMain complaintMain;		// 关联主表主键
	private User user;  //当前登录人员
	private Area area;
	private User dcEmployee;		// 登记人员
	private User linkEmployee;		// 下一环节人员
	private ReportRegistration reportRegistration;//报案人姓名
	private RespondentInfo respondentInfo;//被调查人信息

	public RespondentInfo getRespondentInfo() {
		return respondentInfo;
	}

	public void setRespondentInfo(RespondentInfo respondentInfo) {
		this.respondentInfo = respondentInfo;
	}

	public ReportRegistration getReportRegistration() {

		return reportRegistration;
	}

	public void setReportRegistration(ReportRegistration reportRegistration) {
		this.reportRegistration = reportRegistration;
	}

	public InvestigateEvidence() {
		super();
	}

	public User getLinkEmployee() {
		return linkEmployee;
	}

	public void setLinkEmployee(User linkEmployee) {
		this.linkEmployee = linkEmployee;
	}

	public User getDcEmployee() {
		return dcEmployee;
	}

	public void setDcEmployee(User dcEmployee) {
		this.dcEmployee = dcEmployee;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
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

	public AuditAcceptance getAuditAcceptance() {
		return auditAcceptance;
	}

	public void setAuditAcceptance(AuditAcceptance auditAcceptance) {
		this.auditAcceptance = auditAcceptance;
	}

	public InvestigateEvidence(String id){
		super(id);
	}

	@Length(min=1, max=32, message="调查取证主键长度必须介于 1 和 32 之间")
	public String getInvestigateEvidenceId() {
		return investigateEvidenceId;
	}

	public void setInvestigateEvidenceId(String investigateEvidenceId) {
		this.investigateEvidenceId = investigateEvidenceId;
	}

	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}

	@Length(min=0, max=20, message="调查开始时间长度必须介于 0 和 20 之间")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Length(min=0, max=20, message="调查结束时间长度必须介于 0 和 20 之间")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Length(min=0, max=200, message="调查地点长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Length(min=0, max=500, message="调查事由长度必须介于 0 和 500 之间")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Length(min=0, max=32, message="调查人长度必须介于 0 和 32 之间")
	public String getInvestigator() {
		return investigator;
	}

	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}

	@Length(min=0, max=32, message="调查记录人长度必须介于 0 和 32 之间")
	public String getNoteTaker() {
		return noteTaker;
	}

	public void setNoteTaker(String noteTaker) {
		this.noteTaker = noteTaker;
	}

	@Length(min=0, max=500, message="笔录内容长度必须介于 0 和 500 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Length(min=0, max=500, message="反应焦点长度必须介于 0 和 500 之间")
	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	@Length(min=0, max=1, message="被调查类型 1 患方  2 医方长度必须介于 0 和 1 之间")
	public String getInvestigateType() {
		return investigateType;
	}

	public void setInvestigateType(String investigateType) {
		this.investigateType = investigateType;
	}

	@Length(min=0, max=32, message="处理人长度必须介于 0 和 32 之间")
	public String getHandlePeople() {
		return handlePeople;
	}

	public void setHandlePeople(String handlePeople) {
		this.handlePeople = handlePeople;
	}

	@Length(min=0, max=10, message="处理日期长度必须介于 0 和 10 之间")
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