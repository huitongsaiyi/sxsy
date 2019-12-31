/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.datatype.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 统计基础数据Entity
 * @author zhangfan
 * @version 2019-12-24
 */
public class DataType extends DataEntity<DataType> {
	
	private static final long serialVersionUID = 1L;
	private String dataTypeId;		// 主键
	private String maintype;		// 主类型
	private String title;		// 标题
	private String icon;		// 图标地址
	private String name;		// 名称
	private String type;		// 类型
	
	public DataType() {
		super();
	}

	public DataType(String id){
		super(id);
	}

	//@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getDataTypeId() {
		return dataTypeId;
	}

	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
	
	@Length(min=0, max=100, message="主类型长度必须介于 0 和 100 之间")
	public String getMaintype() {
		return maintype;
	}

	public void setMaintype(String maintype) {
		this.maintype = maintype;
	}
	
	@Length(min=0, max=100, message="标题长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=500, message="图标地址长度必须介于 0 和 500 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}