package com.sayee.sxsy.api.officeapi.service;

import com.sayee.sxsy.api.officeapi.dao.OrganizationApiDao;
import com.sayee.sxsy.api.officeapi.entity.Organization;
import com.sayee.sxsy.api.officeapi.entity.OrganizationApiEntity;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.donxon.com
 * @Description 机构
 */
@Service
public class OrganizationApiService extends CrudService<OrganizationApiDao,OrganizationApiEntity> {
    @Autowired
    private OrganizationApiDao officeApiDao;
    public List<String> getAreaList(){
       List<String> list=officeApiDao.getAreaList();
       return list;
    }
    public List<Organization> getListByAreaId(String areaId){
        List<Organization> list=officeApiDao.getListByAreaId(areaId);
        return list;
    }
    public OrganizationApiEntity getOrganizationById(String ID){
        return officeApiDao.getOrganizationById(ID);
    }
}
