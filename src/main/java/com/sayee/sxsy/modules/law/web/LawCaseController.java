/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.law.web;

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
import com.sayee.sxsy.modules.law.entity.LawCase;
import com.sayee.sxsy.modules.law.service.LawCaseService;

/**
 * 法律法规Controller
 * @author zhangfan
 * @version 2019-12-03
 */
@Controller
@RequestMapping(value = "${adminPath}/law/lawCase")
public class LawCaseController extends BaseController {

	@Autowired
	private LawCaseService lawCaseService;
	
	@ModelAttribute
	public LawCase get(@RequestParam(required=false) String id) {
		LawCase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lawCaseService.get(id);
		}
		if (entity == null){
			entity = new LawCase();
		}
		return entity;
	}
	
	@RequiresPermissions("law:lawCase:view")
	@RequestMapping(value = {"list", ""})
	public String list(LawCase lawCase, HttpServletRequest request, HttpServletResponse response, Model model) {
		String law=request.getParameter("law");
		if(lawCase.getType()==null||lawCase.getType().equals("")){
            lawCase.setType(law);
        }else{
		    lawCase.setContent(null);
        }
		Page<LawCase> page = lawCaseService.findPage(new Page<LawCase>(request, response), lawCase);
		model.addAttribute("page", page);
		model.addAttribute("law", law);
		return "modules/law/lawCaseList";
	}

	@RequiresPermissions("law:lawCase:view")
	@RequestMapping(value = "form")
	public String form(LawCase lawCase, Model model, HttpServletRequest request) {
		String law=request.getParameter("law");
		model.addAttribute("lawCase", lawCase);
		model.addAttribute("law", law);
		return "modules/law/lawCaseForm";
	}

	@RequiresPermissions("law:lawCase:edit")
	@RequestMapping(value = "save")
	public String save(LawCase lawCase, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, lawCase)){
			return form(lawCase, model,request);
		}
		lawCaseService.save(lawCase);
		addMessage(redirectAttributes, "保存信息成功");
		return "redirect:"+Global.getAdminPath()+"/law/lawCase/?repage&law="+lawCase.getType()+"";
	}
	
	@RequiresPermissions("law:lawCase:edit")
	@RequestMapping(value = "delete")
	public String delete(LawCase lawCase, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, Model model) {
		lawCaseService.delete(lawCase);
		addMessage(redirectAttributes, "删除信息成功");
		//return "redirect:"+Global.getAdminPath()+"/law/lawCase/?repage";
        String law = request.getParameter("law");
        lawCase.setType(law);
        String list = list(lawCase, request, response, model);
        return list;
	}

}