/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.epidemic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.modules.epidemic.dao.EgressInfoDao;
import com.sayee.sxsy.modules.epidemic.entity.EgressInfo;
import com.sayee.sxsy.modules.epidemic.entity.Statis;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.commons.collections.CollectionUtils;
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
import com.sayee.sxsy.modules.epidemic.entity.EpidemicDaily;
import com.sayee.sxsy.modules.epidemic.service.EpidemicDailyService;

import java.util.ArrayList;
import java.util.List;

/**
 * 疫情日报Controller
 * @author zhangfan
 * @version 2020-02-11
 */
@Controller
@RequestMapping(value = "${adminPath}/epidemic/epidemicDaily")
public class EpidemicDailyController extends BaseController {

	@Autowired
	private EpidemicDailyService epidemicDailyService;

	@ModelAttribute
	public EpidemicDaily get(@RequestParam(required=false) String id) {
		EpidemicDaily entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = epidemicDailyService.get(id);
		}
		if (entity == null){
			entity = new EpidemicDaily();
		}
		return entity;
	}
	
	@RequiresPermissions("epidemic:epidemicDaily:view")
	@RequestMapping(value = {"list", ""})
	public String list(EpidemicDaily epidemicDaily, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EpidemicDaily> page = epidemicDailyService.findPage(new Page<EpidemicDaily>(request, response), epidemicDaily);
		model.addAttribute("page", page);
		return "modules/epidemic/epidemicDailyList";
	}

	@RequiresPermissions("epidemic:epidemicDaily:view")
	@RequestMapping(value = "form")
	public String form(EpidemicDaily epidemicDaily, Model model,HttpServletRequest request) {

		if (CollectionUtils.isEmpty(epidemicDaily.getEgressInfoList())){
			List<EgressInfo> list=new ArrayList<>();
			EgressInfo egressInfo=new EgressInfo();
			egressInfo.setEgressName(UserUtils.getUser().getName());//名称
			egressInfo= epidemicDailyService.getAcquiesce(egressInfo);
			if (egressInfo==null){
				egressInfo=new EgressInfo();
				egressInfo.setEgressSex("1");
				egressInfo.setRelation("1");
			}
			egressInfo.setIsEgress("0");
			list.add(egressInfo);
			epidemicDaily.setEgressInfoList(list);
		}
		model.addAttribute("epidemicDaily", epidemicDaily);
		String type = request.getParameter("type");
		if("view".equals(type)){
			return "modules/epidemic/epidemicDailyView";
		}else{
			return "modules/epidemic/epidemicDailyForm";
		}


	}

	@RequiresPermissions("epidemic:epidemicDaily:edit")
	@RequestMapping(value = "save")
	public String save(EpidemicDaily epidemicDaily, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, epidemicDaily)){
			return form(epidemicDaily, model,request);
		}
		epidemicDailyService.save(epidemicDaily);
		addMessage(redirectAttributes, "保存疫情日报成功");
		return "redirect:"+Global.getAdminPath()+"/epidemic/epidemicDaily/?repage";
	}
	
	@RequiresPermissions("epidemic:epidemicDaily:edit")
	@RequestMapping(value = "delete")
	public String delete(EpidemicDaily epidemicDaily, RedirectAttributes redirectAttributes) {
		epidemicDailyService.delete(epidemicDaily);
		addMessage(redirectAttributes, "删除疫情日报成功");
		return "redirect:"+Global.getAdminPath()+"/epidemic/epidemicDaily/?repage";
	}


	@RequestMapping(value = "statistics")
	public String statistics(EpidemicDaily epidemicDaily, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Statis> page = epidemicDailyService.findStatistics(new Page<Statis>(request, response), epidemicDaily);
		model.addAttribute("page", page);
		return "modules/epidemic/statistics";
	}

}