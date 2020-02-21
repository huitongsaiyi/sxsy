/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.epidemic.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.epidemic.entity.EpidemicDaily;
import com.sayee.sxsy.modules.epidemic.entity.Statis;

import java.util.List;

/**
 * 疫情日报DAO接口
 * @author zhangfan
 * @version 2020-02-11
 */
@MyBatisDao
public interface EpidemicDailyDao extends CrudDao<EpidemicDaily> {

    List<Statis> findStatistics(EpidemicDaily epidemicDaily);
}