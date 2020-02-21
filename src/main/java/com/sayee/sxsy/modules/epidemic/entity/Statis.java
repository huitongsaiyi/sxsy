/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.epidemic.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 疫情日报Entity
 * @author zhangfan
 * @version 2020-02-11
 */
public class Statis extends DataEntity<Statis> {

	private static final long serialVersionUID = 1L;
	private String deptName;		// 人员姓名
	private String deptId;		// 人员姓名
	private String shibao;		// 性别
	private String yingbao;		// 年龄
	private String dayu;		// 关系
	private String waichu;		// 车次航班号

	public Statis() {
		super();
	}

	public Statis(String id){
		super(id);
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getShibao() {
		return shibao;
	}

	public void setShibao(String shibao) {
		this.shibao = shibao;
	}

	public String getYingbao() {
		return yingbao;
	}

	public void setYingbao(String yingbao) {
		this.yingbao = yingbao;
	}

	public String getDayu() {
		return dayu;
	}

	public void setDayu(String dayu) {
		this.dayu = dayu;
	}

	public String getWaichu() {
		return waichu;
	}

	public void setWaichu(String waichu) {
		this.waichu = waichu;
	}
}