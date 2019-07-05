/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.auditacceptance.service;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;
import org.apache.poi.hpsf.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.auditacceptance.dao.AuditAcceptanceDao;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
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
		//保存附件
		this.savefj(request,auditAcceptance);

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
		String files20 = request.getParameter("files20");
		String acceId = null;
		String itemId = auditAcceptance.getAuditAcceptanceId();
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
		if(StringUtils.isNotBlank(files6)){
			String acceId6=request.getParameter("acceId6");
			if(StringUtils.isNotBlank(acceId6)){
				preOperativeConsentService.updatefj(files6,itemId,fjtype6);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files6,fjtype6);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype6);
		}
		if(StringUtils.isNotBlank(files7)){
			String acceId7=request.getParameter("acceId7");
			if(StringUtils.isNotBlank(acceId7)){
				preOperativeConsentService.updatefj(files7,itemId,fjtype7);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files7,fjtype7);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype7);
		}
		if(StringUtils.isNotBlank(files8)){
			String acceId8=request.getParameter("acceId8");
			if(StringUtils.isNotBlank(acceId8)){
				preOperativeConsentService.updatefj(files8,itemId,fjtype8);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files8,fjtype8);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype8);
		}
		if(StringUtils.isNotBlank(files9)){
			String acceId9=request.getParameter("acceId9");
			if(StringUtils.isNotBlank(acceId9)){
				preOperativeConsentService.updatefj(files9,itemId,fjtype9);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files9,fjtype9);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype9);
		}
		if(StringUtils.isNotBlank(files10)){
			String acceId10=request.getParameter("acceId10");
			if(StringUtils.isNotBlank(acceId10)){
				preOperativeConsentService.updatefj(files10,itemId,fjtype10);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files10,fjtype10);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype10);
		}
		if(StringUtils.isNotBlank(files11)){
			String acceId11=request.getParameter("acceId11");
			if(StringUtils.isNotBlank(acceId11)){
				preOperativeConsentService.updatefj(files11,itemId,fjtype11);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files11,fjtype11);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype11);
		}
		if(StringUtils.isNotBlank(files12)){
			String acceId12=request.getParameter("acceId12");
			if(StringUtils.isNotBlank(acceId12)){
				preOperativeConsentService.updatefj(files12,itemId,fjtype12);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files12,fjtype12);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype12);
		}
		if(StringUtils.isNotBlank(files13)){
			String acceId13=request.getParameter("acceId13");
			if(StringUtils.isNotBlank(acceId13)){
				preOperativeConsentService.updatefj(files13,itemId,fjtype13);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files13,fjtype13);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype13);
		}
		if(StringUtils.isNotBlank(files14)){
			String acceId14=request.getParameter("acceId14");
			if(StringUtils.isNotBlank(acceId14)){
				preOperativeConsentService.updatefj(files14,itemId,fjtype14);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files14,fjtype14);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype14);
		}
		if(StringUtils.isNotBlank(files15)){
			String acceId15=request.getParameter("acceId15");
			if(StringUtils.isNotBlank(acceId15)){
				preOperativeConsentService.updatefj(files15,itemId,fjtype15);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files15,fjtype15);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype15);
		}
		if(StringUtils.isNotBlank(files16)){
			String acceId16=request.getParameter("acceId16");
			if(StringUtils.isNotBlank(acceId16)){
				preOperativeConsentService.updatefj(files16,itemId,fjtype16);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files16,fjtype16);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype16);
		}
		if(StringUtils.isNotBlank(files17)){
			String acceId17=request.getParameter("acceId17");
			if(StringUtils.isNotBlank(acceId17)){
				preOperativeConsentService.updatefj(files17,itemId,fjtype17);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files17,fjtype17);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype17);
		}
		if(StringUtils.isNotBlank(files18)){
			String acceId18=request.getParameter("acceId18");
			if(StringUtils.isNotBlank(acceId18)){
				preOperativeConsentService.updatefj(files18,itemId,fjtype18);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files18,fjtype18);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype18);
		}
		if(StringUtils.isNotBlank(files19)){
			String acceId19=request.getParameter("acceId19");
			if(StringUtils.isNotBlank(acceId19)){
				preOperativeConsentService.updatefj(files19,itemId,fjtype19);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files19,fjtype19);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype19);
		}
		if(StringUtils.isNotBlank(files20)){
			String acceId20=request.getParameter("acceId20");
			if(StringUtils.isNotBlank(acceId20)){
				preOperativeConsentService.updatefj(files20,itemId,fjtype20);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files20,fjtype20);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype20);
		}
	}

	public void exportWord(AuditAcceptance auditAcceptance,String export, HttpServletRequest request, HttpServletResponse response) {
		WordExportUtil wordExportUtil=new WordExportUtil();
		auditAcceptance=this.get(auditAcceptance.getAuditAcceptanceId());
		if (auditAcceptance.getMediateApplyInfo()==null){
			auditAcceptance.setMediateApplyInfo(new MediateApplyInfo());
		}
		String printName=request.getParameter("printName");
		String path=request.getServletContext().getRealPath("/");
		String modelPath=path;
		String newFileName="无标题文件.docx";
		String savaPath=path;
		String pdfPath=path;
		Map<String, Object> params = new HashMap<String, Object>();
		if ("patientAcc".equals(export)){
			params.put("patient", auditAcceptance.getMediateApplyInfo().getPatientName());
			params.put("hospital", auditAcceptance.getComplaintMain().getHospital().getName());
			path += "/doc/acceptanceP.docx";  //模板文件位置
			modelPath += "/doc/acceptancePM.docx";
			savaPath +="/userfiles/audit/acceptanceP.docx";
			pdfPath +="/userfiles/audit/acceptanceP.pdf";
			newFileName="患方通知书.docx";
		}else if("hospitalAcc".equals(export)){
			params.put("patient", auditAcceptance.getMediateApplyInfo().getPatientName());
			params.put("hospital", auditAcceptance.getComplaintMain().getHospital().getName());
			path += "/doc/acceptanceD.docx";  //模板文件位置
			modelPath += "/doc/acceptanceDM.docx";
			savaPath +="/userfiles/audit/acceptanceD.docx";
			pdfPath +="/userfiles/audit/acceptanceD.pdf";
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
			savaPath +="/userfiles/audit/disputeApplyPatient.docx";
			pdfPath +="/userfiles/audit/disputeApplyPatient.pdf";
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
			savaPath +="/userfiles/audit/disputeApplyDoctor.docx";
			pdfPath +="/userfiles/audit/disputeApplyDoctor.pdf";
			newFileName="医疗纠纷调解申请书（医方）.docx";
		}else if("DisAcc".equals(export)){
			params.put("jfgy", StringUtils.isBlank(auditAcceptance.getMediateApplyInfo().getSummaryOfDisputes()) ? "" : auditAcceptance.getMediateApplyInfo().getSummaryOfDisputes());
			params.put("source", "1".equals(auditAcceptance.getCaseSource()) ? "当事人意见" :"人民调解委员会主动调解");
			params.put("nowTime", DateUtils.getYear()+"年"+DateUtils.getMonth()+"月"+DateUtils.getDay()+"日");
			params.put("patient", auditAcceptance.getMediateApplyInfo().getPatientName());
			params.put("hospital", auditAcceptance.getComplaintMain().getHospital().getName());
			path += "/doc/disputeAcceptance.docx";  //模板文件位置
			modelPath += "/doc/disputeAcceptanceM.docx";
			savaPath +="/userfiles/audit/disputeAcceptance.docx";
			pdfPath +="/userfiles/audit/disputeAcceptance.pdf";
			newFileName="人民调解受理登记表.docx";

		}

		try{
			File file =new File(request.getServletContext().getRealPath("/")+"/userfiles/audit");
			if (!file.exists()){
				file.mkdirs();
			}
			List<String[]> testList = new ArrayList<String[]>();
			String fileName= new String(newFileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
			wordExportUtil.getWord(path,modelPath,savaPath,params,testList,fileName,response);
			/*wToPdfChange(savaPath,pdfPath);
			PDFprint(new File(pdfPath),"KONICA MINOLTA XPS Color Laser Class Driver");*/
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//word转化pdf，传入转换前的文件路径（例："E:\\a.docx"）和转换后的文件路径（例："E:\\a.pdf"）
	public static void wToPdfChange(String wordFile,String pdfFile){//wordFile word 的路径  //pdfFile pdf 的路径

		ActiveXComponent app = null;
		System.out.println("开始转换...");
		// 开始时间
		// long start = System.currentTimeMillis();
		try {
			// 打开word
			app = new ActiveXComponent("Word.Application");
			// 获得word中所有打开的文档
			Dispatch documents = app.getProperty("Documents").toDispatch();
			System.out.println("打开文件: " + wordFile);
			// 打开文档
			Dispatch document = Dispatch.call(documents, "Open", wordFile, false, true).toDispatch();
			// 如果文件存在的话，不会覆盖，会直接报错，所以我们需要判断文件是否存在
			File target = new File(pdfFile);
			if (target.exists()) {
				target.delete();
			}
			System.out.println("另存为: " + pdfFile);
			Dispatch.call(document, "SaveAs", pdfFile, 17);
			// 关闭文档
			Dispatch.call(document, "Close", false);
		}catch(Exception e) {
			System.out.println("转换失败"+e.getMessage());
		}finally {
			// 关闭office
			app.invoke("Quit", 0);
		}
	}



	//这里传入的文件为word转化生成的pdf文件
	public static void PDFprint(File file ,String printerName) throws Exception {
		PDDocument document = null;
		try {
			document = PDDocument.load(file);
			PrinterJob printJob = PrinterJob.getPrinterJob();
			printJob.setJobName(file.getName());
			if (printerName != null) {
				// 查找并设置打印机
				//获得本台电脑连接的所有打印机
				PrintService[] printServices = PrinterJob.lookupPrintServices();                			 if(printServices == null || printServices.length == 0) {
					System.out.print("打印失败，未找到可用打印机，请检查。");
					return ;
				}
				PrintService printService = null;
				//匹配指定打印机
				for (int i = 0;i < printServices.length; i++) {
					System.out.println(printServices[i].getName());
					if (printServices[i].getName().contains(printerName)) {
						printService = printServices[i];
						break;
					}
				}
				if(printService!=null){
					printJob.setPrintService(printService);
				}else{
					System.out.print("打印失败，未找到名称为" + printerName + "的打印机，请检查。");
					return ;
				}
			}
			//设置纸张及缩放
			PDFPrintable pdfPrintable = new PDFPrintable(document, Scaling.ACTUAL_SIZE);
			//设置多页打印
			Book book = new Book();
			PageFormat pageFormat = new PageFormat();
			//设置打印方向
			pageFormat.setOrientation(PageFormat.PORTRAIT);//纵向
			pageFormat.setPaper(getPaper());//设置纸张
			book.append(pdfPrintable, pageFormat, document.getNumberOfPages());
			printJob.setPageable(book);
			printJob.setCopies(1);//设置打印份数
			//添加打印属性
			HashPrintRequestAttributeSet pars = new HashPrintRequestAttributeSet();
			pars.add(Sides.DUPLEX); //设置单双页
			printJob.print(pars);
		}finally {
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Paper getPaper() {
		Paper paper = new Paper();
		// 默认为A4纸张，对应像素宽和高分别为 595, 842
		int width = 595;
		int height = 842;
		// 设置边距，单位是像素，10mm边距，对应 28px
		int marginLeft = 10;
		int marginRight = 0;
		int marginTop = 10;
		int marginBottom = 0;
		paper.setSize(width, height);
		// 下面一行代码，解决了打印内容为空的问题
		paper.setImageableArea(marginLeft, marginRight, width - (marginLeft + marginRight), height - (marginTop + marginBottom));
		return paper;
	}




}