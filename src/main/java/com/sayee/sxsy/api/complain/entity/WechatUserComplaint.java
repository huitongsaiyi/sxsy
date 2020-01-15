package com.sayee.sxsy.api.complain.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description
 */
public class WechatUserComplaint extends DataEntity<WechatUserComplaint> {
    private static final long serialVersionUID = 1L;
    private String WechatUserId;
    private String ComplaintMainId;

    public String getWechatUserId() {
        return WechatUserId;
    }

    public void setWechatUserId(String wechatUserId) {
        WechatUserId = wechatUserId;
    }

    public String getComplaintMainId() {
        return ComplaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        ComplaintMainId = complaintMainId;
    }
}
