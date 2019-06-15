/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.medicalofficeemp.web;

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
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.medicalofficeemp.service.MedicalOfficeEmpService;

/**
 * 医方人员机构关联信息Controller
 * @author gbq
 * @version 2019-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/medicalofficeemp/medicalOfficeEmp")
public class MedicalOfficeEmpController extends BaseController {

	@Autowired
	private MedicalOfficeEmpService medicalOfficeEmpService;
	
	@ModelAttribute
	public MedicalOfficeEmp get(@RequestParam(required=false) String id) {
		MedicalOfficeEmp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medicalOfficeEmpService.get(id);
		}
		if (entity == null){
			entity = new MedicalOfficeEmp();
		}
		return entity;
	}
	
	@RequiresPermissions("medicalofficeemp:medicalOfficeEmp:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedicalOfficeEmp medicalOfficeEmp, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedicalOfficeEmp> page = medicalOfficeEmpService.findPage(new Page<MedicalOfficeEmp>(request, response), medicalOfficeEmp); 
		model.addAttribute("page", page);
		return "modules/medicalofficeemp/medicalOfficeEmpList";
	}

	@RequiresPermissions("medicalofficeemp:medicalOfficeEmp:view")
	@RequestMapping(value = "form")
	public String form(MedicalOfficeEmp medicalOfficeEmp, Model model) {
		model.addAttribute("medicalOfficeEmp", medicalOfficeEmp);
		return "modules/medicalofficeemp/medicalOfficeEmpForm";
	}

	@RequiresPermissions("medicalofficeemp:medicalOfficeEmp:edit")
	@RequestMapping(value = "save")
	public String save(MedicalOfficeEmp medicalOfficeEmp, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medicalOfficeEmp)){
			return form(medicalOfficeEmp, model);
		}
		medicalOfficeEmpService.save(medicalOfficeEmp);
		addMessage(redirectAttributes, "保存医方人员机构关联信息成功");
		return "redirect:"+Global.getAdminPath()+"/medicalofficeemp/medicalOfficeEmp/?repage";
	}
	
	@RequiresPermissions("medicalofficeemp:medicalOfficeEmp:edit")
	@RequestMapping(value = "delete")
	public String delete(MedicalOfficeEmp medicalOfficeEmp, RedirectAttributes redirectAttributes) {
		medicalOfficeEmpService.delete(medicalOfficeEmp);
		addMessage(redirectAttributes, "删除医方人员机构关联信息成功");
		return "redirect:"+Global.getAdminPath()+"/medicalofficeemp/medicalOfficeEmp/?repage";
	}

}