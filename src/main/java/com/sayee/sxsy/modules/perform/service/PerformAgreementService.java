/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.perform.service;

import java.util.List;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.perform.entity.PerformAgreement;
import com.sayee.sxsy.modules.perform.dao.PerformAgreementDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 履行协议Service
 * @author lyt
 * @version 2019-06-14
 */
@Service
@Transactional(readOnly = true)
public class PerformAgreementService extends CrudService<PerformAgreementDao, PerformAgreement> {

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;

	public PerformAgreement get(String id) {
		return super.get(id);
	}
	
	public List<PerformAgreement> findList(PerformAgreement performAgreement) {
		return super.findList(performAgreement);
	}
	
	public Page<PerformAgreement> findPage(Page<PerformAgreement> page, PerformAgreement performAgreement) {
		return super.findPage(page, performAgreement);
	}
	
	@Transactional(readOnly = false)
	public void save(PerformAgreement performAgreement) {
		if (StringUtils.isBlank(performAgreement.getPerformAgreementId())){		//判断主键Id是否为空
			performAgreement.preInsert();
			performAgreement.setPerformAgreementId(performAgreement.getId());
			if(StringUtils.isBlank(performAgreement.getAgreementPayAmount()) || performAgreement.getAgreementPayAmount()==null){
				performAgreement.setAgreementPayAmount("0");
			}
			if(StringUtils.isBlank(performAgreement.getHospitalPayAmount()) || performAgreement.getHospitalPayAmount()==null){
				performAgreement.setHospitalPayAmount("0");
			}
			if(StringUtils.isBlank(performAgreement.getInsurancePayAmount()) || performAgreement.getInsurancePayAmount()==null){
				performAgreement.setInsurancePayAmount("0");
			}
			dao.insert(performAgreement);
		}else {
			performAgreement.preUpdate();
			dao.update(performAgreement);
		}
//		super.save(performAgreement);
	}
	
	@Transactional(readOnly = false)
	public void delete(PerformAgreement performAgreement) {
		super.delete(performAgreement);
	}

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request,PerformAgreement performAgreement){
		String files = request.getParameter("files");
		String files1 = request.getParameter("files1");
		String acceId1 = IdGen.uuid();
		String acceId2 = IdGen.uuid();
		String itemId1 = performAgreement.getPerformAgreementId();
		String itemId2 = performAgreement.getPerformAgreementId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		preOperativeConsentService.save1(acceId1,itemId1,files,fjtype1);
		preOperativeConsentService.save1(acceId2,itemId2,files1,fjtype2);
	}
}