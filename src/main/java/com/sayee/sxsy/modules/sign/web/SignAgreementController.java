/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sign.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.BaseUtils;
import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
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
import com.sayee.sxsy.modules.sign.entity.SignAgreement;
import com.sayee.sxsy.modules.sign.service.SignAgreementService;

import java.util.List;
import java.util.Map;

/**
 * 签署协议Controller
 * @author zhangfan
 * @version 2019-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sign/signAgreement")
public class SignAgreementController extends BaseController {

	@Autowired
	private SignAgreementService signAgreementService;
	@Autowired
	SummaryInfoService summaryInfoService;
    @Autowired
    private MachineAccountService machineAccountService;

	@ModelAttribute
	public SignAgreement get(@RequestParam(required=false) String id) {
		SignAgreement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = signAgreementService.get(id);
		}
		if (entity == null){
			entity = new SignAgreement();
		}
		return entity;
	}
	
	@RequiresPermissions("sign:signAgreement:view")
	@RequestMapping(value = {"list", ""})
	public String list(SignAgreement signAgreement, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SignAgreement> page = signAgreementService.findPage(new Page<SignAgreement>(request, response), signAgreement); 
		model.addAttribute("page", page);
		return "modules/sign/signAgreementList";
	}

	@RequiresPermissions("sign:signAgreement:view")
	@RequestMapping(value = "form")
	public String form(SignAgreement signAgreement, Model model,HttpServletRequest request) {
		//协议号
		if(null==signAgreement.getAgreementNumber()){
			List<SignAgreement> numx = signAgreementService.findList(signAgreement);
			if(numx.size()<=1){
				String code = BaseUtils.getCode("time", "3", "PROPOSAL", "proposal_code");
				String c1= code.substring(0, 4);
				signAgreement.setAgreementNumber("晋医人调字["+c1+"]001号");
			}else{
//				String proposalCode = list.get(0).getProposalCode();
				String agreementNumber = numx.get(0).getAgreementNumber();
				String a=BaseUtils.getCode("time","3","SIGN_AGREEMENT","agreement_number");
				String c=a.substring(0,4);
				String d = agreementNumber.substring(11,14);
				int d1=Integer.valueOf(d);
				int d2=d1+1;
				String format = String.format("%0" + 3 + "d", d2);
				String e=agreementNumber.replace(agreementNumber.substring(6,10),c);
				String e1=e.replace(e.substring(11,14),format);
				signAgreement.setAgreementNumber(e1);

			}
		}
		//在修改时 拿到 用逗号分割的数据  进行处理
		List<TypeInfo> tjqk=BaseUtils.getType("3");
		signAgreementService.label(tjqk,signAgreement.getMediation());
		model.addAttribute("tjqk", tjqk);
		List<TypeInfo> xyydsx=BaseUtils.getType("4");
		signAgreementService.label(xyydsx,signAgreement.getAgreedMatter());
		model.addAttribute("xyydsx", xyydsx);
		List<TypeInfo> lxxyfs=BaseUtils.getType("5");
		signAgreementService.label(lxxyfs,signAgreement.getPerformAgreementMode());
		model.addAttribute("lxxyfs", lxxyfs);
		List<TypeInfo> xysm=BaseUtils.getType("6");
		signAgreementService.label(xysm,signAgreement.getAgreementExplain());
		model.addAttribute("xysm", xysm);
		//附件展示
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(signAgreement.getSignAgreementId());
		for (Map<String, Object> map:filePath) {
			if("7".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("8".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("9".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files3",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId3",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("10".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files4",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId4",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
		String type = request.getParameter("type");
		if("view".equals(type)){
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			Map<String, Object> map = summaryInfoService.getViewDetail(signAgreement.getComplaintMainId());
			model.addAttribute("map",map);
			model.addAttribute("signAgreement", signAgreement);
			return "modules/sign/signAgreementView";
		}else{
			model.addAttribute("signAgreement", signAgreement);
			return "modules/sign/signAgreementForm";
		}

	}

	@RequiresPermissions("sign:signAgreement:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,SignAgreement signAgreement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, signAgreement)&&"yes".equals(signAgreement.getComplaintMain().getAct().getFlag())){
			return form(signAgreement, model,request);
		}
		try {
			signAgreementService.save(request,signAgreement);
            machineAccountService.savetz(signAgreement.getMachineAccount(), "e", signAgreement.getSignAgreementId());
			if ("yes".equals(signAgreement.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes, "流程已启动，流程ID：" + signAgreement.getComplaintMain().getProcInsId());
			}else {
				addMessage(redirectAttributes, "保存签署协议成功");
			}
		} catch (Exception e) {
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
		return "redirect:"+Global.getAdminPath()+"/sign/signAgreement/?repage";
	}
	
	@RequiresPermissions("sign:signAgreement:edit")
	@RequestMapping(value = "delete")
	public String delete(SignAgreement signAgreement, RedirectAttributes redirectAttributes) {
		signAgreementService.delete(signAgreement);
		addMessage(redirectAttributes, "删除签署协议成功");
		return "redirect:"+Global.getAdminPath()+"/sign/signAgreement/?repage";
	}

}