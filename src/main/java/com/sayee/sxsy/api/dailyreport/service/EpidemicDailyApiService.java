package com.sayee.sxsy.api.dailyreport.service;

import com.sayee.sxsy.api.dailyreport.dao.EpidemicDailyApiDao;
import com.sayee.sxsy.api.dailyreport.entity.EpidemicDailyApi;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 */
@Service
public class EpidemicDailyApiService extends CrudService<EpidemicDailyApiDao, EpidemicDailyApi> {
    @Autowired
    private EpidemicDailyApiDao epidemicDailyApiDao;
    public void save(EpidemicDailyApi epidemicDailyApi){
        epidemicDailyApiDao.insert(epidemicDailyApi);
    }
    public String[] getDailyReportInfo(String wechatUserId){
        return epidemicDailyApiDao.getDailyReportInfo(wechatUserId);
    }
    public Integer getIsReport(String wechatUserId){
        Map map=new HashMap();
        map.put("wechatUserId",wechatUserId);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String nowDate=sdf.format(new Date());
        map.put("nowDate",nowDate);
        return epidemicDailyApiDao.getIsReport(map);
    }
}
