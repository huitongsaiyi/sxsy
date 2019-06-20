/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.mediateapplyinfo.web;

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
import com.sayee.sxsy.modules.mediateapplyinfo.entity.MediateApplyInfo;
import com.sayee.sxsy.modules.mediateapplyinfo.service.MediateApplyInfoService;

/**
 * 调节申请Controller
 * @author lyt
 * @version 2019-06-19
 */
@Controller
@RequestMapping(value = "${adminPath}/mediateapplyinfo/mediateApplyInfo")
public class MediateApplyInfoController extends BaseController {

	@Autowired
	private MediateApplyInfoService mediateApplyInfoService;
	
	@ModelAttribute
	public MediateApplyInfo get(@RequestParam(required=false) String id) {
		MediateApplyInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mediateApplyInfoService.get(id);
		}
		if (entity == null){
			entity = new MediateApplyInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("mediateapplyinfo:mediateApplyInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MediateApplyInfo mediateApplyInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MediateApplyInfo> page = mediateApplyInfoService.findPage(new Page<MediateApplyInfo>(request, response), mediateApplyInfo); 
		model.addAttribute("page", page);
		return "modules/mediateapplyinfo/mediateApplyInfoList";
	}

	@RequiresPermissions("mediateapplyinfo:mediateApplyInfo:view")
	@RequestMapping(value = "form")
	public String form(MediateApplyInfo mediateApplyInfo, Model model) {
		model.addAttribute("mediateApplyInfo", mediateApplyInfo);
		return "modules/mediateapplyinfo/mediateApplyInfoForm";
	}

	@RequiresPermissions("mediateapplyinfo:mediateApplyInfo:edit")
	@RequestMapping(value = "save")
	public String save(MediateApplyInfo mediateApplyInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mediateApplyInfo)){
			return form(mediateApplyInfo, model);
		}
		mediateApplyInfoService.save(mediateApplyInfo);
		addMessage(redirectAttributes, "保存调节申请成功");
		return "redirect:"+Global.getAdminPath()+"/mediateapplyinfo/mediateApplyInfo/?repage";
	}
	
	@RequiresPermissions("mediateapplyinfo:mediateApplyInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MediateApplyInfo mediateApplyInfo, RedirectAttributes redirectAttributes) {
		mediateApplyInfoService.delete(mediateApplyInfo);
		addMessage(redirectAttributes, "删除调节申请成功");
		return "redirect:"+Global.getAdminPath()+"/mediateapplyinfo/mediateApplyInfo/?repage";
	}

}