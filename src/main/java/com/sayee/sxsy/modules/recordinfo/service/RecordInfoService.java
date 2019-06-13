/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.recordinfo.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.recordinfo.dao.RecordInfoDao;

/**
 * 笔录Service
 * @author 逯洋涛
 * @version 2019-06-11
 */
@Service
@Transactional(readOnly = true)
public class RecordInfoService extends CrudService<RecordInfoDao, RecordInfo> {

	public RecordInfo get(String id) {
		return super.get(id);
	}
	
	public List<RecordInfo> findList(RecordInfo recordInfo) {
		return super.findList(recordInfo);
	}
	
	public Page<RecordInfo> findPage(Page<RecordInfo> page, RecordInfo recordInfo) {
		return super.findPage(page, recordInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(RecordInfo recordInfo) {
		if(StringUtils.isBlank(recordInfo.getRecordId())){
			//判断主键ID是否为空
			recordInfo.preInsert();
			recordInfo.setRecordId(recordInfo.getId());
			//将主键ID设为UUID
			dao.insert(recordInfo);
		}else{//如果不为空进行更新

			//修改报案登记表
			recordInfo.preUpdate();
			dao.update(recordInfo);
		}
//		super.save(recordInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(RecordInfo recordInfo) {
		super.delete(recordInfo);
	}
	
}