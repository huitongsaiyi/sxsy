/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.perform.entity;

import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

import javax.validation.constraints.DecimalMin;

/**
 * 履行协议Entity
 * @author lyt
 * @version 2019-06-14
 */
public class PerformAgreement extends DataEntity<PerformAgreement> {
	
	private static final long serialVersionUID = 1L;
	private String performAgreementId;		// 履行协议主键
	private String complaintMainId;		// 主表主键
	private String agreementPayAmount;		// 协议赔付总金额
	private String hospitalPayAmount;		// 医院赔付金额
	private String hospitalPayTime;		// 医院赔付时间
	private String insurancePayAmount;		// 保险公司赔付金额
	private String insurancePayTime;		// 保险公司赔付时间
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	private ComplaintMain complaintMain;		//主表
	private AuditAcceptance auditAcceptance;//保单号
	private Area area;
	private ReportRegistration reportRegistration;//报案人姓名
	private User linkEmployee;
	private User user;  //当前登陆人

	public PerformAgreement() {
		super();
	}

	public PerformAgreement(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public ReportRegistration getReportRegistration() {
		return reportRegistration;
	}

	public void setReportRegistration(ReportRegistration reportRegistration) {
		this.reportRegistration = reportRegistration;
	}

	public AuditAcceptance getAuditAcceptance() {
		return auditAcceptance;
	}

	public void setAuditAcceptance(AuditAcceptance auditAcceptance) {
		this.auditAcceptance = auditAcceptance;
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	@Length(min=0, max=32, message="履行协议主键长度必须介于 0 和 32 之间")
	public String getPerformAgreementId() {
		return performAgreementId;
	}

	public void setPerformAgreementId(String performAgreementId) {
		this.performAgreementId = performAgreementId;
	}
	
	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}

	@Length(min=1,max=20,message = "协议金额不能为空，且长度必须介于 0 和 20 之间")
	@DecimalMin(value = "0",message = "协议金额最低为0,且不能为空")
	public String getAgreementPayAmount() {
		return agreementPayAmount;
	}

	public void setAgreementPayAmount(String agreementPayAmount) {
		this.agreementPayAmount = agreementPayAmount;
	}

	@Length(min=1,max=20,message = "协议金额不能为空，且长度必须介于 0 和 20 之间")
	@DecimalMin(value = "0",message = "协议金额最低为0,且不能为空")
	public String getHospitalPayAmount() {
		return hospitalPayAmount;
	}

	public void setHospitalPayAmount(String hospitalPayAmount) {
		this.hospitalPayAmount = hospitalPayAmount;
	}
	
	@Length(min=1, max=20, message="医院赔付时间长度必须介于 0 和 20 之间")
	public String getHospitalPayTime() {
		return hospitalPayTime;
	}

	public void setHospitalPayTime(String hospitalPayTime) {
		this.hospitalPayTime = hospitalPayTime;
	}

	@Length(min=1,max=20,message = "协议金额不能为空，且长度必须介于 0 和 20 之间")
	@DecimalMin(value = "0",message = "协议金额最低为0,且不能为空")
	public String getInsurancePayAmount() {
		return insurancePayAmount;
	}

	public void setInsurancePayAmount(String insurancePayAmount) {
		this.insurancePayAmount = insurancePayAmount;
	}
	
	@Length(min=1, max=20, message="保险公司赔付时间长度必须介于 0 和 20 之间")
	public String getInsurancePayTime() {
		return insurancePayTime;
	}

	public void setInsurancePayTime(String insurancePayTime) {
		this.insurancePayTime = insurancePayTime;
	}
	
	@Length(min=0, max=32, message="下一处理环节长度必须介于 0 和 32 之间")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
	
	@Length(min=1, max=32, message="下一环节处理人长度必须介于 0 和 32 之间")
	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}
	
}