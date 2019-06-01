/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.typeinfo.web;

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
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import com.sayee.sxsy.modules.typeinfo.service.TypeInfoService;

/**
 * 类型总表Controller
 * @author lyt
 * @version 2019-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/typeinfo/typeInfo")
public class TypeInfoController extends BaseController {

	@Autowired
	private TypeInfoService typeInfoService;
	
	@ModelAttribute
	public TypeInfo get(@RequestParam(required=false) String id) {
		TypeInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = typeInfoService.get(id);
		}
		if (entity == null){
			entity = new TypeInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("typeinfo:typeInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(TypeInfo typeInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TypeInfo> page = typeInfoService.findPage(new Page<TypeInfo>(request, response), typeInfo); 
		model.addAttribute("page", page);
		return "modules/typeinfo/typeInfoList";
	}

	@RequiresPermissions("typeinfo:typeInfo:view")
	@RequestMapping(value = "form")
	public String form(TypeInfo typeInfo, Model model) {
		model.addAttribute("typeInfo", typeInfo);
		return "modules/typeinfo/typeInfoForm";
	}

	@RequiresPermissions("typeinfo:typeInfo:edit")
	@RequestMapping(value = "save")
	public String save(TypeInfo typeInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, typeInfo)){
			return form(typeInfo, model);
		}
		typeInfoService.save(typeInfo);
		addMessage(redirectAttributes, "保存类型成功");
		return "redirect:"+Global.getAdminPath()+"/typeinfo/typeInfo/?repage";
	}
	
	@RequiresPermissions("typeinfo:typeInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(TypeInfo typeInfo, RedirectAttributes redirectAttributes) {
		typeInfoService.delete(typeInfo);
		addMessage(redirectAttributes, "删除类型成功");
		return "redirect:"+Global.getAdminPath()+"/typeinfo/typeInfo/?repage";
	}

}