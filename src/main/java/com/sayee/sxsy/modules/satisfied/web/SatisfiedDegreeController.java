/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.satisfied.web;

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
import com.sayee.sxsy.modules.satisfied.entity.SatisfiedDegree;
import com.sayee.sxsy.modules.satisfied.service.SatisfiedDegreeService;

/**
 * 满意度Controller
 * @author zhangfan
 * @version 2019-12-03
 */
@Controller
@RequestMapping(value = "${adminPath}/satisfied/satisfiedDegree")
public class SatisfiedDegreeController extends BaseController {

	@Autowired
	private SatisfiedDegreeService satisfiedDegreeService;
	
	@ModelAttribute
	public SatisfiedDegree get(@RequestParam(required=false) String id) {
		SatisfiedDegree entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = satisfiedDegreeService.get(id);
		}
		if (entity == null){
			entity = new SatisfiedDegree();
		}
		return entity;
	}
	
	@RequiresPermissions("satisfied:satisfiedDegree:view")
	@RequestMapping(value = {"list", ""})
	public String list(SatisfiedDegree satisfiedDegree, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SatisfiedDegree> page = satisfiedDegreeService.findPage(new Page<SatisfiedDegree>(request, response), satisfiedDegree); 
		model.addAttribute("page", page);
		return "modules/satisfied/satisfiedDegreeList";
	}

	@RequiresPermissions("satisfied:satisfiedDegree:view")
	@RequestMapping(value = "form")
	public String form(SatisfiedDegree satisfiedDegree, Model model,HttpServletRequest request) {
	    String type=request.getParameter("type");
        model.addAttribute("satisfiedDegree", satisfiedDegree);
	    if ("view".equals(type)){
            return "modules/satisfied/satisfiedDegreeView";
        }else {
            return "modules/satisfied/satisfiedDegreeForm";
        }
	}

	@RequiresPermissions("satisfied:satisfiedDegree:edit")
	@RequestMapping(value = "save")
	public String save(SatisfiedDegree satisfiedDegree, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, satisfiedDegree)){
			return form(satisfiedDegree, model,null);
		}
		satisfiedDegreeService.save(satisfiedDegree);
		addMessage(redirectAttributes, "保存满意度成功");
		return "redirect:"+Global.getAdminPath()+"/satisfied/satisfiedDegree/?repage";
	}
	
	@RequiresPermissions("satisfied:satisfiedDegree:edit")
	@RequestMapping(value = "delete")
	public String delete(SatisfiedDegree satisfiedDegree, RedirectAttributes redirectAttributes) {
		satisfiedDegreeService.delete(satisfiedDegree);
		addMessage(redirectAttributes, "删除满意度成功");
		return "redirect:"+Global.getAdminPath()+"/satisfied/satisfiedDegree/?repage";
	}

}