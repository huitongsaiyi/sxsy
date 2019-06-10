/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.nestigateeividence.web;

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
import com.sayee.sxsy.modules.nestigateeividence.entity.InvestigateEvidence;
import com.sayee.sxsy.modules.nestigateeividence.service.InvestigateEvidenceService;

/**
 * 调查取证Controller
 * @author gbq
 * @version 2019-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/nestigateeividence/investigateEvidence")
public class InvestigateEvidenceController extends BaseController {

	@Autowired
	private InvestigateEvidenceService investigateEvidenceService;
	
	@ModelAttribute
	public InvestigateEvidence get(@RequestParam(required=false) String id) {
		InvestigateEvidence entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = investigateEvidenceService.get(id);
		}
		if (entity == null){
			entity = new InvestigateEvidence();
		}
		return entity;
	}
	
	@RequiresPermissions("nestigateeividence:investigateEvidence:view")
	@RequestMapping(value = {"list", ""})
	public String list(InvestigateEvidence investigateEvidence, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<InvestigateEvidence> page = investigateEvidenceService.findPage(new Page<InvestigateEvidence>(request, response), investigateEvidence); 
		model.addAttribute("page", page);
		return "modules/nestigateeividence/investigateEvidenceList";
	}

	@RequiresPermissions("nestigateeividence:investigateEvidence:view")
	@RequestMapping(value = "form")
	public String form(InvestigateEvidence investigateEvidence, Model model) {
		model.addAttribute("investigateEvidence", investigateEvidence);
		return "modules/nestigateeividence/investigateEvidenceForm";
	}

	@RequiresPermissions("nestigateeividence:investigateEvidence:edit")
	@RequestMapping(value = "save")
	public String save(InvestigateEvidence investigateEvidence, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, investigateEvidence)){
			return form(investigateEvidence, model);
		}
		investigateEvidenceService.save(investigateEvidence);
		addMessage(redirectAttributes, "保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/nestigateeividence/investigateEvidence/?repage";
	}
	
	@RequiresPermissions("nestigateeividence:investigateEvidence:edit")
	@RequestMapping(value = "delete")
	public String delete(InvestigateEvidence investigateEvidence, RedirectAttributes redirectAttributes) {
		investigateEvidenceService.delete(investigateEvidence);
		addMessage(redirectAttributes, "删除成功成功");
		return "redirect:"+Global.getAdminPath()+"/nestigateeividence/investigateEvidence/?repage";
	}

}