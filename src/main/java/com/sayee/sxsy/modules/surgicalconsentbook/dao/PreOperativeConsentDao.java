/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.surgicalconsentbook.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.surgicalconsentbook.entity.PreOperativeConsent;
import org.apache.ibatis.annotations.Param;

/**
 * 术前同意书见证管理DAO接口
 * @author gbq
 * @version 2019-05-31
 */
@MyBatisDao
public interface PreOperativeConsentDao extends CrudDao<PreOperativeConsent> {
	public void insertzf( @Param("acceId1") String acceId1,@Param("itemId1") String itemId1,@Param("files1") String files1);
}