package com.sayee.sxsy.api.train.service;

import com.sayee.sxsy.api.train.dao.TrainApiDao;
import com.sayee.sxsy.api.train.entity.TrainEntity;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaint.dao.ComplaintInfoDao;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.train.entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
@Service
public class TrainApiService extends CrudService<TrainApiDao, TrainEntity> {
    @Autowired
    private TrainApiDao trainDao;

    public Page<TrainEntity> getVideo(Page<TrainEntity> page, TrainEntity train) {
        return super.findPage(page, train);
    }

    public TrainEntity videoDetail(String id) {
        TrainEntity train=trainDao.get(id);
        train.setPath(StringUtils.isBlank(train.getPath()) ? "" : train.getPath().replace("|",""));
        return train;
    }
}
