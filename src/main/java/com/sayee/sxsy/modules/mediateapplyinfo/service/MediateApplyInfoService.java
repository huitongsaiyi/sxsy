/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.mediateapplyinfo.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.mediateapplyinfo.entity.MediateApplyInfo;
import com.sayee.sxsy.modules.mediateapplyinfo.dao.MediateApplyInfoDao;

/**
 * 调节申请Service
 * @author lyt
 * @version 2019-06-19
 */
@Service
@Transactional(readOnly = true)
public class MediateApplyInfoService extends CrudService<MediateApplyInfoDao, MediateApplyInfo> {

	public MediateApplyInfo get(String id) {
		return super.get(id);
	}
	
	public List<MediateApplyInfo> findList(MediateApplyInfo mediateApplyInfo) {
		return super.findList(mediateApplyInfo);
	}
	
	public Page<MediateApplyInfo> findPage(Page<MediateApplyInfo> page, MediateApplyInfo mediateApplyInfo) {
		return super.findPage(page, mediateApplyInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(MediateApplyInfo mediateApplyInfo) {
		if(StringUtils.isBlank(mediateApplyInfo.getMediateApplyId())){
			mediateApplyInfo.preInsert();
			mediateApplyInfo.setMediateApplyId(mediateApplyInfo.getId());
			dao.insert(mediateApplyInfo);
		}else{
			mediateApplyInfo.preUpdate();
			dao.update(mediateApplyInfo);
		}
//		super.save(mediateApplyInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MediateApplyInfo mediateApplyInfo) {
		super.delete(mediateApplyInfo);
	}
	
}