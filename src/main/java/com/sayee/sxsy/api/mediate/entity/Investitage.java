package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description 调查取证
 */
public class Investitage extends DataEntity<Investitage> {
    private static final long serialVersionUID = 1L;
    private String investigateEvidenceId;
    private String complaintMainId;
    private String startTime;//接收参数:调查开始时间
    private String endTime;//接收参数:调查结束时间
    private String address;//接收参数:调查地点
    private String cause;//接收参数:调查事由
    private String investigator;//接收参数:调查人
    private String noteTaker;//接收参数:调查记录人
    private String content;//接收参数:笔录内容
    private String focus;//接收参数:反应焦点
    private String investigateType;//接收参数:被调查类型，1患方，2医方
    private String handlePeople;//接收参数：处理人
    private String handleTime;
    private String nextLink;
    private String nextLinkMan;//接收参数:下一处理人
    private String createUser;//接收参数:创建人

    public String getInvestigateEvidenceId() {
        return investigateEvidenceId;
    }

    public void setInvestigateEvidenceId(String investigateEvidenceId) {
        this.investigateEvidenceId = investigateEvidenceId;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getInvestigator() {
        return investigator;
    }

    public void setInvestigator(String investigator) {
        this.investigator = investigator;
    }

    public String getNoteTaker() {
        return noteTaker;
    }

    public void setNoteTaker(String noteTaker) {
        this.noteTaker = noteTaker;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getInvestigateType() {
        return investigateType;
    }

    public void setInvestigateType(String investigateType) {
        this.investigateType = investigateType;
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
