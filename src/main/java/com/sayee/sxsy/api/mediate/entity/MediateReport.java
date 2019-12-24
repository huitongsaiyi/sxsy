package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @author www.donxon.com
 * @Description
 */
public class MediateReport extends DataEntity<MediateReport> {
    private static final long serialVersionUID = 1L;
    private String reportRegistrationId;
    private String complaintMainId;
    private String reportEmp;//报案人姓名
    private String patientMobile;//病人电话
    private String patientRelation;//与患者关系
    private String reportTime;//报案日期
    private String registrationEmp;//登记人员
    private String registrationTime;//登记日期
    private String disputeTime;//纠纷发生时间
    private String isMajor;//是否重大
    private String summaryOfDisputes;//投诉纠纷概要
    private String focus;//投诉纠纷焦点
    private String patientAsk;//患方要求
    private String nextLink;//下一环节
    private String nextLinkMan;//下一环节处理人
    private String createUser;//创建人员
    private String doctorMobile;//医方联系方式
    private String policyNumber;//保单号

    public String getReportRegistrationId() {
        return reportRegistrationId;
    }

    public void setReportRegistrationId(String reportRegistrationId) {
        this.reportRegistrationId = reportRegistrationId;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    public String getReportEmp() {
        return reportEmp;
    }

    public void setReportEmp(String reportEmp) {
        this.reportEmp = reportEmp;
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
    }

    public String getPatientRelation() {
        return patientRelation;
    }

    public void setPatientRelation(String patientRelation) {
        this.patientRelation = patientRelation;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getRegistrationEmp() {
        return registrationEmp;
    }

    public void setRegistrationEmp(String registrationEmp) {
        this.registrationEmp = registrationEmp;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getDisputeTime() {
        return disputeTime;
    }

    public void setDisputeTime(String disputeTime) {
        this.disputeTime = disputeTime;
    }

    public String getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(String isMajor) {
        this.isMajor = isMajor;
    }

    public String getSummaryOfDisputes() {
        return summaryOfDisputes;
    }

    public void setSummaryOfDisputes(String summaryOfDisputes) {
        this.summaryOfDisputes = summaryOfDisputes;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getPatientAsk() {
        return patientAsk;
    }

    public void setPatientAsk(String patientAsk) {
        this.patientAsk = patientAsk;
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

    public String getDoctorMobile() {
        return doctorMobile;
    }

    public void setDoctorMobile(String doctorMobile) {
        this.doctorMobile = doctorMobile;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
}
