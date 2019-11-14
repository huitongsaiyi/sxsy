/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.surgicalconsentbook.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.surgicalconsentbook.entity.PreOperativeConsent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 术前同意书见证管理DAO接口
 * @author gbq
 * @version 2019-05-31
 */
@MyBatisDao
public interface PreOperativeConsentDao extends CrudDao<PreOperativeConsent> {
	public void insertzf( @Param("acceId1") String acceId1,@Param("itemId1") String itemId1,@Param("files1") String files1,@Param("fjtype") String fjtype);
	public void updateFile(@Param("files1") String files1,@Param("itemId1") String itemId1,@Param("fjtype") String fjtype);
	public void deletfj(@Param("itemId1") String itemId1,@Param("fjtype") String fjtype);

	List<Map<String, Object>> selectCreatby(@Param("visitorDate")String visitorDate,@Param("visitorDateEnd")String visitorDateEnd, @Param("visitorMonthDate") String visitorMonthDate, @Param("visitorMonthDateEnd") String visitorMonthDateEnd, @Param("involveDepartment")String involveDepartment, @Param("involveEmployee")String involveEmployee);
}