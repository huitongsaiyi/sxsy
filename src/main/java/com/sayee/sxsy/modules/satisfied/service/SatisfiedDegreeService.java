/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.satisfied.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.satisfied.entity.SatisfiedDegree;
import com.sayee.sxsy.modules.satisfied.dao.SatisfiedDegreeDao;

/**
 * 满意度Service
 * @author zhangfan
 * @version 2019-12-03
 */
@Service
@Transactional(readOnly = true)
public class SatisfiedDegreeService extends CrudService<SatisfiedDegreeDao, SatisfiedDegree> {

	public SatisfiedDegree get(String id) {
		return super.get(id);
	}
	
	public List<SatisfiedDegree> findList(SatisfiedDegree satisfiedDegree) {
		return super.findList(satisfiedDegree);
	}
	
	public Page<SatisfiedDegree> findPage(Page<SatisfiedDegree> page, SatisfiedDegree satisfiedDegree) {
		return super.findPage(page, satisfiedDegree);
	}
	
	@Transactional(readOnly = false)
	public void save(SatisfiedDegree satisfiedDegree) {
		super.save(satisfiedDegree);
	}
	
	@Transactional(readOnly = false)
	public void delete(SatisfiedDegree satisfiedDegree) {
		super.delete(satisfiedDegree);
	}
	
}