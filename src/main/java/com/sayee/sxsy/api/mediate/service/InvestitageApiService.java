package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.InvestitageDao;
import com.sayee.sxsy.api.mediate.entity.Investitage;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 调查取证
 */
@Service
public class InvestitageApiService extends CrudService<InvestitageDao, Investitage> {
    @Autowired
    private InvestitageDao investitageDao;
    public void save(Investitage investitage){
        investitageDao.insert(investitage);
    }
}
