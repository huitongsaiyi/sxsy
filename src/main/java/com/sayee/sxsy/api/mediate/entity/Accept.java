package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @author www.donxon.com
 * @Description
 */
public class Accept extends DataEntity<Accept> {
    private static final long serialVersionUID = 1L;
    private String auditAcceptanceId;
    private String complaintMainId;
    private String summaryOfDisputes;//纠纷概要
    private String caseSource;//案件来源
    private String guaranteeTime;//起保日期
    private String insuranceCompany;//保险公司
    private String policyNumber;//保单号
    private String diagnosisMode;//诊疗方式
    private String treatmentOutcome;//诊疗结果
    private String handlePeople;//处理人
    private String handleTime;//处理日期
    private String nextLink;//下一环节
    private String createUser;//创建者
    private String nextLinkMan;//下一环节处理人

    public String getAuditAcceptanceId() {
        return auditAcceptanceId;
    }

    public void setAuditAcceptanceId(String auditAcceptanceId) {
        this.auditAcceptanceId = auditAcceptanceId;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    public String getSummaryOfDisputes() {
        return summaryOfDisputes;
    }

    public void setSummaryOfDisputes(String summaryOfDisputes) {
        this.summaryOfDisputes = summaryOfDisputes;
    }

    public String getCaseSource() {
        return caseSource;
    }

    public void setCaseSource(String caseSource) {
        this.caseSource = caseSource;
    }

    public String getGuaranteeTime() {
        return guaranteeTime;
    }

    public void setGuaranteeTime(String guaranteeTime) {
        this.guaranteeTime = guaranteeTime;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getDiagnosisMode() {
        return diagnosisMode;
    }

    public void setDiagnosisMode(String diagnosisMode) {
        this.diagnosisMode = diagnosisMode;
    }

    public String getTreatmentOutcome() {
        return treatmentOutcome;
    }

    public void setTreatmentOutcome(String treatmentOutcome) {
        this.treatmentOutcome = treatmentOutcome;
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

    public String getNextLink() {
        return nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getNextLinkMan() {
        return nextLinkMan;
    }

    public void setNextLinkMan(String nextLinkMan) {
        this.nextLinkMan = nextLinkMan;
    }
}
