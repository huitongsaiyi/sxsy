/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.casefeedback.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.casefeedback.entity.CaseFeedback;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 案件反馈DAO接口
 * @author lyt
 * @version 2019-06-20
 */
@MyBatisDao
public interface CaseFeedbackDao extends CrudDao<CaseFeedback> {
    /*查询不同节点的  主键
     * 详情需要参数
     * */
    public Map<String,Object> getViewDetail(@Param("complaintMainId") String complaintMainId);
}