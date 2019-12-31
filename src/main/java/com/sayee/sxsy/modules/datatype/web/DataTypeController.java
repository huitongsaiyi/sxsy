/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.datatype.web;

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
import com.sayee.sxsy.modules.datatype.entity.DataType;
import com.sayee.sxsy.modules.datatype.service.DataTypeService;

/**
 * 统计基础数据Controller
 * @author zhangfan
 * @version 2019-12-24
 */
@Controller
@RequestMapping(value = "${adminPath}/datatype/dataType")
public class DataTypeController extends BaseController {

	@Autowired
	private DataTypeService dataTypeService;
	
	@ModelAttribute
	public DataType get(@RequestParam(required=false) String id) {
		DataType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataTypeService.get(id);
		}
		if (entity == null){
			entity = new DataType();
		}
		return entity;
	}
	
	@RequiresPermissions("datatype:dataType:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataType dataType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataType> page = dataTypeService.findPage(new Page<DataType>(request, response), dataType); 
		model.addAttribute("page", page);
		return "modules/datatype/dataTypeList";
	}

	@RequiresPermissions("datatype:dataType:view")
	@RequestMapping(value = "form")
	public String form(DataType dataType, Model model) {
		model.addAttribute("dataType", dataType);
		return "modules/datatype/dataTypeForm";
	}

	@RequiresPermissions("datatype:dataType:edit")
	@RequestMapping(value = "save")
	public String save(DataType dataType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataType)){
			return form(dataType, model);
		}
		dataTypeService.save(dataType);
		addMessage(redirectAttributes, "保存统计基础数据成功");
		return "redirect:"+Global.getAdminPath()+"/datatype/dataType/?repage";
	}
	
	@RequiresPermissions("datatype:dataType:edit")
	@RequestMapping(value = "delete")
	public String delete(DataType dataType, RedirectAttributes redirectAttributes) {
		dataTypeService.delete(dataType);
		addMessage(redirectAttributes, "删除统计基础数据成功");
		return "redirect:"+Global.getAdminPath()+"/datatype/dataType/?repage";
	}

}