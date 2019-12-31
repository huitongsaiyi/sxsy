package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.MediateReportDao;
import com.sayee.sxsy.api.mediate.entity.MediateReport;
import com.sayee.sxsy.api.mediate.entity.TaskEntity;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description
 */
@Service
public class MediateReportApiService extends CrudService<MediateReportDao,MediateReport> {
    @Autowired
    private MediateReportDao mediateReportDao;
    public Integer saveMediateReport(MediateReport mediateReport){
        return mediateReportDao.insert(mediateReport);
    }
    public TaskEntity getTaskId(String complaintMainId){
        return mediateReportDao.getTaskId(complaintMainId);
    }
}
