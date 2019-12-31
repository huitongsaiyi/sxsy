/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.datatype.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.api.officeapi.entity.OfficeApi;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.datatype.entity.DataType;
import com.sayee.sxsy.modules.datatype.dao.DataTypeDao;

/**
 * 统计基础数据Service
 * @author zhangfan
 * @version 2019-12-24
 */
@Service
@Transactional(readOnly = true)
public class DataTypeService extends CrudService<DataTypeDao, DataType> {

	public DataType get(String id) {
		return super.get(id);
	}
	
	public List<DataType> findList(DataType dataType) {
		return super.findList(dataType);
	}
	
	public Page<DataType> findPage(Page<DataType> page, DataType dataType) {
		return super.findPage(page, dataType);
	}
	
	@Transactional(readOnly = false)
	public void save(DataType dataType) {
		if (StringUtils.isBlank(dataType.getDataTypeId())){
			dataType.preInsert();
			dataType.setDataTypeId(dataType.getId());
			dao.insert(dataType);
		}else {
			dataType.preUpdate();
			dao.update(dataType);
		}
	}

	@Transactional(readOnly = false)
	public void delete(DataType dataType) {
		super.delete(dataType);
	}





}