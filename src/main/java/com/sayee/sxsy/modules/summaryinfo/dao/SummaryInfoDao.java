/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.summaryinfo.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.summaryinfo.entity.SummaryInfo;

import java.util.List;

/**
 * 案件总结DAO接口
 * @author gbq
 * @version 2019-06-17
 */
@MyBatisDao
public interface SummaryInfoDao extends CrudDao<SummaryInfo> {
    public List<SummaryInfo> findListSummmary(SummaryInfo summaryInfo);
	
}