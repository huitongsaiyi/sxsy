package com.sayee.sxsy.api.mediate.entity;

import java.io.Serializable;

/**
 * @author www.donxon.com
 * @Description 通用字段
 */
public class MediateCommon implements Serializable {
    private static final long serialVersionUID = 1L;
    private String caseNumber;
    private String complaintMode;
    private String visitorName;
    private String visitorMobile;
    private String visitorDate;
    private String visitorNumber;
    private String patientRelation;
    private String summaryOfDisputes;
    private String appeal;
    private String receptionEmployee;
    private String receptionDate;
    private String nextLinkMan;
    private String patientName;
    private String status;
    private String patientSex;
    private String patientAge;
    private String involveHospital;

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getComplaintMode() {
        return complaintMode;
    }

    public void setComplaintMode(String complaintMode) {
        this.complaintMode = complaintMode;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorMobile() {
        return visitorMobile;
    }

    public void setVisitorMobile(String visitorMobile) {
        this.visitorMobile = visitorMobile;
    }

    public String getVisitorDate() {
        return visitorDate;
    }

    public void setVisitorDate(String visitorDate) {
        this.visitorDate = visitorDate;
    }

    public String getVisitorNumber() {
        return visitorNumber;
    }

    public void setVisitorNumber(String visitorNumber) {
        this.visitorNumber = visitorNumber;
    }

    public String getPatientRelation() {
        return patientRelation;
    }

    public void setPatientRelation(String patientRelation) {
        this.patientRelation = patientRelation;
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

    public String getReceptionEmployee() {
        return receptionEmployee;
    }

    public void setReceptionEmployee(String receptionEmployee) {
        this.receptionEmployee = receptionEmployee;
    }

    public String getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(String receptionDate) {
        this.receptionDate = receptionDate;
    }

    public String getNextLinkMan() {
        return nextLinkMan;
    }

    public void setNextLinkMan(String nextLinkMan) {
        this.nextLinkMan = nextLinkMan;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getInvolveHospital() {
        return involveHospital;
    }

    public void setInvolveHospital(String involveHospital) {
        this.involveHospital = involveHospital;
    }
}
