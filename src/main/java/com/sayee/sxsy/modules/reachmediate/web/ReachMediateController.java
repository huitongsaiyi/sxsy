/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.reachmediate.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.AjaxHelper;
import com.sayee.sxsy.modules.program.entity.MediateProgram;
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
import com.sayee.sxsy.modules.reachmediate.entity.ReachMediate;
import com.sayee.sxsy.modules.reachmediate.service.ReachMediateService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Autowired
	private SummaryInfoService summaryInfoService;
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
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(reachMediate.getReachMediateId());
		for(Map<String, Object> map:filePath){
			if("7".equals(MapUtils.getString(map, "fjtype"))){
				model.addAttribute("files1", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
			if("8".equals(MapUtils.getString(map, "fjtype"))){
				model.addAttribute("files2", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
			if("9".equals(MapUtils.getString(map, "fjtype"))){
				model.addAttribute("files3", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
				model.addAttribute("acceId3",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
			if("10".equals(MapUtils.getString(map, "fjtype"))){
				model.addAttribute("files4", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
				model.addAttribute("acceId4",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
			if("11".equals(MapUtils.getString(map, "fjtype"))){
				model.addAttribute("files5", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
				model.addAttribute("acceId5",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
		//每次 进入调解程序 表 是将 查到的置空
		reachMediateService.clearDomain(reachMediate);
		List<MediateProgram> mediateProgramList = reachMediate.getMediateProgramList();
		model.addAttribute("proSize",mediateProgramList.size());
		String type = request.getParameter("type");
		if("view".equals(type)){
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			Map<String, Object> map = summaryInfoService.getViewDetail(reachMediate.getComplaintMainId());
			model.addAttribute("map",map);
			model.addAttribute("reachMediate", reachMediate);
			return "modules/reachmediate/reachMediateView";
		}else {
			model.addAttribute("reachMediate", reachMediate);
			return "modules/reachmediate/reachMediateForm";
		}
	}

	@RequiresPermissions("reachmediate:reachMediate:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,ReachMediate reachMediate, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		String export=request.getParameter("export");
		if (StringUtils.isNotBlank(export) && !export.equals("no")){
//			reachMediateService.save(reachMediate, request);
			ReachMediate reachMediate1 = reachMediateService.get(reachMediate.getReachMediateId());
			String path = reachMediateService.exportWord(reachMediate1,export,"false",request,response);
			return path;
		}else {
			if (!beanValidator(model, reachMediate) && "yes".equals(reachMediate.getComplaintMain().getAct().getFlag()) || !beanValidator(model, reachMediate.getComplaintMain()) && "yes".equals(reachMediate.getComplaintMain().getAct().getFlag())) {
				return form(reachMediate, model, request);
			}
			try {

				reachMediateService.save(reachMediate, request);
				if ("yes".equals(reachMediate.getComplaintMain().getAct().getFlag())) {
					addMessage(redirectAttributes, "流程已启动，流程ID：" + reachMediate.getComplaintMain().getProcInsId());
					return "redirect:"+Global.getAdminPath()+"/reachmediate/reachMediate/?repage";
				} else {
					model.addAttribute("message","保存达成调解成功");
					return form(this.get(reachMediate.getReachMediateId()), model,request);
				}
			} catch (Exception e) {
				logger.error("启动纠纷调解流程失败:", e);
				addMessage(redirectAttributes, "系统内部错误");
				return "redirect:"+Global.getAdminPath()+"/reachmediate/reachMediate/?repage";
			}
		}

//		reachMediateService.save(reachMediate);
//		addMessage(redirectAttributes, "保存达成调解成功");

	}
	
	@RequiresPermissions("reachmediate:reachMediate:edit")
	@RequestMapping(value = "delete")
	public String delete(ReachMediate reachMediate, RedirectAttributes redirectAttributes) {
		reachMediateService.delete(reachMediate);
		addMessage(redirectAttributes, "删除达成调解成功");
		return "redirect:"+Global.getAdminPath()+"/reachmediate/reachMediate/?repage";
	}

	@RequestMapping(value = "pass")
	public void pass(HttpServletRequest request,HttpServletResponse response) {
		String code="";//1.成功 0失败
		String reachMediateId=request.getParameter("reachMediateId");//前台传过来的状态
		String export=request.getParameter("export");//前台传过来的状态
		String print=request.getParameter("print");//前台传过来的状态
		ReachMediate reachMediate = reachMediateService.get(reachMediateId);
		code=reachMediateService.exportWord(reachMediate,export,print,request,response);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("url",code);
		AjaxHelper.responseWrite(request,response,"1","success",map);
	}

}