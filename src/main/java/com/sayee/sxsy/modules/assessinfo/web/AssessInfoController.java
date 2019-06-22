/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessinfo.web;

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
import com.sayee.sxsy.modules.assessinfo.entity.AssessInfo;
import com.sayee.sxsy.modules.assessinfo.service.AssessInfoService;

/**
 * 案件评价Controller
 * @author lyt
 * @version 2019-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/assessinfo/assessInfo")
public class AssessInfoController extends BaseController {

	@Autowired
	private AssessInfoService assessInfoService;
	
	@ModelAttribute
	public AssessInfo get(@RequestParam(required=false) String id) {
		AssessInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = assessInfoService.get(id);
		}
		if (entity == null){
			entity = new AssessInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("assessinfo:assessInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(AssessInfo assessInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AssessInfo> page = assessInfoService.findPage(new Page<AssessInfo>(request, response), assessInfo); 
		model.addAttribute("page", page);
		return "modules/assessinfo/assessInfoList";
	}

	@RequiresPermissions("assessinfo:assessInfo:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request,AssessInfo assessInfo, Model model) {
		String type = request.getParameter("type");
		if("view".equals(type)){
			model.addAttribute("assessInfo", assessInfo);
			return "modules/assessinfo/assessInfoView";
		}else {
			model.addAttribute("assessInfo", assessInfo);
			return "modules/assessinfo/assessInfoForm";
		}
	}

	@RequiresPermissions("assessinfo:assessInfo:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,AssessInfo assessInfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, assessInfo)){
				return form(request,assessInfo, model);
			}
			assessInfoService.save(assessInfo);
			if ("yes".equals(assessInfo.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes, "流程已启动，流程ID：" + assessInfo.getComplaintMain().getProcInsId());
			}else {
				addMessage(redirectAttributes, "保存案件评价成功");
			}
		} catch (Exception e) {
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
		return "redirect:"+Global.getAdminPath()+"/assessinfo/assessInfo/?repage";
	}
	
	@RequiresPermissions("assessinfo:assessInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(AssessInfo assessInfo, RedirectAttributes redirectAttributes) {
		assessInfoService.delete(assessInfo);
		addMessage(redirectAttributes, "删除案件评价成功");
		return "redirect:"+Global.getAdminPath()+"/assessinfo/assessInfo/?repage";
	}

}