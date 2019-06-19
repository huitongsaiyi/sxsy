/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.typeinfo.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类型总表DAO接口
 * @author lyt
 * @version 2019-06-01
 */
@MyBatisDao
public interface TypeInfoDao extends CrudDao<TypeInfo> {
    /**
     * typeinfo
     * @param relationModel
     *
     */
    public List<TypeInfo> findTypeList(@Param("relationModel") String relationModel);
    /**
     * 对编号之类的数据 进行自增；像 案件编号
     * @author zf
     * @version 2019年6月14日
     * @param table    数据库表名（大写）
     * @param field   字段名称
     */
    public String findCode(@Param("table") String table,@Param("field") String field,@Param("time") String time);
}