package com.sayee.sxsy.api.dailyreport.service;

import com.sayee.sxsy.api.dailyreport.dao.EgressInfoApiDao;
import com.sayee.sxsy.api.dailyreport.entity.EgressInfoApi;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class EgressInfoApiService extends CrudService<EgressInfoApiDao, EgressInfoApi> {
    @Autowired EgressInfoApiDao egressInfoApiDao;
    public void save(EgressInfoApi egressInfoApi){
        egressInfoApiDao.insert(egressInfoApi);
    }
}
