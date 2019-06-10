/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintdetail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.act.utils.ActUtils;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.complaintdetail.entity.ComplaintMainDetail;
import com.sayee.sxsy.modules.complaintdetail.dao.ComplaintMainDetailDao;

/**
 * 医调委投诉接待Service
 * @author zhangfan
 * @version 2019-06-05
 */
@Service
@Transactional(readOnly = true)
public class ComplaintMainDetailService extends CrudService<ComplaintMainDetailDao, ComplaintMainDetail> {
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ComplaintMainDao complaintMainDao;
	@Autowired
	private ComplaintMainDetailDao complaintMainDetailDao;
	public ComplaintMainDetail get(String id) {
		return super.get(id);
	}
	
	public List<ComplaintMainDetail> findList(ComplaintMainDetail complaintMainDetail) {
		return super.findList(complaintMainDetail);
	}
	
	public Page<ComplaintMainDetail> findPage(Page<ComplaintMainDetail> page, ComplaintMainDetail complaintMainDetail) {
		return super.findPage(page, complaintMainDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(ComplaintMainDetail complaintMainDetail) {
		if (StringUtils.isNotBlank(complaintMainDetail.getComplaintMainDetailId())){
			//说明是 进行修改
			//修改主表信息
			ComplaintMain complaintMain=complaintMainDetail.getComplaintMain();
			complaintMain.preUpdate();
			complaintMain.setComplaintMainId(complaintMainDetail.getComplaintMainId());
			complaintMainDao.update(complaintMain);
			//修改投诉接待表
			complaintMainDetail.preUpdate();
			complaintMainDetailDao.update(complaintMainDetail);
		}else{
			//进行新增
			//先保存主表
			ComplaintMain complaintMain=complaintMainDetail.getComplaintMain();
			complaintMain.preInsert();
			complaintMain.setComplaintMainId(complaintMain.getId());
			if(StringUtils.isBlank(complaintMain.getPatientAge())){
				complaintMain.setPatientAge("0");
			}
			complaintMainDao.insert(complaintMain);
			//在保存子表
			complaintMainDetail.preInsert();
			complaintMainDetail.setComplaintMainId(complaintMain.getComplaintMainId());
			complaintMainDetail.setComplaintMainDetailId(complaintMainDetail.getId());
			dao.insert(complaintMainDetail);
		}
		if ("yes".equals(complaintMainDetail.getComplaintMain().getAct().getFlag())){
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","1");
			User assigness= UserUtils.get(complaintMainDetail.getNextLinkMan());
			var.put("enrollment_user",assigness.getLoginName());
			var.put("id","complaint_main_id");
			// 启动流程
			actTaskService.startProcess("complaint", "complaint_main", complaintMainDetail.getComplaintMain().getComplaintMainId(), complaintMainDetail.getComplaintMain().getCaseNumber(),var);
			//启动流程的时候 创建一个 隐藏的角色

		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ComplaintMainDetail complaintMainDetail) {
		super.delete(complaintMainDetail);
	}
	
}