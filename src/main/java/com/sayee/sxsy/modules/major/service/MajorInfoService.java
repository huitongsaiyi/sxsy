/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.major.service;

import java.util.List;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.major.entity.MajorInfo;
import com.sayee.sxsy.modules.major.dao.MajorInfoDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 重大事件Service
 * @author zhangfan
 * @version 2019-12-09
 */
@Service
@Transactional(readOnly = true)
public class MajorInfoService extends CrudService<MajorInfoDao, MajorInfo> {

	@Autowired
	private MajorInfoDao majorInfoDao;

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;

	public MajorInfo get(String id) {
		return super.get(id);
	}
	
	public List<MajorInfo> findList(MajorInfo majorInfo) {
		return super.findList(majorInfo);
	}
	
	public Page<MajorInfo> findPage(Page<MajorInfo> page, MajorInfo majorInfo) {
		return super.findPage(page, majorInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(MajorInfo majorInfo, HttpServletRequest request) {
		majorInfo.setEventNumber(StringUtils.isNotBlank(majorInfo.getEventNumber()) ? majorInfo.getEventNumber() : "0");
		majorInfo.setNumberPolice(StringUtils.isNotBlank(majorInfo.getNumberPolice()) ? majorInfo.getNumberPolice() : "0");
		majorInfo.setMedicalTroubleNum(StringUtils.isNotBlank(majorInfo.getMedicalTroubleNum()) ? majorInfo.getMedicalTroubleNum() : "0");
		majorInfo.setHurtNumber(StringUtils.isNotBlank(majorInfo.getHurtNumber()) ? majorInfo.getHurtNumber() : "0");
		majorInfo.setLimitNumber(StringUtils.isNotBlank(majorInfo.getLimitNumber()) ? majorInfo.getLimitNumber() : "0");
		if(StringUtils.isBlank(majorInfo.getMajorId())){
			majorInfo.preInsert();
			majorInfo.setMajorId(majorInfo.getId());
			dao.insert(majorInfo);
			//在增加重大表 后，修改主表 重大字段
			majorInfoDao.updateMain(majorInfo.getMajorId(),majorInfo.getComplaintMainId());
		}else {
			majorInfo.preUpdate();
			dao.update(majorInfo);
		}
		//this.savefj(request,majorInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MajorInfo majorInfo) {
		super.delete(majorInfo);
	}
//
//
//	//保存附件
//	public void savefj(HttpServletRequest request, MajorInfo majorInfo){
//		String scenePictures = request.getParameter("scenePictures");
//		String video = request.getParameter("video");
//		String audio = request.getParameter("audio");
//		String acceId = null;
//		String itemId = majorInfo.getMajorId();
//		String fjtype = request.getParameter("fjtype");
//		if(StringUtils.isNotBlank(scenePictures)){
//			String acceId1=request.getParameter("acceId1");
//			if(StringUtils.isNotBlank(acceId1)){
//				preOperativeConsentService.updatefj(files,itemId1,fjtype1);
//			}else{
//				acceId= IdGen.uuid();
//				preOperativeConsentService.save1(acceId,itemId1,files,fjtype1);
//			}
//		}else{
//			preOperativeConsentService.delefj(itemId1,fjtype1);
//		}
//	}
}