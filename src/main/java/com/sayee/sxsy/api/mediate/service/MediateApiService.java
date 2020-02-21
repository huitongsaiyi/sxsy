package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.complain.entity.ComplaintApi;
import com.sayee.sxsy.api.mediate.dao.MediateApiDao;
import com.sayee.sxsy.api.mediate.entity.ActInst;
import com.sayee.sxsy.api.mediate.entity.Mediate;
import com.sayee.sxsy.api.mediate.entity.MediateApiEntity;
import com.sayee.sxsy.api.mediate.entity.MediateCommon;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 调解
 */
@Service
public class MediateApiService extends CrudService<MediateApiDao, MediateApiEntity> {
    @Autowired
    private MediateApiDao mediateApiDao;
    public Integer saveMediate(MediateApiEntity mediateApiEntity){
       return mediateApiDao.insert(mediateApiEntity);
    }
    /*创建人员的医调委调解列表*/
    public List<Mediate> mediateList(String wechatUserId){
        return mediateApiDao.mediateList(wechatUserId);
    }
    /*医院调解列表*/
    public List<Mediate> mediateListForHos(String wechatUserId){
        return mediateApiDao.mediateListForHos(wechatUserId);

    }
    /*医调委调解列表*/
    public List<Mediate> getMediateList(String wechatUserId){
        return mediateApiDao.getMediateList(wechatUserId);
    }
    /*医院调解列表*/
    public List<Mediate> getMediateListForHos(String wechatUserId){
        return mediateApiDao.getMediateListForHos(wechatUserId);

    }
    /*获取案件编号*/
    public String getCaseNumber(){
        //SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        String nowDate=sdf.format(new Date());
        //System.out.println(nowDate);
        String caseNumber=mediateApiDao.getCaseNumber(nowDate);
        String number="";
        if(null==caseNumber||caseNumber.isEmpty()){
            number=nowDate+"0001";
        }else{
            try {
                long value = Long.valueOf(caseNumber) + 1;
                number=String.valueOf(value);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return number;
    }
    /*调解详情*/
    public MediateCommon findMediateById(String complaintMainId){
        return mediateApiDao.findMediateById(complaintMainId);
    }
    public void setProInstId(Map map){
        mediateApiDao.setProInstId(map);
    }
    /*调解状态*/
    public String getStatus(String complaintMainId){
        return mediateApiDao.getStatus(complaintMainId);
    }
    public List<ActInst> getActInfo(String complaintMainId){
       return mediateApiDao.getActInfo(complaintMainId);
    }
    public MediateCommon getMediateInfo(String complaintMainId){
        return mediateApiDao.getMediateInfo(complaintMainId);
    }
    /*投诉处理人*/
    public String getDistributionUser(String areaId){
        return mediateApiDao.getDistributionUser(areaId);
    }
    /*投诉医院处理人*/
    public String getDistribution(String involveHospitalId){
        return mediateApiDao.getDistribution(involveHospitalId);
    }
    /*用户投诉列表*/
    public List<ComplaintApi> getComplaintList(String wechatUserId){
        return mediateApiDao.getComplaintList(wechatUserId);
    }
    /*用户调解列表*/
    public List<Mediate> getComplaintToytwList(String wechatUserId){
        return mediateApiDao.getComplaintToytwList(wechatUserId);
    }
    /*医院投诉列表*/
    public List<ComplaintApi> complaintListForHos(String wechatUserId){
        return mediateApiDao.complaintListForHos(wechatUserId);
    }
    public Map getMediateUser(String tableName,String complaintMainId){
        Map map=new HashMap();
        map.put("tableName",tableName);
        map.put("complaintMainId",complaintMainId);
        return mediateApiDao.getMediateUser(map);
    }
    /*获取进度表对应id*/
    public String getMediateActId(String tableName,String tableId,String complaintMainId){
        Map map=new HashMap();
        map.put("tableName",tableName);
        map.put("tableId",tableId);
        map.put("complaintMainId",complaintMainId);
        return mediateApiDao.getMediateActId(map);
    }
   /*获取调解列表*/
    public List<Mediate> mediateCommenFindList(String wechatUserId){
        return mediateApiDao.mediateCommenFindList(wechatUserId);
    }
    /*获取医院调解列表*/
    public List<Mediate> mediateCommenFindListForHos(String wechatUserId){
        return mediateApiDao.mediateCommenFindListForHos(wechatUserId);
    }
    /*获取案件评论状态*/
    public Integer getIsAssess(String complaintMainId){
        if(mediateApiDao.getIsAssess(complaintMainId)>0){
            return 1;
        }else{
            return 0;
        }
    }
/*
 public List<Mediate> getAssessList(String wechatUserId){
        return mediateApiDao.getAssessList(wechatUserId);
    }
    public List<Mediate> getAgreementList(String wechatUserId){
        return mediateApiDao.getAssessList(wechatUserId);
    }
    public List<Mediate> assessCommenFindList(String wechatUserId){
        return mediateApiDao.assessCommenFindList(wechatUserId);
    }

    public List<Mediate> agreementCommenFindList(String wechatUserId){
        return mediateApiDao.agreementCommenFindList(wechatUserId);
    }
    public List<Mediate> mediateCommenFindListForHos(String wechatUserId){
        return mediateApiDao.mediateCommenFindList(wechatUserId);
    }*/
}
