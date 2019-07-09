/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaint.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
	public ComplaintInfo checkcaseNumber(@Param("caseNumber") String caseNumber,@Param("complaintMainId") String complaintMainId);
    /**
     * 工作统计
     * @param
     * @return
     */
    List<Map<String, Object>> selectEveryOne(@Param("visitorDate")String visitorDate,@Param("visitorMonthDate") String visitorMonthDate, @Param("involveDepartment")String involveDepartment, @Param("involveEmployee")String involveEmployee);
    /**
     * 单人工作统计
     * @param
     * @return
     */
    Map<String, Object> selectPerson(@Param("createBy")String createBy,@Param("visitorDate")String visitorDate,@Param("visitorMonthDate") String visitorMonthDate);

}