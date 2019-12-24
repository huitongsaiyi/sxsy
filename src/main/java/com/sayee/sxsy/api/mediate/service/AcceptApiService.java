package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.AcceptDao;
import com.sayee.sxsy.api.mediate.entity.Accept;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author www.donxon.com
 * @Description
 */
@Service
public class AcceptApiService extends CrudService<AcceptDao, Accept> {
    @Autowired
    private AcceptDao acceptDao;
    public void save(Accept accept){
        acceptDao.insert(accept);
    }
}
