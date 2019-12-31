package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.PerformDao;
import com.sayee.sxsy.api.mediate.entity.Perform;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class PerformApiService extends CrudService<PerformDao, Perform> {
    @Autowired
    private PerformDao performDao;
    public void save(Perform perform){
        performDao.insert(perform);
    }

}
