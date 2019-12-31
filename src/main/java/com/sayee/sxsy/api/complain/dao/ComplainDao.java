package com.sayee.sxsy.api.complain.dao;

import com.sayee.sxsy.api.complain.entity.ComplainEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;


/**
 * @Description 投诉
 */
@MyBatisDao
public interface ComplainDao extends CrudDao<ComplainEntity> {

}
