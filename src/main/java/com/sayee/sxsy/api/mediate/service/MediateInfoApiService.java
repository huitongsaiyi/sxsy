package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.MediateInfoApiDao;
import com.sayee.sxsy.api.mediate.entity.MediateInfoApiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 调解信息详情
 */
@Service
public class MediateInfoApiService {
    @Autowired
    private MediateInfoApiDao mediateInfoApiDao;
    public Integer saveMediate(MediateInfoApiEntity mediateInfoApiEntity){
       return mediateInfoApiDao.insert(mediateInfoApiEntity);
    }

}
