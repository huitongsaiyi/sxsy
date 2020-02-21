/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.signtype.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.signtype.entity.SignTypeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 协议书DAO接口
 * @author zhangfan
 * @version 2020-02-04
 */
@MyBatisDao
public interface SignTypeInfoDao extends CrudDao<SignTypeInfo> {

    public List<SignTypeInfo> findSignTypeList(@Param("relationModel") String relationModel,@Param("signId") String signId);
}