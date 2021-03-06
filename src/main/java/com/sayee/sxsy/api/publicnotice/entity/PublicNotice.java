package com.sayee.sxsy.api.publicnotice.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description
 */
public class PublicNotice extends DataEntity<PublicNotice> {
    private static final long serialVersionUID = 1L;
    private String id;
    private String type;
    private String title;
    private String content;
    private String departmentName;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
