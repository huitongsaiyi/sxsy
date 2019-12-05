/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.organ.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.organ.entity.ServiceOrgan;

/**
 * 服务机构信息DAO接口
 * @author zhangfan
 * @version 2019-12-03
 */
@MyBatisDao
public interface ServiceOrganDao extends CrudDao<ServiceOrgan> {
	
}