/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.assessinfo.entity.AssessInfo;
import com.sayee.sxsy.modules.assessinfo.dao.AssessInfoDao;

/**
 * 案件评价Service
 * @author lyt
 * @version 2019-06-17
 */
@Service
@Transactional(readOnly = true)
public class AssessInfoService extends CrudService<AssessInfoDao, AssessInfo> {
	@Autowired
	private ActTaskService actTaskService;
	public AssessInfo get(String id) {
		return super.get(id);
	}
	
	public List<AssessInfo> findList(AssessInfo assessInfo) {
		return super.findList(assessInfo);
	}
	
	public Page<AssessInfo> findPage(Page<AssessInfo> page, AssessInfo assessInfo) {
		assessInfo.setUser(UserUtils.getUser());
		return super.findPage(page, assessInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(AssessInfo assessInfo) {
		if(StringUtils.isBlank((assessInfo.getCreateBy().getId()))){		//检索创建人ID是否已存在
			//若不存在进行插入
			assessInfo.preInsert();
			assessInfo.setAssessId(assessInfo.getId());
			if(StringUtils.isBlank(assessInfo.getAssessGrade()) || "".equals(assessInfo.getAssessGrade())){         //判断评论分数是否为空
                assessInfo.setAssessGrade("0");
            }
			dao.insert(assessInfo);
		}else{			//若存在进行更新
			assessInfo.preUpdate();
			dao.update(assessInfo);
		}
		if ("yes".equals(assessInfo.getComplaintMain().getAct().getFlag())){
			//List<Act> list = actTaskService.todoList(assessApply.getComplaintMain().getAct());
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(assessInfo.getNextLinkMan());
			var.put("feedback_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(assessInfo.getComplaintMain().getAct().getTaskId(), assessInfo.getComplaintMain().getAct().getProcInsId(), assessInfo.getComplaintMain().getAct().getComment(), assessInfo.getComplaintMain().getCaseNumber(), var);
		}
//	    super.save(assessInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(AssessInfo assessInfo) {
		super.delete(assessInfo);
	}

	public Map<String,Object> getViewDetail(String complaintMainId) {
		Map<String,Object> map=dao.getViewDetail(complaintMainId);
		return map;
	}
}