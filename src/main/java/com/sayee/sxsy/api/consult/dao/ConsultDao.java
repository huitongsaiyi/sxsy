package com.sayee.sxsy.api.consult.dao;

import com.sayee.sxsy.api.consult.entity.ConsultApi;
import com.sayee.sxsy.api.consult.entity.ConsultApiEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * @Description 咨询
 */
@MyBatisDao
public interface ConsultDao extends CrudDao<ConsultApiEntity> {
    /*咨询列表*/
    List<ConsultApi> getConsultList();
    void reply(Map map);
    /*用户咨询列表*/
    List<ConsultApi> getConsultListByUid(String uid);
}
