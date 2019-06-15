/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessappraisal.entity;

import com.google.common.collect.Lists;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.proposal.entity.Proposal;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.List;

/**
 * 评估鉴定Entity
 * @author gbq
 * @version 2019-06-13
 */
public class AssessAppraisal extends DataEntity<AssessAppraisal> {
	
	private static final long serialVersionUID = 1L;
	private String assessAppraisalId;		// 评估鉴定主键
	private String complaintMainId;		// 主表主键
	private String applyType;		// 申请类型
	private String responsibilityRatio;		// 责任比例
	private String host;		// 主持人
	private String clerk;		// 书记员
	private String patientName;		// 患者姓名
	private String patientSex;		// 患者性别
	private String patientAge;		// 患者年龄
	private String hospitalNumber;		// 住院号
	private String involveHospital;		// 涉及医院
	private String diagnosticAnalysis;		// 诊断分析
	private String treatmentAnalysis;		// 治疗分析
	private String otherMedicalAnalysis;		// 其他医疗分析
	private String medicalExpert;		// 医学专家
	private String legalExpert;		// 法律顾问
	private String other;		// 其他
	private String handlePeople;		// 处理人
	private String handleTime;		// 处理日期
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	private RecordInfo recordInfo1;//评估鉴定笔录(患方)
	private RecordInfo recordInfo2;//评估鉴定笔录(医方)
	private AuditAcceptance auditAcceptance;//保单号
	private ComplaintMain complaintMain;		// 关联主表主键
	private User user;  //当前登录人员
	private Area area;
	private User linkEmployee;		// 下一环节人员
	private User dcEmployee;		// 登记人员
	private ReportRegistration reportRegistration;//报案人姓名
	private Proposal proposal;//意见书
	private List<PatientLinkEmp> patientLinkEmps1 = Lists.newArrayList();//患方信息
	private List<PatientLinkEmp> patientLinkEmps = Lists.newArrayList();//患方联系人信息
	private List<MedicalOfficeEmp> medicalofficeempList2 = Lists.newArrayList();//医方信息

	public AssessAppraisal() {
		super();
	}

	public AssessAppraisal(String id){
		super(id);
	}

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public List<PatientLinkEmp> getPatientLinkEmps() {
		return patientLinkEmps;
	}

	public void setPatientLinkEmps(List<PatientLinkEmp> patientLinkEmps) {
		this.patientLinkEmps = patientLinkEmps;
	}

	public List<MedicalOfficeEmp> getMedicalofficeempList2() {
		return medicalofficeempList2;
	}

	public void setMedicalofficeempList2(List<MedicalOfficeEmp> medicalofficeempList2) {
		this.medicalofficeempList2 = medicalofficeempList2;
	}

	public List<PatientLinkEmp> getPatientLinkEmps1() {
		return patientLinkEmps1;
	}

	public void setPatientLinkEmps1(List<PatientLinkEmp> patientLinkEmps1) {
		this.patientLinkEmps1 = patientLinkEmps1;
	}

	public ReportRegistration getReportRegistration() {
		return reportRegistration;
	}

	public void setReportRegistration(ReportRegistration reportRegistration) {
		this.reportRegistration = reportRegistration;
	}

	public User getDcEmployee() {
		return dcEmployee;
	}

	public void setDcEmployee(User dcEmployee) {
		this.dcEmployee = dcEmployee;
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

	public AuditAcceptance getAuditAcceptance() {
		return auditAcceptance;
	}

	public void setAuditAcceptance(AuditAcceptance auditAcceptance) {
		this.auditAcceptance = auditAcceptance;
	}

	public RecordInfo getRecordInfo1() {
		return recordInfo1;
	}

	public void setRecordInfo1(RecordInfo recordInfo1) {
		this.recordInfo1 = recordInfo1;
	}

	public RecordInfo getRecordInfo2() {
		return recordInfo2;
	}

	public void setRecordInfo2(RecordInfo recordInfo2) {
		this.recordInfo2 = recordInfo2;
	}


	public String getAssessAppraisalId() {
		return assessAppraisalId;
	}

	public void setAssessAppraisalId(String assessAppraisalId) {
		this.assessAppraisalId = assessAppraisalId;
	}
	

	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=0, max=1, message="申请类型长度必须介于 0 和 1 之间")
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	@Length(min=0, max=10, message="责任比例长度必须介于 0 和 10 之间")
	public String getResponsibilityRatio() {
		return responsibilityRatio;
	}

	public void setResponsibilityRatio(String responsibilityRatio) {
		this.responsibilityRatio = responsibilityRatio;
	}
	
	@Length(min=0, max=32, message="主持人长度必须介于 0 和 32 之间")
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	@Length(min=0, max=32, message="书记员长度必须介于 0 和 32 之间")
	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}
	
	@Length(min=0, max=10, message="患者姓名长度必须介于 0 和 10 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Length(min=0, max=1, message="患者性别长度必须介于 0 和 1 之间")
	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	
	@Length(min=0, max=4, message="患者年龄长度必须介于 0 和 4 之间")
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	
	@Length(min=0, max=10, message="住院号长度必须介于 0 和 10 之间")
	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}
	
	@Length(min=0, max=32, message="涉及医院长度必须介于 0 和 32 之间")
	public String getInvolveHospital() {
		return involveHospital;
	}

	public void setInvolveHospital(String involveHospital) {
		this.involveHospital = involveHospital;
	}
	
	@Length(min=0, max=10, message="诊断分析长度必须介于 0 和 10 之间")
	public String getDiagnosticAnalysis() {
		return diagnosticAnalysis;
	}

	public void setDiagnosticAnalysis(String diagnosticAnalysis) {
		this.diagnosticAnalysis = diagnosticAnalysis;
	}
	
	@Length(min=0, max=10, message="治疗分析长度必须介于 0 和 10 之间")
	public String getTreatmentAnalysis() {
		return treatmentAnalysis;
	}

	public void setTreatmentAnalysis(String treatmentAnalysis) {
		this.treatmentAnalysis = treatmentAnalysis;
	}
	
	@Length(min=0, max=10, message="其他医疗分析长度必须介于 0 和 10 之间")
	public String getOtherMedicalAnalysis() {
		return otherMedicalAnalysis;
	}

	public void setOtherMedicalAnalysis(String otherMedicalAnalysis) {
		this.otherMedicalAnalysis = otherMedicalAnalysis;
	}
	
	@Length(min=0, max=32, message="医学专家长度必须介于 0 和 32 之间")
	public String getMedicalExpert() {
		return medicalExpert;
	}

	public void setMedicalExpert(String medicalExpert) {
		this.medicalExpert = medicalExpert;
	}
	
	@Length(min=0, max=32, message="法律顾问长度必须介于 0 和 32 之间")
	public String getLegalExpert() {
		return legalExpert;
	}

	public void setLegalExpert(String legalExpert) {
		this.legalExpert = legalExpert;
	}
	
	@Length(min=0, max=200, message="其他长度必须介于 0 和 200 之间")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
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