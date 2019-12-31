package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.AssessDao;
import com.sayee.sxsy.api.mediate.entity.Assess;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class AssessApiService extends CrudService<AssessDao, Assess> {
    @Autowired
    private AssessDao assessDao;
    public void save(Assess assess){
        assessDao.insert(assess);
    }
}
