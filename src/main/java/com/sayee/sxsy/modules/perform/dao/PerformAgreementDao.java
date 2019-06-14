/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.perform.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.perform.entity.PerformAgreement;

/**
 * 履行协议DAO接口
 * @author lyt
 * @version 2019-06-14
 */
@MyBatisDao
public interface PerformAgreementDao extends CrudDao<PerformAgreement> {
	
}