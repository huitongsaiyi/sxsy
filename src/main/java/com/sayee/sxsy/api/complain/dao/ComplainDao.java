package com.sayee.sxsy.api.complain.dao;

import com.sayee.sxsy.api.complain.entity.ComplainEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;


/**
 * @author www.donxon.com
 * @Description
 */
@MyBatisDao
public interface ComplainDao extends CrudDao<ComplainEntity> {

}
