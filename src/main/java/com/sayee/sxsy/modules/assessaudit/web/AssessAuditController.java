/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessaudit.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
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
import com.sayee.sxsy.modules.assessaudit.entity.AssessAudit;
import com.sayee.sxsy.modules.assessaudit.service.AssessAuditService;

import java.util.List;
import java.util.Map;

/**
 * 评估鉴定审批Controller
 * @author lyt
 * @version 2019-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/assessaudit/assessAudit")
public class AssessAuditController extends BaseController {

	@Autowired
	private AssessAuditService assessAuditService;



	@ModelAttribute
	public AssessAudit get(@RequestParam(required=false) String id) {
		AssessAudit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = assessAuditService.get(id);
		}
		if (entity == null){
			entity = new AssessAudit();
		}
		return entity;
	}
	
	@RequiresPermissions("assessaudit:assessAudit:view")
	@RequestMapping(value = {"list", ""})
	public String list(AssessAudit assessAudit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AssessAudit> page = assessAuditService.findPage(new Page<AssessAudit>(request, response), assessAudit); 
		model.addAttribute("page", page);
		return "modules/assessaudit/assessAuditList";
	}

	@RequiresPermissions("assessaudit:assessAudit:view")
	@RequestMapping(value = "form")
	public String form(AssessAudit assessAudit, Model model) {
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(assessAudit.getAssessAuditId());
		for(Map<String,Object> map :filePath){
			if("12".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}else if("13".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}
		}
		model.addAttribute("assessAudit", assessAudit);
		return "modules/assessaudit/assessAuditForm";
	}

	@RequiresPermissions("assessaudit:assessAudit:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,AssessAudit assessAudit, Model model, RedirectAttributes redirectAttributes) {

		try{
			assessAuditService.save(assessAudit);
			assessAuditService.savefj(request,assessAudit);
			if("yes".equals(assessAudit.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes,"流程已启动，流程ID："+assessAudit.getComplaintMain().getProcInsId());
			}else{
				addMessage(redirectAttributes,"保存评估审批成功");
			}
		}catch (Exception e){
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes,"系统内部错误！");
		}
//		if (!beanValidator(model, assessAudit)){
//			return form(assessAudit, model);
//		}
//		assessAuditService.save(assessAudit);
//		addMessage(redirectAttributes, "保存评估鉴定审批成功");
		return "redirect:"+Global.getAdminPath()+"/assessaudit/assessAudit/?repage";
	}
	
	@RequiresPermissions("assessaudit:assessAudit:edit")
	@RequestMapping(value = "delete")
	public String delete(AssessAudit assessAudit, RedirectAttributes redirectAttributes) {
		assessAuditService.delete(assessAudit);
		addMessage(redirectAttributes, "删除评估鉴定审批成功");
		return "redirect:"+Global.getAdminPath()+"/assessaudit/assessAudit/?repage";
	}

}