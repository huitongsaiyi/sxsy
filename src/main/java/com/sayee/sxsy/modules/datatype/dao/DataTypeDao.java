/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.datatype.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.datatype.entity.DataType;

/**
 * 统计基础数据DAO接口
 * @author zhangfan
 * @version 2019-12-24
 */
@MyBatisDao
public interface DataTypeDao extends CrudDao<DataType> {
	
}