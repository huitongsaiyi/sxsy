package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.ReachDao;
import com.sayee.sxsy.api.mediate.entity.Reach;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author www.donxon.com
 * @Description
 */
@Service
public class ReachApiService extends CrudService<ReachDao, Reach> {
    @Autowired
    private ReachDao reachDao;
    public void save(Reach reach){
        reachDao.insert(reach);
    }
}
