package com.sayee.sxsy.api.officeapi.dao;

import com.sayee.sxsy.api.officeapi.entity.OfficeApi;
import com.sayee.sxsy.api.officeapi.entity.OfficeApiEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.sys.entity.Dict;

import java.util.List;

@MyBatisDao
public interface OfficeApiDao extends CrudDao<OfficeApiEntity> {
    List<OfficeApi> getParentList();
    List<OfficeApi> getListByParentId(String ID);
    String getDict(String labelName);
}
