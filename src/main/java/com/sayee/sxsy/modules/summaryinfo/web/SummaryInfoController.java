/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.summaryinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sayee.sxsy.modules.summaryinfo.entity.SummaryInfo;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;

import java.util.List;
import java.util.Map;

/**
 * 案件总结Controller
 * @author gbq
 * @version 2019-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/summaryinfo/summaryInfo")
public class SummaryInfoController extends BaseController {

	@Autowired
	private SummaryInfoService summaryInfoService;
	
	@ModelAttribute
	public SummaryInfo get(@RequestParam(required=false) String id) {
		SummaryInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = summaryInfoService.get(id);
		}
		if (entity == null){
			entity = new SummaryInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("summaryinfo:summaryInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(SummaryInfo summaryInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SummaryInfo> page = summaryInfoService.findPage(new Page<SummaryInfo>(request, response), summaryInfo); 
		model.addAttribute("page", page);
		return "modules/summaryinfo/summaryInfoList";
	}

	@RequiresPermissions("summaryinfo:summaryInfo:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request,SummaryInfo summaryInfo, Model model) {
		String type = request.getParameter("type");
		//卷宗编号
		List<SummaryInfo> list = summaryInfoService.findListSummmary(summaryInfo);
		if(list.size() == 0){
			summaryInfo.setFileNumber("1000001");
		}else{
			String fileNumber=list.get(0).getFileNumber();
			int a=Integer.valueOf(fileNumber);
			int b=a+1;
			summaryInfo.setFileNumber(String.valueOf(b));
		}
		//附件
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(summaryInfo.getSummaryId());
		for(Map<String, Object> map:filePath){
			if("25".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("26".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("27".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files3",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId3",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("28".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files4",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId4",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("29".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files5",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId5",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("30".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files6",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId6",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("31".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files7",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId7",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("32".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files8",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId8",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("33".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files9",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId9",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("34".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files10",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId10",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("35".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files11",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId11",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("36".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files12",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId12",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("37".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files13",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId13",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("38".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files14",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId14",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("39".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files15",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId15",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("40".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files16",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId16",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("41".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files17",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId17",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("42".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files18",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId18",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("43".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files19",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId19",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
		if("view".equals(type)){
			model.addAttribute("summaryInfo", summaryInfo);
			return "modules/summaryinfo/summaryInfoView";
		}else {
			model.addAttribute("summaryInfo", summaryInfo);
			return "modules/summaryinfo/summaryInfoForm";
		}
	}

	@RequiresPermissions("summaryinfo:summaryInfo:edit")
	@RequestMapping(value = "save")
	public String save(SummaryInfo summaryInfo, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, summaryInfo)){
			return form(request,summaryInfo, model);
		}
		summaryInfoService.save(summaryInfo,request);
		addMessage(redirectAttributes, "保存案件总结成功");
		return "redirect:"+Global.getAdminPath()+"/summaryinfo/summaryInfo/?repage";
	}
	
	@RequiresPermissions("summaryinfo:summaryInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(SummaryInfo summaryInfo, RedirectAttributes redirectAttributes) {
		summaryInfoService.delete(summaryInfo);
		addMessage(redirectAttributes, "删除案件总结成功");
		return "redirect:"+Global.getAdminPath()+"/summaryinfo/summaryInfo/?repage";
	}

}