/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.auditacceptance.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.auditacceptance.service.AuditAcceptanceService;

/**
 * 审核受理Controller
 * @author zhangfan
 * @version 2019-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/auditacceptance/auditAcceptance")
public class AuditAcceptanceController extends BaseController {

	@Autowired
	private AuditAcceptanceService auditAcceptanceService;
	
	@ModelAttribute
	public AuditAcceptance get(@RequestParam(required=false) String id) {
		AuditAcceptance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = auditAcceptanceService.get(id);
		}
		if (entity == null){
			entity = new AuditAcceptance();
		}
		return entity;
	}
	
	@RequiresPermissions("auditacceptance:auditAcceptance:view")
	@RequestMapping(value = {"list", ""})
	public String list(AuditAcceptance auditAcceptance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AuditAcceptance> page = auditAcceptanceService.findPage(new Page<AuditAcceptance>(request, response), auditAcceptance); 
		model.addAttribute("page", page);
		return "modules/auditacceptance/auditAcceptanceList";
	}

	@RequiresPermissions("auditacceptance:auditAcceptance:view")
	@RequestMapping(value = "form")
	public String form(AuditAcceptance auditAcceptance, Model model) {
		model.addAttribute("auditAcceptance", auditAcceptance);
		return "modules/auditacceptance/auditAcceptanceForm";
	}

	@RequiresPermissions("auditacceptance:auditAcceptance:edit")
	@RequestMapping(value = "save")
	public String save(AuditAcceptance auditAcceptance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, auditAcceptance)){
			return form(auditAcceptance, model);
		}
		auditAcceptanceService.save(auditAcceptance);
		addMessage(redirectAttributes, "保存审核受理成功");
		return "redirect:"+Global.getAdminPath()+"/auditacceptance/auditAcceptance/?repage";
	}
	
	@RequiresPermissions("auditacceptance:auditAcceptance:edit")
	@RequestMapping(value = "delete")
	public String delete(AuditAcceptance auditAcceptance, RedirectAttributes redirectAttributes) {
		auditAcceptanceService.delete(auditAcceptance);
		addMessage(redirectAttributes, "删除审核受理成功");
		return "redirect:"+Global.getAdminPath()+"/auditacceptance/auditAcceptance/?repage";
	}

}