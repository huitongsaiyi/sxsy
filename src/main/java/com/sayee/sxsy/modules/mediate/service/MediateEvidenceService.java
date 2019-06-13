/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.mediate.service;

import java.util.List;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.record.dao.MediateRecordDao;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.record.service.MediateRecordService;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.recordinfo.service.RecordInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.mediate.entity.MediateEvidence;
import com.sayee.sxsy.modules.mediate.dao.MediateEvidenceDao;

import javax.servlet.http.HttpServletRequest;

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
	private RecordInfoService recordInfoService;		//笔录业务层



	public MediateEvidence get(String id) {
		return super.get(id);
	}
	
	public List<MediateEvidence> findList(MediateEvidence mediateEvidence) {
		return super.findList(mediateEvidence);
	}
	
	public Page<MediateEvidence> findPage(Page<MediateEvidence> page, MediateEvidence mediateEvidence) {
		return super.findPage(page, mediateEvidence);
	}
	
	@Transactional(readOnly = false)
	public void save(MediateEvidence mediateEvidence) {
		if(StringUtils.isBlank(mediateEvidence.getMediateEvidenceId())){
			//判断主键ID是否为空
			mediateEvidence.getRecordInfo().setRelationId(mediateEvidence.getMediateEvidenceId());		//将关联表的主键ID添加到子表属性中
			mediateEvidence.getRecordInfo().getYrecordInfo().setRelationId(mediateEvidence.getMediateEvidenceId());
			recordInfoService.save(mediateEvidence.getRecordInfo());		//患方笔录
			recordInfoService.save(mediateEvidence.getRecordInfo().getYrecordInfo());		//医方笔录
			mediateEvidence.preInsert();
			mediateEvidence.setMediateEvidenceId(mediateEvidence.getId());
			//将主键ID设为UUID
			dao.insert(mediateEvidence);
		}else{//如果不为空进行更新

			//修改报案登记表
			mediateEvidence.preUpdate();
			dao.update(mediateEvidence);
		}
		for (MediateRecord mediateRecord : mediateEvidence.getmediateEvidenceList()){
		    if(mediateRecord.getId() == null){
		        continue;
            }
            if(MediateRecord.DEL_FLAG_NORMAL.equals(mediateRecord.getDelFlag())){
		        if(StringUtils.isBlank(mediateRecord.getId())){
		            mediateRecord.setRelationId(mediateEvidence.getMediateEvidenceId());
		            mediateRecord.preInsert();
		            mediateRecord.setMediateRecord(mediateRecord.getId());
                    mediateRecordDao.insert(mediateRecord);
                }else {
		            mediateRecord.preUpdate();
		            mediateRecordDao.update(mediateRecord);
                }
            }
        }
//		super.save(mediateEvidence);
	}
	
	@Transactional(readOnly = false)
	public void delete(MediateEvidence mediateEvidence) {
		super.delete(mediateEvidence);
	}

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request,MediateEvidence mediateEvidence){
		String files = request.getParameter("files");
		String files1 = request.getParameter("files");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files");
		String acceId1 = IdGen.uuid();
		String acceId2 = IdGen.uuid();
		String acceId3 = IdGen.uuid();
		String acceId4 = IdGen.uuid();
		String acceId5 = IdGen.uuid();
		String itemId1 = mediateEvidence.getMediateEvidenceId();
		String itemId2 = mediateEvidence.getMediateEvidenceId();
		String itemId3 = mediateEvidence.getMediateEvidenceId();
		String itemId4 = mediateEvidence.getMediateEvidenceId();
		String itemId5 = mediateEvidence.getMediateEvidenceId();
		String fjtype1 = request.getParameter("fjtype");
		String fjtype2 = request.getParameter("fjtype1");
		String fjtype3 = request.getParameter("fjtype2");
		String fjtype4 = request.getParameter("fjtype3");
		String fjtype5 = request.getParameter("fjtype4");
		preOperativeConsentService.save1(acceId1,itemId1,files,fjtype1);
		preOperativeConsentService.save1(acceId2,itemId2,files1,fjtype2);
		preOperativeConsentService.save1(acceId3,itemId3,files2,fjtype3);
		preOperativeConsentService.save1(acceId4,itemId4,files3,fjtype4);
		preOperativeConsentService.save1(acceId5,itemId5,files4,fjtype5);
	}
}