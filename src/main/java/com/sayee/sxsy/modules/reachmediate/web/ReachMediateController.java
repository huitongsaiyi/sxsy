/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.reachmediate.web;

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
import com.sayee.sxsy.modules.reachmediate.entity.ReachMediate;
import com.sayee.sxsy.modules.reachmediate.service.ReachMediateService;

/**
 * 达成调解Controller
 * @author lyt
 * @version 2019-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/reachmediate/reachMediate")
public class ReachMediateController extends BaseController {

	@Autowired
	private ReachMediateService reachMediateService;
	
	@ModelAttribute
	public ReachMediate get(@RequestParam(required=false) String id) {
		ReachMediate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reachMediateService.get(id);
		}
		if (entity == null){
			entity = new ReachMediate();
		}
		return entity;
	}
	
	@RequiresPermissions("reachmediate:reachMediate:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReachMediate reachMediate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReachMediate> page = reachMediateService.findPage(new Page<ReachMediate>(request, response), reachMediate); 
		model.addAttribute("page", page);
		return "modules/reachmediate/reachMediateList";
	}

	@RequiresPermissions("reachmediate:reachMediate:view")
	@RequestMapping(value = "form")
	public String form(ReachMediate reachMediate, Model model,HttpServletRequest request) {
		reachMediateService.findfj(reachMediate,model);
		model.addAttribute("reachMediate", reachMediate);
		return "modules/reachmediate/reachMediateForm";
	}

	@RequiresPermissions("reachmediate:reachMediate:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,ReachMediate reachMediate, Model model, RedirectAttributes redirectAttributes) {
		try{
			reachMediateService.save(reachMediate);
			if(!beanValidator(model,reachMediate)){
				return form(reachMediate,model,request);
			}
			reachMediateService.savefj(request,reachMediate);
			if("yes".equals(reachMediate.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes,"流程已启动，流程ID："+reachMediate.getComplaintMain().getProcInsId());
			}else{
				addMessage(redirectAttributes,"保存达成调解成功");
			}
		}catch(Exception e){
			logger.error("启动纠纷调解流程失败:", e);
			addMessage(redirectAttributes,"系统内部错误");
		}
//		if (!beanValidator(model, reachMediate)){
//			return form(reachMediate, model);
//		}
//		reachMediateService.save(reachMediate);
//		addMessage(redirectAttributes, "保存达成调解成功");
		return "redirect:"+Global.getAdminPath()+"/reachmediate/reachMediate/?repage";
	}
	
	@RequiresPermissions("reachmediate:reachMediate:edit")
	@RequestMapping(value = "delete")
	public String delete(ReachMediate reachMediate, RedirectAttributes redirectAttributes) {
		reachMediateService.delete(reachMediate);
		addMessage(redirectAttributes, "删除达成调解成功");
		return "redirect:"+Global.getAdminPath()+"/reachmediate/reachMediate/?repage";
	}

}