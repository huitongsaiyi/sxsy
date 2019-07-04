/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.program.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.program.entity.MediateProgram;
import com.sayee.sxsy.modules.program.dao.MediateProgramDao;

/**
 * 调解程序Service
 * @author zhangfan
 * @version 2019-07-04
 */
@Service
@Transactional(readOnly = true)
public class MediateProgramService extends CrudService<MediateProgramDao, MediateProgram> {

	public MediateProgram get(String id) {
		return super.get(id);
	}
	
	public List<MediateProgram> findList(MediateProgram mediateProgram) {
		return super.findList(mediateProgram);
	}
	
	public Page<MediateProgram> findPage(Page<MediateProgram> page, MediateProgram mediateProgram) {
		return super.findPage(page, mediateProgram);
	}
	
	@Transactional(readOnly = false)
	public void save(MediateProgram mediateProgram) {
		super.save(mediateProgram);
	}
	
	@Transactional(readOnly = false)
	public void delete(MediateProgram mediateProgram) {
		super.delete(mediateProgram);
	}
	
}