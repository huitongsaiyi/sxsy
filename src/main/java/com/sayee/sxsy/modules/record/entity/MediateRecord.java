/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.record.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 调解志Entity
 * @author lyt
 * @version 2019-06-10
 */
public class MediateRecord extends DataEntity<MediateRecord> {
	
	private static final long serialVersionUID = 1L;
	private String mediateRecord;		// 调解志主键
	private String relationId;		// 关联表主键
	private String time;		// 时间
	private String content;		// 内容
	private String result;		// 结果
	
	public MediateRecord() {
		super();
	}

	public MediateRecord(String id){
		super(id);
	}

	@Length(min=0, max=32, message="调解志主键长度必须介于 0 和 32 之间")
	public String getMediateRecord() {
		return mediateRecord;
	}

	public void setMediateRecord(String mediateRecord) {
		this.mediateRecord = mediateRecord;
	}
	
	@Length(min=0, max=32, message="关联表主键长度必须介于 0 和 32 之间")
	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	@Length(min=0, max=20, message="时间长度必须介于 0 和 20 之间")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	@Length(min=0, max=500, message="内容长度必须介于 0 和 500 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=500, message="结果长度必须介于 0 和 500 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}