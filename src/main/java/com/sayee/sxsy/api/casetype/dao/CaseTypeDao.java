package com.sayee.sxsy.api.casetype.dao;

import com.sayee.sxsy.api.casetype.entity.CaseTypeEntity;
import com.sayee.sxsy.api.officeapi.entity.OfficeApi;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * @author www.donxon.com
 * @Description
 */
@MyBatisDao
public interface CaseTypeDao extends CrudDao<CaseTypeEntity> {
    List<OfficeApi> getParentList();
    List<OfficeApi> getListByParentId(String ID);
}
