/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;

/**
 * 纠纷调解Service
 * @author lyt
 * @version 2019-06-04
 */
@Service
@Transactional(readOnly = true)
public class ComplaintMainService extends CrudService<ComplaintMainDao, ComplaintMain> {

	public ComplaintMain get(String id) {
		return super.get(id);
	}
	
	public List<ComplaintMain> findList(ComplaintMain complaintMain) {
		return super.findList(complaintMain);
	}
	
	public Page<ComplaintMain> findPage(Page<ComplaintMain> page, ComplaintMain complaintMain) {
		return super.findPage(page, complaintMain);
	}
	
	@Transactional(readOnly = false)
	public void save(ComplaintMain complaintMain) {
		super.save(complaintMain);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComplaintMain complaintMain) {
		super.delete(complaintMain);
	}
	
}