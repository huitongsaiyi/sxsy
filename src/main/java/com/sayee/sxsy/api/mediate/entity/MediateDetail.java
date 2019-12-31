package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description
 */
public class MediateDetail extends DataEntity<MediateDetail> {
    private static final long serialVersionUID = 1L;
    private String complaintMainDetailId;
    private String complaintMainId;
    private String complaintMode;
    private String visitorName;
    private String visitorMobile;
    private String visitorDate;
    private String visitorNumber;
    private String patientRelation;
    private String isMajor;
    private String otherType;
    private String summaryOfDisputes;
    private String appeal;
    private String receptionEmployee;
    private String receptionDate;
    private String nextLink;
    private String nextLinkMan;
    private String createUser;
    private String updateUser;
    private String caseNumber;

    public String getComplaintMainDetailId() {
        return complaintMainDetailId;
    }

    public void setComplaintMainDetailId(String complaintMainDetailId) {
        this.complaintMainDetailId = complaintMainDetailId;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
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

    public String getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(String isMajor) {
        this.isMajor = isMajor;
    }

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
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

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
