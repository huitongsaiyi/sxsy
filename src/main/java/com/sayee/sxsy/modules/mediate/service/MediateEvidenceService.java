/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.mediate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.WordExportUtil;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.mediateapplyinfo.entity.MediateApplyInfo;
import com.sayee.sxsy.modules.record.dao.MediateRecordDao;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.record.service.MediateRecordService;
import com.sayee.sxsy.modules.recordinfo.dao.RecordInfoDao;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.recordinfo.service.RecordInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.mediate.entity.MediateEvidence;
import com.sayee.sxsy.modules.mediate.dao.MediateEvidenceDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 质证调解Service
 * @author lyt
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class MediateEvidenceService extends CrudService<MediateEvidenceDao, MediateEvidence> {

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private MediateRecordDao mediateRecordDao;		//调解志DAO层
	@Autowired
	private RecordInfoDao recordInfoDao;    //笔录Dao
	@Autowired
	private ActTaskService actTaskService;


	public MediateEvidence get(String id) {
		MediateEvidence mediateEvidence=super.get(id);
		//调解志 明细查询
		MediateRecord mediateRecord=new MediateRecord();
		mediateRecord.setRelationId(mediateEvidence.getMediateEvidenceId());
		mediateEvidence.setMediateEvidenceList(mediateRecordDao.findList(mediateRecord));
		return mediateEvidence;
	}
	
	public List<MediateEvidence> findList(MediateEvidence mediateEvidence) {
		//获取当前登陆用户
		mediateEvidence.setUser(UserUtils.getUser());
		return super.findList(mediateEvidence);
	}
	
	public Page<MediateEvidence> findPage(Page<MediateEvidence> page, MediateEvidence mediateEvidence) {
		//获取当前登陆用户
		mediateEvidence.setUser(UserUtils.getUser());
		return super.findPage(page, mediateEvidence);
	}
	
	@Transactional(readOnly = false)
	public void save(MediateEvidence mediateEvidence,HttpServletRequest request) {
		if(StringUtils.isBlank(mediateEvidence.getCreateBy().getId())){
            mediateEvidence.preInsert();
            mediateEvidence.setMediateEvidenceId(mediateEvidence.getId());
            //将主键ID设为UUID
            dao.insert(mediateEvidence);
            //保存笔录(患方)
            RecordInfo huanf = mediateEvidence.getRecordInfo();
            huanf.preInsert();
            huanf.setRecordId(IdGen.uuid());
            huanf.setRelationId(mediateEvidence.getMediateEvidenceId());
            huanf.setType("1");
            huanf.setModuleType("1");
            recordInfoDao.insert(huanf);
            //保存笔录（医方） 由于医方笔录共用患方的笔录 所以先把共用的信息赋值给医方
            RecordInfo yif = mediateEvidence.getRecordInfo().getYrecordInfo();
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
            yif.setRelationId(mediateEvidence.getMediateEvidenceId());
            yif.setType("2");
            yif.setModuleType("1");
            recordInfoDao.insert(yif);
		}else{
		    //如果不为空进行更新
			//修改报案登记表
			mediateEvidence.preUpdate();
			dao.update(mediateEvidence);
			//更新笔录(患方)
            RecordInfo huanf = mediateEvidence.getRecordInfo();
            huanf.preUpdate();
            recordInfoDao.update(huanf);
            //更新笔录(医方)
            RecordInfo yif = mediateEvidence.getRecordInfo().getYrecordInfo();
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
        this.tjz(mediateEvidence);
		//保存附件
		this.savefj(request,mediateEvidence);
		if ("yes".equals(mediateEvidence.getComplaintMain().getAct().getFlag())){

			Map<String,Object> var=new HashMap<String, Object>();

			var.put("pass",StringUtils.isBlank(mediateEvidence.getNextLink()) ? "0" : mediateEvidence.getNextLink());
			User assigness= UserUtils.get(mediateEvidence.getNextLinkMan());
			var.put("apply_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(mediateEvidence.getComplaintMain().getAct().getTaskId(), mediateEvidence.getComplaintMain().getAct().getProcInsId(), mediateEvidence.getComplaintMain().getAct().getComment(), mediateEvidence.getComplaintMain().getCaseNumber(), var);

		}

	}
	
	@Transactional(readOnly = false)
	public void delete(MediateEvidence mediateEvidence) {
		super.delete(mediateEvidence);
	}

    @Transactional(readOnly = false)
    public void tjz(MediateEvidence mediateEvidence){
	    List<MediateRecord> emp = new ArrayList<MediateRecord>();
	    if(mediateEvidence.getMediateEvidenceList()!=null){
	        emp=mediateEvidence.getMediateEvidenceList();
        }
        for (MediateRecord mediateRecord:emp) {
            if(MediateRecord.DEL_FLAG_NORMAL.equals(mediateRecord.getDelFlag())||"".equals(mediateRecord.getDelFlag())){
                if(StringUtils.isBlank(mediateRecord.getMediateRecord())){
                    mediateRecord.setRelationId(mediateEvidence.getMediateEvidenceId());
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
	public void savefj(HttpServletRequest request,MediateEvidence mediateEvidence){
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files4");
		String files5 = request.getParameter("files5");
		String acceId = null;
		String itemId = mediateEvidence.getMediateEvidenceId();
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

	public void exportWord(MediateEvidence mediateEvidence, String export, HttpServletRequest request, HttpServletResponse response) {
		WordExportUtil wordExportUtil=new WordExportUtil();
		mediateEvidence=this.get(mediateEvidence.getMediateEvidenceId());
		String path=request.getServletContext().getRealPath("/");
		String modelPath=path;
		String newFileName="无标题文件.docx";
		Map<String, Object> params = new HashMap<String, Object>();
		if ("meeting".equals(export)){
			params.put("time}", mediateEvidence.getMeetingTime());
			params.put("address", mediateEvidence.getMeetingAddress());
			params.put("case", mediateEvidence.getCaseInfoName());
			params.put("ytw", mediateEvidence.getYtwUser()==null ? "" : mediateEvidence.getYtwUser().getName());
			params.put("patient", mediateEvidence.getPatient());
			params.put("doctor", mediateEvidence.getDoctorUser()==null ? "" : mediateEvidence.getDoctorUser().getName());
			path += "/doc/mediateMeeting.docx";  //模板文件位置
			modelPath += "/doc/mediateMeetingM.docx";
			newFileName="调解程序表.docx";
		}
		try{
			List<String[]> testList = new ArrayList<String[]>();
			String fileName= new String(newFileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
			wordExportUtil.getWord(path,modelPath,params,testList,fileName,response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}