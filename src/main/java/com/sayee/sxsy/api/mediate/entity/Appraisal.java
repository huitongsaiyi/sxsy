package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @author www.donxon.com
 * @Description
 */
public class Appraisal extends DataEntity<Appraisal> {
    private static final long serialVersionUID = 1L;
    private String assessAppraisalId;
    private String complaintMainId;
    private String applyType;//接收参数:申请类型
    private String responsibilityRatio;//接收参数：责任度
    private String host;//接收参数:主持人
    private String clerk;//接收参数:书记员
    private String patientName;//接收参数:患者姓名
    private String patientSex;//接收参数:患者性别
    private String patientAge;//接收参数:患者年龄
    private String hospitalNumber;//接收参数:住院号
    private String involveHospital;//接收参数:涉及医院
    private String diagnosticAnalysis;//接收参数:诊断分析
    private String treatmentAnalysis;//接收参数:治疗分析
    private String otherMedicalAnalysis;//接收参数:其他医疗分析
    private String eighteenItems;//接收参数:违反18项核心制度
    private String medicalExpert;//接收参数:医学专家分析
    private String legalExpert;//接收参数:法律顾问分析
    private String other;//接收参数:其他
    private String handlePeople;//接收参数:处理人
    private String handleTime;//接收参数:处理日期
    private String doctorClear;//接收参数:医方是否清楚
    private String patientClear;//接收参数:患方是否清楚
    private String patientAvoid;//接收参数:患方回避
    private String doctorAvoid;//接收参数:医方回避
    private String nextLink;
    private String nextLinkMan;//接收参数:下一环节处理人
    private String createUser;//接收参数:创建者
    private String medicalExpertName;//接收参数:医学专家
    private String legalExpertName;//接收参数:法律专家
    private String scale;//接收参数:比例

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

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getResponsibilityRatio() {
        return responsibilityRatio;
    }

    public void setResponsibilityRatio(String responsibilityRatio) {
        this.responsibilityRatio = responsibilityRatio;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getClerk() {
        return clerk;
    }

    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(String hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    public String getInvolveHospital() {
        return involveHospital;
    }

    public void setInvolveHospital(String involveHospital) {
        this.involveHospital = involveHospital;
    }

    public String getDiagnosticAnalysis() {
        return diagnosticAnalysis;
    }

    public void setDiagnosticAnalysis(String diagnosticAnalysis) {
        this.diagnosticAnalysis = diagnosticAnalysis;
    }

    public String getTreatmentAnalysis() {
        return treatmentAnalysis;
    }

    public void setTreatmentAnalysis(String treatmentAnalysis) {
        this.treatmentAnalysis = treatmentAnalysis;
    }

    public String getOtherMedicalAnalysis() {
        return otherMedicalAnalysis;
    }

    public void setOtherMedicalAnalysis(String otherMedicalAnalysis) {
        this.otherMedicalAnalysis = otherMedicalAnalysis;
    }

    public String getEighteenItems() {
        return eighteenItems;
    }

    public void setEighteenItems(String eighteenItems) {
        this.eighteenItems = eighteenItems;
    }

    public String getMedicalExpert() {
        return medicalExpert;
    }

    public void setMedicalExpert(String medicalExpert) {
        this.medicalExpert = medicalExpert;
    }

    public String getLegalExpert() {
        return legalExpert;
    }

    public void setLegalExpert(String legalExpert) {
        this.legalExpert = legalExpert;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getHandlePeople() {
        return handlePeople;
    }

    public void setHandlePeople(String handlePeople) {
        this.handlePeople = handlePeople;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getMedicalExpertName() {
        return medicalExpertName;
    }

    public void setMedicalExpertName(String medicalExpertName) {
        this.medicalExpertName = medicalExpertName;
    }

    public String getLegalExpertName() {
        return legalExpertName;
    }

    public void setLegalExpertName(String legalExpertName) {
        this.legalExpertName = legalExpertName;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
