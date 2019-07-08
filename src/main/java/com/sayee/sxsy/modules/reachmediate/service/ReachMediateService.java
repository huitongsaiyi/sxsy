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
import com.sayee.sxsy.common.utils.WordExportUtil;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.program.dao.MediateProgramDao;
import com.sayee.sxsy.modules.program.entity.MediateProgram;
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
import javax.servlet.http.HttpServletResponse;

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
	private RecordInfoDao recordInfoDao;		//笔录业务层

	@Autowired
	private MediateProgramDao mediateProgramDao;		//程序表DAO层

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
		//调节会议查询
		MediateProgram mediateProgram = new MediateProgram();
		mediateProgram.setRelationId(reachMediate.getReachMediateId());
		reachMediate.setMediateProgramList(mediateProgramDao.findList(mediateProgram));
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
			reachMediate.setReachMediateId(reachMediate.getId());
			//将主键设为UUID
			reachMediate.setReaDoctor(reachMediate.getDoctorOffice().getId());
			dao.insert(reachMediate);
			//保存笔录(患方)
			RecordInfo huanf = reachMediate.getRecordInfo();
			huanf.preInsert();
			huanf.setRecordId(IdGen.uuid());
			huanf.setDoctor(reachMediate.getReaDoctor());
			huanf.setHost(reachMediate.getReaUserId());
			huanf.setNoteTaker(reachMediate.getReaClerk());
			huanf.setStartTime(reachMediate.getReaMeetingTime());
			huanf.setEndTime(reachMediate.getReaMeetingTime());
			huanf.setRecordAddress(reachMediate.getReaAddress());
			huanf.setPatient(reachMediate.getReaPatient());
			huanf.setRelationId(reachMediate.getReachMediateId());
			huanf.setType("1");
			huanf.setModuleType("1");
			recordInfoDao.insert(huanf);
			//保存笔录（医方） 由于医方笔录共用患方的笔录 所以先把共用的信息赋值给医方
			RecordInfo yif = reachMediate.getRecordInfo().getYrecordInfo();
			yif.setStartTime(huanf.getStartTime());
			yif.setEndTime(huanf.getEndTime());
			yif.setRecordAddress(huanf.getRecordAddress());
			yif.setCause(huanf.getCause());
			yif.setHost(huanf.getHost());
			yif.setNoteTaker(huanf.getNoteTaker());
			yif.setPatient(huanf.getPatient());
			yif.setDoctor(huanf.getDoctor());
			yif.setOtherParticipants(huanf.getOtherParticipants());
			yif.preInsert();
			yif.setRecordId(IdGen.uuid());
			yif.setRelationId(reachMediate.getReachMediateId());
			yif.setType("2");
			yif.setModuleType("1");
			recordInfoDao.insert(yif);
		}else{	//如果不为空则进行更新
			//修改达成调解表
			reachMediate.preUpdate();
			reachMediate.setReaDoctor(reachMediate.getDoctorOffice().getId());
			dao.update(reachMediate);
			//更新笔录(患方)
			RecordInfo huanf = reachMediate.getRecordInfo();
			huanf.setDoctor(reachMediate.getReaDoctor());
			huanf.setHost(reachMediate.getReaUserId());
			huanf.setNoteTaker(reachMediate.getReaClerk());
			huanf.setStartTime(reachMediate.getReaMeetingTime());
			huanf.setEndTime(reachMediate.getReaMeetingTime());
			huanf.setRecordAddress(reachMediate.getReaAddress());
			huanf.setPatient(reachMediate.getReaPatient());
			recordInfoDao.update(huanf);
			//更新笔录(医方)
			RecordInfo yif = reachMediate.getRecordInfo().getYrecordInfo();
			yif.setStartTime(huanf.getStartTime());
			yif.setEndTime(huanf.getEndTime());
			yif.setRecordAddress(huanf.getRecordAddress());
			yif.setCause(huanf.getCause());
			yif.setHost(huanf.getHost());
			yif.setNoteTaker(huanf.getNoteTaker());
			yif.setPatient(huanf.getPatient());
			yif.setDoctor(huanf.getDoctor());
			yif.setOtherParticipants(huanf.getOtherParticipants());
			yif.preUpdate();
			recordInfoDao.update(yif);
		}
		//保存调解志
		this.tjz(reachMediate);
		this.savefj(request,reachMediate);		//调解志保存
		//保存调解程序 与 调解志
		if(StringUtils.isNotBlank(reachMediate.getReaMeetingTime())){
			this.saveMeeting(reachMediate);
		}
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
	public void tjz(ReachMediate reachMediate){
		List<MediateRecord> emp = new ArrayList<MediateRecord>();
		if(reachMediate.getMediateEvidenceList()!=null){
			emp=reachMediate.getMediateEvidenceList();
		}
		for (MediateRecord mediateRecord:emp) {
			if(MediateRecord.DEL_FLAG_NORMAL.equals(mediateRecord.getDelFlag())||"".equals(mediateRecord.getDelFlag())){
				if(StringUtils.isBlank(mediateRecord.getMediateRecord())){
					mediateRecord.setRelationId(reachMediate.getReachMediateId());
					mediateRecord.setMediateRecord(IdGen.uuid());
					mediateRecord.preInsert();
					mediateRecord.setDelFlag("0");
					mediateRecordDao.insert(mediateRecord);
				}else{
					mediateRecord.preUpdate();
					mediateRecordDao.update(mediateRecord);
				}
			}else{
				mediateRecordDao.delete(mediateRecord);
			}
		}

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

	public void exportWord(ReachMediate reachMediate, String export, HttpServletRequest request, HttpServletResponse response) {
		WordExportUtil wordExportUtil=new WordExportUtil();
		reachMediate=this.get(reachMediate.getReachMediateId());
		String path=request.getSession().getServletContext().getRealPath("/");
		String modelPath=path;
		String newFileName="无标题文件.docx";
		Map<String, Object> params = new HashMap<String, Object>();
		if ("meeting".equals(export)){
			params.put("time}", reachMediate.getReaMeetingTime());
			params.put("address", reachMediate.getReaAddress());
			params.put("case", reachMediate.getReaCaseInfo());
			params.put("ytw", reachMediate.getYtwUser()==null ? "" : reachMediate.getYtwUser().getName());
			params.put("patient", reachMediate.getReaPatient());
			params.put("doctor", reachMediate.getDoctorUser()==null ? "" : reachMediate.getDoctorUser().getName());
			path += "/doc/mediateMeeting.docx";  //模板文件位置
			modelPath += "/doc/mediateMeetingM.docx";
			newFileName="调解程序表.docx";
		}
		try{
			List<String[]> testList = new ArrayList<String[]>();
			String fileName= new String(newFileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
			wordExportUtil.getWord(path,modelPath,"",params,testList,fileName,response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void clearDomain(ReachMediate reachMediate){
		reachMediate.setReaMeetingTime("");
		reachMediate.setReaAddress("");
		reachMediate.setReaDoctor("");
		reachMediate.setDoctorUser(new User());
		reachMediate.setReaUserId("");
		reachMediate.setYtwUser(new User());
		reachMediate.setReaPatient("");
	}

	public void saveMeeting(ReachMediate reachMediate){
		int cishu=mediateProgramDao.getMax(reachMediate.getReachMediateId())+1;
		MediateProgram mediateProgram=new MediateProgram();
		mediateProgram.setAddress(reachMediate.getReaAddress());
		mediateProgram.setMeetingTime(reachMediate.getReaMeetingTime());
		mediateProgram.setCaseInfo(reachMediate.getReaCaseInfo());
		mediateProgram.setDoctor(reachMediate.getReaDoctor());
		mediateProgram.setUser(new User());
		mediateProgram.getUser().setId(reachMediate.getReaUserId());
		mediateProgram.setMediator(reachMediate.getReaUserId());
		mediateProgram.setClerk(reachMediate.getReaClerk());
		mediateProgram.setPatient(reachMediate.getReaPatient());
		mediateProgram.setOther(reachMediate.getReaOther());
		mediateProgram.preInsert();
		mediateProgram.setMediateProgramId(mediateProgram.getId());
		mediateProgram.setMeetingFrequency(String.valueOf(cishu));
		mediateProgram.setRelationId(reachMediate.getReachMediateId());
		mediateProgramDao.insert(mediateProgram);
		//保存一条 调解会的 调解志
		MediateRecord mediateRecord=new MediateRecord();
		mediateRecord.setTime(reachMediate.getReaMeetingTime());
		mediateRecord.setContent("调解会");
		mediateRecord.setResult("");
		mediateRecord.setRelationId(reachMediate.getReachMediateId());
		mediateRecord.setMediateRecord(IdGen.uuid());
		mediateRecord.preInsert();
		mediateRecord.setDelFlag("0");
		mediateRecordDao.insert(mediateRecord);
	}

}