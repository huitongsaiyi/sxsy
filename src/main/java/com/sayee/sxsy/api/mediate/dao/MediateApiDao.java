package com.sayee.sxsy.api.mediate.dao;

import com.sayee.sxsy.api.complain.entity.ComplaintApi;
import com.sayee.sxsy.api.mediate.entity.ActInst;
import com.sayee.sxsy.api.mediate.entity.Mediate;
import com.sayee.sxsy.api.mediate.entity.MediateApiEntity;
import com.sayee.sxsy.api.mediate.entity.MediateCommon;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * @Description
 */
@MyBatisDao
public interface MediateApiDao extends CrudDao<MediateApiEntity> {
    List<Mediate> mediateList(String wechatUserId);
    List<Mediate> mediateListForHos(String wechatUserId);
    List<Mediate> getMediateList(String wechatUserId);
    List<Mediate> getMediateListForHos(String wechatUserId);
    String getCaseNumber(String nowDate);
    MediateCommon findMediateById(String complaintMainId);
    void setProInstId(Map map);
    String getStatus(String complaintMainId);
    List<ActInst> getActInfo(String complaintMainId);
    MediateCommon getMediateInfo(String complaintMainId);
    String getDistributionUser(String areaId);
    List<ComplaintApi> getComplaintList(String wechatUserId);
    List<ComplaintApi> complaintListForHos(String wechatUserId);
    Map getMediateUser(Map map);
    String getMediateActId(Map map);

    List<Mediate> mediateCommenFindList(String wechatUserId);
    List<Mediate> mediateCommenFindListForHos(String wechatUserId);

   /* List<Mediate> getAssessList(String wechatUserId);
    List<Mediate> getAgreementList(String wechatUserId);
    List<Mediate> agreementCommenFindList(String wechatUserId);
    List<Mediate> assessCommenFindList(String wechatUserId);
    List<Mediate> mediateCommenFindListForHos(String wechatUserId);*/
    List<Mediate> getComplaintToytwList(String wechatUserId);
    Integer getIsAssess(String complaintMainId);
}
