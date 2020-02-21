/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.signtype.web;

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
import com.sayee.sxsy.modules.signtype.entity.SignTypeInfo;
import com.sayee.sxsy.modules.signtype.service.SignTypeInfoService;

/**
 * 协议书Controller
 * @author zhangfan
 * @version 2020-02-04
 */
@Controller
@RequestMapping(value = "${adminPath}/signtype/signTypeInfo")
public class SignTypeInfoController extends BaseController {

	@Autowired
	private SignTypeInfoService signTypeInfoService;
	
	@ModelAttribute
	public SignTypeInfo get(@RequestParam(required=false) String id) {
		SignTypeInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = signTypeInfoService.get(id);
		}
		if (entity == null){
			entity = new SignTypeInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("signtype:signTypeInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(SignTypeInfo signTypeInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SignTypeInfo> page = signTypeInfoService.findPage(new Page<SignTypeInfo>(request, response), signTypeInfo); 
		model.addAttribute("page", page);
		return "modules/signtype/signTypeInfoList";
	}

	@RequiresPermissions("signtype:signTypeInfo:view")
	@RequestMapping(value = "form")
	public String form(SignTypeInfo signTypeInfo, Model model) {
		model.addAttribute("signTypeInfo", signTypeInfo);
		return "modules/signtype/signTypeInfoForm";
	}

	@RequiresPermissions("signtype:signTypeInfo:edit")
	@RequestMapping(value = "save")
	public String save(SignTypeInfo signTypeInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, signTypeInfo)){
			return form(signTypeInfo, model);
		}
		signTypeInfoService.save(signTypeInfo);
		addMessage(redirectAttributes, "保存协议书成功");
		return "redirect:"+Global.getAdminPath()+"/signtype/signTypeInfo/?repage";
	}
	
	@RequiresPermissions("signtype:signTypeInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(SignTypeInfo signTypeInfo, RedirectAttributes redirectAttributes) {
		signTypeInfoService.delete(signTypeInfo);
		addMessage(redirectAttributes, "删除协议书成功");
		return "redirect:"+Global.getAdminPath()+"/signtype/signTypeInfo/?repage";
	}

}