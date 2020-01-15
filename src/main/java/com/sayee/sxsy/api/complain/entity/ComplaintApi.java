package com.sayee.sxsy.api.complain.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 */
public class ComplaintApi implements Serializable {
    private static final long serialVersionUID = 1L;
    private String complaintId;
    private String visitorName;//来访者姓名
    private String createDate;//创建时间
    private String handleWay;//处理方式
    private String summaryOfDisputes;//概要
    private String handleResult;//处理结果
    private String receptionDate;//处理日期
    private String name;//处理人

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        this.createDate = sdf.format(createDate);
    }

    public String getSummaryOfDisputes() {
        return summaryOfDisputes;
    }

    public void setSummaryOfDisputes(String summaryOfDisputes) {
        this.summaryOfDisputes = summaryOfDisputes;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public String getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(String receptionDate) {
        this.receptionDate = receptionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandleWay() {
        return handleWay;
    }

    public void setHandleWay(String handleWay) {
        this.handleWay = handleWay;
    }
}
