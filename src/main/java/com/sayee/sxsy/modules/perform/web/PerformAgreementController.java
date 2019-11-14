/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.perform.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
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
import com.sayee.sxsy.modules.perform.entity.PerformAgreement;
import com.sayee.sxsy.modules.perform.service.PerformAgreementService;

import java.util.List;
import java.util.Map;

/**
 * 履行协议Controller
 * @author lyt
 * @version 2019-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/perform/performAgreement")
public class PerformAgreementController extends BaseController {
	@Autowired
	private MachineAccountService machineAccountService;
	@Autowired
	private PerformAgreementService performAgreementService;
	@Autowired
	private SummaryInfoService summaryInfoService;
	@ModelAttribute
	public PerformAgreement get(@RequestParam(required=false) String id) {
		PerformAgreement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = performAgreementService.get(id);
		}
		if (entity == null){
			entity = new PerformAgreement();
		}
		return entity;
	}
	
	@RequiresPermissions("perform:performAgreement:view")
	@RequestMapping(value = {"list", ""})
	public String list(PerformAgreement performAgreement, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PerformAgreement> page = performAgreementService.findPage(new Page<PerformAgreement>(request, response), performAgreement); 
		model.addAttribute("page", page);
		return "modules/perform/performAgreementList";
	}

	@RequiresPermissions("perform:performAgreement:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request,PerformAgreement performAgreement, Model model) {
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(performAgreement.getPerformAgreementId());
		for(Map<String,Object> map :filePath){
			if("14".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("15".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
		String type = request.getParameter("type");
		if("view".equals(type)){
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			Map<String, Object> map = summaryInfoService.getViewDetail(performAgreement.getComplaintMainId());
			model.addAttribute("map",map);
			model.addAttribute("performAgreement", performAgreement);
			return "modules/perform/performAgreementView";
		}else {
			model.addAttribute("performAgreement", performAgreement);
			return "modules/perform/performAgreementForm";
		}
	}

	@RequiresPermissions("perform:performAgreement:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,PerformAgreement performAgreement, Model model, RedirectAttributes redirectAttributes) {
		try {
			performAgreementService.save(performAgreement);
			machineAccountService.savetz(performAgreement.getMachineAccount(),"per",performAgreement);
			performAgreementService.savefj(request,performAgreement);
			if("yes".equals(performAgreement.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes,"流程已启动，流程ID："+performAgreement.getComplaintMain().getProcInsId());
				return "redirect:"+Global.getAdminPath()+"/perform/performAgreement/?repage";
			}else {
				model.addAttribute("message","保存履行协议成功");
				return form(request,this.get(performAgreement.getPerformAgreementId()), model);
			}
		}catch (Exception e){
			logger.error("启动纠纷调解失败：", e);
			addMessage(redirectAttributes,"系统内部错误！");
			return "redirect:"+Global.getAdminPath()+"/perform/performAgreement/?repage";
		}
//		if (!beanValidator(model, performAgreement)){
//			return form(performAgreement, model);
//		}
//		performAgreementService.save(performAgreement);
//		addMessage(redirectAttributes, "保存履行协议成功");

	}
	
	@RequiresPermissions("perform:performAgreement:edit")
	@RequestMapping(value = "delete")
	public String delete(PerformAgreement performAgreement, RedirectAttributes redirectAttributes) {
		performAgreementService.delete(performAgreement);
		addMessage(redirectAttributes, "删除履行协议成功");
		return "redirect:"+Global.getAdminPath()+"/perform/performAgreement/?repage";
	}

}