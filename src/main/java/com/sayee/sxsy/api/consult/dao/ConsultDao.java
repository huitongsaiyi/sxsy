package com.sayee.sxsy.api.consult.dao;

import com.sayee.sxsy.api.consult.entity.ConsultEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * @author www.donxon.com
 * @Description
 */
@MyBatisDao
public interface ConsultDao extends CrudDao<ConsultEntity> {
    List<ConsultEntity> getConsultList();
    void reply(Map map);
    List<ConsultEntity> getConsultListByUid(String uid);
}
