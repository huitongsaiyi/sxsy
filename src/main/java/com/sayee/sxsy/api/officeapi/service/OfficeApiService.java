package com.sayee.sxsy.api.officeapi.service;

import com.sayee.sxsy.api.officeapi.dao.OfficeApiDao;
import com.sayee.sxsy.api.officeapi.entity.OfficeApi;
import com.sayee.sxsy.api.officeapi.entity.OfficeApiEntity;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.sys.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.donxon.com
 * @Description
 */
@Service
public class OfficeApiService extends CrudService<OfficeApiDao, OfficeApiEntity> {
    @Autowired
    private OfficeApiDao officeApiDao;
    public List<OfficeApi> getParentList(){
        return officeApiDao.getParentList();
    }
    public List<OfficeApi> getListByParentId(String Id){
        return officeApiDao.getListByParentId(Id);
    }
    public String getDict(String lableName){
        return officeApiDao.getDict(lableName);
    }
}
