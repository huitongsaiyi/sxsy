/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.auditacceptance.service;

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
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.mediateapplyinfo.dao.MediateApplyInfoDao;
import com.sayee.sxsy.modules.mediateapplyinfo.entity.MediateApplyInfo;
import com.sayee.sxsy.modules.mediateapplyinfo.service.MediateApplyInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.auditacceptance.dao.AuditAcceptanceDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 审核受理Service
 * @author zhangfan
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class AuditAcceptanceService extends CrudService<AuditAcceptanceDao, AuditAcceptance> {
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private MediateApplyInfoDao mediateApplyInfoDao;

	public AuditAcceptance get(String id) {
		AuditAcceptance auditAcceptance = super.get(id);

		return super.get(id);
	}
	
	public List<AuditAcceptance> findList(AuditAcceptance auditAcceptance) {
		//获取当前登陆用户
		auditAcceptance.setUser(UserUtils.getUser());
		return super.findList(auditAcceptance);
	}
	
	public Page<AuditAcceptance> findPage(Page<AuditAcceptance> page, AuditAcceptance auditAcceptance) {
		//获取当前登陆用户
		auditAcceptance.setUser(UserUtils.getUser());
		return super.findPage(page, auditAcceptance);
	}
	
	@Transactional(readOnly = false)
	public void save(HttpServletRequest request, AuditAcceptance auditAcceptance) {
		MediateApplyInfo huanf=auditAcceptance.getMediateApplyInfo();
		MediateApplyInfo yif=auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo();
		if(StringUtils.isBlank(auditAcceptance.getCreateBy().getId())){
			//判断主键ID是否为空
			auditAcceptance.preInsert();
			auditAcceptance.setAuditAcceptanceId(auditAcceptance.getId());
			//将主键ID设为UUID
			dao.insert(auditAcceptance);
			//保存患方申请
			huanf.preInsert();
			huanf.setMediateApplyId(IdGen.uuid());
			huanf.setAuditAcceptanceId(auditAcceptance.getAuditAcceptanceId());
			huanf.setApplyType("1");
			mediateApplyInfoDao.insert(huanf);
			//保存医方申请
			yif.preInsert();
			yif.setMediateApplyId(IdGen.uuid());
			yif.setAuditAcceptanceId(auditAcceptance.getAuditAcceptanceId());
			yif.setApplyType("2");
			mediateApplyInfoDao.insert(yif);
		}else{//如果不为空进行更新
			//修改表
			auditAcceptance.preUpdate();
			dao.update(auditAcceptance);
//			if(StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getAuditAcceptanceId()) || "".equals(auditAcceptance.getMediateApplyInfo().getAuditAcceptanceId())){
//				auditAcceptance.getMediateApplyInfo().setAuditAcceptanceId(auditAcceptance.getAuditAcceptanceId());
//				auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().setAuditAcceptanceId(auditAcceptance.getAuditAcceptanceId());
//			}
//			if(StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getApplyType()) || "".equals(auditAcceptance.getMediateApplyInfo().getApplyType())){
//				auditAcceptance.getMediateApplyInfo().setApplyType("1");
//				auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().setApplyType("2");
//			}
			//更新患方申请
			huanf.preUpdate();
			mediateApplyInfoDao.update(huanf);
			//更新医方申请
			yif.preUpdate();
			mediateApplyInfoDao.update(yif);

		}

		//修改主表信息 因为处理的是  主表事由信息的  对主表信息进行修改即可
//		ComplaintMain complaintMain=reportRegistration.getComplaintMain();
//		complaintMain.preUpdate();
//		complaintMain.setComplaintMainId(reportRegistration.getComplaintMainId());
//		complaintMainDao.update(complaintMain);
//		super.save(auditAcceptance);
		if ("yes".equals(auditAcceptance.getComplaintMain().getAct().getFlag())){
			List<Act> list = actTaskService.todoList(auditAcceptance.getComplaintMain().getAct());

			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(auditAcceptance.getNextLinkMan());
			var.put("forensics_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(auditAcceptance.getComplaintMain().getAct().getTaskId(), auditAcceptance.getComplaintMain().getAct().getProcInsId(), auditAcceptance.getComplaintMain().getAct().getComment(), auditAcceptance.getComplaintMain().getCaseNumber(), var);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(AuditAcceptance auditAcceptance) {
		super.delete(auditAcceptance);
	}

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request, AuditAcceptance auditAcceptance){
		String files = request.getParameter("files");
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files4");
		String files5 = request.getParameter("files5");
		String files6 = request.getParameter("files6");
		String files7 = request.getParameter("files7");
		String files8 = request.getParameter("files8");
		String files9 = request.getParameter("files9");
		String files10 = request.getParameter("files10");
		String files11 = request.getParameter("files11");
		String files12 = request.getParameter("files12");
		String files13 = request.getParameter("files13");
		String files14 = request.getParameter("files14");
		String files15 = request.getParameter("files15");
		String files16 = request.getParameter("files16");
		String files17 = request.getParameter("files17");
		String files18 = request.getParameter("files18");
		String files19 = request.getParameter("files19");
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
		String acceId20 = IdGen.uuid();
		String itemId1 = auditAcceptance.getAuditAcceptanceId();
		String itemId2 = auditAcceptance.getAuditAcceptanceId();
		String itemId3 = auditAcceptance.getAuditAcceptanceId();
		String itemId4 = auditAcceptance.getAuditAcceptanceId();
		String itemId5 = auditAcceptance.getAuditAcceptanceId();
		String itemId6 = auditAcceptance.getAuditAcceptanceId();
		String itemId7 = auditAcceptance.getAuditAcceptanceId();
		String itemId8 = auditAcceptance.getAuditAcceptanceId();
		String itemId9 = auditAcceptance.getAuditAcceptanceId();
		String itemId10 = auditAcceptance.getAuditAcceptanceId();
		String itemId11 = auditAcceptance.getAuditAcceptanceId();
		String itemId12 = auditAcceptance.getAuditAcceptanceId();
		String itemId13 = auditAcceptance.getAuditAcceptanceId();
		String itemId14 = auditAcceptance.getAuditAcceptanceId();
		String itemId15 = auditAcceptance.getAuditAcceptanceId();
		String itemId16 = auditAcceptance.getAuditAcceptanceId();
		String itemId17 = auditAcceptance.getAuditAcceptanceId();
		String itemId18= auditAcceptance.getAuditAcceptanceId();
		String itemId19 = auditAcceptance.getAuditAcceptanceId();
		String itemId20 = auditAcceptance.getAuditAcceptanceId();
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
		String fjtype15 = request.getParameter("fjtype15");
		String fjtype16 = request.getParameter("fjtype16");
		String fjtype17 = request.getParameter("fjtype17");
		String fjtype18 = request.getParameter("fjtype18");
		String fjtype19 = request.getParameter("fjtype19");
		String fjtype20 = request.getParameter("fjtype20");
		preOperativeConsentService.save1(acceId1,itemId1,files,fjtype1);
		preOperativeConsentService.save1(acceId2,itemId2,files1,fjtype2);
		preOperativeConsentService.save1(acceId3,itemId3,files2,fjtype3);
		preOperativeConsentService.save1(acceId4,itemId4,files3,fjtype4);
		preOperativeConsentService.save1(acceId5,itemId5,files4,fjtype5);
		preOperativeConsentService.save1(acceId6,itemId6,files5,fjtype6);
		preOperativeConsentService.save1(acceId7,itemId7,files6,fjtype7);
		preOperativeConsentService.save1(acceId8,itemId8,files7,fjtype8);
		preOperativeConsentService.save1(acceId9,itemId9,files8,fjtype9);
		preOperativeConsentService.save1(acceId10,itemId10,files9,fjtype10);
		preOperativeConsentService.save1(acceId11,itemId11,files10,fjtype11);
		preOperativeConsentService.save1(acceId12,itemId12,files11,fjtype12);
		preOperativeConsentService.save1(acceId13,itemId13,files12,fjtype13);
		preOperativeConsentService.save1(acceId14,itemId14,files13,fjtype14);
		preOperativeConsentService.save1(acceId15,itemId15,files14,fjtype15);
		preOperativeConsentService.save1(acceId16,itemId16,files15,fjtype16);
		preOperativeConsentService.save1(acceId17,itemId17,files16,fjtype17);
		preOperativeConsentService.save1(acceId18,itemId18,files17,fjtype18);
		preOperativeConsentService.save1(acceId19,itemId19,files18,fjtype19);
		preOperativeConsentService.save1(acceId20,itemId20,files19,fjtype20);
	}

    public void exportWord(AuditAcceptance auditAcceptance,String export, HttpServletRequest request, HttpServletResponse response) {
		WordExportUtil wordExportUtil=new WordExportUtil();
		auditAcceptance=this.get(auditAcceptance.getAuditAcceptanceId());
		if (auditAcceptance.getMediateApplyInfo()==null){
			auditAcceptance.setMediateApplyInfo(new MediateApplyInfo());
		}
		String path=request.getServletContext().getRealPath("/");
		String modelPath=path;
		String newFileName="无标题文件.docx";
		Map<String, Object> params = new HashMap<String, Object>();
		if ("patientAcc".equals(export)){
			params.put("patient", auditAcceptance.getMediateApplyInfo().getPatientName());
			params.put("hospital", auditAcceptance.getComplaintMain().getHospital().getName());
			path += "/doc/acceptanceP.docx";  //模板文件位置
            modelPath += "/doc/acceptancePM.docx";
			newFileName="患方通知书.docx";
		}else if("hospitalAcc".equals(export)){
			params.put("patient", auditAcceptance.getMediateApplyInfo().getPatientName());
			params.put("hospital", auditAcceptance.getComplaintMain().getHospital().getName());
			path += "/doc/acceptanceD.docx";  //模板文件位置
            modelPath += "/doc/acceptanceDM.docx";
			newFileName="医方通知书.docx";
		}else if("patientDis".equals(export)){
			params.put("sqr", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getApplyer()) ? "" : auditAcceptance.getMediateApplyInfo().getApplyer());
			params.put("yhzgx", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getPatientRelation()) ? "" : "1".equals(auditAcceptance.getMediateApplyInfo().getPatientRelation()) ? "本人": "2".equals(auditAcceptance.getMediateApplyInfo().getPatientRelation()) ? "夫妻" : "3".equals(auditAcceptance.getMediateApplyInfo().getPatientRelation()) ? "子女" : "4".equals(auditAcceptance.getMediateApplyInfo().getPatientRelation()) ? "父母" : "5".equals(auditAcceptance.getMediateApplyInfo().getPatientRelation()) ? "兄妹" : "6".equals(auditAcceptance.getMediateApplyInfo().getPatientRelation()) ? "亲属" : "7".equals(auditAcceptance.getMediateApplyInfo().getPatientRelation()) ? "其他" : "无"   );
			params.put("phone", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getPatientMobile()) ? "" : auditAcceptance.getMediateApplyInfo().getPatientMobile());
			params.put("name", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getPatientName()) ? "" : auditAcceptance.getMediateApplyInfo().getPatientName());
			params.put("sex", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getPatientSex()) ? "" : "1".equals(auditAcceptance.getMediateApplyInfo().getPatientSex()) ? "男" :"女");
			params.put("age", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getPatientAge()) ? "" : auditAcceptance.getMediateApplyInfo().getPatientAge());
			params.put("hospital", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getInvolveHospital()) ? "" : auditAcceptance.getMediateApplyInfo().getSjOffice().getName());
			params.put("jfgy", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getSummaryOfDisputes()) ? "" : auditAcceptance.getMediateApplyInfo().getSummaryOfDisputes());
			path += "/doc/disputeApplyPatient.docx";  //模板文件位置
            modelPath += "/doc/disputeApplyPatientM.docx";
			newFileName="医疗纠纷调解申请书（患方）.docx";
		}else if("doctorDis".equals(export)){
			params.put("hospital", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getApplyHospital()) ? "" : auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getSqOffice().getName());
			params.put("agent", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getAgent()) ? "" : auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getAgent());
			params.put("phone", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getHospitalMobile()) ? "" : auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getHospitalMobile());
			params.put("patientName", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getPatientName()) ? "" : auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getPatientName());
			params.put("sex", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getPatientSex()) ? "" :"1".equals(auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getPatientSex()) ? "男" : "女");
			params.put("age", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getPatientAge()) ? "" : auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getPatientAge());
			params.put("jfgy", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getSummaryOfDisputes()) ? "" : auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo().getSummaryOfDisputes());
			path += "/doc/disputeApplyDoctor.docx";  //模板文件位置
            modelPath += "/doc/disputeApplyDoctorM.docx";
			newFileName="医疗纠纷调解申请书（医方）.docx";
		}else if("DisAcc".equals(export)){
			params.put("jfgy", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getSummaryOfDisputes()) ? "" : auditAcceptance.getMediateApplyInfo().getSummaryOfDisputes());
			params.put("source", "1".equals(auditAcceptance.getCaseSource()) ? "当事人意见" :"人民调解委员会主动调解");
			params.put("nowTime", DateUtils.getYear()+"年"+DateUtils.getMonth()+"月"+DateUtils.getDay()+"日");
			params.put("patient", auditAcceptance.getMediateApplyInfo().getPatientName());
			params.put("hospital", auditAcceptance.getComplaintMain().getHospital().getName());
			path += "/doc/disputeAcceptance.docx";  //模板文件位置
            modelPath += "/doc/disputeAcceptanceM.docx";
			newFileName="人民调解受理登记表.docx";
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