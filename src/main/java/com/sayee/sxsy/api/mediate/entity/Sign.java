package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @author www.donxon.com
 * @Description
 */
public class Sign extends DataEntity<Sign> {
    private static final long serialVersionUID = 1L;
    private String signAgreementId;
    private String complaintMainId;
    private String agreementNumber;//协议号
    private String ratifyAccord;//签署时间
    private String agreementAmount;//协议金额
    private String insuranceAmount;//保险金额
    private String claimSettlementTime;//交理赔时间
    private String summaryOfDisputes;//纠纷概要
    private String mediation;//调解情况  多个逗号隔开
    private String agreedMatter;//协议约定事项  多个逗号隔开
    private String performAgreementMode;//履行协议方式 多个逗号隔开
    private String agreementExplain;//协议说明  多个逗号隔开
    private String compensateTime;//赔付时间
    private String handlePeople;//处理人
    private String handleTime;//处理时间
    private String nextLink;
    private String nextLinkMan;
    private String createUser;

    public String getSignAgreementId() {
        return signAgreementId;
    }

    public void setSignAgreementId(String signAgreementId) {
        this.signAgreementId = signAgreementId;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public String getRatifyAccord() {
        return ratifyAccord;
    }

    public void setRatifyAccord(String ratifyAccord) {
        this.ratifyAccord = ratifyAccord;
    }

    public String getAgreementAmount() {
        return agreementAmount;
    }

    public void setAgreementAmount(String agreementAmount) {
        this.agreementAmount = agreementAmount;
    }

    public String getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(String insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String getClaimSettlementTime() {
        return claimSettlementTime;
    }

    public void setClaimSettlementTime(String claimSettlementTime) {
        this.claimSettlementTime = claimSettlementTime;
    }

    public String getSummaryOfDisputes() {
        return summaryOfDisputes;
    }

    public void setSummaryOfDisputes(String summaryOfDisputes) {
        this.summaryOfDisputes = summaryOfDisputes;
    }

    public String getMediation() {
        return mediation;
    }

    public void setMediation(String mediation) {
        this.mediation = mediation;
    }

    public String getAgreedMatter() {
        return agreedMatter;
    }

    public void setAgreedMatter(String agreedMatter) {
        this.agreedMatter = agreedMatter;
    }

    public String getPerformAgreementMode() {
        return performAgreementMode;
    }

    public void setPerformAgreementMode(String performAgreementMode) {
        this.performAgreementMode = performAgreementMode;
    }

    public String getAgreementExplain() {
        return agreementExplain;
    }

    public void setAgreementExplain(String agreementExplain) {
        this.agreementExplain = agreementExplain;
    }

    public String getCompensateTime() {
        return compensateTime;
    }

    public void setCompensateTime(String compensateTime) {
        this.compensateTime = compensateTime;
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
}
