/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.insuranceslip.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.insuranceslip.entity.InsuranceSlip;
import com.sayee.sxsy.modules.insuranceslip.dao.InsuranceSlipDao;

/**
 * 投保单Service
 * @author yang
 * @version 2020-03-23
 */
@Service
@Transactional(readOnly = true)
public class InsuranceSlipService extends CrudService<InsuranceSlipDao, InsuranceSlip> {

	public InsuranceSlip get(String id) {
		return super.get(id);
	}
	
	public List<InsuranceSlip> findList(InsuranceSlip insuranceSlip) {
		return super.findList(insuranceSlip);
	}
	
	public Page<InsuranceSlip> findPage(Page<InsuranceSlip> page, InsuranceSlip insuranceSlip) {
		return super.findPage(page, insuranceSlip);
	}
	
	@Transactional(readOnly = false)
	public void save(InsuranceSlip insuranceSlip) {
		super.save(insuranceSlip);
	}
	
	@Transactional(readOnly = false)
	public void delete(InsuranceSlip insuranceSlip) {
		super.delete(insuranceSlip);
	}
	
}