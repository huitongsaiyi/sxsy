/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.stopmediate.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.stopmediate.entity.StopMediate;
import com.sayee.sxsy.modules.stopmediate.dao.StopMediateDao;

/**
 * 终止调解Service
 * @author lyt
 * @version 2019-06-20
 */
@Service
@Transactional(readOnly = true)
public class StopMediateService extends CrudService<StopMediateDao, StopMediate> {

	public StopMediate get(String id) {
		return super.get(id);
	}
	
	public List<StopMediate> findList(StopMediate stopMediate) {
		return super.findList(stopMediate);
	}
	
	public Page<StopMediate> findPage(Page<StopMediate> page, StopMediate stopMediate) {
		return super.findPage(page, stopMediate);
	}
	
	@Transactional(readOnly = false)
	public void save(StopMediate stopMediate) {
		if(StringUtils.isBlank(stopMediate.getCreateBy().getId()) || "".equals(stopMediate.getCreateBy().getId())){
			stopMediate.preInsert();
			stopMediate.setStopMediateId(stopMediate.getId());
			dao.insert(stopMediate);
		}else{
			stopMediate.preUpdate();
			dao.update(stopMediate);
		}
//		super.save(stopMediate);
	}
	
	@Transactional(readOnly = false)
	public void delete(StopMediate stopMediate) {
		super.delete(stopMediate);
	}
	
}