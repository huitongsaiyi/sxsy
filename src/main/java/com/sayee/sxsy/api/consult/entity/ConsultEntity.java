package com.sayee.sxsy.api.consult.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.Date;

/**
 * @author www.donxon.com
 * @Description
 */
public class ConsultEntity extends DataEntity<ConsultEntity> {
    private static final long serialVersionUID = 1L;
    private String consultId;
    private String name;
    private String content;
    private String uid;
    private String reply;
    private Date replyDate;
    private String replyUid;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public String getReplyUid() {
        return replyUid;
    }

    public void setReplyUid(String replyUid) {
        this.replyUid = replyUid;
    }

    public String getConsultId() {
        return consultId;
    }

    public void setConsultId(String consultId) {
        this.consultId = consultId;
    }
}
