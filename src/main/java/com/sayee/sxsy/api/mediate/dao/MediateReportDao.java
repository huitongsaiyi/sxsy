package com.sayee.sxsy.api.mediate.dao;

import com.sayee.sxsy.api.mediate.entity.MediateReport;
import com.sayee.sxsy.api.mediate.entity.TaskEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.Map;

/**
 * @author www.donxon.com
 * @Description
 */
@MyBatisDao
public interface MediateReportDao extends CrudDao<MediateReport> {
    TaskEntity getTaskId(String complaintMainId);
}
