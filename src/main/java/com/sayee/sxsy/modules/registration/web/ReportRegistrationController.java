/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.registration.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.IdGen;
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
		if("view".equals(type)) {		//判断数据内容是否一致，一致将数据发送到详情页面；不一致，页面跳转到添加页面
			model.addAttribute("reportRegistration",reportRegistration);
			return "modules/registration/reportRegistrationView";
		}else{
			model.addAttribute("reportRegistration", reportRegistration);
			return "modules/registration/reportRegistrationForm";
		}
	}

	@RequiresPermissions("registration:reportRegistration:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,ReportRegistration reportRegistration, Model model, RedirectAttributes redirectAttributes) {
		String files = request.getParameter("files");
		try {
			reportRegistrationService.save(reportRegistration);
			String acceId1 = IdGen.uuid();
			String itemId1 = reportRegistration.getReportRegistrationId();
			String fjtype1 = request.getParameter("fjtype");
			reportRegistrationService.savefj(acceId1,itemId1,files,fjtype1);
			if ("yes".equals(reportRegistration.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes, "流程已启动，流程ID：" + reportRegistration.getComplaintMain().getProcInsId());
			}else {
				addMessage(redirectAttributes, "保存报案登记成功");
			}
		} catch (Exception e) {
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
//		if (!beanValidator(model, reportRegistration)){
//			return form(request,reportRegistration, model);
//		}
		return "redirect:"+Global.getAdminPath()+"/registration/reportRegistration/?repage";
	}

	@RequiresPermissions("registration:reportRegistration:edit")
	@RequestMapping(value = "delete")
	public String delete(ReportRegistration reportRegistration, RedirectAttributes redirectAttributes) {
		reportRegistrationService.delete(reportRegistration);
		addMessage(redirectAttributes, "删除报案信息成功");
		return "redirect:"+Global.getAdminPath()+"/registration/reportRegistration/?repage";
	}

}