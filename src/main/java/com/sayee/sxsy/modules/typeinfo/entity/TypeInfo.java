/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.typeinfo.entity;

import org.hibernate.validator.constraints.Length;
import com.sayee.sxsy.modules.sys.entity.User;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 类型总表Entity
 * @author lyt
 * @version 2019-06-01
 */
public class TypeInfo extends DataEntity<TypeInfo> {
	
	private static final long serialVersionUID = 1L;
	private String typeId;		// 类型主键
	private String typeName;		// 类型名称
	private String content;		// 内容
	private String relationModel;		// 关联模块 1.意见书中的&ldquo;分析意见&rdquo; 2.意见书中的&ldquo;结论&rdquo;  3 签署协议中的&ldquo;调解情况&rdquo; 4.签署协议中的&ldquo;协议约定事项&rdquo; 5..签署协议中的&ldquo;履行协议方式&rdquo; 6. .签署协议中的&ldquo;协议说明&rdquo;
	private User createById;		// 创建人ID
	private User updateById;		// 更新人ID
    private String label;
    private String type1;

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public TypeInfo() {
		super();
	}

	public TypeInfo(String id){
		super(id);
	}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Length(min=0, max=32, message="类型主键长度必须介于 0 和 32 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Length(min=1, max=200, message="类型名称长度必须介于 1 和 200 之间")
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

	@Length(min = 0,max = 2,message = "签署协议中的协议说明长度必须介于 0 和 2 之间")
	public String getRelationModel() {
		return relationModel;
	}

	public void setRelationModel(String relationModel) {
		this.relationModel = relationModel;
	}
	
	public User getCreateById() {
		return createById;
	}

	public void setCreateById(User createById) {
		this.createById = createById;
	}
	
	public User getUpdateById() {
		return updateById;
	}

	public void setUpdateById(User updateById) {
		this.updateById = updateById;
	}
	
}