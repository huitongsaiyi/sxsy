/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessinfo.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.assessinfo.entity.AssessInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 案件评价DAO接口
 * @author lyt
 * @version 2019-06-17
 */
@MyBatisDao
public interface AssessInfoDao extends CrudDao<AssessInfo> {
    /*查询不同节点的  主键
     * 详情需要参数
     * */
    public Map<String,Object> getViewDetail(@Param("complaintMainId") String complaintMainId);
}