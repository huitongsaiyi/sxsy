/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.signtype.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 协议书Entity
 * @author zhangfan
 * @version 2020-02-04
 */
public class SignTypeInfo extends DataEntity<SignTypeInfo> {
	
	private static final long serialVersionUID = 1L;
	private String typeId;		// 类型主键
	private String signId;		// 签署协议主键
	private String typeName;		// 类型名称
	private String content;		// 内容
	private String label;		// 内容
	private String source;		// 内容
	private String relationModel;		// 关联模块 1.意见书中的&ldquo;分析意见&rdquo; 2.意见书中的&ldquo;结论&rdquo;  3 签署协议中的&ldquo;调解情况&rdquo; 4.签署协议中的&ldquo;协议约定事项&rdquo; 5..签署协议中的&ldquo;履行协议方式&rdquo; 6. .签署协议中的&ldquo;协议说明&rdquo;
	
	public SignTypeInfo() {
		super();
	}

	public SignTypeInfo(String id){
		super(id);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Length(min=1, max=32, message="类型主键长度必须介于 1 和 32 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Length(min=1, max=32, message="签署协议主键长度必须介于 1 和 32 之间")
	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}
	
	@Length(min=0, max=200, message="类型名称长度必须介于 0 和 200 之间")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Length(min=0, max=1000, message="内容长度必须介于 0 和 1000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getRelationModel() {
		return relationModel;
	}

	public void setRelationModel(String relationModel) {
		this.relationModel = relationModel;
	}
	
}