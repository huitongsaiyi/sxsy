package com.sayee.sxsy.api.complain.service;

import com.sayee.sxsy.api.complain.dao.ComplainDao;
import com.sayee.sxsy.api.complain.entity.ComplainEntity;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 投诉
 */
@Service
public class ComplainService extends CrudService<ComplainDao, ComplainEntity> {
    @Autowired
    private ComplainDao complainDao;
    public void saveComplain(ComplainEntity complainEntity){
        complainDao.insert(complainEntity);
    }
}
