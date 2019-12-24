package com.sayee.sxsy.api.mediate.entity;

import java.io.Serializable;

/**
 * @author www.donxon.com
 * @Description
 */
public class TaskEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String taskId;
    private String procInsId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcInsId() {
        return procInsId;
    }

    public void setProcInsId(String procInsId) {
        this.procInsId = procInsId;
    }
}
