/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.summaryinfo.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.summaryinfo.entity.SummaryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 案件总结DAO接口
 * @author gbq
 * @version 2019-06-17
 */
@MyBatisDao
public interface SummaryInfoDao extends CrudDao<SummaryInfo> {
    public List<SummaryInfo> findListSummmary(SummaryInfo summaryInfo);

    /*查询不同节点的  主键
    * 详情需要参数
    * */
    public Map<String,Object> getViewDetail(@Param("complaintMainId") String complaintMainId);
}