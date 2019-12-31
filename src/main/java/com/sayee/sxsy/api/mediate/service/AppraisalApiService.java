package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.AppraisalDao;
import com.sayee.sxsy.api.mediate.entity.Appraisal;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class AppraisalApiService extends CrudService<AppraisalDao, Appraisal> {
    @Autowired
    private AppraisalDao appraisalDao;
    public void save(Appraisal appraisal){
        appraisalDao.insert(appraisal);
    }
}
