/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.perform.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.perform.entity.PerformAgreement;
import com.sayee.sxsy.modules.perform.dao.PerformAgreementDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 履行协议Service
 * @author lyt
 * @version 2019-06-14
 */
@Service
@Transactional(readOnly = true)
public class PerformAgreementService extends CrudService<PerformAgreementDao, PerformAgreement> {

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private ActTaskService actTaskService;
	public PerformAgreement get(String id) {
		return super.get(id);
	}
	
	public List<PerformAgreement> findList(PerformAgreement performAgreement) {
		performAgreement.setUser(UserUtils.getUser());
		return super.findList(performAgreement);
	}
	
	public Page<PerformAgreement> findPage(Page<PerformAgreement> page, PerformAgreement performAgreement) {
		List<String> aa= ObjectUtils.convert(UserUtils.getRoleList().toArray(),"enname",true);
		User user=UserUtils.getUser();
		if (user.isAdmin() || aa.contains("commission") || aa.contains("DirectorOfMediation")){//是管理员  医调委主任 调解部副主任  查看全部
			//!aa.contains("dept") &&
		}else if((  aa.contains("deputyDirector") ||aa.contains("director")) ){
			//工作站 主任 副主任 看自己 的员工
			List<String> list=new ArrayList<String>();
			List<User> listUser=UserUtils.getUserByOffice(user.getOffice().getId());
			for (User people:listUser) {
				list.add(people.getLoginName());
			}
			if (list.size()>0){
				performAgreement.setList(list);
			}else {
				list.add(user.getLoginName());
				performAgreement.setList(list);
			}
		}else {//不是管理员查看自己创建的
			performAgreement.setUser(UserUtils.getUser());
		}
		return super.findPage(page, performAgreement);
	}
	
	@Transactional(readOnly = false)
	public void save(PerformAgreement performAgreement) {
		if (StringUtils.isBlank(performAgreement.getCreateBy().getId())){		//判断主键Id是否为空
			performAgreement.preInsert();
			performAgreement.setPerformAgreementId(performAgreement.getId());
			if(StringUtils.isBlank(performAgreement.getAgreementPayAmount()) || performAgreement.getAgreementPayAmount()==null){
				performAgreement.setAgreementPayAmount("0");
			}
			if(StringUtils.isBlank(performAgreement.getHospitalPayAmount()) || performAgreement.getHospitalPayAmount()==null){
				performAgreement.setHospitalPayAmount("0");
			}
			if(StringUtils.isBlank(performAgreement.getInsurancePayAmount()) || performAgreement.getInsurancePayAmount()==null){
				performAgreement.setInsurancePayAmount("0");
			}
			dao.insert(performAgreement);
		}else {
			performAgreement.preUpdate();
			dao.update(performAgreement);
		}
		if ("yes".equals(performAgreement.getComplaintMain().getAct().getFlag())){
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(performAgreement.getNextLinkMan());
			var.put("summary_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(performAgreement.getComplaintMain().getAct().getTaskId(), performAgreement.getComplaintMain().getAct().getProcInsId(), performAgreement.getComplaintMain().getAct().getComment(), performAgreement.getComplaintMain().getCaseNumber(), var);
		}
//		super.save(performAgreement);
	}
	
	@Transactional(readOnly = false)
	public void delete(PerformAgreement performAgreement) {
		super.delete(performAgreement);
	}

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request,PerformAgreement performAgreement){
		String files = request.getParameter("files");
		String files1 = request.getParameter("files1");
		String acceId = null;
		String itemId = performAgreement.getPerformAgreementId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		if(StringUtils.isNotBlank(files)){
			String acceId1 =request.getParameter("acceId1");
			if(StringUtils.isNotBlank(acceId1)){
				preOperativeConsentService.updatefj(files,itemId,fjtype1);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files,fjtype1);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype1);
		}

		if(StringUtils.isNotBlank(files1)){
			String acceId2=request.getParameter("acceId2");
			if(StringUtils.isNotBlank(acceId2)){
				preOperativeConsentService.updatefj(files1,itemId,fjtype2);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files1,fjtype2);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype2);
		}
	}
}