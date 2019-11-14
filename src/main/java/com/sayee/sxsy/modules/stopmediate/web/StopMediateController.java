/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.stopmediate.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.AjaxHelper;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
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
import com.sayee.sxsy.modules.stopmediate.entity.StopMediate;
import com.sayee.sxsy.modules.stopmediate.service.StopMediateService;

import java.util.HashMap;
import java.util.Map;

/**
 * 终止调解Controller
 * @author lyt
 * @version 2019-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/stopmediate/stopMediate")
public class StopMediateController extends BaseController {

	@Autowired
	private StopMediateService stopMediateService;

	@ModelAttribute
	public StopMediate get(@RequestParam(required=false) String id) {
		StopMediate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = stopMediateService.get(id);
		}
		if (entity == null){
			entity = new StopMediate();
		}
		return entity;
	}
	
	@RequiresPermissions("stopmediate:stopMediate:view")
	@RequestMapping(value = {"list", ""})
	public String list(StopMediate stopMediate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StopMediate> page = stopMediateService.findPage(new Page<StopMediate>(request, response), stopMediate); 
		model.addAttribute("page", page);
		return "modules/stopmediate/stopMediateList";
	}

	@RequiresPermissions("stopmediate:stopMediate:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request, StopMediate stopMediate, Model model) {
		String type = request.getParameter("type");
		String module = request.getParameter("module");
		String taskId = request.getParameter("taskId");
		//获取路径
		String url1=request.getParameter("url1");
		model.addAttribute("url1",url1);
		String url2=request.getParameter("url2");
		model.addAttribute("url2",url2);
		String url3=request.getParameter("url3");
		model.addAttribute("url3",url3);
		String url4=request.getParameter("url4");
		model.addAttribute("url4",url4);
		String url5=request.getParameter("url5");
		model.addAttribute("url5",url5);
		String url6=request.getParameter("url6");
		model.addAttribute("url6",url6);
		String url7=request.getParameter("url7");
		model.addAttribute("url7",url7);
		String url8=request.getParameter("url8");
		model.addAttribute("url8",url8);
		String url9=request.getParameter("url9");
		model.addAttribute("url9",url9);
		String url10=request.getParameter("url10");
		model.addAttribute("url10",url10);
		//对 某个模块 进来的数据进行分析处理后返回
        stopMediate=stopMediateService.handle(stopMediate,module,request);
		if (StringUtils.isNotBlank(taskId)){
			stopMediate.getComplaintMain().getAct().setTaskId(taskId);
		}
		if("view".equals(type)){
            model.addAttribute("stopMediate", stopMediate);
            return "modules/stopmediate/stopMediateView";
        }else {
            model.addAttribute("stopMediate", stopMediate);
            return "modules/stopmediate/stopMediateForm";
		}
	}

	@RequiresPermissions("stopmediate:stopMediate:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request, StopMediate stopMediate, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		String export=request.getParameter("export");
		String flag=request.getParameter("flag");
		if ("yes".equals(flag)){
			stopMediate.getComplaintMain().getAct().setFlag(flag);
		}
		String urlRegistration = request.getParameter("urlRegistration");
		String urlAuditacceptance = request.getParameter("urlAuditacceptance");
		String urlInvestigateEvidence = request.getParameter("urlInvestigateEvidence");
		String urlAssessAppraisal = request.getParameter("urlAssessAppraisal");
		String urlAssessApply = request.getParameter("urlAssessApply");
		String urlAssessAudit = request.getParameter("urlAssessAudit");
		String urlMediateEvidence = request.getParameter("urlMediateEvidence");
		String urlReachMediate = request.getParameter("urlReachMediate");
		String urlSignAgreement = request.getParameter("urlSignAgreement");
		String urlPerformAgreement = request.getParameter("urlPerformAgreement");
		if (export.equals("yes")){
			stopMediateService.exportWord(stopMediate,export,"fasle",request,response);
			return "";
		}else {
			try {
				stopMediateService.save(stopMediate);
				if ("yes".equals(stopMediate.getComplaintMain().getAct().getFlag())){
					addMessage(redirectAttributes, "流程已启动，流程ID：" + stopMediate.getComplaintMain().getProcInsId());
				}else {
					addMessage(redirectAttributes, "保存终止调解成功");
				}
			} catch (Exception e) {
				logger.error("启动纠纷调解流程失败：", e);
				addMessage(redirectAttributes, "系统内部错误！");
			}

			if ("yes".equals(stopMediate.getComplaintMain().getAct().getFlag())){
				return "redirect:"+Global.getAdminPath()+"/summaryinfo/summaryInfo/?repage";
			}else if(StringUtils.isNotBlank(urlRegistration)){
				return "redirect:"+Global.getAdminPath()+urlRegistration;
			}else if(StringUtils.isNotBlank(urlAuditacceptance)){
				return "redirect:"+Global.getAdminPath()+urlAuditacceptance;
			}else if(StringUtils.isNotBlank(urlInvestigateEvidence)){
				return "redirect:"+Global.getAdminPath()+urlInvestigateEvidence;
			}else if(StringUtils.isNotBlank(urlAssessAppraisal)){
				return "redirect:"+Global.getAdminPath()+urlAssessAppraisal;
			}else if(StringUtils.isNotBlank(urlAssessApply)){
				return "redirect:"+Global.getAdminPath()+urlAssessApply;
			}else if(StringUtils.isNotBlank(urlAssessAudit)){
				return "redirect:"+Global.getAdminPath()+urlAssessAudit;
			}else if(StringUtils.isNotBlank(urlMediateEvidence)){
				return "redirect:"+Global.getAdminPath()+urlMediateEvidence;
			}else if(StringUtils.isNotBlank(urlReachMediate)){
				return "redirect:"+Global.getAdminPath()+urlReachMediate;
			}else if(StringUtils.isNotBlank(urlSignAgreement)){
				return "redirect:"+Global.getAdminPath()+urlSignAgreement;
			}else if(StringUtils.isNotBlank(urlPerformAgreement)){
				return "redirect:"+Global.getAdminPath()+urlPerformAgreement;
			}
			else{
				return "redirect:"+Global.getAdminPath()+"/stopmediate/stopMediate/?repage";
			}

		}
//		if (!beanValidator(model, stopMediate)){
//			return form(request, stopMediate, model);
//		}


	}
	
	@RequiresPermissions("stopmediate:stopMediate:edit")
	@RequestMapping(value = "delete")
	public String delete(StopMediate stopMediate, RedirectAttributes redirectAttributes) {
		stopMediateService.delete(stopMediate);
		addMessage(redirectAttributes, "删除终止调解成功");
		return "redirect:"+Global.getAdminPath()+"/stopmediate/stopMediate/?repage";
	}

	@RequestMapping(value = "exportWord")
	public void exportWord(HttpServletRequest request,HttpServletResponse response) {
		String code="";//1.成功 0失败
		Map<String,Object> map=new HashMap<String,Object>();
		String stopMediateId=request.getParameter("stopMediateId");//前台传过来的状态
		if (StringUtils.isBlank(stopMediateId)){
			map.put("status","0");
			AjaxHelper.responseWrite(request,response,"1","success",map);
			return;
		}
		String export=request.getParameter("export");//前台传过来的状态
		String print=request.getParameter("print");//前台传过来的状态
		StopMediate stopMediate=stopMediateService.get(stopMediateId);
		code=stopMediateService.exportWord(stopMediate,export,print,request,response);

		map.put("url",code);
		AjaxHelper.responseWrite(request,response,"1","success",map);

	}
}