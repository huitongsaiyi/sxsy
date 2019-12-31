package com.sayee.sxsy.api.publicnotice.dao;

import com.sayee.sxsy.api.publicnotice.entity.LawCaseApi;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * @Description
 */
@MyBatisDao
public interface LawCaseApiDao extends CrudDao<LawCaseApi> {
    List<LawCaseApi> getPageLawCase(Map map);
    int getTotal(String type);
    LawCaseApi getLawCaseInfo(String id);
    LawCaseApi getServiceByType(String type);
}
