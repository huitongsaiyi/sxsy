/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.casefeedback.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.modules.assessinfo.service.AssessInfoService;
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
import com.sayee.sxsy.modules.casefeedback.entity.CaseFeedback;
import com.sayee.sxsy.modules.casefeedback.service.CaseFeedbackService;

import java.util.Map;

/**
 * 案件反馈Controller
 * @author lyt
 * @version 2019-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/casefeedback/caseFeedback")
public class CaseFeedbackController extends BaseController {

	@Autowired
	private CaseFeedbackService caseFeedbackService;

	
	@ModelAttribute
	public CaseFeedback get(@RequestParam(required=false) String id) {
		CaseFeedback entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = caseFeedbackService.get(id);
		}
		if (entity == null){
			entity = new CaseFeedback();
		}
		return entity;
	}
	
	@RequiresPermissions("casefeedback:caseFeedback:view")
	@RequestMapping(value = {"list", ""})
	public String list(CaseFeedback caseFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CaseFeedback> page = caseFeedbackService.findPage(new Page<CaseFeedback>(request, response), caseFeedback); 
		model.addAttribute("page", page);
		return "modules/casefeedback/caseFeedbackList";
	}

	@RequiresPermissions("casefeedback:caseFeedback:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request,CaseFeedback caseFeedback, Model model) {
		String type = request.getParameter("type");
		if("view".equals(type)){
			Map<String, Object> map = caseFeedbackService.getViewDetail(caseFeedback.getComplaintMainId());
			model.addAttribute("map",map);
			model.addAttribute("caseFeedback", caseFeedback);
			return "modules/casefeedback/caseFeedbackView";
		}else {
			model.addAttribute("caseFeedback", caseFeedback);
			return "modules/casefeedback/caseFeedbackForm";
		}
	}

	@RequiresPermissions("casefeedback:caseFeedback:edit")
	@RequestMapping(value = "save")
	public String save(CaseFeedback caseFeedback, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, caseFeedback)){
			return form(request,caseFeedback, model);
		}
		caseFeedbackService.save(caseFeedback);
		addMessage(redirectAttributes, "保存案件反馈成功");
		return "redirect:"+Global.getAdminPath()+"/casefeedback/caseFeedback/?repage";
	}
	
	@RequiresPermissions("casefeedback:caseFeedback:edit")
	@RequestMapping(value = "delete")
	public String delete(CaseFeedback caseFeedback, RedirectAttributes redirectAttributes) {
		caseFeedbackService.delete(caseFeedback);
		addMessage(redirectAttributes, "删除案件反馈成功");
		return "redirect:"+Global.getAdminPath()+"/casefeedback/caseFeedback/?repage";
	}

}