/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessappraisal.entity;

import com.google.common.collect.Lists;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
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
	private User hosts;//主持人
	private User clerks;//书记员
	private String patientName;		// 患者姓名
	private String patientSex;		// 患者性别
	private String patientAge;		// 患者年龄
	private String hospitalNumber;		// 住院号
	private String involveHospital;		// 涉及医院
	private String diagnosticAnalysis;		// 诊断分析
	private String treatmentAnalysis;		// 治疗分析
	private String otherMedicalAnalysis;		// 其他医疗分析
	private String eighteenItems;		// 违反18项
	private String medicalExpert;		// 医学专家
	private String legalExpert;		// 法律顾问
	private String calculatedAmount;		// 计算金额
	private String other;		// 其他
	private String handlePeople;		// 处理人
	private String handleTime;		// 处理日期
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	private RecordInfo recordInfo1;//评估鉴定笔录(患方)
	private AuditAcceptance auditAcceptance;//保单号
	private ComplaintMain complaintMain;		// 关联主表主键
	private User user;  //当前登录人员
	private Area area;
	private User linkEmployee;		// 下一环节人员
	private User dcEmployee;		// 登记人员
	private ReportRegistration reportRegistration;//报案人姓名
	private Proposal proposal;//意见书
	private List<PatientLinkEmp> patientLinkEmpList = Lists.newArrayList();     //患方信息
	private List<PatientLinkEmp> patientLinkDList = Lists.newArrayList();		//联系人
	private List<MedicalOfficeEmp> medicalOfficeEmpList = Lists.newArrayList();		//医方信息
	private List<TypeInfo> typeInfosList;//意见
	private List<TypeInfo> typeInfosList2;//结论
    private MachineAccount machineAccount;//台账信息
	private String medicalExpertName;//医学专家（名称）
	private String legalExpertName;//法律专家（名称）
	private String doctorClear;	//医方是否听清楚
	private String patientClear;	//患方是否听清楚
	private String patientAvoid;	//患方是否回避
	private String doctorAvoid;	//医方是否回避
	private String scale;//比例
	private List<String> list;//工作站人员list

	private String createUser;  //创建人员id

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	@Length(min=1, max=10, message="涉及核心制度不能为空!")
	public String getEighteenItems() {
		return eighteenItems;
	}

	public void setEighteenItems(String eighteenItems) {
		this.eighteenItems = eighteenItems;
	}

	@Length(min=1, max=10, message="责任比例不能为空!")
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getDoctorAvoid() {
		return doctorAvoid;
	}

	public void setDoctorAvoid(String doctorAvoid) {
		this.doctorAvoid = doctorAvoid;
	}

	public String getDoctorClear() {
		return doctorClear;
	}

	public void setDoctorClear(String doctorClear) {
		this.doctorClear = doctorClear;
	}

	public String getPatientAvoid() {
		return patientAvoid;
	}

	public void setPatientAvoid(String patientAvoid) {
		this.patientAvoid = patientAvoid;
	}

	public String getPatientClear() {
		return patientClear;
	}

	public void setPatientClear(String patientClear) {
		this.patientClear = patientClear;
	}

	public User getClerks() {
		return clerks;
	}

	public void setClerks(User clerks) {
		this.clerks = clerks;
	}

	public User getHosts() {
		return hosts;
	}

	public void setHosts(User hosts) {
		this.hosts = hosts;
	}

	//@Length(min=1, max=30, message="法律专家长度必须介于 1 和 30 之间")
	public String getLegalExpertName() {
		return legalExpertName;
	}

	public void setLegalExpertName(String legalExpertName) {
		this.legalExpertName = legalExpertName;
	}
	//@Length(min=1, max=30, message="医学专家长度必须介于 1 和 30 之间")
	public String getMedicalExpertName() {
		return medicalExpertName;
	}

	public void setMedicalExpertName(String medicalExpertName) {
		this.medicalExpertName = medicalExpertName;
	}

	public MachineAccount getMachineAccount() {
        return machineAccount;
    }

    public void setMachineAccount(MachineAccount machineAccount) {
        this.machineAccount = machineAccount;
    }

	public List<TypeInfo> getTypeInfosList2() {
		return typeInfosList2;
	}

	public void setTypeInfosList2(List<TypeInfo> typeInfosList2) {
		this.typeInfosList2 = typeInfosList2;
	}

	public List<TypeInfo> getTypeInfosList() {
		return typeInfosList;
	}

	public void setTypeInfosList(List<TypeInfo> typeInfosList) {
		this.typeInfosList = typeInfosList;
	}

	public String getCalculatedAmount() {
		return calculatedAmount;
	}

	public void setCalculatedAmount(String calculatedAmount) {
		this.calculatedAmount = calculatedAmount;
	}

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

	public List<MedicalOfficeEmp> getMedicalOfficeEmpList() {
		return medicalOfficeEmpList;
	}

	public void setMedicalOfficeEmpList(List<MedicalOfficeEmp> medicalOfficeEmpList) {
		this.medicalOfficeEmpList = medicalOfficeEmpList;
	}

	public List<PatientLinkEmp> getPatientLinkDList() {
		return patientLinkDList;
	}

	public void setPatientLinkDList(List<PatientLinkEmp> patientLinkDList) {
		this.patientLinkDList = patientLinkDList;
	}

	public List<PatientLinkEmp> getPatientLinkEmpList() {
		return patientLinkEmpList;
	}

	public void setPatientLinkEmpList(List<PatientLinkEmp> patientLinkEmpList) {
		this.patientLinkEmpList = patientLinkEmpList;
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

	//@Length(min=0, max=1, message="申请类型长度必须介于 0 和 1 之间")
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	@Length(min=1, max=10, message="责任度不能为空!")
	public String getResponsibilityRatio() {
		return responsibilityRatio;
	}

	public void setResponsibilityRatio(String responsibilityRatio) {
		this.responsibilityRatio = responsibilityRatio;
	}

	//@Length(min=0, max=32, message="主持人长度必须介于 0 和 32 之间")
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	//@Length(min=0, max=32, message="书记员长度必须介于 0 和 32 之间")
	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}

	//@Length(min=1, max=10, message="患者姓名长度必须介于 1 和 10 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	//@Length(min=1, max=1, message="患者性别长度必须介于 1 和 1 之间")
	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	//@Length(min=1, max=4, message="患者年龄长度必须介于 1 和 4 之间")
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	//@Length(min=1, max=10, message="住院号长度必须介于 1 和 10 之间")
	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}

	//@Length(min=0, max=32, message="涉及医院长度必须介于 0 和 32 之间")
	public String getInvolveHospital() {
		return involveHospital;
	}

	public void setInvolveHospital(String involveHospital) {
		this.involveHospital = involveHospital;
	}

	//@Length(min=1, max=100, message="诊断分析长度必须介于 1 和 10000 之间")
	public String getDiagnosticAnalysis() {
		return diagnosticAnalysis;
	}

	public void setDiagnosticAnalysis(String diagnosticAnalysis) {
		this.diagnosticAnalysis = diagnosticAnalysis;
	}

	//@Length(min=1, max=10000, message="治疗分析长度必须介于 1 和 10000 之间")
	public String getTreatmentAnalysis() {
		return treatmentAnalysis;
	}

	public void setTreatmentAnalysis(String treatmentAnalysis) {
		this.treatmentAnalysis = treatmentAnalysis;
	}

	//@Length(min=1, max=10000, message="其他医疗分析长度必须介于 1 和 10000 之间")
	public String getOtherMedicalAnalysis() {
		return otherMedicalAnalysis;
	}

	public void setOtherMedicalAnalysis(String otherMedicalAnalysis) {
		this.otherMedicalAnalysis = otherMedicalAnalysis;
	}

	//@Length(min=1, max=32, message="医学专家长度必须介于 1 和 32 之间")
	public String getMedicalExpert() {
		return medicalExpert;
	}

	public void setMedicalExpert(String medicalExpert) {
		this.medicalExpert = medicalExpert;
	}

	//@Length(min=1, max=32, message="法律顾问长度必须介于 1 和 32 之间")
	public String getLegalExpert() {
		return legalExpert;
	}

	public void setLegalExpert(String legalExpert) {
		this.legalExpert = legalExpert;
	}

	//@Length(min=1, max=200, message="其他长度必须介于 1 和 200 之间")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	//@Length(min=0, max=32, message="处理人长度必须介于 0 和 32 之间")
	public String getHandlePeople() {
		return handlePeople;
	}

	public void setHandlePeople(String handlePeople) {
		this.handlePeople = handlePeople;
	}

	//@Length(min=0, max=20, message="处理日期长度必须介于 0 和 20 之间")
	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	//@Length(min=0, max=32, message="下一处理环节长度必须介于 0 和 32 之间")
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