/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.casefeedback.entity;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.utils.Collections3;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.List;

/**
 * 案件反馈Entity
 * @author lyt
 * @version 2019-06-20
 */
public class CaseFeedback extends DataEntity<CaseFeedback> {
	
	private static final long serialVersionUID = 1L;
	private String feedbackId;		// 反馈主键
	private String complaintMainId;		// 主表主键
	private String feedbackEmp;		// 反馈给人员id
	private String feedbackOffice;		// 反馈给部门
	private ComplaintMain complaintMain;		//主表
	private AuditAcceptance auditAcceptance;//保单号
	private Area area;
	private ReportRegistration reportRegistration;//报案人姓名
	private User linkEmployee;
	private User user;  //当前登陆人
	private List<User> listUser = Lists.newArrayList();		//反馈人员姓名
	private List<Office> listOffice = Lists.newArrayList();		//反馈部门名
	private String caseFeedBackIds;
	private String caseFeedOfficeIds;
	private Office office;

	
	public CaseFeedback() {
		super();
	}

	public CaseFeedback(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getCaseFeedBackIds() {
		return Collections3.extractToString(listUser, "id", ",") ;
	}

	public void setCaseFeedOfficeIds(String feedbackOffice){
		this.listOffice = Lists.newArrayList();
		for(String id: StringUtils.split(feedbackOffice,",")){
			this.listOffice.add(this.getOffice());
		}
	}

	public String getCaseFeedOfficeIds() {
		return Collections3.extractToString(listOffice, "id", ",") ;
	}

	public void setCaseFeedBackIds(String feedbackEmp){
		this.listUser = Lists.newArrayList();
		for(String id: StringUtils.split(feedbackEmp,",")){
			this.listUser.add(this.getUser());
		}
	}

	public String getOaNotifyOfficeNames() {
		return Collections3.extractToString(listOffice, "name", ",") ;
	}

	public void setOaNotifyOfficeNames(String oaNotifyRecord) {
		// 什么也不做
	}

	public String getOaNotifyRecordNames() {
		return Collections3.extractToString(listUser, "name", ",") ;
	}

	public void setOaNotifyRecordNames(String oaNotifyRecord) {
		// 什么也不做
	}

	public List<Office> getListOffice() {
		return listOffice;
	}

	public void setListOffice(List<Office> listOffice) {
		this.listOffice = listOffice;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
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

	public User getLinkEmployee() {
		return linkEmployee;
	}

	public void setLinkEmployee(User linkEmployee) {
		this.linkEmployee = linkEmployee;
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

	@Length(min=0, max=32, message="反馈主键长度必须介于 0 和 32 之间")
	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	
	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=1, max=1000, message="反馈给人员长度必须介于 0 和 1000 之间")
	public String getFeedbackEmp() {
		return feedbackEmp;
	}

	public void setFeedbackEmp(String feedbackEmp) {
		this.feedbackEmp = feedbackEmp;
	}
	
	@Length(min=1, max=1000, message="反馈给部门长度必须介于 0 和 1000 之间")
	public String getFeedbackOffice() {
		return feedbackOffice;
	}

	public void setFeedbackOffice(String feedbackOffice) {
		this.feedbackOffice = feedbackOffice;
	}
	
}