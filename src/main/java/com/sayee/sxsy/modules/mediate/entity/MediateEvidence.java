/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.mediate.entity;

import com.google.common.collect.Lists;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.program.entity.MediateProgram;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;
import com.sayee.sxsy.modules.sys.entity.User;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.List;

/**
 * 质证调解Entity
 *
 * @author lyt
 * @version 2019-06-10
 */
public class MediateEvidence extends DataEntity<MediateEvidence> {

    private static final long serialVersionUID = 1L;
    private String mediateEvidenceId;        // 质证调解主键
    private String complaintMainId;        // 主表主键
    private String mediator;		// 调解员
    private String clerk;		// 书记员
    private String other;		// 其他
    private String mediateResult;        // 调解结果  1成功 2 失败
    private String summary;        // 会议总结
    private String patient;        // 患方
    private String userId;        // 患方
    private String doctor;        // 医方
    private String caseInfoName;        // 案件
    private String meetingAddress;        // 地点
    private String meetingTime;        // 会议时间
    private String handlePeople;        // 处理人
    private String handleTime;        // 处理日期
    private String nextLink;        // next_link
    private String nextLinkMan;        // next_link_man
    private User user;        // 当前登录人员
    private User ytwUser;        // 医调委人员  多人用逗号隔开
    private User doctorUser;        // 医方实体类
    private User linkEmployee;		// 下一环节人员
    private ComplaintMain complaintMain;        //关联主表
    private List<MediateRecord> mediateEvidenceList = Lists.newArrayList();     //关联调解志子表
    private List<MediateProgram> mediateProgramList = Lists.newArrayList();     //关联调解程序子表
    private RecordInfo recordInfo;      //关联笔录子表
    private Area area;
    private AuditAcceptance auditAcceptance;
    private ReportRegistration reportRegistration;
    private MachineAccount machineAccount;


    public MediateEvidence() {
        super();
    }

    public MediateEvidence(String id) {
        super(id);
    }

    public List<MediateProgram> getMediateProgramList() {
        return mediateProgramList;
    }

    public void setMediateProgramList(List<MediateProgram> mediateProgramList) {
        this.mediateProgramList = mediateProgramList;
    }

    public String getMediator() {
        return mediator;
    }

    public void setMediator(String mediator) {
        this.mediator = mediator;
    }

    @Length(min = 0, max = 100, message = "书记员长度必须介于 0 和 100 之间")
    public String getClerk() {
        return clerk;
    }

    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    @Length(min = 0, max = 100, message = "其他长度必须介于 0 和 100 之间")
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public MachineAccount getMachineAccount() {
        return machineAccount;
    }

    public void setMachineAccount(MachineAccount machineAccount) {
        this.machineAccount = machineAccount;
    }


    public User getDoctorUser() {
        return doctorUser;
    }

    public void setDoctorUser(User doctorUser) {
        this.doctorUser = doctorUser;
    }

    public User getLinkEmployee() {
        return linkEmployee;
    }

    public void setLinkEmployee(User linkEmployee) {
        this.linkEmployee = linkEmployee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReportRegistration getReportRegistration() {
        return reportRegistration;
    }

    public void setReportRegistration(ReportRegistration reportRegistration) {
        this.reportRegistration = reportRegistration;
    }

    public AuditAcceptance getAuditAcceptance() {
        return auditAcceptance;
    }

    public void setAuditAcceptance(AuditAcceptance auditAcceptance) {
        this.auditAcceptance = auditAcceptance;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public RecordInfo getRecordInfo() {
        return recordInfo;
    }

    public void setRecordInfo(RecordInfo recordInfo) {
        this.recordInfo = recordInfo;
    }

    public void setMediateEvidenceList(List<MediateRecord> mediateEvidenceList) {
        this.mediateEvidenceList = mediateEvidenceList;
    }

    public List<MediateRecord> getMediateEvidenceList() {
        return mediateEvidenceList;
    }

    public ComplaintMain getComplaintMain() {
        return complaintMain;
    }

    public void setComplaintMain(ComplaintMain complaintMain) {
        this.complaintMain = complaintMain;
    }
    @Length(min = 0, max = 100, message = "医调委人员长度必须介于 0 和 100 之间")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Length(min = 0, max = 32, message = "质证调解主键长度必须介于 0 和 32 之间")
    public String getMediateEvidenceId() {
        return mediateEvidenceId;
    }

    public void setMediateEvidenceId(String mediateEvidenceId) {
        this.mediateEvidenceId = mediateEvidenceId;
    }

    @Length(min = 0, max = 32, message = "主表主键长度必须介于 0 和 32 之间")
    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    @Length(min = 0, max = 1, message = "调解结果  1成功 2 失败长度必须介于 0 和 1 之间")
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

    public User getYtwUser() {
        return ytwUser;
    }

    public void setYtwUser(User ytwUser) {
        this.ytwUser = ytwUser;
    }

    @Length(min = 0, max = 100, message = "患方长度必须介于 0 和 100 之间")
    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @Length(min = 0, max = 200, message = "医方长度必须介于 0 和 200 之间")
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Length(min = 1, max = 500, message = "案件长度必须介于 1 和 500 之间")
    public String getCaseInfoName() {
        return caseInfoName;
    }

    public void setCaseInfoName(String caseInfoName) {
        this.caseInfoName = caseInfoName;
    }

    @Length(min = 0, max = 100, message = "地点长度必须介于 0 和 100 之间")
    public String getMeetingAddress() {
        return meetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }

    @Length(min = 0, max = 20, message = "会议时间长度必须介于 0 和 20 之间")
    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    @Length(min = 0, max = 10, message = "处理人长度必须介于 0 和 10 之间")
    public String getHandlePeople() {
        return handlePeople;
    }

    public void setHandlePeople(String handlePeople) {
        this.handlePeople = handlePeople;
    }

    @Length(min = 0, max = 20, message = "处理日期长度必须介于 0 和 20 之间")
    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    @Length(min = 0, max = 32, message = "next_link长度必须介于 0 和 32 之间")
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

}