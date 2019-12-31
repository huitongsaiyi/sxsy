package com.sayee.sxsy.api.officeapi.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description
 */
public class OrganizationApiEntity extends DataEntity<OrganizationApiEntity> {
    private static final long serialVersionUID = 1L;
    private String organizationId;
    private String name;
    private String hospitalGrade;
    private String grade;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospitalGrade() {
        return hospitalGrade;
    }

    public void setHospitalGrade(String hospitalGrade) {
        this.hospitalGrade = hospitalGrade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
