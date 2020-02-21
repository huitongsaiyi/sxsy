package com.sayee.sxsy.api.dailyreport.dao;

import com.sayee.sxsy.api.dailyreport.entity.EpidemicDailyApi;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.Map;

/**
 * @Description
 */
@MyBatisDao
public interface EpidemicDailyApiDao extends CrudDao<EpidemicDailyApi> {
    String[] getDailyReportInfo(String wechatUserId);
    Integer getIsReport(Map map);
}
