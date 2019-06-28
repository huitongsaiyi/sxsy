/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessappraisal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.BaseUtils;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.sign.service.SignAgreementService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import com.sayee.sxsy.modules.typeinfo.service.TypeInfoService;
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
    private MachineAccountService machineAccountService;
    @Autowired
    private SignAgreementService signAgreementService;
    @Autowired
    private TypeInfoService typeInfoService;
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
			model.addAttribute("assessAppraisal", assessAppraisal);
			return "modules/assessappraisal/assessAppraisalView";
		}else{
			model.addAttribute("assessAppraisal", assessAppraisal);
			return "modules/assessappraisal/assessAppraisalForm";
		}

	}

	@RequiresPermissions("assessappraisal:assessAppraisal:edit")
	@RequestMapping(value = "save")
	public String save(AssessAppraisal assessAppraisal, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if ("yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag()) &&(!beanValidator(model, assessAppraisal)||!beanValidator(model,assessAppraisal.getComplaintMain())||!beanValidator(model,assessAppraisal.getRecordInfo1())||!beanValidator(model,assessAppraisal.getRecordInfo1().getYrecordInfo())||!beanValidator(model,assessAppraisal.getProposal())) ){
			return form(assessAppraisal, model,request);
		}
		try{
			assessAppraisalService.save(assessAppraisal,request);
            machineAccountService.savetz(assessAppraisal.getMachineAccount(), "d", assessAppraisal.getAssessAppraisalId());
			if("yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes, "流程已启动，流程ID：" + assessAppraisal.getComplaintMain().getProcInsId());
			}else {
				addMessage(redirectAttributes, "保存评估鉴定成功");
			}

		}catch(Exception e){
			logger.error("启动鉴定评估流程失败：",e);
			addMessage(redirectAttributes,"系统内部错误");

		}
		return "redirect:"+Global.getAdminPath()+"/assessappraisal/assessAppraisal/?repage";
	}

	@RequiresPermissions("assessappraisal:assessAppraisal:edit")
	@RequestMapping(value = "delete")
	public String delete(AssessAppraisal assessAppraisal, RedirectAttributes redirectAttributes) {
		assessAppraisalService.delete(assessAppraisal);
		addMessage(redirectAttributes, "删除评估鉴定成功");
		return "redirect:"+Global.getAdminPath()+"/assessappraisal/assessAppraisal/?repage";
	}

}