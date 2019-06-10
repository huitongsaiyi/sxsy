/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.respondentinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.respondentinfo.entity.RespondentInfo;
import com.sayee.sxsy.modules.respondentinfo.dao.RespondentInfoDao;

/**
 * 被调查人信息Service
 * @author gbq
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class RespondentInfoService extends CrudService<RespondentInfoDao, RespondentInfo> {

	public RespondentInfo get(String id) {
		return super.get(id);
	}
	
	public List<RespondentInfo> findList(RespondentInfo respondentInfo) {
		return super.findList(respondentInfo);
	}
	
	public Page<RespondentInfo> findPage(Page<RespondentInfo> page, RespondentInfo respondentInfo) {
		return super.findPage(page, respondentInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(RespondentInfo respondentInfo) {
		super.save(respondentInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(RespondentInfo respondentInfo) {
		super.delete(respondentInfo);
	}
	
}