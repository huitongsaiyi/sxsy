/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.respondentinfo.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 被调查人信息Entity
 * @author gbq
 * @version 2019-06-10
 */
public class RespondentInfo extends DataEntity<RespondentInfo> {
	
	private static final long serialVersionUID = 1L;
	private String respondentId;		// 被调查人主键
	private String investigationEvidenceId;		// 调查取证主键
	private String respondentName;		// 被调查人姓名
	private String respondentSex;		// 被调查人性别
	private String respondentAge;		// 被调查人年龄
	private String respondentMobile;		// 被调查人联系方式
	private String respondentWorkUnit;		// 被调查人工作单位
	private String respondentPost;		// 被调查人职务
	private String respondentIdentity;		// 被调查人身份 字典维护
	
	public RespondentInfo() {
		super();
	}

	public RespondentInfo(String id){
		super(id);
	}

	@Length(min=1, max=32, message="被调查人主键长度必须介于 1 和 32 之间")
	public String getRespondentId() {
		return respondentId;
	}

	public void setRespondentId(String respondentId) {
		this.respondentId = respondentId;
	}
	
	@Length(min=0, max=32, message="调查取证主键长度必须介于 0 和 32 之间")
	public String getInvestigationEvidenceId() {
		return investigationEvidenceId;
	}

	public void setInvestigationEvidenceId(String investigationEvidenceId) {
		this.investigationEvidenceId = investigationEvidenceId;
	}
	
	@Length(min=0, max=10, message="被调查人姓名长度必须介于 0 和 10 之间")
	public String getRespondentName() {
		return respondentName;
	}

	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}
	
	@Length(min=0, max=1, message="被调查人性别长度必须介于 0 和 1 之间")
	public String getRespondentSex() {
		return respondentSex;
	}

	public void setRespondentSex(String respondentSex) {
		this.respondentSex = respondentSex;
	}
	
	@Length(min=0, max=4, message="被调查人年龄长度必须介于 0 和 4 之间")
	public String getRespondentAge() {
		return respondentAge;
	}

	public void setRespondentAge(String respondentAge) {
		this.respondentAge = respondentAge;
	}
	
	@Length(min=0, max=15, message="被调查人联系方式长度必须介于 0 和 15 之间")
	public String getRespondentMobile() {
		return respondentMobile;
	}

	public void setRespondentMobile(String respondentMobile) {
		this.respondentMobile = respondentMobile;
	}
	
	@Length(min=0, max=30, message="被调查人工作单位长度必须介于 0 和 30 之间")
	public String getRespondentWorkUnit() {
		return respondentWorkUnit;
	}

	public void setRespondentWorkUnit(String respondentWorkUnit) {
		this.respondentWorkUnit = respondentWorkUnit;
	}
	
	@Length(min=0, max=30, message="被调查人职务长度必须介于 0 和 30 之间")
	public String getRespondentPost() {
		return respondentPost;
	}

	public void setRespondentPost(String respondentPost) {
		this.respondentPost = respondentPost;
	}
	
	@Length(min=0, max=1, message="被调查人身份 字典维护长度必须介于 0 和 1 之间")
	public String getRespondentIdentity() {
		return respondentIdentity;
	}

	public void setRespondentIdentity(String respondentIdentity) {
		this.respondentIdentity = respondentIdentity;
	}
	
}