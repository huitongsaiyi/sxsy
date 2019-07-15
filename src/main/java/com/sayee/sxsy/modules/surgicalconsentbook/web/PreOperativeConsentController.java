/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.surgicalconsentbook.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.modules.surgicalconsentbook.dao.PreOperativeConsentDao;
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
import com.sayee.sxsy.modules.surgicalconsentbook.entity.PreOperativeConsent;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;

import java.util.List;
import java.util.Map;

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
		List<Map<String, Object>>  filePath = FileBaseUtils.getFilePath(preOperativeConsent.getId());
		for (Map<String, Object> map :filePath){

			if ("1".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("2".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}


		}
        //手术同意书编号
		List<PreOperativeConsent> list=preOperativeConsentService.findList(preOperativeConsent);
		if(list.size()==0) {
			preOperativeConsent.setSurgicalConsentId("1000001");

		}else{
			int max=0;
			for(int i=0; i<list.size(); i++){
				String surgicalConsentId =list.get(i).getSurgicalConsentId();
				int a=Integer.valueOf(surgicalConsentId);
				if(a>max){
					max = a;
				}
			}
			int b=max+1;
			preOperativeConsent.setSurgicalConsentId(String.valueOf(b));
		}


		model.addAttribute("preOperativeConsent", preOperativeConsent);
		return "modules/surgicalconsentbook/preOperativeConsentForm";
	}

	@RequiresPermissions("surgicalconsentbook:preOperativeConsent:edit")
	@RequestMapping(value = "save")
	public String save(PreOperativeConsent preOperativeConsent, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (!beanValidator(model, preOperativeConsent)){
			return form(preOperativeConsent, model,request);

		}

			preOperativeConsentService.save(preOperativeConsent,request);

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