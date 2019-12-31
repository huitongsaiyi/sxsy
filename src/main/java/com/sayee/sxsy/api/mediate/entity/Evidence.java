package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description
 */
public class Evidence extends DataEntity<Evidence> {
    private static final long serialVersionUID = 1L;
    private String mediateEvidenceId;
    private String complaintMainId;
    private String mediateResult;//调解结果：1成功，2失败
    private String summary;//会议总结
    private String userId;//医调委人员 多人用逗号隔开
    private String patient;//患方
    private String doctor;//医方
    private String caseInfo;//案件
    private String address;//地点
    private String meetingTime;//会议时间
    private String handlePeople;//处理人
    private String handleTime;//处理时间
    private String nextLink;
    private String nextLinkMan;
    private String createUser;

    public String getMediateEvidenceId() {
        return mediateEvidenceId;
    }

    public void setMediateEvidenceId(String mediateEvidenceId) {
        this.mediateEvidenceId = mediateEvidenceId;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    public String getMediateResult() {
        return mediateResult;
    }

    public void setMediateResult(String mediateResult) {
        this.mediateResult = mediateResult;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(String caseInfo) {
        this.caseInfo = caseInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
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
