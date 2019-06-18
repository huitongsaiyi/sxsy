/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintdetail.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
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
import com.sayee.sxsy.modules.complaintdetail.entity.ComplaintMainDetail;
import com.sayee.sxsy.modules.complaintdetail.service.ComplaintMainDetailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 医调委投诉接待Controller
 * @author zhangfan
 * @version 2019-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/complaintdetail/complaintMainDetail")
public class ComplaintMainDetailController extends BaseController {

	@Autowired
	private ComplaintMainDetailService complaintMainDetailService;
	
	@ModelAttribute
	public ComplaintMainDetail get(@RequestParam(required=false) String id) {
		ComplaintMainDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = complaintMainDetailService.get(id);
		}
		if (entity == null){
			entity = new ComplaintMainDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("complaintdetail:complaintMainDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComplaintMainDetail complaintMainDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComplaintMainDetail> page = complaintMainDetailService.findPage(new Page<ComplaintMainDetail>(request, response), complaintMainDetail); 
		model.addAttribute("page", page);
		return "modules/complaintdetail/complaintMainDetailList";
	}

	@RequiresPermissions("complaintdetail:complaintMainDetail:view")
	@RequestMapping(value = "form")
	public String form(ComplaintMainDetail complaintMainDetail, Model model,HttpServletRequest request) {
		String type=request.getParameter("type");
		if ("view".equals(type)) {
			model.addAttribute("complaintMainDetail", complaintMainDetail);
			return "modules/complaintdetail/complaintMainDetailView";
		}else {

			model.addAttribute("complaintMainDetail", complaintMainDetail);
			return "modules/complaintdetail/complaintMainDetailForm";
		}
	}

	@RequiresPermissions("complaintdetail:complaintMainDetail:edit")
	@RequestMapping(value = "save")
	public String save(ComplaintMainDetail complaintMainDetail, Model model, RedirectAttributes redirectAttributes) {

		try {
			complaintMainDetailService.save(complaintMainDetail);
			if ("yes".equals(complaintMainDetail.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes, "流程已启动，流程ID：" + complaintMainDetail.getComplaintMain().getProcInsId());
			}else {
				addMessage(redirectAttributes, "保存投诉接待成功");
			}
		} catch (Exception e) {
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
		return "redirect:"+Global.getAdminPath()+"/complaintdetail/complaintMainDetail/?repage";
//		return "redirect:" + adminPath + "/oa/leave/form";
//
//		if (!beanValidator(model, complaintMainDetail)){
//			return form(complaintMainDetail, model);
//		}



	}
	
	@RequiresPermissions("complaintdetail:complaintMainDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(ComplaintMainDetail complaintMainDetail, RedirectAttributes redirectAttributes) {
		complaintMainDetailService.delete(complaintMainDetail);
		addMessage(redirectAttributes, "删除投诉接待成功");
		return "redirect:"+Global.getAdminPath()+"/complaintdetail/complaintMainDetail/?repage";
	}

}