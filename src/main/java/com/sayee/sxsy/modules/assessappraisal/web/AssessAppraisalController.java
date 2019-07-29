/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessappraisal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.AjaxHelper;
import com.sayee.sxsy.common.utils.BaseUtils;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.proposal.entity.Proposal;
import com.sayee.sxsy.modules.proposal.service.ProposalService;
import com.sayee.sxsy.modules.sign.service.SignAgreementService;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import com.sayee.sxsy.modules.typeinfo.service.TypeInfoService;
import org.activiti.engine.impl.util.json.JSONArray;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.assessappraisal.entity.AssessAppraisal;
import com.sayee.sxsy.modules.assessappraisal.service.AssessAppraisalService;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评估鉴定Controller
 * @author gbq
 * @version 2019-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/assessappraisal/assessAppraisal")
public class AssessAppraisalController extends BaseController {

	@Autowired
	private AssessAppraisalService assessAppraisalService;
	@Autowired
	private SignAgreementService signAgreementService;
	@Autowired
	private TypeInfoService typeInfoService;
	@Autowired
	SummaryInfoService summaryInfoService;
	@Autowired
    private MachineAccountService machineAccountService;
	@Autowired
	ProposalService proposalService;
	@Autowired
	ComplaintMainService complaintMainService;
	@ModelAttribute
	public AssessAppraisal get(@RequestParam(required=false) String id) {
		AssessAppraisal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = assessAppraisalService.get(id);
		}
		if (entity == null){
			entity = new AssessAppraisal();
		}
		return entity;
	}

	@RequiresPermissions("assessappraisal:assessAppraisal:view")
	@RequestMapping(value = {"list", ""})
	public String list(AssessAppraisal assessAppraisal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AssessAppraisal> page = assessAppraisalService.findPage(new Page<AssessAppraisal>(request, response), assessAppraisal);
		model.addAttribute("page", page);
		return "modules/assessappraisal/assessAppraisalList";
	}

	@RequiresPermissions("assessappraisal:assessAppraisal:view")
	@RequestMapping(value = "form")
	public String form(AssessAppraisal assessAppraisal, Model model,HttpServletRequest request) {
		if(null==assessAppraisal.getProposal().getProposalCode()){
			Proposal proposal =new Proposal();
			List<Proposal> list = proposalService.findList(proposal);
			if(list.size()==0){
				String code = BaseUtils.getCode("time", "3", "PROPOSAL", "proposal_code");
				String c1= code.substring(0, 4);
				proposal.setProposalCode("晋医人调评["+c1+"]001号");
				proposal.setTreatmentSummary(assessAppraisal.getProposal().getTreatmentSummary());
				assessAppraisal.setProposal(proposal);
			}else{
			    int max=0;
			    for(int i=0;i<list.size();i++){
                    String proposalCode = list.get(i).getProposalCode();
                    String d = proposalCode.substring(11, 14);
                    int d1=Integer.valueOf(d);
                    if(d1>max){
                        max=d1;
                    }
                }
                int d2=max+1;
				String a=BaseUtils.getCode("time","3","PROPOSAL","proposal_code");
				String c=a.substring(0,4);
				String format = String.format("%0" + 3 + "d", d2);
				String e1="晋医人调评["+c+"]"+format+"号";
				proposal.setProposalCode(e1);
				proposal.setTreatmentSummary(assessAppraisal.getProposal().getTreatmentSummary());
				assessAppraisal.setProposal(proposal);

			}
		}
		ComplaintMain complaintMain = complaintMainService.get(assessAppraisal.getComplaintMainId());
		if(assessAppraisal.getPatientLinkEmpList().size()==0){
			List<PatientLinkEmp> patientLinkEmpList = assessAppraisal.getPatientLinkEmpList();
			PatientLinkEmp patientLinkEmp = new PatientLinkEmp();
			patientLinkEmp.setPatientLinkName(complaintMain.getPatientName());
			patientLinkEmp.setPatientLinkSex(complaintMain.getPatientSex());
			patientLinkEmp.setIdNumber(complaintMain.getCaseNumber());
			patientLinkEmpList.add(patientLinkEmp);
		}
		if(assessAppraisal.getMedicalOfficeEmpList().size()==0){
			MedicalOfficeEmp medicalOfficeEmp = new MedicalOfficeEmp();
			List<MedicalOfficeEmp> medicalOfficeEmpList = assessAppraisal.getMedicalOfficeEmpList();
			medicalOfficeEmp.setMedicalOfficeName(complaintMain.getHospital().getName());
			medicalOfficeEmpList.add(medicalOfficeEmp);
		}

		List<TypeInfo> fxyj = BaseUtils.getType("1");
		List<TypeInfo> jl = BaseUtils.getType("2");
		if (assessAppraisal.getProposal()!=null ){
			signAgreementService.label(fxyj,assessAppraisal.getProposal().getAnalysisOpinion());
			signAgreementService.label(jl,assessAppraisal.getProposal().getConclusion());
		}
		model.addAttribute("fxyj",fxyj);
		model.addAttribute("jl",jl);
		//获取附件
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(assessAppraisal.getAssessAppraisalId());
		for (Map<String, Object> map:filePath) {
			if("16".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("17".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("18".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files3",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId3",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("19".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files4",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId4",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("20".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files5",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId5",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("21".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files6",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId6",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("22".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files7",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId7",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
        String type = request.getParameter("type");
		if("view".equals(type)){
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			Map<String, Object> map = summaryInfoService.getViewDetail(assessAppraisal.getComplaintMainId());
			model.addAttribute("map",map);
			model.addAttribute("assessAppraisal", assessAppraisal);
			return "modules/assessappraisal/assessAppraisalView";
		}else{
			if(assessAppraisal!=null){
				if(StringUtils.isBlank(assessAppraisal.getRecordInfo1().getRecordContent())){
					assessAppraisal.getRecordInfo1().setRecordContent("(注:以下调解员简称调,患方简称患,医方简称医,医学专家简称医专,法律专家简称法专)\n调:医患双方介绍身份，确认有无要求回避?\n患:\n医:\n调:介绍鉴定委员会成员 ，确认有无要求回避?\n患:\n医:\n调:宣读会议程序及注意事项，问询医患双方是否听清楚?\n患:\n医:\n调:宣布会议开始,请患方代表陈述。\n患:\n调:其他人员有无补充\n患:\n调:请医学专家提问\n医专:\n患:\n医专:\n患:\n调:请法律专家提问\n法专:\n患:\n调:请患方退场,确认笔录,若无异议请签名\n调:请医院代表入场、陈述\n医:\n调:请医学专家提问\n医专:\n医:\n医专:\n医:\n调:请法律专家提问\n法专:\n医:");
				}
				if(StringUtils.isBlank(assessAppraisal.getRecordInfo1().getYrecordInfo().getRecordContent())){
					assessAppraisal.getRecordInfo1().getYrecordInfo().setRecordContent("(注:以下调解员简称调,患方简称患,医方简称医,医学专家简称医专,法律专家简称法专)\n调:医患双方介绍身份，确认有无要求回避?\n患:\n医:\n调:介绍鉴定委员会成员 ，确认有无要求回避?\n患:\n医:\n调:宣读会议程序及注意事项，问询医患双方是否听清楚?\n患:\n医:\n调:宣布会议开始,请患方代表陈述。\n患:\n调:其他人员有无补充\n患:\n调:请医学专家提问\n医专:\n患:\n医专:\n患:\n调:请法律专家提问\n法专:\n患:\n调:请患方退场,确认笔录,若无异议请签名\n调:请医院代表入场、陈述\n医:\n调:请医学专家提问\n医专:\n医:\n医专:\n医:\n调:请法律专家提问\n法专:\n医:");
				}
			}
			model.addAttribute("assessAppraisal", assessAppraisal);
			return "modules/assessappraisal/assessAppraisalForm";
		}

	}

	@RequiresPermissions("assessappraisal:assessAppraisal:edit")
	@RequestMapping(value = "save")
	public String save(AssessAppraisal assessAppraisal, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) {
//		if (!beanValidator(model, assessAppraisal)&&"yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())||!beanValidator(model,assessAppraisal.getComplaintMain())&&"yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())||!beanValidator(model,assessAppraisal.getRecordInfo1())&&"yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())||!beanValidator(model,assessAppraisal.getRecordInfo1().getYrecordInfo())&&"yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())||!beanValidator(model,assessAppraisal.getProposal())&&"yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())){
//			return form(assessAppraisal, model,request);
//		}
		String export=request.getParameter("export");
		if (StringUtils.isNotBlank(export) && !export.equals("no")){
			AssessAppraisal assessAppraisal1 = assessAppraisalService.get(assessAppraisal.getAssessAppraisalId());
			String path = assessAppraisalService.exportWord(assessAppraisal1,export,"false",request,response);
			return path;
		}else {
			try {
				assessAppraisalService.save(assessAppraisal, request);
				machineAccountService.savetz(assessAppraisal.getMachineAccount(), "d", assessAppraisal.getAssessAppraisalId());
				if ("yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())) {
					addMessage(redirectAttributes, "流程已启动，流程ID：" + assessAppraisal.getComplaintMain().getProcInsId());
				} else {
					addMessage(redirectAttributes, "保存评估鉴定成功");
				}

			} catch (Exception e) {
				logger.error("启动鉴定评估流程失败：", e);
				addMessage(redirectAttributes, "系统内部错误");

			}
			return "redirect:" + Global.getAdminPath() + "/assessappraisal/assessAppraisal/?repage";
		}
	}

	@RequiresPermissions("assessappraisal:assessAppraisal:edit")
	@RequestMapping(value = "delete")
	public String delete(AssessAppraisal assessAppraisal, RedirectAttributes redirectAttributes) {
		assessAppraisalService.delete(assessAppraisal);
		addMessage(redirectAttributes, "删除评估鉴定成功");
		return "redirect:"+Global.getAdminPath()+"/assessappraisal/assessAppraisal/?repage";
	}
	@RequestMapping(value = "pass")
	public void pass(HttpServletRequest request,HttpServletResponse response) {
		String code="";//1.成功 0失败
		String assessAppraisalId=request.getParameter("assessAppraisalId");//前台传过来的状态
		String export=request.getParameter("export");//前台传过来的状态
		String print=request.getParameter("print");//前台传过来的状态
		AssessAppraisal assessAppraisal = assessAppraisalService.get(assessAppraisalId);
		code=assessAppraisalService.exportWord(assessAppraisal,export,print,request,response);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("url",code);
		AjaxHelper.responseWrite(request,response,"1","success",map);

	}

}