/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessapply.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
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
import com.sayee.sxsy.modules.assessapply.entity.AssessApply;
import com.sayee.sxsy.modules.assessapply.service.AssessApplyService;

/**
 * 评估申请Controller
 * @author zhangfan
 * @version 2019-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/assessapply/assessApply")
public class AssessApplyController extends BaseController {

	@Autowired
	private AssessApplyService assessApplyService;
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@ModelAttribute
	public AssessApply get(@RequestParam(required=false) String id) {
		AssessApply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = assessApplyService.get(id);
		}
		if (entity == null){
			entity = new AssessApply();
		}
		return entity;
	}
	
	@RequiresPermissions("assessapply:assessApply:view")
	@RequestMapping(value = {"list", ""})
	public String list(AssessApply assessApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AssessApply> page = assessApplyService.findPage(new Page<AssessApply>(request, response), assessApply); 
		model.addAttribute("page", page);
		return "modules/assessapply/assessApplyList";
	}

	@RequiresPermissions("assessapply:assessApply:view")
	@RequestMapping(value = "form")
	public String form(AssessApply assessApply, Model model) {
		model.addAttribute("assessApply", assessApply);
		return "modules/assessapply/assessApplyForm";
	}

	@RequiresPermissions("assessapply:assessApply:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,AssessApply assessApply, Model model, RedirectAttributes redirectAttributes) {
		String files = request.getParameter("files");
		try {
			assessApplyService.save(assessApply);
			String acceId1 = IdGen.uuid();
			String itemId1 = assessApply.getAssessApplyId();
			String fjtype1 = request.getParameter("fjtype");
			preOperativeConsentService.save1(acceId1,itemId1,files,fjtype1);
			if ("yes".equals(assessApply.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes, "流程已启动，流程ID：" + assessApply.getComplaintMain().getProcInsId());
			}else {
				addMessage(redirectAttributes, "保存评估申请成功");
			}
		} catch (Exception e) {
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
//		if (!beanValidator(model, assessApply)){
//			return form(assessApply, model);
//		}
//		assessApplyService.save(assessApply);
		return "redirect:"+Global.getAdminPath()+"/assessapply/assessApply/?repage";
	}
	
	@RequiresPermissions("assessapply:assessApply:edit")
	@RequestMapping(value = "delete")
	public String delete(AssessApply assessApply, RedirectAttributes redirectAttributes) {
		assessApplyService.delete(assessApply);
		addMessage(redirectAttributes, "删除评估申请成功");
		return "redirect:"+Global.getAdminPath()+"/assessapply/assessApply/?repage";
	}

}