/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.web;

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
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;

/**
 * 纠纷调解Controller
 * @author lyt
 * @version 2019-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/complaintmain/complaintMain")
public class ComplaintMainController extends BaseController {

	@Autowired
	private ComplaintMainService complaintMainService;
	
	@ModelAttribute
	public ComplaintMain get(@RequestParam(required=false) String id) {
		ComplaintMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = complaintMainService.get(id);
		}
		if (entity == null){
			entity = new ComplaintMain();
		}
		return entity;
	}
	
	@RequiresPermissions("complaintmain:complaintMain:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComplaintMain complaintMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComplaintMain> page = complaintMainService.findPage(new Page<ComplaintMain>(request, response), complaintMain); 
		model.addAttribute("page", page);
		return "modules/complaintmain/complaintMainList";
	}

	@RequiresPermissions("complaintmain:complaintMain:view")
	@RequestMapping(value = "form")
	public String form(ComplaintMain complaintMain, Model model) {
		model.addAttribute("complaintMain", complaintMain);
		return "modules/complaintmain/complaintMainForm";
	}

	@RequiresPermissions("complaintmain:complaintMain:edit")
	@RequestMapping(value = "save")
	public String save(ComplaintMain complaintMain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, complaintMain)){
			return form(complaintMain, model);
		}
		complaintMainService.save(complaintMain);
		addMessage(redirectAttributes, "保存纠纷调解成功");
		return "redirect:"+Global.getAdminPath()+"/complaintmain/complaintMain/?repage";
	}
	
	@RequiresPermissions("complaintmain:complaintMain:edit")
	@RequestMapping(value = "delete")
	public String delete(ComplaintMain complaintMain, RedirectAttributes redirectAttributes) {
		complaintMainService.delete(complaintMain);
		addMessage(redirectAttributes, "删除纠纷调解成功");
		return "redirect:"+Global.getAdminPath()+"/complaintmain/complaintMain/?repage";
	}

}