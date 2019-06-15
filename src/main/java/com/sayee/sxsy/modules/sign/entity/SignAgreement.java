/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sign.entity;

import com.google.common.collect.Lists;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.List;

/**
 * 签署协议Entity
 * @author zhangfan
 * @version 2019-06-14
 */
public class SignAgreement extends DataEntity<SignAgreement> {
	
	private static final long serialVersionUID = 1L;
	private String signAgreementId;		// 签署协议主键
	private String complaintMainId;		// 主表主键
	private ComplaintMain complaintMain;  //主表
	private AuditAcceptance auditAcceptance;//保单号
	private Area area;
	private ReportRegistration reportRegistration;//报案人姓名
	private User user;  //当前登录人员
//	private User dlEmployee;   //代理人实体类，先留着，后期又可能选择人员树
//	private Office sqOffice; // 申请医院
//	private Office sjOffice; // 涉及医院
	private User linkEmployee;		// 下一环节人员
	private String agreementNumber;		// 协议号
	private String ratifyAccord;		// 签署协议/判决时间
	private String agreementAmount;		// 协议金额
	private String insuranceAmount;		// 保险金额
	private String claimSettlementTime;		// 交理赔时间
	private String summaryOfDisputes;		// 纠纷概要
	private String mediation;		// 调解情况 多个逗号
	private List<TypeInfo> mediationList;		// 调解情况 多个逗号
	private String agreedMatter;		// 协议约定事项  多个逗号隔开
	private List<TypeInfo> meatterList; //协议约定事项
	private String performAgreementMode;		// 履行协议方式  多个逗号隔开
	private List<TypeInfo> performList;
	private String agreementExplain;		// 协议说明  多个逗号隔开
	private List<TypeInfo> agreementList;
	private String compensateTime;		// 赔付时间
	private String handlePeople;		// 处理人
	private String handleTime;		// 处理日期
	private String nextLink;		// next_link
	private String nextLinkMan;		// next_link_man
	private String beginCompensateTime;		// 开始 赔付时间
	private String endCompensateTime;		// 结束 赔付时间
	private List<PatientLinkEmp> patientLinkEmpList = Lists.newArrayList();     //关联患方联系人
	private List<PatientLinkEmp> patientLinkDList = Lists.newArrayList();		//患方代理人
	private List<MedicalOfficeEmp> medicalOfficeEmpList = Lists.newArrayList();		//医方管理人员


	public SignAgreement() {
		super();
	}

	public SignAgreement(String id){
		super(id);
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	public List<PatientLinkEmp> getPatientLinkEmpList() {
		return patientLinkEmpList;
	}

	public void setPatientLinkEmpList(List<PatientLinkEmp> patientLinkEmpList) {
		this.patientLinkEmpList = patientLinkEmpList;
	}

	public List<PatientLinkEmp> getPatientLinkDList() {
		return patientLinkDList;
	}

	public void setPatientLinkDList(List<PatientLinkEmp> patientLinkDList) {
		this.patientLinkDList = patientLinkDList;
	}

	public List<MedicalOfficeEmp> getMedicalOfficeEmpList() {
		return medicalOfficeEmpList;
	}

	public void setMedicalOfficeEmpList(List<MedicalOfficeEmp> medicalOfficeEmpList) {
		this.medicalOfficeEmpList = medicalOfficeEmpList;
	}

	public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	public List<TypeInfo> getMediationList() {
		return mediationList;
	}

	public void setMediationList(List<TypeInfo> mediationList) {
		this.mediationList = mediationList;
	}

	public List<TypeInfo> getMeatterList() {
		return meatterList;
	}

	public void setMeatterList(List<TypeInfo> meatterList) {
		this.meatterList = meatterList;
	}

	public List<TypeInfo> getPerformList() {
		return performList;
	}

	public void setPerformList(List<TypeInfo> performList) {
		this.performList = performList;
	}

	public List<TypeInfo> getAgreementList() {
		return agreementList;
	}

	public void setAgreementList(List<TypeInfo> agreementList) {
		this.agreementList = agreementList;
	}

	public AuditAcceptance getAuditAcceptance() {
		return auditAcceptance;
	}

	public void setAuditAcceptance(AuditAcceptance auditAcceptance) {
		this.auditAcceptance = auditAcceptance;
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

	@Length(min=1, max=32, message="签署协议主键长度必须介于 1 和 32 之间")
	public String getSignAgreementId() {
		return signAgreementId;
	}

	public void setSignAgreementId(String signAgreementId) {
		this.signAgreementId = signAgreementId;
	}
	
	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=0, max=20, message="协议号长度必须介于 0 和 20 之间")
	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}
	
	@Length(min=0, max=20, message="签署协议/判决时间长度必须介于 0 和 20 之间")
	public String getRatifyAccord() {
		return ratifyAccord;
	}

	public void setRatifyAccord(String ratifyAccord) {
		this.ratifyAccord = ratifyAccord;
	}
	
	public String getAgreementAmount() {
		return agreementAmount;
	}

	public void setAgreementAmount(String agreementAmount) {
		this.agreementAmount = agreementAmount;
	}
	
	public String getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	
	@Length(min=0, max=20, message="交理赔时间长度必须介于 0 和 20 之间")
	public String getClaimSettlementTime() {
		return claimSettlementTime;
	}

	public void setClaimSettlementTime(String claimSettlementTime) {
		this.claimSettlementTime = claimSettlementTime;
	}
	
	public String getSummaryOfDisputes() {
		return summaryOfDisputes;
	}

	public void setSummaryOfDisputes(String summaryOfDisputes) {
		this.summaryOfDisputes = summaryOfDisputes;
	}
	
	@Length(min=0, max=200, message="调解情况 多个逗号长度必须介于 0 和 200 之间")
	public String getMediation() {
		return mediation;
	}

	public void setMediation(String mediation) {
		this.mediation = mediation;
	}
	
	@Length(min=0, max=200, message="协议约定事项  多个逗号隔开长度必须介于 0 和 200 之间")
	public String getAgreedMatter() {
		return agreedMatter;
	}

	public void setAgreedMatter(String agreedMatter) {
		this.agreedMatter = agreedMatter;
	}
	
	@Length(min=0, max=200, message="履行协议方式  多个逗号隔开长度必须介于 0 和 200 之间")
	public String getPerformAgreementMode() {
		return performAgreementMode;
	}

	public void setPerformAgreementMode(String performAgreementMode) {
		this.performAgreementMode = performAgreementMode;
	}
	
	@Length(min=0, max=200, message="协议说明  多个逗号隔开长度必须介于 0 和 200 之间")
	public String getAgreementExplain() {
		return agreementExplain;
	}

	public void setAgreementExplain(String agreementExplain) {
		this.agreementExplain = agreementExplain;
	}
	
	@Length(min=0, max=20, message="赔付时间长度必须介于 0 和 20 之间")
	public String getCompensateTime() {
		return compensateTime;
	}

	public void setCompensateTime(String compensateTime) {
		this.compensateTime = compensateTime;
	}
	
	@Length(min=0, max=32, message="处理人长度必须介于 0 和 32 之间")
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
	
	@Length(min=0, max=32, message="next_link长度必须介于 0 和 32 之间")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
	
	@Length(min=0, max=32, message="next_link_man长度必须介于 0 和 32 之间")
	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}
	
	public String getBeginCompensateTime() {
		return beginCompensateTime;
	}

	public void setBeginCompensateTime(String beginCompensateTime) {
		this.beginCompensateTime = beginCompensateTime;
	}
	
	public String getEndCompensateTime() {
		return endCompensateTime;
	}

	public void setEndCompensateTime(String endCompensateTime) {
		this.endCompensateTime = endCompensateTime;
	}
		
}