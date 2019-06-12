/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.mediate.web;

import javax.persistence.Id;
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
import com.sayee.sxsy.modules.mediate.entity.MediateEvidence;
import com.sayee.sxsy.modules.mediate.service.MediateEvidenceService;

/**
 * 质证调解Controller
 * @author lyt
 * @version 2019-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/mediate/mediateEvidence")
public class MediateEvidenceController extends BaseController {


	@Autowired
	private MediateEvidenceService mediateEvidenceService;
	
	@ModelAttribute
	public MediateEvidence get(@RequestParam(required=false) String id) {
		MediateEvidence entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mediateEvidenceService.get(id);
		}
		if (entity == null){
			entity = new MediateEvidence();
		}
		return entity;
	}
	
	@RequiresPermissions("mediate:mediateEvidence:view")
	@RequestMapping(value = {"list", ""})
	public String list(MediateEvidence mediateEvidence, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MediateEvidence> page = mediateEvidenceService.findPage(new Page<MediateEvidence>(request, response), mediateEvidence); 
		model.addAttribute("page", page);
		return "modules/mediate/mediateEvidenceList";
	}

	@RequiresPermissions("mediate:mediateEvidence:view")
	@RequestMapping(value = "form")
	public String form(MediateEvidence mediateEvidence, Model model) {
		model.addAttribute("mediateEvidence", mediateEvidence);
		return "modules/mediate/mediateEvidenceForm";
	}

	@RequiresPermissions("mediate:mediateEvidence:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,MediateEvidence mediateEvidence, Model model, RedirectAttributes redirectAttributes) {
		try {
			mediateEvidenceService.savefj(request,mediateEvidence);
			mediateEvidenceService.save(mediateEvidence);

			if ("yes".equals(mediateEvidence.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes, "流程已启动，流程ID：" + mediateEvidence.getComplaintMain().getProcInsId());
			}else {
				addMessage(redirectAttributes, "保存报案登记成功");
			}
		} catch (Exception e) {
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
//		if (!beanValidator(model, mediateEvidence)){
//			return form(mediateEvidence, model);
//		}
		mediateEvidenceService.save(mediateEvidence);
		addMessage(redirectAttributes, "保存质证调解成功");
		return "redirect:"+Global.getAdminPath()+"/mediate/mediateEvidence/?repage";
	}
	
	@RequiresPermissions("mediate:mediateEvidence:edit")
	@RequestMapping(value = "delete")
	public String delete(MediateEvidence mediateEvidence, RedirectAttributes redirectAttributes) {
		mediateEvidenceService.delete(mediateEvidence);
		addMessage(redirectAttributes, "删除质证调解成功");
		return "redirect:"+Global.getAdminPath()+"/mediate/mediateEvidence/?repage";
	}

}