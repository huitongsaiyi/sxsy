package com.sayee.sxsy.api.dailyreport.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description
 */
public class EgressInfoApi extends DataEntity<EgressInfoApi> {
    private static final long serialVersionUID = 1L;
    private String egressId;
    private String epidemicId;//日报表id
    private String egressName;//人员姓名
    private String egressSex;//性别
    private String egressAge;//年龄
    private String relation;//与填表人关系
    private String trainFlight;//车次/航班
    private String userAddress;//住址
    private String temperature;//体温
    private String healthCondition;//健康状况
    private String isEgress;//是否外出
    private String egressTime;//外出时间
    private String egressMode;//外出方式
    private String egressPlace;//外出地点
    private String returnTime;//返回时间
    private String returnMode;//返回方式
    private String returnPlace;//返回地点
    private String remark;
    private String createUser;
    public String getEgressId() {
        return egressId;
    }

    public void setEgressId(String egressId) {
        this.egressId = egressId;
    }

    public String getEpidemicId() {
        return epidemicId;
    }

    public void setEpidemicId(String epidemicId) {
        this.epidemicId = epidemicId;
    }

    public String getEgressName() {
        return egressName;
    }

    public void setEgressName(String egressName) {
        this.egressName = egressName;
    }

    public String getEgressSex() {
        return egressSex;
    }

    public void setEgressSex(String egressSex) {
        this.egressSex = egressSex;
    }

    public String getEgressAge() {
        return egressAge;
    }

    public void setEgressAge(String egressAge) {
        this.egressAge = egressAge;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getTrainFlight() {
        return trainFlight;
    }

    public void setTrainFlight(String trainFlight) {
        this.trainFlight = trainFlight;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getIsEgress() {
        return isEgress;
    }

    public void setIsEgress(String isEgress) {
        this.isEgress = isEgress;
    }

    public String getEgressTime() {
        return egressTime;
    }

    public void setEgressTime(String egressTime) {
        this.egressTime = egressTime;
    }

    public String getEgressMode() {
        return egressMode;
    }

    public void setEgressMode(String egressMode) {
        this.egressMode = egressMode;
    }

    public String getEgressPlace() {
        return egressPlace;
    }

    public void setEgressPlace(String egressPlace) {
        this.egressPlace = egressPlace;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnMode() {
        return returnMode;
    }

    public void setReturnMode(String returnMode) {
        this.returnMode = returnMode;
    }

    public String getReturnPlace() {
        return returnPlace;
    }

    public void setReturnPlace(String returnPlace) {
        this.returnPlace = returnPlace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
