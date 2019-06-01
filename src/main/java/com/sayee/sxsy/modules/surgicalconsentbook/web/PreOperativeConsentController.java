/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.surgicalconsentbook.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.modules.surgicalconsentbook.dao.PreOperativeConsentDao;
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
import com.sayee.sxsy.modules.surgicalconsentbook.entity.PreOperativeConsent;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;

import java.util.List;

/**
 * 术前同意书见证管理Controller
 * @author gbq
 * @version 2019-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/surgicalconsentbook/preOperativeConsent")
public class PreOperativeConsentController extends BaseController  {

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;

	@ModelAttribute
	public PreOperativeConsent get(@RequestParam(required=false) String id) {
		PreOperativeConsent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = preOperativeConsentService.get(id);
		}
		if (entity == null){
			entity = new PreOperativeConsent();
		}
		return entity;
	}



	@RequiresPermissions("surgicalconsentbook:preOperativeConsent:view")
	@RequestMapping(value = {"list", ""})
	public String list(PreOperativeConsent preOperativeConsent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PreOperativeConsent> page = preOperativeConsentService.findPage(new Page<PreOperativeConsent>(request, response), preOperativeConsent);

		model.addAttribute("page", page);
		return "modules/surgicalconsentbook/preOperativeConsentList";
	}

	@RequiresPermissions("surgicalconsentbook:preOperativeConsent:view")
	@RequestMapping(value = "form")
	public String form(PreOperativeConsent preOperativeConsent, Model model,HttpServletRequest request) {

     //    request.setAttribute("f",request.getParameter("files"));

		List<PreOperativeConsent> list=preOperativeConsentService.findList(preOperativeConsent);

		if(list.size()==0) {

			preOperativeConsent.setSurgicalConsentId("1000001");

//			itemId1=preOperativeConsent.getSurgicalConsentId();
//			acceId1=IdGen.uuid();
		}else{
			String surgicalConsentId =list.get(0).getSurgicalConsentId();
			int a=Integer.valueOf(surgicalConsentId);
			int b=a+1;


			preOperativeConsent.setSurgicalConsentId(String.valueOf(b));
//			itemId=list.get(0).getSurgicalConsentId();
		}


		model.addAttribute("preOperativeConsent", preOperativeConsent);
		return "modules/surgicalconsentbook/preOperativeConsentForm";
	}

	@RequiresPermissions("surgicalconsentbook:preOperativeConsent:edit")
	@RequestMapping(value = "save")
	public String save(PreOperativeConsent preOperativeConsent, Model model, RedirectAttributes redirectAttributes,String acceId,String itemId,String files ,HttpServletRequest request) {

		if (!beanValidator(model, preOperativeConsent)){
			return form(preOperativeConsent, model,request);

		}
		preOperativeConsentService.save(preOperativeConsent);
        String acceId1=IdGen.uuid();
		String itemId1=preOperativeConsent.getId();
		String files1=request.getParameter("files");

		preOperativeConsentService.save1(acceId1,itemId1,files1);
		addMessage(redirectAttributes, "保存术前同意书成功");
		return "redirect:"+Global.getAdminPath()+"/surgicalconsentbook/preOperativeConsent/?repage";
	}

	@RequiresPermissions("surgicalconsentbook:preOperativeConsent:edit")
	@RequestMapping(value = "delete")
	public String delete(PreOperativeConsent preOperativeConsent, RedirectAttributes redirectAttributes) {
		preOperativeConsentService.delete(preOperativeConsent);
		addMessage(redirectAttributes, "删除术前同意书成功");
		return "redirect:"+Global.getAdminPath()+"/surgicalconsentbook/preOperativeConsent/?repage";
	}


}