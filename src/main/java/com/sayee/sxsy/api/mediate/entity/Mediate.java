package com.sayee.sxsy.api.mediate.entity;

import java.io.Serializable;

/**
 * @author www.donxon.com
 * @Description 调解列表
 */
public class Mediate implements Serializable {
    private static final long serialVersionUID = 1L;
    private String complaintId;
    private String visitorName;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
