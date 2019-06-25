/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.reachmediate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.record.dao.MediateRecordDao;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.recordinfo.dao.RecordInfoDao;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.recordinfo.service.RecordInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.reachmediate.entity.ReachMediate;
import com.sayee.sxsy.modules.reachmediate.dao.ReachMediateDao;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * 达成调解Service
 * @author lyt
 * @version 2019-06-14
 */
@Service
@Transactional(readOnly = true)
public class ReachMediateService extends CrudService<ReachMediateDao, ReachMediate> {

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;

	@Autowired
	private MediateRecordDao mediateRecordDao;		//调解志DAO层

	@Autowired
	private RecordInfoService recordInfoService;		//笔录业务层

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private PreOperativeConsentService getPreOperativeConsentService;

	public ReachMediate get(String id) {
		ReachMediate reachMediate = super.get(id);
		//调解志 明细查询
		MediateRecord mediateRecord = new MediateRecord();
		mediateRecord.setRelationId(reachMediate.getReachMediateId());
		reachMediate.setMediateEvidenceList(mediateRecordDao.findReachMediateList(mediateRecord));
		return reachMediate;
	}
	
	public List<ReachMediate> findList(ReachMediate reachMediate) {
		reachMediate.setUser(UserUtils.getUser());
		return super.findList(reachMediate);
	}
	
	public Page<ReachMediate> findPage(Page<ReachMediate> page, ReachMediate reachMediate) {
		reachMediate.setUser(UserUtils.getUser());
		return super.findPage(page, reachMediate);
	}
	
	@Transactional(readOnly = false)
	public void save(ReachMediate reachMediate,HttpServletRequest request) {
         System.out.println(reachMediate.getReachMediateId());
		if(StringUtils.isBlank(reachMediate.getCreateBy().getId())){
			reachMediate.preInsert();
			reachMediate.setReachMediateId(reachMediate.getId());		//将主键设为UUID
			dao.insert(reachMediate);

		}else{	//如果不为空则进行更新
			//修改达成调解表
			reachMediate.preUpdate();
			dao.update(reachMediate);
		}
		this.saveRecord(reachMediate);
		for(MediateRecord mediateRecord : reachMediate.getMediateEvidenceList()){
			if(mediateRecord.getId() == null){
				continue;
			}
			if(MediateRecord.DEL_FLAG_NORMAL.equals(mediateRecord.getDelFlag())){
				if(StringUtils.isBlank(mediateRecord.getMediateRecord())){
					mediateRecord.setRelationId(reachMediate.getReachMediateId());
					mediateRecord.preInsert();
					mediateRecord.setMediateRecord(mediateRecord.getId());
					mediateRecordDao.insert(mediateRecord);
				}else{
					mediateRecord.preUpdate();
					mediateRecordDao.update(mediateRecord);
				}
			}else{
				mediateRecordDao.delete(mediateRecord);
			}
		}
		this.savefj(request,reachMediate);		//调解志保存
		if("yes".equals(reachMediate.getComplaintMain().getAct().getFlag())){
			Map<String,Object> var = new HashMap<String,Object>();
			var.put("pass","0");
			User assigness = UserUtils.get(reachMediate.getNextLinkMan());
			var.put("sign_user",assigness.getLoginName());
			//执行流程
			actTaskService.complete(reachMediate.getComplaintMain().getAct().getTaskId(),reachMediate.getComplaintMain().getAct().getProcInsId(),reachMediate.getComplaintMain().getAct().getComment(),reachMediate.getComplaintMain().getCaseNumber(),var);

		}
//		super.save(reachMediate);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReachMediate reachMediate) {
		super.delete(reachMediate);
	}

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request,ReachMediate reachMediate){
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files4");
		String files5 = request.getParameter("files5");
		String acceId = null;
		String itemId = reachMediate.getReachMediateId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		String fjtype3 = request.getParameter("fjtype3");
		String fjtype4 = request.getParameter("fjtype4");
		String fjtype5 = request.getParameter("fjtype5");
		if(StringUtils.isNotBlank(files1)){
			String acceId1=request.getParameter("acceId1");
			if(StringUtils.isNotBlank(acceId1)){
				preOperativeConsentService.updatefj(files1,itemId,fjtype1);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files1,fjtype1);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype1);
		}

		if(StringUtils.isNotBlank(files2)){
			String acceId2=request.getParameter("acceId2");
			if(StringUtils.isNotBlank(acceId2)){
				preOperativeConsentService.updatefj(files2,itemId,fjtype2);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files2,fjtype2);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype2);
		}

		if(StringUtils.isNotBlank(files3)){
			String acceId3=request.getParameter("acceId3");
			if(StringUtils.isNotBlank(acceId3)){
				preOperativeConsentService.updatefj(files3,itemId,fjtype3);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files3,fjtype3);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype3);
		}

		if(StringUtils.isNotBlank(files4)){
			String acceId4=request.getParameter("acceId4");
			if(StringUtils.isNotBlank(acceId4)){
				preOperativeConsentService.updatefj(files4,itemId,fjtype4);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files4,fjtype4);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype4);
		}

		if(StringUtils.isNotBlank(files5)){
			String acceId5=request.getParameter("acceId5");
			if(StringUtils.isNotBlank(acceId5)){
				preOperativeConsentService.updatefj(files5,itemId,fjtype5);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files5,fjtype5);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype5);
		}
	}

	@Transactional(readOnly = false)
	public void saveRecord(ReachMediate reachMediate){
		reachMediate.getRecordInfo().getYrecordInfo().setRelationId(reachMediate.getReachMediateId());
		reachMediate.getRecordInfo().getYrecordInfo().setModuleType("1");
		reachMediate.getRecordInfo().getYrecordInfo().setType("2");
		recordInfoService.save(reachMediate.getRecordInfo().getYrecordInfo());		//医方笔录

		reachMediate.getRecordInfo().setRelationId(reachMediate.getReachMediateId());
		reachMediate.getRecordInfo().setModuleType("1");
		reachMediate.getRecordInfo().setType("1");
		recordInfoService.save(reachMediate.getRecordInfo());		//患方笔录
	}
	@Transactional(readOnly = false)
	public void findfj(ReachMediate reachMediate,Model model){
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(reachMediate.getReachMediateId());
		for(Map<String,Object> map :filePath){
			if("7".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}else if("8".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}else if("9".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}else if("10".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files3",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}else if("11".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files4",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}
		}

	}

}