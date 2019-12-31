package com.sayee.sxsy.api.statistic.dao;

import com.sayee.sxsy.api.statistic.entity.StatisticEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.datatype.entity.DataType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author www.donxon.com
 * @Description
 */
@MyBatisDao
public interface StatisticDao extends CrudDao<StatisticEntity> {
    /*查询 统计表头*/
    List<Map> findDataType();

    /**
     * 获取案件类型条数
     */
    public List<Map<String, Object>> findTypeInfo(@Param("year")String year, @Param("beginMonthDate")String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 不同医院级别 不同的投诉数量
     */
    public List<Map<String, Object>> findGrade(@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);
    /**
     * 获取每月数据
     */
    List<Map<String, Object>> getEveryMonthData(@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);
    /**
     * 获取山西省各地案件数量
     */
    public List<Map<String,Object>> findAreaName (@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);

    /**
     * 获取各个专业的案件数量
     */
    public List<Map<String,Object>> findDepartment(@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);
    /**
     * 调解数据统计中的 投诉类型统计
     */
    List<Map<String, Object>> findTypeInfoTj(@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);
    /**
     * 调解数据统计中的 各医院登记 数量
     */
    List<Map<String, Object>> findGradeTj(@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);
    /**
     * 调解数据统计中的 每月数量统计
     */
    List<Map<String, Object>> getEveryMonthDataTj(@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);
    /**
     * 调解数据统计中的 各市区数据统计
     */
    List<Map<String, Object>> findAreaNameTj(@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);
    /**
     * 调解数据统计中的 各专业数据统计
     */
    List<Map<String, Object>> findDepartmentTj(@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);
    /**
     * 调解数据统计中的 责任度 饼状图
     */
    List<Map<String, Object>> findDutyTj(@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);
    /**
     * 调解数据统计中的 赔偿金额比例 饼状图
     */
    List<Map<String, Object>> findAmountRatioTj(@Param("year")String year,@Param("beginMonthDate")String beginMonthDate,@Param("endMonthDate") String endMonthDate,@Param("officeId") String officeId);

}
