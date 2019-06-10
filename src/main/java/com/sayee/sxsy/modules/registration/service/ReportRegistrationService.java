/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.registration.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.complaintdetail.service.ComplaintMainDetailService;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ComplaintMainDao complaintMainDao;

	public ReportRegistration get(String id) {
		return super.get(id);
	}

	public List<ReportRegistration> findList(ReportRegistration reportRegistration) {
		return super.findList(reportRegistration);
	}

	public Page<ReportRegistration> findPage(Page<ReportRegistration> page, ReportRegistration reportRegistration) {
		//获取当前登录用户
		reportRegistration.setUser(UserUtils.getUser());
		//在报案登记的数据 都会管理 实例ID  ，但是存在投诉接待来，没进行保存；  保存了 没进行下一步的
		Page<ReportRegistration> a=super.findPage(page, reportRegistration);
		return super.findPage(page, reportRegistration);
	}
	/* 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<ReportRegistration> findInsPage(Page<ReportRegistration> page, ReportRegistration reportRegistration) {
		reportRegistration.setPage(page);
		page.setList(dao.findList(reportRegistration));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(ReportRegistration reportRegistration) {
		if(reportRegistration.getCreateBy()==null){
			//判断主键ID是否为空
			reportRegistration.preInsert();
			reportRegistration.setReportRegistrationId(reportRegistration.getId());
			//将主键ID设为UUID
			dao.insert(reportRegistration);
		}else{//如果不为空进行更新

			//修改报案登记表
			reportRegistration.preUpdate();
			dao.update(reportRegistration);
		}
		//修改主表信息 因为处理的是  主表事由信息的  对主表信息进行修改即可
		ComplaintMain complaintMain=reportRegistration.getComplaintMain();
		complaintMain.preUpdate();
		complaintMain.setComplaintMainId(reportRegistration.getComplaintMainId());
		complaintMainDao.update(complaintMain);
//		super.save(reportRegistration);
		if ("yes".equals(reportRegistration.getComplaintMain().getAct().getFlag())){
			List<Act> list = actTaskService.todoList(reportRegistration.getComplaintMain().getAct());

			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness=UserUtils.get(reportRegistration.getNextLinkMan());
			var.put("check_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(reportRegistration.getComplaintMain().getAct().getTaskId(), reportRegistration.getComplaintMain().getAct().getProcInsId(), reportRegistration.getComplaintMain().getAct().getComment(), reportRegistration.getComplaintMain().getCaseNumber(), var);
		}
	}

	@Transactional(readOnly = false)
	public void delete(ReportRegistration reportRegistration) {
		super.delete(reportRegistration);
	}
	@Transactional(readOnly = false)
	public void savefj(String acceId1,String itemId1,String files1,String fjtype){
		super.dao.insertzf(acceId1,itemId1,files1,fjtype);
	}
}