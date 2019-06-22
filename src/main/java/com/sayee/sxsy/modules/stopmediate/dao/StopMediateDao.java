/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.stopmediate.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.stopmediate.entity.StopMediate;
import org.apache.ibatis.annotations.Param;

/**
 * 终止调解DAO接口
 * @author lyt
 * @version 2019-06-20
 */
@MyBatisDao
public interface StopMediateDao extends CrudDao<StopMediate> {
    /**
     * 卷宗编号验重
     * @param complaintMainId
     * @return
     */
    public StopMediate passCom(@Param("complaintMainId") String complaintMainId);
}