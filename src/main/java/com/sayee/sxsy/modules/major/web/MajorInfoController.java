/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.major.web;

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
import com.sayee.sxsy.modules.major.entity.MajorInfo;
import com.sayee.sxsy.modules.major.service.MajorInfoService;

/**
 * 重大事件Controller
 * @author zhangfan
 * @version 2019-12-09
 */
@Controller
@RequestMapping(value = "${adminPath}/major/majorInfo")
public class MajorInfoController extends BaseController {

	@Autowired
	private MajorInfoService majorInfoService;
	
	@ModelAttribute
	public MajorInfo get(@RequestParam(required=false) String id) {
		MajorInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = majorInfoService.get(id);
		}
		if (entity == null){
			entity = new MajorInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("major:majorInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MajorInfo majorInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MajorInfo> page = majorInfoService.findPage(new Page<MajorInfo>(request, response), majorInfo); 
		model.addAttribute("page", page);
		return "modules/major/majorInfoList";
	}

	@RequiresPermissions("major:majorInfo:view")
	@RequestMapping(value = "form")
	public String form(MajorInfo majorInfo, Model model) {
		model.addAttribute("majorInfo", majorInfo);
		return "modules/major/majorInfoForm";
	}

	@RequiresPermissions("major:majorInfo:edit")
	@RequestMapping(value = "save")
	public String save(MajorInfo majorInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, majorInfo)){
			return form(majorInfo, model);
		}
		majorInfoService.save(majorInfo);
		addMessage(redirectAttributes, "保存重大事件成功");
		return "redirect:"+Global.getAdminPath()+"/major/majorInfo/?repage";
	}
	
	@RequiresPermissions("major:majorInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MajorInfo majorInfo, RedirectAttributes redirectAttributes) {
		majorInfoService.delete(majorInfo);
		addMessage(redirectAttributes, "删除重大事件成功");
		return "redirect:"+Global.getAdminPath()+"/major/majorInfo/?repage";
	}

}