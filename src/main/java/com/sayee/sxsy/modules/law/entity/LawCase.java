/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.law.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 法律法规Entity
 * @author zhangfan
 * @version 2019-12-03
 */
public class LawCase extends DataEntity<LawCase> {
	
	private static final long serialVersionUID = 1L;
	private String lawCaseId;		// 主键
	private String type;		// 类型1法律法规 2经典案例
	private String publishTime;		// 公布时间
	private String title;		// 标题
	private String content;		// 内容
	
	public LawCase() {
		super();
	}

	public LawCase(String id){
		super(id);
	}

	//@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getLawCaseId() {
		return lawCaseId;
	}

	public void setLawCaseId(String lawCaseId) {
		this.lawCaseId = lawCaseId;
	}
	
	@Length(min=0, max=1, message="类型1法律法规 2经典案例长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=20, message="公布时间长度必须介于 0 和 20 之间")
	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	
	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}