/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.proposal.web;

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
import com.sayee.sxsy.modules.proposal.entity.Proposal;
import com.sayee.sxsy.modules.proposal.service.ProposalService;

/**
 * 意见书Controller
 * @author gbq
 * @version 2019-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/proposal/proposal")
public class ProposalController extends BaseController {

	@Autowired
	private ProposalService proposalService;
	
	@ModelAttribute
	public Proposal get(@RequestParam(required=false) String id) {
		Proposal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = proposalService.get(id);
		}
		if (entity == null){
			entity = new Proposal();
		}
		return entity;
	}
	
	@RequiresPermissions("proposal:proposal:view")
	@RequestMapping(value = {"list", ""})
	public String list(Proposal proposal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Proposal> page = proposalService.findPage(new Page<Proposal>(request, response), proposal); 
		model.addAttribute("page", page);
		return "modules/proposal/proposalList";
	}

	@RequiresPermissions("proposal:proposal:view")
	@RequestMapping(value = "form")
	public String form(Proposal proposal, Model model) {
		model.addAttribute("proposal", proposal);
		return "modules/proposal/proposalForm";
	}

	@RequiresPermissions("proposal:proposal:edit")
	@RequestMapping(value = "save")
	public String save(Proposal proposal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, proposal)){
			return form(proposal, model);
		}
		proposalService.save(proposal);
		addMessage(redirectAttributes, "保存意见书成功");
		return "redirect:"+Global.getAdminPath()+"/proposal/proposal/?repage";
	}
	
	@RequiresPermissions("proposal:proposal:edit")
	@RequestMapping(value = "delete")
	public String delete(Proposal proposal, RedirectAttributes redirectAttributes) {
		proposalService.delete(proposal);
		addMessage(redirectAttributes, "删除意见书成功");
		return "redirect:"+Global.getAdminPath()+"/proposal/proposal/?repage";
	}

}