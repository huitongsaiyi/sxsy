/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.organ.entity;

import org.hibernate.validator.constraints.Length;
import com.sayee.sxsy.modules.sys.entity.User;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 服务机构信息Entity
 * @author zhangfan
 * @version 2019-12-03
 */
public class ServiceOrgan extends DataEntity<ServiceOrgan> {
	
	private static final long serialVersionUID = 1L;
	private String serviceOrganId;		// 主键
	private String phone;		// 电话
	private String address;		// 地址
	private String email;		// 邮箱
	private String workTime;		// 工作时间
	private String introduce;		// 服务机构简介
	private String duty;		// 主要责任
	private String serviceTenet;		// 服务宗旨
	private String threeAid;		// 三个援助
	private String userName;		// 调解员名称 用逗号隔开
	private User user;		// 调解员(多个用逗号隔开)
	
	public ServiceOrgan() {
		super();
	}

	public ServiceOrgan(String id){
		super(id);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	//@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getServiceOrganId() {
		return serviceOrganId;
	}

	public void setServiceOrganId(String serviceOrganId) {
		this.serviceOrganId = serviceOrganId;
	}
	
	@Length(min=0, max=20, message="电话长度必须介于 0 和 20 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="地址长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=50, message="邮箱长度必须介于 0 和 50 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=50, message="工作时间长度必须介于 0 和 50 之间")
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}