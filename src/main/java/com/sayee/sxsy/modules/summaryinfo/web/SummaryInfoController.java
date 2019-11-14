/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.summaryinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.BaseUtils;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
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
    @Autowired
    private MachineAccountService machineAccountService;
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
		if(null==summaryInfo.getFileNumber()){
			List<SummaryInfo> list = summaryInfoService.findListSummmary(summaryInfo);
			if(list.size()==0){
				String code = BaseUtils.getCode("time", "4", "SUMMARY_INFO", "file_number");
				String num=code.substring(0,4);
				summaryInfo.setFileNumber(num+"-0001");
			}else{
				int max=0;
				for (int i=0;i<list.size();i++) {
					String fileNumber = list.get(i).getFileNumber();
					String n=fileNumber.substring(5,9);
					int n1=Integer.valueOf(n);
					if(n1>max){
						max=n1;
					}
				}
				int n2=max+1;
				String format = String.format("%0" + 4 + "d", n2);
				String code1 = BaseUtils.getCode("time", "4", "SUMMARY_INFO", "file_number");
				String time= code1.substring(0,4);
				String f1 =time+"-"+format;
				summaryInfo.setFileNumber(f1);
			}
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
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			Map<String,Object> map=summaryInfoService.getViewDetail(summaryInfo.getComplaintMainId());
			model.addAttribute("summaryInfo", summaryInfo);
			model.addAttribute("map", map);
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
		try{
			summaryInfoService.save(summaryInfo,request);
			machineAccountService.savetz(summaryInfo.getMachineAccount(), "f", summaryInfo);
			if("yes".equals(summaryInfo.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes, "流程已启动，流程ID：" + summaryInfo.getComplaintMain().getProcInsId());
				return "redirect:"+Global.getAdminPath()+"/summaryinfo/summaryInfo/?repage";
			}else {
				model.addAttribute("message","保存结案总结成功");
				return form(request,this.get(summaryInfo.getSummaryId()), model);
			}

		}catch(Exception e){
			logger.error("启动鉴定评估流程失败：",e);
			addMessage(redirectAttributes,"系统内部错误");
			return "redirect:"+Global.getAdminPath()+"/summaryinfo/summaryInfo/?repage";
		}

	}
	
	@RequiresPermissions("summaryinfo:summaryInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(SummaryInfo summaryInfo, RedirectAttributes redirectAttributes) {
		summaryInfoService.delete(summaryInfo);
		addMessage(redirectAttributes, "删除案件总结成功");
		return "redirect:"+Global.getAdminPath()+"/summaryinfo/summaryInfo/?repage";
	}

}