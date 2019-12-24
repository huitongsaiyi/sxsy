package com.sayee.sxsy.api.statistic.dao;

import com.sayee.sxsy.api.statistic.entity.StatisticEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

/**
 * @author www.donxon.com
 * @Description
 */
@MyBatisDao
public interface StatisticDao extends CrudDao<StatisticEntity> {
}
