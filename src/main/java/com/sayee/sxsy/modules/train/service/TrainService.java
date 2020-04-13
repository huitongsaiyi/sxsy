/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.train.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.train.entity.Train;
import com.sayee.sxsy.modules.train.dao.TrainDao;
/**
 * 培训视频Service
 * @author wjm
 * @version 2020-03-31
 */
@Service
@Transactional(readOnly = true)
public class TrainService extends CrudService<TrainDao, Train> {

	public Train get(String id) {
		return super.get(id);
	}
	
	public List<Train> findList(Train train) {
		return super.findList(train);
	}
	
	public Page<Train> findPage(Page<Train> page, Train train) {
		return super.findPage(page, train);
	}
	
	@Transactional(readOnly = false)
	public void save(Train train) {
		if(train.getVideoPrice().equals("")||train.getVideoPrice()==null){
			train.setVideoPrice("0");
		}
		super.save(train);
	}

	
	@Transactional(readOnly = false)
	public void delete(Train train) {
		super.delete(train);
	}
	
}