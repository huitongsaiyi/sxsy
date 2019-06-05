/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;

/**
 * 纠纷调解DAO接口
 * @author lyt
 * @version 2019-06-04
 */
@MyBatisDao
public interface ComplaintMainDao extends CrudDao<ComplaintMain> {
	
}