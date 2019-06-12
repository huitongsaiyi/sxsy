/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.recordinfo.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;

/**
 * 笔录DAO接口
 * @author 逯洋涛
 * @version 2019-06-11
 */
@MyBatisDao
public interface RecordInfoDao extends CrudDao<RecordInfo> {
	
}