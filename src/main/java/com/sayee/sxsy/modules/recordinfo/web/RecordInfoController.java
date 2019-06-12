/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.recordinfo.web;

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
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.recordinfo.service.RecordInfoService;

/**
 * 笔录Controller
 * @author 逯洋涛
 * @version 2019-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/recordinfo/recordInfo")
public class RecordInfoController extends BaseController {

	@Autowired
	private RecordInfoService recordInfoService;
	
	@ModelAttribute
	public RecordInfo get(@RequestParam(required=false) String id) {
		RecordInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = recordInfoService.get(id);
		}
		if (entity == null){
			entity = new RecordInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("recordinfo:recordInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(RecordInfo recordInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RecordInfo> page = recordInfoService.findPage(new Page<RecordInfo>(request, response), recordInfo); 
		model.addAttribute("page", page);
		return "modules/recordinfo/recordInfoList";
	}

	@RequiresPermissions("recordinfo:recordInfo:view")
	@RequestMapping(value = "form")
	public String form(RecordInfo recordInfo, Model model) {
		model.addAttribute("recordInfo", recordInfo);
		return "modules/recordinfo/recordInfoForm";
	}

	@RequiresPermissions("recordinfo:recordInfo:edit")
	@RequestMapping(value = "save")
	public String save(RecordInfo recordInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, recordInfo)){
			return form(recordInfo, model);
		}
		recordInfoService.save(recordInfo);
		addMessage(redirectAttributes, "保存笔录成功");
		return "redirect:"+Global.getAdminPath()+"/recordinfo/recordInfo/?repage";
	}
	
	@RequiresPermissions("recordinfo:recordInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(RecordInfo recordInfo, RedirectAttributes redirectAttributes) {
		recordInfoService.delete(recordInfo);
		addMessage(redirectAttributes, "删除笔录成功");
		return "redirect:"+Global.getAdminPath()+"/recordinfo/recordInfo/?repage";
	}

}