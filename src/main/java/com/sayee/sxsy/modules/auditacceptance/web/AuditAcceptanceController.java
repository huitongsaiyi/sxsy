/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.auditacceptance.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import org.apache.commons.collections.MapUtils;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
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
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.auditacceptance.service.AuditAcceptanceService;

import java.util.List;
import java.util.Map;

/**
 * 审核受理Controller
 * @author zhangfan
 * @version 2019-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/auditacceptance/auditAcceptance")
public class AuditAcceptanceController extends BaseController {

	@Autowired
	private AuditAcceptanceService auditAcceptanceService;
    @Autowired
    private MachineAccountService machineAccountService;
	@ModelAttribute
	public AuditAcceptance get(@RequestParam(required=false) String id) {
		AuditAcceptance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = auditAcceptanceService.get(id);
		}
		if (entity == null){
			entity = new AuditAcceptance();
		}
		return entity;
	}
	
	@RequiresPermissions("auditacceptance:auditAcceptance:view")
	@RequestMapping(value = {"list", ""})
	public String list(AuditAcceptance auditAcceptance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AuditAcceptance> page = auditAcceptanceService.findPage(new Page<AuditAcceptance>(request, response), auditAcceptance); 
		model.addAttribute("page", page);
		return "modules/auditacceptance/auditAcceptanceList";
	}

	@RequiresPermissions("auditacceptance:auditAcceptance:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request,AuditAcceptance auditAcceptance, Model model) {
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(auditAcceptance.getAuditAcceptanceId());
		for (Map<String, Object> map:filePath){
			if ("1".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("2".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("3".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files3",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId3",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("4".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files4",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId4",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("5".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files5",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId5",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("6".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files6",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId6",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("7".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files7",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId7",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("8".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files8",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId8",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("9".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files9",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId9",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("10".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files10",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId10",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("11".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files11",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId11",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("12".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files12",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId12",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("13".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files13",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId13",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("14".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files14",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId14",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("15".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files15",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId15",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("16".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files16",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId16",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("17".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files17",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId17",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("18".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files18",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId18",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("19".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files19",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId19",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("20".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files20",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId20",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
		String type = request.getParameter("type");
		if("view".equals(type)) {
			model.addAttribute("auditAcceptance", auditAcceptance);
			return "modules/auditacceptance/auditAcceptanceView";
		}else{
			model.addAttribute("auditAcceptance", auditAcceptance);
			return "modules/auditacceptance/auditAcceptanceForm";
		}
	}

	@RequiresPermissions("auditacceptance:auditAcceptance:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request, AuditAcceptance auditAcceptance, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
        String export=request.getParameter("export");
        	if (!export.equals("no")){
				auditAcceptanceService.exportWord(auditAcceptance,export,request,response);
				return "";
			}else {
				if ("yes".equals(auditAcceptance.getComplaintMain().getAct().getFlag()) &&(!beanValidator(model, auditAcceptance)||!beanValidator(model,auditAcceptance.getMediateApplyInfo())||!beanValidator(model,auditAcceptance.getMediateApplyInfo().getDocMediateApplyInfo()))  ){
					return form(request,auditAcceptance, model);
				}
				try {
                    auditAcceptanceService.save(request, auditAcceptance);
                    machineAccountService.savetz(auditAcceptance.getMachineAccount(), "a", auditAcceptance.getAuditAcceptanceId());
                    if ("yes".equals(auditAcceptance.getComplaintMain().getAct().getFlag())){
                        addMessage(redirectAttributes, "流程已启动，流程ID：" + auditAcceptance.getComplaintMain().getProcInsId());
                    }else {
                        addMessage(redirectAttributes, "保存审核受理成功");
                    }
				} catch (Exception e) {
					logger.error("启动纠纷调解流程失败：", e);
					addMessage(redirectAttributes, "系统内部错误,请联系管理员！");
				}
				return "redirect:"+Global.getAdminPath()+"/auditacceptance/auditAcceptance/?repage";
			}
	}
	
	@RequiresPermissions("auditacceptance:auditAcceptance:edit")
	@RequestMapping(value = "delete")
	public String delete(AuditAcceptance auditAcceptance, RedirectAttributes redirectAttributes) {
		auditAcceptanceService.delete(auditAcceptance);
		addMessage(redirectAttributes, "删除审核受理成功");
		return "redirect:"+Global.getAdminPath()+"/auditacceptance/auditAcceptance/?repage";
	}
//	@RequestMapping(value = "exportWord")
//	public String exportWord(AuditAcceptance auditAcceptance,HttpServletRequest request,HttpServletResponse response) {
//		try {
//			auditAcceptanceService.exportWord(auditAcceptance,request,response);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return "导出成功";
//	}
}