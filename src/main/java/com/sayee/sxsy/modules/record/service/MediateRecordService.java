/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.record.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.record.dao.MediateRecordDao;

/**
 * 调解志Service
 * @author lyt
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class MediateRecordService extends CrudService<MediateRecordDao, MediateRecord> {

	public MediateRecord get(String id) {
		return super.get(id);
	}
	
	public List<MediateRecord> findList(MediateRecord mediateRecord) {
		return super.findList(mediateRecord);
	}
	
	public Page<MediateRecord> findPage(Page<MediateRecord> page, MediateRecord mediateRecord) {
		return super.findPage(page, mediateRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(MediateRecord mediateRecord) {
		super.save(mediateRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(MediateRecord mediateRecord) {
		super.delete(mediateRecord);
	}
	
}