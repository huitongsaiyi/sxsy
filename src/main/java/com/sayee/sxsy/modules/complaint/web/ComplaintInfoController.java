/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaint.web;

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
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.complaint.service.ComplaintInfoService;

/**
 * 投诉接待Controller
 * @author zhangfan
 * @version 2019-05-27
 */
@Controller
@RequestMapping(value = "${adminPath}/complaint/complaintInfo")
public class ComplaintInfoController extends BaseController {

	@Autowired
	private ComplaintInfoService complaintInfoService;
	
	@ModelAttribute
	public ComplaintInfo get(@RequestParam(required=false) String id) {
		ComplaintInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = complaintInfoService.get(id);
		}
		if (entity == null){
			entity = new ComplaintInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("complaint:complaintInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComplaintInfo complaintInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComplaintInfo> page = complaintInfoService.findPage(new Page<ComplaintInfo>(request, response), complaintInfo);

		model.addAttribute("page", page);
		return "modules/complaint/complaintInfoList";
	}

	@RequiresPermissions("complaint:complaintInfo:view")
	@RequestMapping(value = "form")
	public String form(ComplaintInfo complaintInfo, Model model) {
		model.addAttribute("complaintInfo", complaintInfo);
		return "modules/complaint/complaintInfoForm";
	}

	@RequiresPermissions("complaint:complaintInfo:edit")
	@RequestMapping(value = "save")
	public String save(ComplaintInfo complaintInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, complaintInfo )|| false==complaintInfoService.checkcaseNumber(complaintInfo.getCaseNumber())){
			return form(complaintInfo, model);
		}
		complaintInfoService.save(complaintInfo);
		addMessage(redirectAttributes, "保存投诉接待成功");
		return "redirect:"+Global.getAdminPath()+"/complaint/complaintInfo/?repage";
	}
	
	@RequiresPermissions("complaint:complaintInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(ComplaintInfo complaintInfo, RedirectAttributes redirectAttributes) {
		complaintInfoService.delete(complaintInfo);
		addMessage(redirectAttributes, "删除投诉接待成功");
		return "redirect:"+Global.getAdminPath()+"/complaint/complaintInfo/?repage";
	}

}