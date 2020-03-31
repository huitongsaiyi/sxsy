/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.insuranceslip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.AjaxHelper;
import com.sayee.sxsy.modules.sys.entity.Dict;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.utils.DictUtils;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
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
import com.sayee.sxsy.modules.insuranceslip.entity.InsuranceSlip;
import com.sayee.sxsy.modules.insuranceslip.service.InsuranceSlipService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 投保单Controller
 * @author yang
 * @version 2020-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/insuranceslip/insuranceSlip")
public class InsuranceSlipController extends BaseController {

	@Autowired
	private InsuranceSlipService insuranceSlipService;
	
	@ModelAttribute
	public InsuranceSlip get(@RequestParam(required=false) String id) {
		InsuranceSlip entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = insuranceSlipService.get(id);
		}
		if (entity == null){
			entity = new InsuranceSlip();
		}
		return entity;
	}
	
	@RequiresPermissions("insuranceslip:insuranceSlip:view")
	@RequestMapping(value = {"list", ""})
	public String list(InsuranceSlip insuranceSlip, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<InsuranceSlip> page = insuranceSlipService.findPage(new Page<InsuranceSlip>(request, response), insuranceSlip); 
		model.addAttribute("page", page);
		return "modules/insuranceslip/insuranceSlipList";
	}

	@RequiresPermissions("insuranceslip:insuranceSlip:view")
	@RequestMapping(value = "form")
	public String form(InsuranceSlip insuranceSlip, Model model) {
		model.addAttribute("insuranceSlip", insuranceSlip);
		return "modules/insuranceslip/insuranceSlipForm";
	}

	@RequiresPermissions("insuranceslip:insuranceSlip:edit")
	@RequestMapping(value = "save")
	public String save(InsuranceSlip insuranceSlip, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, insuranceSlip)){
			return form(insuranceSlip, model);
		}
		insuranceSlipService.save(insuranceSlip);
		addMessage(redirectAttributes, "保存投保单成功");
		return "redirect:"+Global.getAdminPath()+"/insuranceslip/insuranceSlip/?repage";
	}
	
	@RequiresPermissions("insuranceslip:insuranceSlip:edit")
	@RequestMapping(value = "delete")
	public String delete(InsuranceSlip insuranceSlip, RedirectAttributes redirectAttributes) {
		insuranceSlipService.delete(insuranceSlip);
		addMessage(redirectAttributes, "删除投保单成功");
		return "redirect:"+Global.getAdminPath()+"/insuranceslip/insuranceSlip/?repage";
	}

	//跳转条款页面
	@RequestMapping(value = "clause")
	public String alcuse(){
		return "modules/insuranceslip/clause";
	}
	//跳转产品介绍页面
	@RequestMapping(value = "introduce")
	public String introduce(){
		return "modules/insuranceslip/introduce";
	}

	@RequestMapping(value = "officeInfo")
	public void officeInfo(HttpServletRequest request,HttpServletResponse response) {
		String code="";//1.成功 0失败
		Map<String,Object> map=new HashMap<String,Object>();
		String hospital=request.getParameter("hospital");//前台传过来的状态
		System.out.println(hospital);
		if (StringUtils.isBlank(hospital)){
			map.put("status","0");
			AjaxHelper.responseWrite(request,response,"1","success",map);
			return;
		}
		Office o = UserUtils.getOfficeId(hospital);  //获取医院对象
		if(StringUtils.isNotBlank(o.getHospitalGrade())){
			String label=DictUtils.getDictLabel(o.getHospitalGrade(),"hospital_grade","其他");
			map.put("hospitalGrade",label); //医院等级
		}
		map.put("policyPhone",o.getPhone()); //投保人联系电话
		map.put("emailAddress",o.getEmail()); //投保人：电子邮箱
		map.put("sitePostcode",o.getAddress()); //投保人：通信地址
		map.put("postalCode",o.getZipCode()); //被保人：邮编
		map.put("gradeValue",o.getHospitalGrade()); //医院等级 value
		map.put("sickbedNumber",o.getBeds()); //床位数

		//获取医院等级的value
//		String dictValue = DictUtils.getDictValue(o.getHospitalGrade(), "hospital_grade", "无");
		//累计赔偿限额
		String accumulatedQuota1 = DictUtils.getDictLabel(o.getHospitalGrade(),"accumulated_quota1","");
		String accumulatedQuota = DictUtils.getDictValue(o.getHospitalGrade(),"accumulated_quota","");
		map.put("accumulatedQuota",accumulatedQuota);

		//每次赔偿限额
		String ecerytimeQuota = DictUtils.getDictValue(o.getHospitalGrade(),"ecerytime_quota","");
		map.put("ecerytimeQuota",ecerytimeQuota);

		//每人累计赔偿限额
		String everyoneQuota = DictUtils.getDictValue(o.getHospitalGrade(),"eceryone_quota","");
		map.put("everyoneQuota",everyoneQuota);

		//法律费用 累计限额
		String lawAccumulatedQuota = DictUtils.getDictValue(o.getHospitalGrade(),"law_accumulated_quota","");
		map.put("lawAccumulatedQuota",lawAccumulatedQuota);

		//法律费用 每次限额
		String lawEverytimeQuota = DictUtils.getDictValue(o.getHospitalGrade(),"law_everytime_quota","");
		map.put("lawEverytimeQuota",lawEverytimeQuota);

		//每床位保险费
		String beds1 = DictUtils.getDictValue("500", "beds", "");
		String beds = DictUtils.getDictValue(beds1,"beds","");
		map.put("beds",beds);

		//附加险
//		String addittionRisk = request.getParameter("addittionRisk");
//		String beds2 = o.getBeds();
//
//		String dictLabel = DictUtils.getDictLabel(o.getBeds(), "beds", "无法计算");
//
//		if (addittionRisk.equals("changsuo")){
//
//		}


		System.out.println(map);
		AjaxHelper.responseWrite(request,response,"1","success",map);
	}

}