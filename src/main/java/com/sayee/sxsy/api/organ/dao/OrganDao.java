package com.sayee.sxsy.api.organ.dao;

import com.sayee.sxsy.api.organ.entity.Organ;
import com.sayee.sxsy.api.organ.entity.OrganUser;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * @Description
 */
@MyBatisDao
public interface OrganDao extends CrudDao<Organ> {
    Organ getOrgan();
    List<OrganUser> getUserList(String[] idList);
    Organ connectUs();
}
