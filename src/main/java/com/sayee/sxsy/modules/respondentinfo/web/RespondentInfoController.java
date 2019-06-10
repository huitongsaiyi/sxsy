/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.respondentinfo.web;

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
import com.sayee.sxsy.modules.respondentinfo.entity.RespondentInfo;
import com.sayee.sxsy.modules.respondentinfo.service.RespondentInfoService;

/**
 * 被调查人信息Controller
 * @author gbq
 * @version 2019-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/respondentinfo/respondentInfo")
public class RespondentInfoController extends BaseController {

	@Autowired
	private RespondentInfoService respondentInfoService;
	
	@ModelAttribute
	public RespondentInfo get(@RequestParam(required=false) String id) {
		RespondentInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = respondentInfoService.get(id);
		}
		if (entity == null){
			entity = new RespondentInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("respondentinfo:respondentInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(RespondentInfo respondentInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RespondentInfo> page = respondentInfoService.findPage(new Page<RespondentInfo>(request, response), respondentInfo); 
		model.addAttribute("page", page);
		return "modules/respondentinfo/respondentInfoList";
	}

	@RequiresPermissions("respondentinfo:respondentInfo:view")
	@RequestMapping(value = "form")
	public String form(RespondentInfo respondentInfo, Model model) {
		model.addAttribute("respondentInfo", respondentInfo);
		return "modules/respondentinfo/respondentInfoForm";
	}

	@RequiresPermissions("respondentinfo:respondentInfo:edit")
	@RequestMapping(value = "save")
	public String save(RespondentInfo respondentInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, respondentInfo)){
			return form(respondentInfo, model);
		}
		respondentInfoService.save(respondentInfo);
		addMessage(redirectAttributes, "保存被调查人信息成功");
		return "redirect:"+Global.getAdminPath()+"/respondentinfo/respondentInfo/?repage";
	}
	
	@RequiresPermissions("respondentinfo:respondentInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(RespondentInfo respondentInfo, RedirectAttributes redirectAttributes) {
		respondentInfoService.delete(respondentInfo);
		addMessage(redirectAttributes, "删除被调查人信息成功");
		return "redirect:"+Global.getAdminPath()+"/respondentinfo/respondentInfo/?repage";
	}

}