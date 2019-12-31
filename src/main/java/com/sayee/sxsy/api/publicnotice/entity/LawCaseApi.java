package com.sayee.sxsy.api.publicnotice.entity;

import com.sayee.sxsy.common.persistence.DataEntity;


/**
 * @Description
 */
public class LawCaseApi extends DataEntity<LawCaseApi> {
    private static final long serialVersionUID = 1L;
    private String lawCaseId;
    private String title;
    private String type;
    private String publishTime;
    private String content;

    public String getLawCaseId() {
        return lawCaseId;
    }

    public void setLawCaseId(String lawCaseId) {
        this.lawCaseId = lawCaseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
