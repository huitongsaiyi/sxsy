/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.typeinfo.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import com.sayee.sxsy.modules.typeinfo.dao.TypeInfoDao;

/**
 * 类型总表Service
 *
 * @author lyt
 * @version 2019-06-01
 */
@Service
@Transactional(readOnly = true)
public class TypeInfoService extends CrudService<TypeInfoDao, TypeInfo> {

    public TypeInfo get(String id) {
        return super.get(id);
    }

    public List<TypeInfo> findList(TypeInfo typeInfo) {
        return super.findList(typeInfo);
    }

    public Page<TypeInfo> findPage(Page<TypeInfo> page, TypeInfo typeInfo) {
        return super.findPage(page, typeInfo);
    }

    @Transactional(readOnly = false)
    public void save(TypeInfo typeInfo) {
        if (StringUtils.isBlank(typeInfo.getId())) {
            typeInfo.preInsert();
            typeInfo.setTypeId(typeInfo.getId());
            dao.insert(typeInfo);
        } else {
            typeInfo.preUpdate();
            dao.update(typeInfo);
        }
//        super.save(typeInfo);
    }

    @Transactional(readOnly = false)
    public void delete(TypeInfo typeInfo) {
        super.delete(typeInfo);
    }

}