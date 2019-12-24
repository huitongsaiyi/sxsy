package com.sayee.sxsy.api.mediate.service;

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
import java.util.List;
import java.util.Map;

/**
 * @author www.donxon.com
 * @Description 调解
 */
@Service
public class MediateApiService extends CrudService<MediateApiDao, MediateApiEntity> {
    @Autowired
    private MediateApiDao mediateApiDao;
    public Integer saveMediate(MediateApiEntity mediateApiEntity){
       return mediateApiDao.insert(mediateApiEntity);
    }
    public List<Mediate> getMediateList(){
        return mediateApiDao.mediateList();
    }
    public String getCaseNumber(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String nowDate=sdf.format(new Date());
        //System.out.println(nowDate);
        String caseNumber=mediateApiDao.getCaseNumber(nowDate);
        String number="";
        if(null==caseNumber||caseNumber.isEmpty()){
            number=nowDate+"001";
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
    public MediateCommon findMediateById(String complaintMainId){
        return mediateApiDao.findMediateById(complaintMainId);
    }
    public void setProInstId(Map map){
        mediateApiDao.setProInstId(map);
    }
    public String getStatus(String complaintMainId){
        return mediateApiDao.getStatus("1");
    }
    public List<ActInst> getActInfo(String complaintMainId){
       return mediateApiDao.getActInfo(complaintMainId);
    }
    public MediateCommon getMediateInfo(String complaintMainId){
        return mediateApiDao.getMediateInfo(complaintMainId);
    }
}
