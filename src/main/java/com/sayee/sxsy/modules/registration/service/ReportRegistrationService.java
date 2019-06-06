/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.registration.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.registration.dao.ReportRegistrationDao;

/**
 * 报案登记Service
 * @author lyt
 * @version 2019-06-05
 */
@Service
@Transactional(readOnly = true)
public class ReportRegistrationService extends CrudService<ReportRegistrationDao, ReportRegistration> {

	public ReportRegistration get(String id) {
		return super.get(id);
	}
	
	public List<ReportRegistration> findList(ReportRegistration reportRegistration) {
		return super.findList(reportRegistration);
	}
	
	public Page<ReportRegistration> findPage(Page<ReportRegistration> page, ReportRegistration reportRegistration) {
		return super.findPage(page, reportRegistration);
	}
	
	@Transactional(readOnly = false)
	public void save(ReportRegistration reportRegistration) {
		if(StringUtils.isNotBlank(reportRegistration.getReportRegistrationId())){	//判断主键ID是否为空
			reportRegistration.preInsert();
			reportRegistration.setReportRegistrationId(reportRegistration.getId());	//将主键ID设为UUID
			dao.insert(reportRegistration);
		}else{		//如果不为空进行更新
			reportRegistration.preUpdate();
			dao.update(reportRegistration);
		}
//		super.save(reportRegistration);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReportRegistration reportRegistration) {
		super.delete(reportRegistration);
	}
	
}