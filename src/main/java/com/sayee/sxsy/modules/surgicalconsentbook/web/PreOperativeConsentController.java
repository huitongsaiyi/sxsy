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
		List<PreOperativeConsent> list=preOperativeConsentService.findList(preOperativeConsent);
		List<Map<String, Object>>  filePath = FileBaseUtils.getFilePath(preOperativeConsent.getId());
		for (Map<String, Object> map :filePath){

			if ("1".equals(MapUtils.getString(map,"fjtype"))){

				model.addAttribute("files",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}else if("2".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}


		}
		 //

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
	public String save(PreOperativeConsent preOperativeConsent, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (!beanValidator(model, preOperativeConsent)){
			return form(preOperativeConsent, model,request);

		}
		String files1 = request.getParameter("files");
		String files2 = request.getParameter("files1");
		if(preOperativeConsent ==null) {
			preOperativeConsentService.save(preOperativeConsent);
			String acceId1 = IdGen.uuid();
			String itemId1 = preOperativeConsent.getId();
			String fjtype1 = request.getParameter("fjtype1");
			String fjtype2 = request.getParameter("fjtype2");
//request.setAttribute("s",files1);
			String acceId2 = IdGen.uuid();
			preOperativeConsentService.save1(acceId1, itemId1, files1, fjtype1);
			preOperativeConsentService.save1(acceId2, itemId1, files2, fjtype2);
		}else if(preOperativeConsent !=null){
			preOperativeConsentService.save(preOperativeConsent);
			//根据file_path是否存在判断，如果存在,则更新附件表，如果不存在就删除对应的附件表
			if(files1 ==""){
               preOperativeConsentService.delefj(preOperativeConsent.getId(),"1");
			}else if(files1 !=""){
				//更新 附件表  保存主表后有 业务主键 item——id
				preOperativeConsentService.updatefj(files1,preOperativeConsent.getId(),"1");
			}
			if(files2 ==""){
				preOperativeConsentService.delefj(preOperativeConsent.getId(),"2");
			}
           else if(files2 !=""){
				preOperativeConsentService.updatefj(files2,preOperativeConsent.getId(),"2");
			}
		}
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