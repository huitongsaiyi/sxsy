/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.registration.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.AjaxHelper;
import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.sign.entity.SignAgreement;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
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
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.registration.service.ReportRegistrationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报案登记Controller
 * @author lyt
 * @version 2019-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/registration/reportRegistration")
public class ReportRegistrationController extends BaseController {

	@Autowired
	private ReportRegistrationService reportRegistrationService;

	@Autowired
	private SummaryInfoService summaryInfoService;
	@Autowired
	private ComplaintMainService complaintMainService;
	@ModelAttribute
	public ReportRegistration get(@RequestParam(required=false) String id) {
		ReportRegistration entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reportRegistrationService.get(id);
		}
		if (entity == null){
			entity = new ReportRegistration();
		}
		return entity;
	}

	@RequiresPermissions("registration:reportRegistration:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReportRegistration reportRegistration, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReportRegistration> page = reportRegistrationService.findPage(new Page<ReportRegistration>(request, response), reportRegistration);
		model.addAttribute("page", page);
		return "modules/registration/reportRegistrationList";
	}

	@RequiresPermissions("registration:reportRegistration:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request,ReportRegistration reportRegistration, Model model) {
		String type = request.getParameter("type");		//接受从页面传回的数据
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(reportRegistration.getReportRegistrationId());
		for (Map<String, Object> map:filePath) {
			if ("0".equals(MapUtils.getString(map, "fjtype"))) {
				model.addAttribute("files", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
		if("view".equals(type)) {		//判断数据内容是否一致，一致将数据发送到详情页面；不一致，页面跳转到添加页面
			Map<String, Object> map = summaryInfoService.getViewDetail(reportRegistration.getComplaintMainId());
			model.addAttribute("map",map);
			model.addAttribute("reportRegistration",reportRegistration);
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			return "modules/registration/reportRegistrationView";
		}else{
			ComplaintMain complaintMain = complaintMainService.get(reportRegistration.getComplaintMainId());
			reportRegistration.getComplaintMain().setTestTree(complaintMain.getTestTree());
			model.addAttribute("reportRegistration", reportRegistration);
			return "modules/registration/reportRegistrationForm";
		}
	}

	@RequiresPermissions("registration:reportRegistration:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,ReportRegistration reportRegistration, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		String export=request.getParameter("export");
		if (StringUtils.isNotBlank(export) && !export.equals("no")){
			ReportRegistration reportRegistration1 = reportRegistrationService.get(reportRegistration.getReportRegistrationId());
			String path = reportRegistrationService.exportWord(reportRegistration1,export,"false",request,response);
			return path;
		}
		try {
			if (!beanValidator(model, reportRegistration)&&"yes".equals(reportRegistration.getComplaintMain().getAct().getFlag())||!beanValidator(model,reportRegistration.getComplaintMain())&&"yes".equals(reportRegistration.getComplaintMain().getAct().getFlag())){
				return form(request,reportRegistration, model);
			}
			reportRegistrationService.save(reportRegistration,request);
			if ("yes".equals(reportRegistration.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes, "流程已启动，流程ID：" + reportRegistration.getComplaintMain().getProcInsId());
			}else {
				addMessage(redirectAttributes, "保存报案登记成功");
			}
		} catch (Exception e) {
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
		return "redirect:"+Global.getAdminPath()+"/registration/reportRegistration/?repage";
	}

	@RequiresPermissions("registration:reportRegistration:edit")
	@RequestMapping(value = "delete")
	public String delete(ReportRegistration reportRegistration, RedirectAttributes redirectAttributes) {
		reportRegistrationService.delete(reportRegistration);
		addMessage(redirectAttributes, "删除报案信息成功");
		return "redirect:"+Global.getAdminPath()+"/registration/reportRegistration/?repage";
	}

	@RequestMapping(value = "pass")
	public void pass(HttpServletRequest request,HttpServletResponse response) {
		String code="";//1.成功 0失败
		String reportRegistrationId=request.getParameter("reportRegistrationId");//前台传过来的状态
		String export=request.getParameter("export");//前台传过来的状态
		String print=request.getParameter("print");//前台传过来的状态
		ReportRegistration reportRegistration = reportRegistrationService.get(reportRegistrationId);
		code=reportRegistrationService.exportWord(reportRegistration,export,print,request,response);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("url",code);
		AjaxHelper.responseWrite(request,response,"1","success",map);

	}
}