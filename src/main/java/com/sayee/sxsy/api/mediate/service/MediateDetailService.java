package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.MediateDetailDao;
import com.sayee.sxsy.api.mediate.entity.MediateDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description 投诉接待
 */
@Service
public class MediateDetailService {
    @Autowired
    private MediateDetailDao mediateDetailDao;
    public Integer save(MediateDetail mediateDetail){
        mediateDetail.preInsert();
        mediateDetail.setComplaintMainDetailId(mediateDetail.getId());
        return mediateDetailDao.insert(mediateDetail);
    }

}
