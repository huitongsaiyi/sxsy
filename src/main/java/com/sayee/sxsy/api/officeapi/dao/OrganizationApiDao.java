package com.sayee.sxsy.api.officeapi.dao;

import com.sayee.sxsy.api.officeapi.entity.Organization;
import com.sayee.sxsy.api.officeapi.entity.OrganizationApiEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * @Description
 */
@MyBatisDao
public interface OrganizationApiDao extends CrudDao<OrganizationApiEntity> {
    List<String> getAreaList();
    List<Organization> getListByAreaId(String areaId);
    OrganizationApiEntity getOrganizationById(String id);
}
