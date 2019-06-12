/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.record.web;

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
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.record.service.MediateRecordService;

/**
 * 调解志Controller
 * @author lyt
 * @version 2019-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/record/mediateRecord")
public class MediateRecordController extends BaseController {

	@Autowired
	private MediateRecordService mediateRecordService;
	
	@ModelAttribute
	public MediateRecord get(@RequestParam(required=false) String id) {
		MediateRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mediateRecordService.get(id);
		}
		if (entity == null){
			entity = new MediateRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("record:mediateRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(MediateRecord mediateRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MediateRecord> page = mediateRecordService.findPage(new Page<MediateRecord>(request, response), mediateRecord); 
		model.addAttribute("page", page);
		return "modules/record/mediateRecordList";
	}

	@RequiresPermissions("record:mediateRecord:view")
	@RequestMapping(value = "form")
	public String form(MediateRecord mediateRecord, Model model) {
		model.addAttribute("mediateRecord", mediateRecord);
		return "modules/record/mediateRecordForm";
	}

	@RequiresPermissions("record:mediateRecord:edit")
	@RequestMapping(value = "save")
	public String save(MediateRecord mediateRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mediateRecord)){
			return form(mediateRecord, model);
		}
		mediateRecordService.save(mediateRecord);
		addMessage(redirectAttributes, "保存调解志成功");
		return "redirect:"+Global.getAdminPath()+"/record/mediateRecord/?repage";
	}
	
	@RequiresPermissions("record:mediateRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(MediateRecord mediateRecord, RedirectAttributes redirectAttributes) {
		mediateRecordService.delete(mediateRecord);
		addMessage(redirectAttributes, "删除调解志成功");
		return "redirect:"+Global.getAdminPath()+"/record/mediateRecord/?repage";
	}

}