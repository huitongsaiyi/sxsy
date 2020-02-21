package com.sayee.sxsy.api.user.service;

import com.sayee.sxsy.api.user.dao.IdCardApiDao;
import com.sayee.sxsy.api.user.entity.IdCardApiEntity;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class IdCardApiService extends CrudService<IdCardApiDao, IdCardApiEntity>{
    @Autowired
    private IdCardApiDao idCardApiDao;
    public void save(IdCardApiEntity idCardApiEntity){
        idCardApiDao.insert(idCardApiEntity);
    }
}
