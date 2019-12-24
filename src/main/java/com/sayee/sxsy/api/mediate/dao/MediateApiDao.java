package com.sayee.sxsy.api.mediate.dao;

import com.sayee.sxsy.api.mediate.entity.ActInst;
import com.sayee.sxsy.api.mediate.entity.Mediate;
import com.sayee.sxsy.api.mediate.entity.MediateApiEntity;
import com.sayee.sxsy.api.mediate.entity.MediateCommon;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * @author www.donxon.com
 * @Description
 */
@MyBatisDao
public interface MediateApiDao extends CrudDao<MediateApiEntity> {
    List<Mediate> mediateList();
    String getCaseNumber(String nowDate);
    MediateCommon findMediateById(String complaintMainId);
    void setProInstId(Map map);
    String getStatus(String complaintMainId);
    List<ActInst> getActInfo(String complaintMainId);
    MediateCommon getMediateInfo(String complaintMainId);
}
