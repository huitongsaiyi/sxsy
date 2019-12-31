package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.SummaryDao;
import com.sayee.sxsy.api.mediate.entity.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class SummaryApiService {
    @Autowired
    private SummaryDao summaryDao;
    public void save(Summary summary){
        summaryDao.insert(summary);
    }
}
