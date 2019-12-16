/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.major.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.major.entity.MajorInfo;
import com.sayee.sxsy.modules.major.dao.MajorInfoDao;

/**
 * 重大事件Service
 * @author zhangfan
 * @version 2019-12-09
 */
@Service
@Transactional(readOnly = true)
public class MajorInfoService extends CrudService<MajorInfoDao, MajorInfo> {

	public MajorInfo get(String id) {
		return super.get(id);
	}
	
	public List<MajorInfo> findList(MajorInfo majorInfo) {
		return super.findList(majorInfo);
	}
	
	public Page<MajorInfo> findPage(Page<MajorInfo> page, MajorInfo majorInfo) {
		return super.findPage(page, majorInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(MajorInfo majorInfo) {
		super.save(majorInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MajorInfo majorInfo) {
		super.delete(majorInfo);
	}
	
}