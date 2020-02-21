/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.epidemic.entity;

import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 疫情日报Entity
 * @author zhangfan
 * @version 2020-02-11
 */
public class EpidemicDaily extends DataEntity<EpidemicDaily> {
	
	private static final long serialVersionUID = 1L;
	private String epidemicId;		// 主键
	private String workUnit;		// 工作单位
	private String fillingDate;		// 填表日期
	private String userName;		// 姓名
	private String userSex;		// 性别
	private String userAge;		// 年龄
	private String userAddress;		// 住址
	private String temperature;		// 体温
	private String healthCondition;		// 健康状况
	private String familyHealthCondition;		// 家人健康状况
	private String remark;		// 备注
	private String isEgress;		// 是否外出
	private String workSituation;		// 工作情况
	private String workRemark;		// 工作情况备注
	private String beginFillingDate;		// 开始 填表日期
	private String endFillingDate;		// 结束 填表日期

	private User user;  //当前登录人员
	private List<String> list;//工作站人员list
	private String officeId;		// 部门id


	private List<EgressInfo> egressInfoList = Lists.newArrayList();		// 子表列表
	
	public EpidemicDaily() {
		super();
	}

	public EpidemicDaily(String id){
		super(id);
	}

	public String getEpidemicId() {
		return epidemicId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getIsEgress() {
		return isEgress;
	}

	public void setIsEgress(String isEgress) {
		this.isEgress = isEgress;
	}

	public void setEpidemicId(String epidemicId) {
		this.epidemicId = epidemicId;
	}
	
	@Length(min=0, max=255, message="工作单位长度必须介于 0 和 255 之间")
	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	
	@Length(min=0, max=20, message="填表日期长度必须介于 0 和 20 之间")
	public String getFillingDate() {
		return fillingDate;
	}

	public void setFillingDate(String fillingDate) {
		this.fillingDate = fillingDate;
	}
	
	@Length(min=0, max=20, message="姓名长度必须介于 0 和 20 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	
	@Length(min=0, max=4, message="年龄长度必须介于 0 和 4 之间")
	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
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
	
	@Length(min=0, max=255, message="家人健康状况长度必须介于 0 和 255 之间")
	public String getFamilyHealthCondition() {
		return familyHealthCondition;
	}

	public void setFamilyHealthCondition(String familyHealthCondition) {
		this.familyHealthCondition = familyHealthCondition;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getWorkSituation() {
		return workSituation;
	}

	public void setWorkSituation(String workSituation) {
		this.workSituation = workSituation;
	}
	
	@Length(min=0, max=1000, message="工作情况备注长度必须介于 0 和 1000 之间")
	public String getWorkRemark() {
		return workRemark;
	}

	public void setWorkRemark(String workRemark) {
		this.workRemark = workRemark;
	}
	
	public String getBeginFillingDate() {
		return beginFillingDate;
	}

	public void setBeginFillingDate(String beginFillingDate) {
		this.beginFillingDate = beginFillingDate;
	}
	
	public String getEndFillingDate() {
		return endFillingDate;
	}

	public void setEndFillingDate(String endFillingDate) {
		this.endFillingDate = endFillingDate;
	}
		
	public List<EgressInfo> getEgressInfoList() {
		return egressInfoList;
	}

	public void setEgressInfoList(List<EgressInfo> egressInfoList) {
		this.egressInfoList = egressInfoList;
	}
}