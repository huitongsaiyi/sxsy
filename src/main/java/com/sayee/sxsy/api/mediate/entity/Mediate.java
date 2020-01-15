package com.sayee.sxsy.api.mediate.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 调解列表
 */
public class Mediate implements Serializable {
    private static final long serialVersionUID = 1L;
    private String complaintMainId;
    private String visitorName;
    private String status;
    private String actName;
    private String caseNumber;
    private String createDate;
    private String name;
    private String photo;
    private String reportingTime;
    private String createName;
    private String hospitalName;
    private String visitorDate;
    private String userStatu;
    private Integer actNum;
    private int isAssess;


    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.createDate = sdf.format(createDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    public String getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(String reportingTime) {
        if(reportingTime.length()>=16) {
             reportingTime = reportingTime.substring(0, 16);
        }
        this.reportingTime = reportingTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getVisitorDate() {
        return visitorDate;
    }

    public void setVisitorDate(String visitorDate) {
        this.visitorDate = visitorDate;
    }

    public String getUserStatu() {
        return userStatu;
    }

    public void setUserStatu(String userStatu) {
        this.userStatu = userStatu;
    }

    public Integer getActNum() {
        return actNum;
    }

    public void setActNum(Integer actNum) {
        this.actNum = actNum;
    }

    public int getIsAssess() {
        return isAssess;
    }

    public void setIsAssess(int isAssess) {
        this.isAssess = isAssess;
    }
}
