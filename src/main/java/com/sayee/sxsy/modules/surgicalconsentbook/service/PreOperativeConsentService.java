/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.surgicalconsentbook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.surgicalconsentbook.entity.PreOperativeConsent;
import com.sayee.sxsy.modules.surgicalconsentbook.dao.PreOperativeConsentDao;

/**
 * 术前同意书见证管理Service
 * @author gbq
 * @version 2019-05-31
 */
@Service
@Transactional(readOnly = true)
public class PreOperativeConsentService extends CrudService<PreOperativeConsentDao, PreOperativeConsent> {

	public PreOperativeConsent get(String id) {
		return super.get(id);
	}
	
	public List<PreOperativeConsent> findList(PreOperativeConsent preOperativeConsent) {
		return super.findList(preOperativeConsent);
	}
	
	public Page<PreOperativeConsent> findPage(Page<PreOperativeConsent> page, PreOperativeConsent preOperativeConsent) {
		return super.findPage(page, preOperativeConsent);
	}
	
	@Transactional(readOnly = false)
	public void save(PreOperativeConsent preOperativeConsent) {

		super.save(preOperativeConsent);


	}
	
	@Transactional(readOnly = false)
	public void delete(PreOperativeConsent preOperativeConsent) {
		super.delete(preOperativeConsent);
	}
	@Transactional(readOnly = false)
	public void save1(String acceId1,String itemId1,String files1){
		super.dao.insertzf(acceId1,itemId1,files1);
	}
	
}