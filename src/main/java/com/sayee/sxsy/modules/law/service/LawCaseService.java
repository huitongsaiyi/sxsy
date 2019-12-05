/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.law.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.law.entity.LawCase;
import com.sayee.sxsy.modules.law.dao.LawCaseDao;

/**
 * 法律法规Service
 * @author zhangfan
 * @version 2019-12-03
 */
@Service
@Transactional(readOnly = true)
public class LawCaseService extends CrudService<LawCaseDao, LawCase> {

	public LawCase get(String id) {
		return super.get(id);
	}
	
	public List<LawCase> findList(LawCase lawCase) {
		return super.findList(lawCase);
	}
	
	public Page<LawCase> findPage(Page<LawCase> page, LawCase lawCase) {
		return super.findPage(page, lawCase);
	}
	
	@Transactional(readOnly = false)
	public void save(LawCase lawCase) {
		if (StringUtils.isBlank(lawCase.getLawCaseId())){
			lawCase.preInsert();
			lawCase.setLawCaseId(lawCase.getId());
			dao.insert(lawCase);
		}else {
			lawCase.preUpdate();
			dao.update(lawCase);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(LawCase lawCase) {
		super.delete(lawCase);
	}
	
}