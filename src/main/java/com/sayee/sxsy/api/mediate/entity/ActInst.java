package com.sayee.sxsy.api.mediate.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 */
public class ActInst implements Serializable {
    private static final long serialVersionUID = 1L;
    private String actName;
    private Date startTime;

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
