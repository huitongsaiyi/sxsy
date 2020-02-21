/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.signtype.service;

import java.util.Date;
import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.signtype.entity.SignTypeInfo;
import com.sayee.sxsy.modules.signtype.dao.SignTypeInfoDao;

/**
 * 协议书Service
 * @author zhangfan
 * @version 2020-02-04
 */
@Service
@Transactional(readOnly = true)
public class SignTypeInfoService extends CrudService<SignTypeInfoDao, SignTypeInfo> {

	public SignTypeInfo get(String id) {
		return super.get(id);
	}
	
	public List<SignTypeInfo> findList(SignTypeInfo signTypeInfo) {
		return super.findList(signTypeInfo);
	}
	
	public Page<SignTypeInfo> findPage(Page<SignTypeInfo> page, SignTypeInfo signTypeInfo) {
		return super.findPage(page, signTypeInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(SignTypeInfo signTypeInfo) {
		Date date=signTypeInfo.getCreateDate();
		if (StringUtils.isBlank(signTypeInfo.getSource())){
			//新增
			signTypeInfo.preInsert();
			signTypeInfo.setTypeId(signTypeInfo.getId());
			signTypeInfo.setCreateDate(date);
			dao.insert(signTypeInfo);
		}else {
			signTypeInfo.preUpdate();
			dao.update(signTypeInfo);
		}
		//super.save(signTypeInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(SignTypeInfo signTypeInfo) {
		super.delete(signTypeInfo);
	}
	
}