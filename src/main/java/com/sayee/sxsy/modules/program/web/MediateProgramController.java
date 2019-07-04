/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.program.web;

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
import com.sayee.sxsy.modules.program.entity.MediateProgram;
import com.sayee.sxsy.modules.program.service.MediateProgramService;

/**
 * 调解程序Controller
 * @author zhangfan
 * @version 2019-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/program/mediateProgram")
public class MediateProgramController extends BaseController {

	@Autowired
	private MediateProgramService mediateProgramService;
	
	@ModelAttribute
	public MediateProgram get(@RequestParam(required=false) String id) {
		MediateProgram entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mediateProgramService.get(id);
		}
		if (entity == null){
			entity = new MediateProgram();
		}
		return entity;
	}
	
	@RequiresPermissions("program:mediateProgram:view")
	@RequestMapping(value = {"list", ""})
	public String list(MediateProgram mediateProgram, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MediateProgram> page = mediateProgramService.findPage(new Page<MediateProgram>(request, response), mediateProgram); 
		model.addAttribute("page", page);
		return "modules/program/mediateProgramList";
	}

	@RequiresPermissions("program:mediateProgram:view")
	@RequestMapping(value = "form")
	public String form(MediateProgram mediateProgram, Model model) {
		model.addAttribute("mediateProgram", mediateProgram);
		return "modules/program/mediateProgramForm";
	}

	@RequiresPermissions("program:mediateProgram:edit")
	@RequestMapping(value = "save")
	public String save(MediateProgram mediateProgram, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mediateProgram)){
			return form(mediateProgram, model);
		}
		mediateProgramService.save(mediateProgram);
		addMessage(redirectAttributes, "保存调解程序成功");
		return "redirect:"+Global.getAdminPath()+"/program/mediateProgram/?repage";
	}
	
	@RequiresPermissions("program:mediateProgram:edit")
	@RequestMapping(value = "delete")
	public String delete(MediateProgram mediateProgram, RedirectAttributes redirectAttributes) {
		mediateProgramService.delete(mediateProgram);
		addMessage(redirectAttributes, "删除调解程序成功");
		return "redirect:"+Global.getAdminPath()+"/program/mediateProgram/?repage";
	}

}