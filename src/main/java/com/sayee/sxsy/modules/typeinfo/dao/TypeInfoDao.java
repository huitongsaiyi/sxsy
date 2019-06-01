/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.typeinfo.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;

/**
 * 类型总表DAO接口
 * @author lyt
 * @version 2019-06-01
 */
@MyBatisDao
public interface TypeInfoDao extends CrudDao<TypeInfo> {
	
}