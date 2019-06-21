/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.stopmediate.web;

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
import com.sayee.sxsy.modules.stopmediate.entity.StopMediate;
import com.sayee.sxsy.modules.stopmediate.service.StopMediateService;

/**
 * 终止调解Controller
 * @author lyt
 * @version 2019-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/stopmediate/stopMediate")
public class StopMediateController extends BaseController {

	@Autowired
	private StopMediateService stopMediateService;
	
	@ModelAttribute
	public StopMediate get(@RequestParam(required=false) String id) {
		StopMediate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = stopMediateService.get(id);
		}
		if (entity == null){
			entity = new StopMediate();
		}
		return entity;
	}
	
	@RequiresPermissions("stopmediate:stopMediate:view")
	@RequestMapping(value = {"list", ""})
	public String list(StopMediate stopMediate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StopMediate> page = stopMediateService.findPage(new Page<StopMediate>(request, response), stopMediate); 
		model.addAttribute("page", page);
		return "modules/stopmediate/stopMediateList";
	}

	@RequiresPermissions("stopmediate:stopMediate:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request, StopMediate stopMediate, Model model) {
		String type = request.getParameter("type");
		if("view".equals(type)){
            model.addAttribute("stopMediate", stopMediate);
            return "modules/stopmediate/stopMediateView";
        }else {
            model.addAttribute("stopMediate", stopMediate);
            return "modules/stopmediate/stopMediateForm";
		}
	}

	@RequiresPermissions("stopmediate:stopMediate:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request, StopMediate stopMediate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, stopMediate)){
			return form(request, stopMediate, model);
		}
		stopMediateService.save(stopMediate);
		addMessage(redirectAttributes, "保存终止调解成功");
		return "redirect:"+Global.getAdminPath()+"/stopmediate/stopMediate/?repage";
	}
	
	@RequiresPermissions("stopmediate:stopMediate:edit")
	@RequestMapping(value = "delete")
	public String delete(StopMediate stopMediate, RedirectAttributes redirectAttributes) {
		stopMediateService.delete(stopMediate);
		addMessage(redirectAttributes, "删除终止调解成功");
		return "redirect:"+Global.getAdminPath()+"/stopmediate/stopMediate/?repage";
	}

}