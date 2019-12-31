package com.sayee.sxsy.api.organ.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 */
public class Organ extends DataEntity<Organ> {
    private static final long serialVersionUID = 1L;
    private String serviceOrganId;
    private String phone;
    private String address;
    private String email;
    private String workTime;
    private String introduce;
    private String duty;
    private String icon;
    private String serviceTenet;
    private String threeAid;
    private String userId;
    private List<OrganUser> userList=new ArrayList<>();

    public String getServiceOrganId() {
        return serviceOrganId;
    }

    public void setServiceOrganId(String serviceOrganId) {
        this.serviceOrganId = serviceOrganId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getServiceTenet() {
        return serviceTenet;
    }

    public void setServiceTenet(String serviceTenet) {
        this.serviceTenet = serviceTenet;
    }

    public String getThreeAid() {
        return threeAid;
    }

    public void setThreeAid(String threeAid) {
        this.threeAid = threeAid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrganUser> getUserList() {
        return userList;
    }

    public void setUserList(List<OrganUser> userList) {
        this.userList = userList;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}