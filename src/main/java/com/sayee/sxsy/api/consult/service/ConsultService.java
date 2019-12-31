package com.sayee.sxsy.api.consult.service;

import com.sayee.sxsy.api.consult.dao.ConsultDao;
import com.sayee.sxsy.api.consult.entity.ConsultEntity;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 咨询
 */
@Service
public class ConsultService extends CrudService<ConsultDao, ConsultEntity> {
    @Autowired
    private ConsultDao consultDao;
    public void addConsult(ConsultEntity consult){
        consultDao.insert(consult);
    }
    public List<ConsultEntity> getConsultList(){
        return consultDao.getConsultList();
    }
    public void reply(Map map){
        consultDao.reply(map);
    }
    public List<ConsultEntity> getConsultListByUid(String uid){
        return consultDao.getConsultListByUid(uid);
    }
}
