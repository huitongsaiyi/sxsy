/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.registration.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.WordExportUtil;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.complaintdetail.service.ComplaintMainDetailService;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	ComplaintMainService complaintMainService;

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
	public void save(ReportRegistration reportRegistration, HttpServletRequest request) {
//		if(reportRegistration.getCreateDate()==null){

        if(StringUtils.isBlank(reportRegistration.getCreateBy().getId())){
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
		//保存附件
		this.savefj(request,reportRegistration);
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
	//保存附件
	public void savefj(HttpServletRequest request,ReportRegistration reportRegistration){
		String files = request.getParameter("files");
		String acceId = null;
		String itemId1 = reportRegistration.getReportRegistrationId();
		String fjtype1 = request.getParameter("fjtype");
		if(StringUtils.isNotBlank(files)){
			String acceId1=request.getParameter("acceId1");
			if(StringUtils.isNotBlank(acceId1)){
				preOperativeConsentService.updatefj(files,itemId1,fjtype1);
			}else{
				acceId=IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId1,files,fjtype1);
			}
		}else{
			preOperativeConsentService.delefj(itemId1,fjtype1);
		}
	}

	//导出打印
	public String exportWord(ReportRegistration reportRegistration, String export,String print,HttpServletRequest request, HttpServletResponse response){
		WordExportUtil wordExportUtil=new WordExportUtil();
		reportRegistration = this.get(reportRegistration.getReportRegistrationId());
		ComplaintMain complaintMain = new ComplaintMain();
		if(StringUtils.isNotBlank(reportRegistration.getComplaintMainId())){
			complaintMain=complaintMainService.get(reportRegistration.getComplaintMainId());
		}

		String path=request.getServletContext().getRealPath("/");
		String returnPath="";
		String newFileName="无标题文件.docx";
		String savaPath=path;
		String pdfPath=path;
		Map<String, Object> params = new HashMap<String, Object>();
		//判断有无案件编号
		String num=null;
		if(reportRegistration.getComplaintMain()!=null){
			num=reportRegistration.getComplaintMain().getCaseNumber()==null?"":reportRegistration.getComplaintMain().getCaseNumber();
		}else{
			num="";
		}
		if("reportDis".equals(export)){
			params.put("hospitol",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getHospital().getName());//医疗机构名称
			params.put("pNum",reportRegistration.getPolicyNumber()==null?"":reportRegistration.getPolicyNumber());//保单号
			params.put("disTime",reportRegistration.getDisputeTime()==null?"":reportRegistration.getDisputeTime());//纠纷发生时间
			if(reportRegistration.getComplaintMain() !=null){
				if("1".equals(reportRegistration.getComplaintMain().getHospitalGrade())){
					params.put("g","特等");//机构等级
				}else if("2".equals(reportRegistration.getComplaintMain().getHospitalGrade())){
					params.put("g","甲等");//机构等级
				}else if("3".equals(reportRegistration.getComplaintMain().getHospitalGrade())){
					params.put("g","乙等");//机构等级
				}else if("4".equals(reportRegistration.getComplaintMain().getHospitalGrade())){
					params.put("g","丙等");//机构等级
				}else{
					params.put("g","");//机构等级
				}
			}else{
				params.put("g","");//机构等级
			}

			params.put("area",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getHospital().getArea().getName());//所属城市
			params.put("reportEmp",reportRegistration.getReportEmp()==null?"":reportRegistration.getReportEmp());//报案人姓名
			params.put("pName",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getPatientName());//患者姓名
			if(reportRegistration.getComplaintMain() !=null){
				if("1".equals(reportRegistration.getComplaintMain().getPatientSex())){
					params.put("sex","男");
				}else if("2".equals(reportRegistration.getComplaintMain().getPatientSex())){
					params.put("sex","女");
				}else{
					params.put("sex","");
				}
			}else{
				params.put("sex","");
			}
			params.put("age",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getPatientAge());//年龄
			params.put("idCard",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getPatientCard()==null?"":reportRegistration.getComplaintMain().getPatientCard());//身份证号
			params.put("reTime",reportRegistration.getReportTime()==null?"":reportRegistration.getReportTime());//出险时间
			if(reportRegistration.getComplaintMain() !=null) {
				if ("huxineike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "呼吸内科");
				} else if ("erke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "儿科");
				} else if ("fuke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "妇科");
				} else if ("yanke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "眼科");
				} else if ("erbihouke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "耳鼻喉科");
				} else if ("kouqiangke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "口腔科");
				} else if ("pifuke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "皮肤科");
				} else if ("zhongyike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "中医科");
				} else if ("zhenjiutuinake".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "针灸推拿科");
				} else if ("xinlizixunshi".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "心理咨询室");
				} else if ("xiaohuaneike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "消化内科");
				} else if ("miniaoneike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "泌尿内科");
				} else if ("xinneike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "心内科");
				} else if ("xueyeke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "血液科");
				} else if ("neifenmike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "内分泌科");
				} else if ("shenjingneike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "神经内科");
				} else if ("xiaoerke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "小儿科");
				} else if ("ganranke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "感染科");
				} else if ("puwaike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "普外科");
				} else if ("guke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "骨科");
				} else if ("shenjingwaike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "神经外科");
				} else if ("gandanwaike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "肝胆外科");
				} else if ("miniaowaike".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "泌尿外科");
				} else if ("shaoshangke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "烧伤科");
				} else if ("chanke".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "产科");
				} else if ("xuetoushi".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "血透室");
				} else if ("zhongzhengjianhushi".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "重症监护室");
				} else if ("外科".equals(reportRegistration.getComplaintMain().getInvolveDepartment())) {
					params.put("de", "");
				} else {
					params.put("de", "");
				}
			}else{
				params.put("de", "");
			}
			    params.put("invo",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getInvolveEmployee());

			if("1".equals(reportRegistration.getIsMajor())){
				params.put("isMajor","是");
			}else if("0".equals(reportRegistration.getIsMajor())){
				params.put("isMajor","否");
			}else{
				params.put("isMajor","");
			}
			params.put("pMobile",reportRegistration.getPatientMobile()==null?"":reportRegistration.getPatientMobile());//患方联系电话
			params.put("hMobile",reportRegistration.getDoctorMobile()==null?"":reportRegistration.getDoctorMobile());//医方联系电话
			params.put("jiufen",reportRegistration.getSummaryOfDisputes()==null?"":reportRegistration.getSummaryOfDisputes());//纠纷概要
			params.put("focus",reportRegistration.getFocus()==null?"":reportRegistration.getFocus());//纠纷焦点
			params.put("patientAsk",reportRegistration.getPatientAsk()==null?"":reportRegistration.getPatientAsk());//患方要求
			params.put("tianbiao",reportRegistration.getDjEmployee()==null?"":reportRegistration.getDjEmployee().getName());//填表人签名
			params.put("reTime",reportRegistration.getRegistrationTime()==null?"":reportRegistration.getRegistrationTime());//填表日期
			params.put("caseNum",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getCaseNumber());//报案号
			params.put("nextLink",reportRegistration.getNextLink()==null?"":reportRegistration.getNextLink());//审核人
			params.put("pRelation",reportRegistration.getPatientRelation()==null?"":reportRegistration.getPatientRelation());//审核日期

			path += "/doc/repord.docx";  //模板文件位置
			savaPath +="/userfiles/report/"+num+"repord.docx";
			pdfPath +="/userfiles/report/"+num+"repord.pdf";
			returnPath="/userfiles/report/"+num+"repord.pdf";
			newFileName="报案信息表.docx";
		}
		try{
			File file =new File(request.getServletContext().getRealPath("/")+"/userfiles/report/"+num);
			if (!file.exists()){
				file.mkdirs();
			}
			List<String[]> testList = new ArrayList<String[]>();
			String fileName= new String(newFileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
			wordExportUtil.getWord(path,path,savaPath,print,params,testList,fileName,response);
			wordExportUtil.doc2pdf(savaPath,new FileOutputStream(pdfPath));
			System.out.println("转pdf成功");
//			if (StringUtils.isNotBlank(printName)){
			//wordExportUtil.wToPdfChange(savaPath,pdfPath);
			//wordExportUtil.PDFprint(new File(pdfPath),printName);
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnPath;
	}
}