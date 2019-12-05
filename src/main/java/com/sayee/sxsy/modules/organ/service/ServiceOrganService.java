/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.organ.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import org.activiti.explorer.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.organ.entity.ServiceOrgan;
import com.sayee.sxsy.modules.organ.dao.ServiceOrganDao;

/**
 * 服务机构信息Service
 * @author zhangfan
 * @version 2019-12-03
 */
@Service
@Transactional(readOnly = true)
public class ServiceOrganService extends CrudService<ServiceOrganDao, ServiceOrgan> {

	public ServiceOrgan get(String id) {
		return super.get(id);
	}
	
	public List<ServiceOrgan> findList(ServiceOrgan serviceOrgan) {
		return super.findList(serviceOrgan);
	}
	
	public Page<ServiceOrgan> findPage(Page<ServiceOrgan> page, ServiceOrgan serviceOrgan) {
		return super.findPage(page, serviceOrgan);
	}
	
	@Transactional(readOnly = false)
	public void save(ServiceOrgan serviceOrgan) {
		if (StringUtils.isBlank(serviceOrgan.getServiceOrganId())){
			serviceOrgan.preInsert();
			serviceOrgan.setServiceOrganId(serviceOrgan.getId());
			dao.insert(serviceOrgan);
		}else {
			serviceOrgan.preUpdate();
			dao.update(serviceOrgan);
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(ServiceOrgan serviceOrgan) {
		super.delete(serviceOrgan);
	}
	
}