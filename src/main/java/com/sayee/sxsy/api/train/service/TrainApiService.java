package com.sayee.sxsy.api.train.service;

import com.sayee.sxsy.api.train.dao.TrainDao;
import com.sayee.sxsy.api.train.entity.Train;
import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
@Service
public class TrainApiService {
    @Autowired
    private TrainDao trainDao;


    public List<Train> getVideo(String s) {
        Train train=new Train();
        train.setSend(s);
        List<Train> aa= trainDao.findList(train);
        return aa;
    }

    public Train videoDetail(String id) {
        Train train=trainDao.get(id);
        train.setPath(StringUtils.isBlank(train.getPath()) ? "" : train.getPath().replace("|",""));
        return train;
    }
}
