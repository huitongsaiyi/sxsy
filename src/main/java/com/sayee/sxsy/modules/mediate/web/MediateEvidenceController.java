/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.mediate.web;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.AjaxHelper;
import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.program.entity.MediateProgram;
import com.sayee.sxsy.modules.record.dao.MediateRecordDao;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import com.sayee.sxsy.test.dao.TestDataChildDao;
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
import com.sayee.sxsy.modules.mediate.entity.MediateEvidence;
import com.sayee.sxsy.modules.mediate.service.MediateEvidenceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Autowired
	SummaryInfoService summaryInfoService;
    @Autowired
    private MachineAccountService machineAccountService;
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
	public String form(MediateEvidence mediateEvidence, Model model,HttpServletRequest request) {
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(mediateEvidence.getMediateEvidenceId());
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
		mediateEvidenceService.clearDomain(mediateEvidence);
		List<MediateProgram> mediateProgramList = mediateEvidence.getMediateProgramList();
		model.addAttribute("proSize",mediateProgramList.size());
		String type = request.getParameter("type");		//接受从页面传回的数据
		if("view".equals(type)){
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			Map<String, Object> map = summaryInfoService.getViewDetail(mediateEvidence.getComplaintMainId());
			model.addAttribute("map",map);
			model.addAttribute("mediateEvidence", mediateEvidence);
			return "modules/mediate/mediateEvidenceView";
		}else{
			model.addAttribute("mediateEvidence", mediateEvidence);
			return "modules/mediate/mediateEvidenceForm";
		}


	}

	@RequiresPermissions("mediate:mediateEvidence:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,MediateEvidence mediateEvidence, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		String export=request.getParameter("export");

		if (StringUtils.isNotBlank(export) && !export.equals("no")){
//			mediateEvidenceService.save(mediateEvidence,request);
			MediateEvidence mediateEvidence1 = mediateEvidenceService.get(mediateEvidence.getMediateEvidenceId());
			String path = mediateEvidenceService.exportWord(mediateEvidence1,export,"false",request,response);
			return path;
		}else {
			if (!beanValidator(model, mediateEvidence)&&"yes".equals(mediateEvidence.getComplaintMain().getAct().getFlag())){
				return form(mediateEvidence,model,request);
			}
			try {
				mediateEvidenceService.save(mediateEvidence,request);
                machineAccountService.savetz(mediateEvidence.getMachineAccount(), "c", mediateEvidence);
				if ("yes".equals(mediateEvidence.getComplaintMain().getAct().getFlag())){
					addMessage(redirectAttributes, "流程已启动，流程ID：" + mediateEvidence.getComplaintMain().getProcInsId());
					return "redirect:"+Global.getAdminPath()+"/mediate/mediateEvidence/?repage";
				}else {
					model.addAttribute("message","保存质证调解成功");
					return form(this.get(mediateEvidence.getMediateEvidenceId()), model,request);
				}
			} catch (Exception e) {
				logger.error("启动纠纷调解流程失败：", e);
				addMessage(redirectAttributes, "系统内部错误！");
				return "redirect:"+Global.getAdminPath()+"/mediate/mediateEvidence/?repage";
			}

		}



//		mediateEvidenceService.save(mediateEvidence);
//		addMessage(redirectAttributes, "保存质证调解成功");
		}
	
	@RequiresPermissions("mediate:mediateEvidence:edit")
	@RequestMapping(value = "delete")
	public String delete(MediateEvidence mediateEvidence, RedirectAttributes redirectAttributes) {
		mediateEvidenceService.delete(mediateEvidence);
		addMessage(redirectAttributes, "删除质证调解成功");
		return "redirect:"+Global.getAdminPath()+"/mediate/mediateEvidence/?repage";
	}

	@RequestMapping(value = "pass")
	public void pass(HttpServletRequest request,HttpServletResponse response) {
		String code="";//1.成功 0失败
		String mediateEvidenceId=request.getParameter("mediateEvidenceId");//前台传过来的状态
		String export=request.getParameter("export");//前台传过来的状态
		String print=request.getParameter("print");//前台传过来的状态
		MediateEvidence mediateEvidence = mediateEvidenceService.get(mediateEvidenceId);
		code=mediateEvidenceService.exportWord(mediateEvidence,export,print,request,response);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("url",code);
		AjaxHelper.responseWrite(request,response,"1","success",map);

	}

}