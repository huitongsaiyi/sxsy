/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaint.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.complaint.dao.ComplaintInfoDao;

/**
 * 投诉接待Service
 * @author zhangfan
 * @version 2019-05-27
 */
@Service
@Transactional(readOnly = true)
public class ComplaintInfoService extends CrudService<ComplaintInfoDao, ComplaintInfo> {

	public ComplaintInfo get(String id) {
		return super.get(id);
	}
	
	public List<ComplaintInfo> findList(ComplaintInfo complaintInfo) {
		return super.findList(complaintInfo);
	}
	
	public Page<ComplaintInfo> findPage(Page<ComplaintInfo> page, ComplaintInfo complaintInfo) {
		return super.findPage(page, complaintInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(ComplaintInfo complaintInfo) {
		super.save(complaintInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComplaintInfo complaintInfo) {
		super.delete(complaintInfo);
	}
	
}