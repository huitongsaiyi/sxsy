package com.sayee.sxsy.api.casetype.service;

import com.sayee.sxsy.api.casetype.dao.CaseTypeDao;
import com.sayee.sxsy.api.casetype.entity.CaseTypeEntity;
import com.sayee.sxsy.api.officeapi.entity.OfficeApi;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 案件分类
 */
@Service
public class CaseTypeApiService extends CrudService<CaseTypeDao, CaseTypeEntity> {
    @Autowired
    private CaseTypeDao caseTypeDao;
    public List<OfficeApi> getParentList(){
        return caseTypeDao.getParentList();
    }
    public List<OfficeApi> getListByParentId(String Id){
        return caseTypeDao.getListByParentId(Id);
    }
}
