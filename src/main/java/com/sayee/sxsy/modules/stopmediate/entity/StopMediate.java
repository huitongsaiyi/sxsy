/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.stopmediate.entity;

import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 终止调解Entity
 * @author lyt
 * @version 2019-06-20
 */
public class StopMediate extends DataEntity<StopMediate> {
	
	private static final long serialVersionUID = 1L;
	private String stopMediateId;		// 终止调解主键
	private String complaintMainId;		// 主表主键
	private String patientName;		// 患者姓名
	private String involveHospital;		// 设计医院
	private String stopTime;		// 终止日期
	private String stopDescribe;		// 终止调解描述
	private String handlePeople;		// handle_people
	private String handleTime;		// handle_time
	private String nextLink;		// next_link
	private String nextLinkMan;		// next_link_man
	private ComplaintMain complaintMain;		//主表
	private AuditAcceptance auditAcceptance;//保单号
	private Area area;
	private ReportRegistration reportRegistration;//报案人姓名
	private User linkEmployee;
	private User user;  //当前登陆人

	public StopMediate() {
		super();
	}

	public StopMediate(String id){
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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public AuditAcceptance getAuditAcceptance() {
		return auditAcceptance;
	}

	public void setAuditAcceptance(AuditAcceptance auditAcceptance) {
		this.auditAcceptance = auditAcceptance;
	}

	public User getLinkEmployee() {
		return linkEmployee;
	}

	public void setLinkEmployee(User linkEmployee) {
		this.linkEmployee = linkEmployee;
	}

	public ReportRegistration getReportRegistration() {
		return reportRegistration;
	}

	public void setReportRegistration(ReportRegistration reportRegistration) {
		this.reportRegistration = reportRegistration;
	}

	@Length(min=0, max=32, message="终止调解主键长度必须介于 0 和 32 之间")
	public String getStopMediateId() {
		return stopMediateId;
	}

	public void setStopMediateId(String stopMediateId) {
		this.stopMediateId = stopMediateId;
	}
	
	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=1, max=20, message="患者姓名长度必须介于 0 和 20 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Length(min=1, max=32, message="设计医院长度必须介于 0 和 32 之间")
	public String getInvolveHospital() {
		return involveHospital;
	}

	public void setInvolveHospital(String involveHospital) {
		this.involveHospital = involveHospital;
	}
	
	@Length(min=1, max=20, message="终止日期长度必须介于 0 和 20 之间")
	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	
	public String getStopDescribe() {
		return stopDescribe;
	}

	public void setStopDescribe(String stopDescribe) {
		this.stopDescribe = stopDescribe;
	}
	
	@Length(min=0, max=32, message="handle_people长度必须介于 0 和 32 之间")
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
	
	@Length(min=1, max=32, message="next_link_man长度必须介于 0 和 32 之间")
	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}
	
}