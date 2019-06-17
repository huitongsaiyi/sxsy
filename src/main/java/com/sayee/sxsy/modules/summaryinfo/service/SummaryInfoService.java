/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.summaryinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
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
import com.sayee.sxsy.modules.summaryinfo.entity.SummaryInfo;
import com.sayee.sxsy.modules.summaryinfo.dao.SummaryInfoDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 案件总结Service
 * @author gbq
 * @version 2019-06-17
 */
@Service
@Transactional(readOnly = true)
public class SummaryInfoService extends CrudService<SummaryInfoDao, SummaryInfo>  {
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private SummaryInfoDao summaryInfoDao;
	public SummaryInfo get(String id) {
		return super.get(id);
	}
	
	public List<SummaryInfo> findList(SummaryInfo summaryInfo) {

		return super.findList(summaryInfo);
	}
	public List<SummaryInfo> findListSummmary(SummaryInfo summaryInfo){
		List<SummaryInfo> listSummmary = summaryInfoDao.findListSummmary(summaryInfo);
		return listSummmary;
	}

	public Page<SummaryInfo> findPage(Page<SummaryInfo> page, SummaryInfo summaryInfo) {
		summaryInfo.setUser(UserUtils.getUser());
		return super.findPage(page, summaryInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(SummaryInfo summaryInfo, HttpServletRequest request) {
		if(StringUtils.isBlank(summaryInfo.getCreateBy().getId())){
			summaryInfo.preInsert();
			summaryInfo.setSummaryId(summaryInfo.getId());
			dao.insert(summaryInfo);
			this.fj(summaryInfo,request);
		}else{
			summaryInfo.preUpdate();
			dao.update(summaryInfo);
		}
		if ("yes".equals(summaryInfo.getComplaintMain().getAct().getFlag())){
			//List<Act> list = actTaskService.todoList(assessApply.getComplaintMain().getAct());
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(summaryInfo.getNextLinkMan());
			var.put("assess_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(summaryInfo.getComplaintMain().getAct().getTaskId(), summaryInfo.getComplaintMain().getAct().getProcInsId(), summaryInfo.getComplaintMain().getAct().getComment(), summaryInfo.getComplaintMain().getCaseNumber(), var);
		}
//		super.save(summaryInfo);
	}

	//保存附件
	public void fj(SummaryInfo summaryInfo,HttpServletRequest request){
		//从前台获取附件
		String files1=request.getParameter("files1");
		String files2=request.getParameter("files2");
		String files3=request.getParameter("files3");
		String files4=request.getParameter("files4");
		String files5=request.getParameter("files5");
		String files6=request.getParameter("files6");
		String files7=request.getParameter("files7");
		String files8=request.getParameter("files8");
		String files9=request.getParameter("files9");
		String files10=request.getParameter("files10");
		String files11=request.getParameter("files11");
		String files12=request.getParameter("files12");
		String files13=request.getParameter("files13");
		String files14=request.getParameter("files14");
		String files15=request.getParameter("files15");
		String files16=request.getParameter("files16");
		String files17=request.getParameter("files17");
		String files18=request.getParameter("files18");
		String files19=request.getParameter("files19");
		//附件的主键
		String acceId1 = IdGen.uuid();
		String acceId2 = IdGen.uuid();
		String acceId3 = IdGen.uuid();
		String acceId4 = IdGen.uuid();
		String acceId5 = IdGen.uuid();
		String acceId6 = IdGen.uuid();
		String acceId7 = IdGen.uuid();
		String acceId8 = IdGen.uuid();
		String acceId9 = IdGen.uuid();
		String acceId10 = IdGen.uuid();
		String acceId11 = IdGen.uuid();
		String acceId12 = IdGen.uuid();
		String acceId13 = IdGen.uuid();
		String acceId14 = IdGen.uuid();
		String acceId15 = IdGen.uuid();
		String acceId16 = IdGen.uuid();
		String acceId17 = IdGen.uuid();
		String acceId18 = IdGen.uuid();
		String acceId19 = IdGen.uuid();
		//从前台获取到的附件类型
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		String fjtype3 = request.getParameter("fjtype3");
		String fjtype4 = request.getParameter("fjtype4");
		String fjtype5 = request.getParameter("fjtype5");
		String fjtype6 = request.getParameter("fjtype6");
		String fjtype7 = request.getParameter("fjtype7");
		String fjtype8 = request.getParameter("fjtype8");
		String fjtype9 = request.getParameter("fjtype9");
		String fjtype10 = request.getParameter("fjtype10");
		String fjtype11 = request.getParameter("fjtype11");
		String fjtype12 = request.getParameter("fjtype12");
		String fjtype13 = request.getParameter("fjtype13");
		String fjtype14 = request.getParameter("fjtype14");
		String fjtype15 = request.getParameter("fjtype1");
		String fjtype16 = request.getParameter("fjtype2");
		String fjtype17 = request.getParameter("fjtype3");
		String fjtype18 = request.getParameter("fjtype4");
		String fjtype19 = request.getParameter("fjtype5");
		//itemId1关联评估鉴定表的主键
		String itemId = summaryInfo.getSummaryId();
		//保存或更新
		if(StringUtils.isNotBlank(files1)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"25");
			preOperativeConsentService.save1(acceId1,itemId,files1,fjtype1);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"25");
		}
		if(StringUtils.isNotBlank(files2)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"26");
			preOperativeConsentService.save1(acceId2,itemId,files2,fjtype2);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"26");
		}
		if(StringUtils.isNotBlank(files3)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"27");
			preOperativeConsentService.save1(acceId3,itemId,files3,fjtype3);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"27");
		}
		if(StringUtils.isNotBlank(files4)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"28");
			preOperativeConsentService.save1(acceId4,itemId,files4,fjtype4);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"28");
		}
		if(StringUtils.isNotBlank(files5)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"29");
			preOperativeConsentService.save1(acceId5,itemId,files5,fjtype5);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"29");
		}
		if(StringUtils.isNotBlank(files6)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"30");
			preOperativeConsentService.save1(acceId6,itemId,files6,fjtype6);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"30");
		}
		if(StringUtils.isNotBlank(files7)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"31");
			preOperativeConsentService.save1(acceId7,itemId,files7,fjtype7);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"31");
		}
		if(StringUtils.isNotBlank(files8)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"32");
			preOperativeConsentService.save1(acceId8,itemId,files8,fjtype8);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"32");
		}
		if(StringUtils.isNotBlank(files9)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"33");
			preOperativeConsentService.save1(acceId9,itemId,files9,fjtype9);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"33");
		}

		if(StringUtils.isNotBlank(files10)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"34");
			preOperativeConsentService.save1(acceId10,itemId,files10,fjtype10);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"34");
		}
		if(StringUtils.isNotBlank(files11)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"35");
			preOperativeConsentService.save1(acceId11,itemId,files11,fjtype11);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"35");
		}
		if(StringUtils.isNotBlank(files12)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"36");
			preOperativeConsentService.save1(acceId12,itemId,files12,fjtype12);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"36");
		}
		if(StringUtils.isNotBlank(files13)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"37");
			preOperativeConsentService.save1(acceId13,itemId,files13,fjtype13);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"37");
		}
		if(StringUtils.isNotBlank(files14)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"38");
			preOperativeConsentService.save1(acceId14,itemId,files1,fjtype14);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"38");
		}
		if(StringUtils.isNotBlank(files15)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"39");
			preOperativeConsentService.save1(acceId15,itemId,files15,fjtype15);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"39");
		}
		if(StringUtils.isNotBlank(files16)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"40");
			preOperativeConsentService.save1(acceId16,itemId,files16,fjtype16);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"40");
		}
		if(StringUtils.isNotBlank(files17)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"41");
			preOperativeConsentService.save1(acceId17,itemId,files17,fjtype17);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"41");
		}
		if(StringUtils.isNotBlank(files18)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"42");
			preOperativeConsentService.save1(acceId18,itemId,files18,fjtype18);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"42");
		}
		if(StringUtils.isNotBlank(files19)){
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"43");
			preOperativeConsentService.save1(acceId19,itemId,files19,fjtype19);
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"43");
		}

		}

	@Transactional(readOnly = false)
	public void delete(SummaryInfo summaryInfo) {
		super.delete(summaryInfo);
	}
	
}