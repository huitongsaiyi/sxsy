package com.sayee.sxsy.api.consult.service;

import com.sayee.sxsy.api.consult.dao.ConsultDao;
import com.sayee.sxsy.api.consult.entity.ConsultApi;
import com.sayee.sxsy.api.consult.entity.ConsultApiEntity;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 咨询
 */
@Service
public class ConsultService extends CrudService<ConsultDao, ConsultApiEntity> {
    @Autowired
    private ConsultDao consultDao;
    /*添加咨询*/
    public void addConsult(ConsultApiEntity consult){
        consultDao.insert(consult);
    }
    public List<ConsultApi> getConsultList(){
        return consultDao.getConsultList();
    }
    public void reply(Map map){
        consultDao.reply(map);
    }
    public List<ConsultApi> getConsultListByUid(String uid){
        return consultDao.getConsultListByUid(uid);
    }
}
