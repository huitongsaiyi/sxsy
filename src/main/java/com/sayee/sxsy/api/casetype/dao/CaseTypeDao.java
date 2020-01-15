package com.sayee.sxsy.api.casetype.dao;

import com.sayee.sxsy.api.casetype.entity.CaseTypeEntity;
import com.sayee.sxsy.api.officeapi.entity.OfficeApi;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * @Description 案件分类
 */
@MyBatisDao
public interface CaseTypeDao extends CrudDao<CaseTypeEntity> {
    /*父级列表*/
    List<OfficeApi> getParentList();
    /*二级列表*/
    List<OfficeApi> getListByParentId(String ID);
}
