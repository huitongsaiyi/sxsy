/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.organ.web;

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
import com.sayee.sxsy.modules.organ.entity.ServiceOrgan;
import com.sayee.sxsy.modules.organ.service.ServiceOrganService;

/**
 * 服务机构信息Controller
 * @author zhangfan
 * @version 2019-12-03
 */
@Controller
@RequestMapping(value = "${adminPath}/organ/serviceOrgan")
public class ServiceOrganController extends BaseController {

	@Autowired
	private ServiceOrganService serviceOrganService;
	
	@ModelAttribute
	public ServiceOrgan get(@RequestParam(required=false) String id) {
		ServiceOrgan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = serviceOrganService.get(id);
		}
		if (entity == null){
			entity = new ServiceOrgan();
		}
		return entity;
	}
	
	@RequiresPermissions("organ:serviceOrgan:view")
	@RequestMapping(value = {"list", ""})
	public String list(ServiceOrgan serviceOrgan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ServiceOrgan> page = serviceOrganService.findPage(new Page<ServiceOrgan>(request, response), serviceOrgan); 
		model.addAttribute("page", page);
		return "modules/organ/serviceOrganList";
	}

	@RequiresPermissions("organ:serviceOrgan:view")
	@RequestMapping(value = "form")
	public String form(ServiceOrgan serviceOrgan, Model model) {
		if (StringUtils.isNotBlank(serviceOrgan.getServiceOrganId())){


		}
		model.addAttribute("serviceOrgan", serviceOrgan);
		return "modules/organ/serviceOrganForm";
	}

	@RequiresPermissions("organ:serviceOrgan:edit")
	@RequestMapping(value = "save")
	public String save(ServiceOrgan serviceOrgan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, serviceOrgan)){
			return form(serviceOrgan, model);
		}
		serviceOrganService.save(serviceOrgan);
		addMessage(redirectAttributes, "保存服务机构信息成功");
		return "redirect:"+Global.getAdminPath()+"/organ/serviceOrgan/?repage";
	}
	
	@RequiresPermissions("organ:serviceOrgan:edit")
	@RequestMapping(value = "delete")
	public String delete(ServiceOrgan serviceOrgan, RedirectAttributes redirectAttributes) {
		serviceOrganService.delete(serviceOrgan);
		addMessage(redirectAttributes, "删除服务机构信息成功");
		return "redirect:"+Global.getAdminPath()+"/organ/serviceOrgan/?repage";
	}

}