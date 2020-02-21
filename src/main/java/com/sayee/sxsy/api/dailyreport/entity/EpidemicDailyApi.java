package com.sayee.sxsy.api.dailyreport.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * @Description
 */
public class EpidemicDailyApi extends DataEntity<EpidemicDailyApi> {
    private static final long serialVersionUID = 1L;
    private String epidemicId;
    private String wechatUserId;//用户id
    private String workUnit;//工作单位
    private String fillingDate;//填表日期
    private String userName;//姓名
    private String userSex;//性别
    private String userAge;//年龄
    private String userAddress;//住址
    private String temperature;//体温
    private String isEgress;
    private String healthCondition;//健康状况
    private String familyHealthCondition;//家人健康状况
    private String workRemark;//工作情况备注
    private String workSituation;//是否完成
    private String remark;//
    private String createUser;//创建人
    public String getEpidemicId() {
        return epidemicId;
    }

    public void setEpidemicId(String epidemicId) {
        this.epidemicId = epidemicId;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getFillingDate() {
        return fillingDate;
    }

    public void setFillingDate(String fillingDate) {
        this.fillingDate = fillingDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
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

    public String getFamilyHealthCondition() {
        return familyHealthCondition;
    }

    public void setFamilyHealthCondition(String familyHealthCondition) {
        this.familyHealthCondition = familyHealthCondition;
    }

    public String getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(String wechatUserId) {
        this.wechatUserId = wechatUserId;
    }

    public String getIsEgress() {
        return isEgress;
    }

    public void setIsEgress(String isEgress) {
        this.isEgress = isEgress;
    }

    public String getWorkSituation() {
        return workSituation;
    }

    public void setWorkSituation(String workSituation) {
        this.workSituation = workSituation;
    }

    public String getWorkRemark() {
        return workRemark;
    }

    public void setWorkRemark(String workRemark) {
        this.workRemark = workRemark;
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
