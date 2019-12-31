package com.sayee.sxsy.api.mediate.dao;

import com.sayee.sxsy.api.mediate.entity.Sign;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

/**
 * @Description
 */
@MyBatisDao
public interface SignDao extends CrudDao<Sign> {
    String getMax(String getYear);
}
