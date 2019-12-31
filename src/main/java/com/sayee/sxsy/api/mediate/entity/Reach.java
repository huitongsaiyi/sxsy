package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description
 */
public class Reach extends DataEntity<Reach> {
    private static final long serialVersionUID = 1L;
    private String reachMediateId;
    private String complaintMainId;
    private String reaMediateResult;//调解结果 1成功 2失败
    private String reaSummary;//会议总结
    private String reaUserId;//医调委人员 多人逗号隔开
    private String reaPatient;//患方
    private String reaDoctor;//医方
    private String reaCaseInfo;//案件
    private String reaAddress;//地点
    private String reaMeetingTime;//会议时间
    private String handlePeople;
    private String handleTime;
    private String nextLink;
    private String nextLinkMan;
    private String createUser;

    public String getReachMediateId() {
        return reachMediateId;
    }

    public void setReachMediateId(String reachMediateId) {
        this.reachMediateId = reachMediateId;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    public String getReaMediateResult() {
        return reaMediateResult;
    }

    public void setReaMediateResult(String reaMediateResult) {
        this.reaMediateResult = reaMediateResult;
    }

    public String getReaSummary() {
        return reaSummary;
    }

    public void setReaSummary(String reaSummary) {
        this.reaSummary = reaSummary;
    }

    public String getReaUserId() {
        return reaUserId;
    }

    public void setReaUserId(String reaUserId) {
        this.reaUserId = reaUserId;
    }

    public String getReaPatient() {
        return reaPatient;
    }

    public void setReaPatient(String reaPatient) {
        this.reaPatient = reaPatient;
    }

    public String getReaDoctor() {
        return reaDoctor;
    }

    public void setReaDoctor(String reaDoctor) {
        this.reaDoctor = reaDoctor;
    }

    public String getReaCaseInfo() {
        return reaCaseInfo;
    }

    public void setReaCaseInfo(String reaCaseInfo) {
        this.reaCaseInfo = reaCaseInfo;
    }

    public String getReaAddress() {
        return reaAddress;
    }

    public void setReaAddress(String reaAddress) {
        this.reaAddress = reaAddress;
    }

    public String getReaMeetingTime() {
        return reaMeetingTime;
    }

    public void setReaMeetingTime(String reaMeetingTime) {
        this.reaMeetingTime = reaMeetingTime;
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
