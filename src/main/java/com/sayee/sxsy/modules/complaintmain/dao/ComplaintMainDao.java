/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 纠纷调解DAO接口
 * @author lyt
 * @version 2019-06-04
 */
@MyBatisDao
public interface ComplaintMainDao extends CrudDao<ComplaintMain> {
    /**
     * 获取我的待办数目
     */
    public Long findCount(@Param("loginName") String loginName);
    /**
     * 获取我的待办数据
     */
    public List<ComplaintMain> selfList(@Param("loginName") String loginName);
    /**
     * 获取某表的主键
     */
    public String getKey(@Param("complaintMainId") String complaintMainId,@Param("key") String key,@Param("table") String table);
    /**
     * 获取我的已办数据
     */
    public List<ComplaintMain> getMyDone(@Param("loginName") String loginName);
    /**
     * 获取案件类型条数
     */
    public List<Map<String, Object>> findTypeInfo(@Param("year")String year,@Param("month")String month);
    /**
     * 不同医院级别 不同的投诉数量
     */
    public List<Map<String, Object>> findGrade(@Param("year")String year,@Param("month")String month);
    /**
     * 获取每月数据
     */
    List<Map<String, Object>> getEveryMonthData(@Param("year")String year,@Param("month")String month);
    /**
     * 获取山西省各地案件数量
     */
    public List<Map<String,Object>> findAreaName (@Param("year")String year,@Param("month")String month);

    /**
     * 获取所有已完成案件
     * @param year
     * @param month
     * @return
     */
    public List<ComplaintMain> findCompleted(@Param("year")String year,@Param("month")String month);

    /**
     * 获取所有未完成案件
     * @param year
     * @param month
     * @return
     */
    public Long findAllEvent(@Param("year")String year,@Param("month")String month);
}