/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;

import java.util.List;
import java.util.Map;

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
	/**
	 * 我的待办列表
	 */
	@RequestMapping(value = "self")
	public String selfList(ComplaintMain complaintMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		//在 task 表中 根据 处理人信息 来查询 当前登录人有几条待办信息，不同的节点返回不同的页面
		complaintMain.setUser(UserUtils.getUser());
		Page<ComplaintMain> page = complaintMainService.selfList(new Page<ComplaintMain>(request, response), complaintMain);
		model.addAttribute("page", page);
//		List<Map<String,Object>> list=complaintMainService.selfList(UserUtils.getUser());
//		model.addAttribute("complaintMain", list);
		return "modules/complaintmain/complaintMainList";
	}


	/**
	 * 获取我的待办数目
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount( Model model) {
		User user= UserUtils.getUser();
		return String.valueOf(complaintMainService.findCount(user));
	}
}