/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.epidemic.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 疫情日报Entity
 * @author zhangfan
 * @version 2020-02-11
 */
public class EgressInfo extends DataEntity<EgressInfo> {
	
	private static final long serialVersionUID = 1L;
	private String egressId;		// egress_id
	private String epidemicId;		// 关联疫情主键 父类
	private String egressName;		// 人员姓名
	private String egressSex;		// 性别
	private String egressAge;		// 年龄
	private String relation;		// 关系
	private String trainFlight;		// 车次航班号
	private String userAddress;		// 住址
	private String temperature;		// 体温
	private String healthCondition;		// 健康状况
	private String isEgress;		// 是否外出
	private String egressTime;		// 外出时间
	private String egressMode;		// 外出方式
	private String egressPlace;		// 外出地点
	private String returnTime;		// 返回时间
	private String returnMode;		// 返回方式
	private String returnPlace;		// 返回地点
	private String remark;		// remark
	
	public EgressInfo() {
		super();
	}

	public EgressInfo(String id){
		super(id);
	}


	@Length(min=1, max=32, message="egress_id长度必须介于 1 和 32 之间")
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

	@Length(min=0, max=20, message="人员姓名长度必须介于 0 和 20 之间")
	public String getEgressName() {
		return egressName;
	}

	public void setEgressName(String egressName) {
		this.egressName = egressName;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getEgressSex() {
		return egressSex;
	}

	public void setEgressSex(String egressSex) {
		this.egressSex = egressSex;
	}
	
	@Length(min=0, max=4, message="年龄长度必须介于 0 和 4 之间")
	public String getEgressAge() {
		return egressAge;
	}

	public void setEgressAge(String egressAge) {
		this.egressAge = egressAge;
	}
	
	@Length(min=0, max=1, message="关系长度必须介于 0 和 1 之间")
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	@Length(min=0, max=255, message="车次航班号长度必须介于 0 和 255 之间")
	public String getTrainFlight() {
		return trainFlight;
	}

	public void setTrainFlight(String trainFlight) {
		this.trainFlight = trainFlight;
	}
	
	@Length(min=0, max=255, message="住址长度必须介于 0 和 255 之间")
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	@Length(min=0, max=255, message="体温长度必须介于 0 和 255 之间")
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	@Length(min=0, max=255, message="健康状况长度必须介于 0 和 255 之间")
	public String getHealthCondition() {
		return healthCondition;
	}

	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}
	
	@Length(min=0, max=1, message="是否外出长度必须介于 0 和 1 之间")
	public String getIsEgress() {
		return isEgress;
	}

	public void setIsEgress(String isEgress) {
		this.isEgress = isEgress;
	}
	
	@Length(min=0, max=20, message="外出时间长度必须介于 0 和 20 之间")
	public String getEgressTime() {
		return egressTime;
	}

	public void setEgressTime(String egressTime) {
		this.egressTime = egressTime;
	}
	
	@Length(min=0, max=255, message="外出方式长度必须介于 0 和 255 之间")
	public String getEgressMode() {
		return egressMode;
	}

	public void setEgressMode(String egressMode) {
		this.egressMode = egressMode;
	}
	
	@Length(min=0, max=255, message="外出地点长度必须介于 0 和 255 之间")
	public String getEgressPlace() {
		return egressPlace;
	}

	public void setEgressPlace(String egressPlace) {
		this.egressPlace = egressPlace;
	}
	
	@Length(min=0, max=20, message="返回时间长度必须介于 0 和 20 之间")
	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	
	@Length(min=0, max=255, message="返回方式长度必须介于 0 和 255 之间")
	public String getReturnMode() {
		return returnMode;
	}

	public void setReturnMode(String returnMode) {
		this.returnMode = returnMode;
	}
	
	@Length(min=0, max=255, message="返回地点长度必须介于 0 和 255 之间")
	public String getReturnPlace() {
		return returnPlace;
	}

	public void setReturnPlace(String returnPlace) {
		this.returnPlace = returnPlace;
	}
	
	@Length(min=0, max=255, message="remark长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}