package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description
 */
public class Perform extends DataEntity<Perform> {
    private static final long serialVersionUID = 1L;
    private String performAgreementId;
    private String complaintMainId;
    private String agreementPayAmount;//协议赔付总金额
    private String hospitalPayAmount;//医院赔付金额
    private String hospitalPayTime;//医院赔付时间
    private String insurancePayAmount;//保险公司赔付金额
    private String insurancePayTime;//保险公司赔付时间
    private String nextLink;
    private String nextLinkMan;
    private String createUser;
    private String compensateTime;//赔付时间
    private String claimSettlementTime;//交理赔时间

    public String getPerformAgreementId() {
        return performAgreementId;
    }

    public void setPerformAgreementId(String performAgreementId) {
        this.performAgreementId = performAgreementId;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    public String getAgreementPayAmount() {
        return agreementPayAmount;
    }

    public void setAgreementPayAmount(String agreementPayAmount) {
        this.agreementPayAmount = agreementPayAmount;
    }

    public String getHospitalPayAmount() {
        return hospitalPayAmount;
    }

    public void setHospitalPayAmount(String hospitalPayAmount) {
        this.hospitalPayAmount = hospitalPayAmount;
    }

    public String getHospitalPayTime() {
        return hospitalPayTime;
    }

    public void setHospitalPayTime(String hospitalPayTime) {
        this.hospitalPayTime = hospitalPayTime;
    }

    public String getInsurancePayAmount() {
        return insurancePayAmount;
    }

    public void setInsurancePayAmount(String insurancePayAmount) {
        this.insurancePayAmount = insurancePayAmount;
    }

    public String getInsurancePayTime() {
        return insurancePayTime;
    }

    public void setInsurancePayTime(String insurancePayTime) {
        this.insurancePayTime = insurancePayTime;
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

    public String getCompensateTime() {
        return compensateTime;
    }

    public void setCompensateTime(String compensateTime) {
        this.compensateTime = compensateTime;
    }

    public String getClaimSettlementTime() {
        return claimSettlementTime;
    }

    public void setClaimSettlementTime(String claimSettlementTime) {
        this.claimSettlementTime = claimSettlementTime;
    }
}
