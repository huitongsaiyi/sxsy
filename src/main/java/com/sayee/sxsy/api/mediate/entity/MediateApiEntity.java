package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @author www.donxon.com
 * @Description 报案登记
 */
public class MediateApiEntity extends DataEntity<MediateApiEntity> {
    //INSERT INTO COMPLAINT_MAIN(complaint_main_id,case_number,patient_name,patient_sex,patient_age,patient_card,patient_mobile,involve_hospital,hospital_level,hospital_grade,involve_department,involve_employee,proc_ins_id,create_by,create_date,update_by,update_date,del_flag,source) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )
    private static final long serialVersionUID = 1L;
    private String complaintMainId;//id
    private String caseNumber;//案件编号
    private String patientName;//患者姓名
    private String patientSex;//性别
    private Integer patientAge;//年龄
    private String patientCard;//患者身份证(!exist)
    private String patientMobile;//患者联系方式
    private String involveHospital;//涉及医院
    private String hospitalLevel;//医院级别
    private String hospitalGrade;//医院等级
    private String involveDepartment;//涉及科室
    private String involveEmployee;//涉及人员
    private String procInsid;//流程实例id
    private String source;//案件来源

    private String visitorDate;//发生时间
    private String summaryOfDisputes;//纠纷概要
    private String appeal;//诉求
    private String mistake;//存在过错
    private String status;//调解状态 0处理中 1 2调查取证 3执政调解 4 评估坚定 5达成调解 6签署协议 7履行协议
    private String createUser;
    private String updateUser;
    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
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

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientCard() {
        return patientCard;
    }

    public void setPatientCard(String patientCard) {
        this.patientCard = patientCard;
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
    }

    public String getInvolveHospital() {
        return involveHospital;
    }

    public void setInvolveHospital(String involveHospital) {
        this.involveHospital = involveHospital;
    }

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getHospitalGrade() {
        return hospitalGrade;
    }

    public void setHospitalGrade(String hospitalGrade) {
        this.hospitalGrade = hospitalGrade;
    }

    public String getInvolveDepartment() {
        return involveDepartment;
    }

    public void setInvolveDepartment(String involveDepartment) {
        this.involveDepartment = involveDepartment;
    }

    public String getInvolveEmployee() {
        return involveEmployee;
    }

    public void setInvolveEmployee(String involveEmployee) {
        this.involveEmployee = involveEmployee;
    }



    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVisitorDate() {
        return visitorDate;
    }

    public void setVisitorDate(String visitorDate) {
        this.visitorDate = visitorDate;
    }

    public String getSummaryOfDisputes() {
        return summaryOfDisputes;
    }

    public void setSummaryOfDisputes(String summaryOfDisputes) {
        this.summaryOfDisputes = summaryOfDisputes;
    }

    public String getAppeal() {
        return appeal;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal;
    }

    public String getMistake() {
        return mistake;
    }

    public void setMistake(String mistake) {
        this.mistake = mistake;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    private String createby;//来访者

    public String getProcInsid() {
        return procInsid;
    }

    public void setProcInsid(String procInsid) {
        this.procInsid = procInsid;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
