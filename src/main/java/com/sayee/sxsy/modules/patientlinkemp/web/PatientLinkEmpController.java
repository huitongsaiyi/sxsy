/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.patientlinkemp.web;

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
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.patientlinkemp.service.PatientLinkEmpService;

/**
 * 患方联系人Controller
 * @author gbq
 * @version 2019-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/patientlinkemp/patientLinkEmp")
public class PatientLinkEmpController extends BaseController {

	@Autowired
	private PatientLinkEmpService patientLinkEmpService;
	
	@ModelAttribute
	public PatientLinkEmp get(@RequestParam(required=false) String id) {
		PatientLinkEmp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = patientLinkEmpService.get(id);
		}
		if (entity == null){
			entity = new PatientLinkEmp();
		}
		return entity;
	}
	
	@RequiresPermissions("patientlinkemp:patientLinkEmp:view")
	@RequestMapping(value = {"list", ""})
	public String list(PatientLinkEmp patientLinkEmp, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PatientLinkEmp> page = patientLinkEmpService.findPage(new Page<PatientLinkEmp>(request, response), patientLinkEmp); 
		model.addAttribute("page", page);
		return "modules/patientlinkemp/patientLinkEmpList";
	}

	@RequiresPermissions("patientlinkemp:patientLinkEmp:view")
	@RequestMapping(value = "form")
	public String form(PatientLinkEmp patientLinkEmp, Model model) {
		model.addAttribute("patientLinkEmp", patientLinkEmp);
		return "modules/patientlinkemp/patientLinkEmpForm";
	}

	@RequiresPermissions("patientlinkemp:patientLinkEmp:edit")
	@RequestMapping(value = "save")
	public String save(PatientLinkEmp patientLinkEmp, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, patientLinkEmp)){
			return form(patientLinkEmp, model);
		}
		patientLinkEmpService.save(patientLinkEmp);
		addMessage(redirectAttributes, "保存患方联系人成功");
		return "redirect:"+Global.getAdminPath()+"/patientlinkemp/patientLinkEmp/?repage";
	}
	
	@RequiresPermissions("patientlinkemp:patientLinkEmp:edit")
	@RequestMapping(value = "delete")
	public String delete(PatientLinkEmp patientLinkEmp, RedirectAttributes redirectAttributes) {
		patientLinkEmpService.delete(patientLinkEmp);
		addMessage(redirectAttributes, "删除患方联系人成功");
		return "redirect:"+Global.getAdminPath()+"/patientlinkemp/patientLinkEmp/?repage";
	}

}