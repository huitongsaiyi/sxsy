/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessapply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.assessapply.entity.AssessApply;
import com.sayee.sxsy.modules.assessapply.dao.AssessApplyDao;

/**
 * 评估申请Service
 * @author zhangfan
 * @version 2019-06-11
 */
@Service
@Transactional(readOnly = true)
public class AssessApplyService extends CrudService<AssessApplyDao, AssessApply> {
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ComplaintMainDao complaintMainDao;

	public AssessApply get(String id) {
		return super.get(id);
	}
	
	public List<AssessApply> findList(AssessApply assessApply) {
		return super.findList(assessApply);
	}
	
	public Page<AssessApply> findPage(Page<AssessApply> page, AssessApply assessApply) {
		return super.findPage(page, assessApply);
	}
	
	@Transactional(readOnly = false)
	public void save(AssessApply assessApply) {
		if(StringUtils.isBlank(assessApply.getCreateBy().getId())){
			//判断主键ID是否为空
			assessApply.preInsert();
			assessApply.setAssessApplyId(assessApply.getId());
			//将主键ID设为UUID
			dao.insert(assessApply);
		}else{//如果不为空进行更新

			//修改报案登记表
			assessApply.preUpdate();
			dao.update(assessApply);
		}
		//修改主表信息 因为处理的是  主表事由信息的  对主表信息进行修改即可
		ComplaintMain complaintMain=assessApply.getComplaintMain();
		complaintMain.preUpdate();
		complaintMain.setComplaintMainId(assessApply.getComplaintMainId());
		complaintMainDao.update(complaintMain);
		if ("yes".equals(assessApply.getComplaintMain().getAct().getFlag())){
			List<Act> list = actTaskService.todoList(assessApply.getComplaintMain().getAct());

			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(assessApply.getNextLinkMan());
			var.put("approval_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(assessApply.getComplaintMain().getAct().getTaskId(), assessApply.getComplaintMain().getAct().getProcInsId(), assessApply.getComplaintMain().getAct().getComment(), assessApply.getComplaintMain().getCaseNumber(), var);
		}


	}
	
	@Transactional(readOnly = false)
	public void delete(AssessApply assessApply) {
		super.delete(assessApply);
	}
	
}