/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.nestigateeividence.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sayee.sxsy.modules.nestigateeividence.entity.InvestigateEvidence;
import com.sayee.sxsy.modules.nestigateeividence.dao.InvestigateEvidenceDao;

/**
 * 调查取证Service
 * @author gbq
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class InvestigateEvidenceService extends CrudService<InvestigateEvidenceDao, InvestigateEvidence> {
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ComplaintMainDao complaintMainDao;
	public InvestigateEvidence get(String id) {
		return super.get(id);
	}
	
	public List<InvestigateEvidence> findList(InvestigateEvidence investigateEvidence) {
		return super.findList(investigateEvidence);
	}
	
	public Page<InvestigateEvidence> findPage(Page<InvestigateEvidence> page, InvestigateEvidence investigateEvidence) {
		//获取当前登陆用户
		investigateEvidence.setUser(UserUtils.getUser());
		Page<InvestigateEvidence> a=super.findPage(page, investigateEvidence);
		return super.findPage(page, investigateEvidence);
	}
	
	@Transactional(readOnly = false)
	public void save(InvestigateEvidence investigateEvidence) {
		if(investigateEvidence.getCreateBy()==null){
			//判断主键ID是否为空
			investigateEvidence.preInsert();
			investigateEvidence.setInvestigateEvidenceId(investigateEvidence.getId());
			//将主键设为uuid
			dao.insert(investigateEvidence);
		}else{
			//如果不为空进行更新
			//修改调查取证表
			investigateEvidence.preUpdate();
			dao.update(investigateEvidence);
		}
		//修改主表信息 因为处理的是  主表事由信息的  对主表信息进行修改即可
		ComplaintMain complaintMain =investigateEvidence.getComplaintMain();
		complaintMain.preUpdate();
		complaintMain.setComplaintMainId(investigateEvidence.getComplaintMainId());
		complaintMainDao.update(complaintMain);

		//super.save(investigateEvidence);
		if("yes".equals(investigateEvidence.getComplaintMain().getAct().getFlag())){
			//获取待办列表
			 List<Act> list = actTaskService.todoList(investigateEvidence.getComplaintMain().getAct());
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness=UserUtils.get(investigateEvidence.getNextLinkMan());
			var.put("check_user",assigness.getLoginName());
		//执行流程
			actTaskService.complete(investigateEvidence.getComplaintMain().getAct().getTaskId(),investigateEvidence.getComplaintMain().getAct().getProcInsId(),investigateEvidence.getComplaintMain().getAct().getComment(),investigateEvidence.getComplaintMain().getCaseNumber(),var);
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(InvestigateEvidence investigateEvidence) {
		super.delete(investigateEvidence);
	}
	
}