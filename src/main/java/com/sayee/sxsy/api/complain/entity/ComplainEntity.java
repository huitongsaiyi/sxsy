package com.sayee.sxsy.api.complain.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @author www.donxon.com
 * @Description
 */
public class ComplainEntity extends DataEntity<ComplainEntity> {
    private static final long serialVersionUID = 1L;
    private static String complaintId;//id
    private String patientName;//患者姓名
    private String visitorName;//投诉者姓名
    private String visitorMobil;//联系方式
    private String patientSex;//性别
    private Integer patientAge;//年龄
    private String involveHospi;//涉及医院
    private String involveDepar;//涉及科室
    private String visitorDate;//发生时间
    private String summaryOfDisputes;//纠纷概要
    private String appeal;//诉求

    public static String getComplaintId() {
        return complaintId;
    }

    public static void setComplaintId(String complaintId) {
        ComplainEntity.complaintId = complaintId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorMobil() {
        return visitorMobil;
    }

    public void setVisitorMobil(String visitorMobil) {
        this.visitorMobil = visitorMobil;
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

    public String getInvolveHospi() {
        return involveHospi;
    }

    public void setInvolveHospi(String involveHospi) {
        this.involveHospi = involveHospi;
    }

    public String getInvolveDepar() {
        return involveDepar;
    }

    public void setInvolveDepar(String involveDepar) {
        this.involveDepar = involveDepar;
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

    private String mistake;//存在过错
    private String status;//调解状态 0处理中 1审核受理 2调查取证 3执政调解 4 评估坚定 5达成调解 6签署协议 7履行协议


}
