/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaint.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;

/**
 * 投诉接待DAO接口
 * @author zhangfan
 * @version 2019-05-27
 */
@MyBatisDao
public interface ComplaintInfoDao extends CrudDao<ComplaintInfo> {
    /**
     * 案件编号验重
     * @param caseNumber
     * @return
     */
	public ComplaintInfo checkcaseNumber(String caseNumber);
}