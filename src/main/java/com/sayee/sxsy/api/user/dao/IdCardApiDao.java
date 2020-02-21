package com.sayee.sxsy.api.user.dao;

import com.sayee.sxsy.api.user.entity.IdCardApiEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

/**
 * @Description
 */
@MyBatisDao
public interface IdCardApiDao extends CrudDao<IdCardApiEntity> {
}
