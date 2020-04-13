/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.insuranceslip.service;

import java.util.ArrayList;
import java.util.List;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaintdetail.entity.ComplaintMainDetail;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.DictUtils;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.insuranceslip.entity.InsuranceSlip;
import com.sayee.sxsy.modules.insuranceslip.dao.InsuranceSlipDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 投保单Service
 * @author yang
 * @version 2020-03-29
 */
@Service
@Transactional(readOnly = true)
public class InsuranceSlipService extends CrudService<InsuranceSlipDao, InsuranceSlip> {

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;

	public InsuranceSlip get(String id) {
		return super.get(id);
	}
	
	public List<InsuranceSlip> findList(InsuranceSlip insuranceSlip) {
		return super.findList(insuranceSlip);
	}
	
	public Page<InsuranceSlip> findPage(Page<InsuranceSlip> page, InsuranceSlip insuranceSlip) {
		String officeType = UserUtils.getUser().getCompany().getOfficeType();//查看当前人 属于保险公司下面的角色
		//如果当前人员角色 是 医调委主任 则看全部数据
		User u = UserUtils.getUser();
        List<String> aa = ObjectUtils.convert(UserUtils.getRoleList().toArray(), "enname", true);
        if ("1".equals(officeType)){
			if (aa.contains("yitiaoweizhuren") || aa.contains("quanshengtiaojiebuzhuren")){//韩主任 医调委主任 看全部数据  before commission
                    if (!"山西".equals(u.getCompany().getName())){
                        insuranceSlip.setArea(u.getCompany().getArea().getId());
                    }
			}else {
				insuranceSlip.setArea("");
			}
		}else if ("2".equals(officeType)){
		    //医院只看自己的 投保单  根据状态 进行修改按钮的隐藏和显示
            insuranceSlip.setPolicyHolder(u.getCompany().getId());
		}else {
		    //保险公司进来 应看到什么

		}

		return super.findPage(page, insuranceSlip);
	}
	
	@Transactional(readOnly = false)
	public void save(InsuranceSlip insuranceSlip,HttpServletRequest request) {
		if (StringUtils.isBlank(insuranceSlip.getInsurancePolicyId())){
			insuranceSlip.preInsert();
			insuranceSlip.setInsurancePolicyId(insuranceSlip.getId());

			insuranceSlip.setReject(StringUtils.isBlank(insuranceSlip.getReject()) ? "":insuranceSlip.getReject()); ;
			insuranceSlip.setSickbedNumber(StringUtils.isBlank(insuranceSlip.getSickbedNumber()) ? "0":insuranceSlip.getSickbedNumber()); ;
			insuranceSlip.setPeopleNumber(StringUtils.isBlank(insuranceSlip.getPeopleNumber()) ? "0":insuranceSlip.getPeopleNumber()); ;
			insuranceSlip.setMedicalStaffNumber(StringUtils.isBlank(insuranceSlip.getMedicalStaffNumber()) ? "0":insuranceSlip.getMedicalStaffNumber()); ;
			insuranceSlip.setEveryoneQuota(StringUtils.isBlank(insuranceSlip.getEveryoneQuota()) ? "0":insuranceSlip.getEveryoneQuota()); ;
			insuranceSlip.setSpiritQuota(StringUtils.isBlank(insuranceSlip.getSpiritQuota()) ? "0":insuranceSlip.getSpiritQuota()); ;
			insuranceSlip.setEverytimeQuota(StringUtils.isBlank(insuranceSlip.getEverytimeQuota()) ? "0":insuranceSlip.getEverytimeQuota());
			insuranceSlip.setAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAccumulatedQuota()) ? "0":insuranceSlip.getAccumulatedQuota()); ;
			insuranceSlip.setLawEverytimeQuota(StringUtils.isBlank(insuranceSlip.getLawEverytimeQuota()) ? "0":insuranceSlip.getLawEverytimeQuota()); ;
			insuranceSlip.setLawAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAccumulatedQuota()) ? "0":insuranceSlip.getAccumulatedQuota()); ;
			insuranceSlip.setAppendEverytimeQuota(StringUtils.isBlank(insuranceSlip.getAppendEverytimeQuota()) ? "0":insuranceSlip.getAppendEverytimeQuota()); ;
			insuranceSlip.setAppendAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAppendAccumulatedQuota()) ? "0":insuranceSlip.getAppendAccumulatedQuota()); ;
			insuranceSlip.setBedPremium(StringUtils.isBlank(insuranceSlip.getBedPremium()) ? "0":insuranceSlip.getBedPremium()); ;
			insuranceSlip.setAllMedicalNumber(StringUtils.isBlank(insuranceSlip.getAllMedicalNumber()) ? "0":insuranceSlip.getAllMedicalNumber()); ;
			insuranceSlip.setAllEveryonePremium(StringUtils.isBlank(insuranceSlip.getAllEveryonePremium()) ? "0":insuranceSlip.getAllEveryonePremium()); ;
			insuranceSlip.setAllPremiumTotal(StringUtils.isBlank(insuranceSlip.getAllPremiumTotal()) ? "0":insuranceSlip.getAllPremiumTotal()); ;
			insuranceSlip.setSelEveryonePremium(StringUtils.isBlank(insuranceSlip.getSelEveryonePremium()) ? "0":insuranceSlip.getSelEveryonePremium()); ;
			insuranceSlip.setSelClinicOperationNumber(StringUtils.isBlank(insuranceSlip.getSelClinicOperationNumber()) ? "0":insuranceSlip.getSelClinicOperationNumber()); ;
			insuranceSlip.setSelClinicNotoperationNumber(StringUtils.isBlank(insuranceSlip.getSelClinicNotoperationNumber()) ? "0":insuranceSlip.getSelClinicNotoperationNumber()); ;
			insuranceSlip.setSelMedicalLaboratoryNumber(StringUtils.isBlank(insuranceSlip.getSelMedicalLaboratoryNumber()) ? "0":insuranceSlip.getSelMedicalLaboratoryNumber()); ;
			insuranceSlip.setSelPremiumTotal(StringUtils.isBlank(insuranceSlip.getSelPremiumTotal()) ? "0":insuranceSlip.getSelPremiumTotal()); ;
			insuranceSlip.setBasicPremiumTotal(StringUtils.isBlank(insuranceSlip.getBasicPremiumTotal()) ? "0":insuranceSlip.getBasicPremiumTotal()); ;
			insuranceSlip.setAddReduce(StringUtils.isBlank(insuranceSlip.getAddReduce()) ? "0":insuranceSlip.getAddReduce()); ;
			insuranceSlip.setEnchanceDeduction(StringUtils.isBlank(insuranceSlip.getEnchanceDeduction()) ? "0":insuranceSlip.getEnchanceDeduction()); ;
			insuranceSlip.setRiskFloat(StringUtils.isBlank(insuranceSlip.getRiskFloat()) ? "0":insuranceSlip.getRiskFloat()); ;
			insuranceSlip.setAsleftPremium(StringUtils.isBlank(insuranceSlip.getAsleftPremium()) ? "0":insuranceSlip.getAsleftPremium()); ;
			insuranceSlip.setAddittionPremium(StringUtils.isBlank(insuranceSlip.getAddittionPremium()) ? "0":insuranceSlip.getAddittionPremium()); ;
			insuranceSlip.setOddicialReceiptsPermium(StringUtils.isBlank(insuranceSlip.getOddicialReceiptsPermium())? "0":insuranceSlip.getOddicialReceiptsPermium()); ;
			insuranceSlip.setInsuranceDate(StringUtils.isBlank(insuranceSlip.getInsuranceDate()) ? "0":insuranceSlip.getInsuranceDate());
			insuranceSlip.setRetroactiveDate(StringUtils.isBlank(insuranceSlip.getRetroactiveDate()) ? "0":insuranceSlip.getRetroactiveDate());
			insuranceSlip.setMedicalPremium(StringUtils.isBlank(insuranceSlip.getMedicalPremium()) ? "0":insuranceSlip.getMedicalPremium());
            //附加险
			insuranceSlip.setAccidentInsuranceOne(StringUtils.isBlank(insuranceSlip.getAccidentInsuranceOne()) ? "0":insuranceSlip.getAccidentInsuranceOne()); ;
			insuranceSlip.setAccidentInsuranceTwo(StringUtils.isBlank(insuranceSlip.getAccidentInsuranceTwo()) ? "0":insuranceSlip.getAccidentInsuranceTwo()); ;
			insuranceSlip.setAccidentMedicalOne(StringUtils.isBlank(insuranceSlip.getAccidentMedicalOne()) ? "0":insuranceSlip.getAccidentMedicalOne()); ;
			insuranceSlip.setAccidentMedicalTwo(StringUtils.isBlank(insuranceSlip.getAccidentMedicalTwo()) ? "0":insuranceSlip.getAccidentMedicalTwo()); ;
			insuranceSlip.setContagionOneQuota(StringUtils.isBlank(insuranceSlip.getContagionOneQuota()) ? "0":insuranceSlip.getContagionOneQuota()); ;
			insuranceSlip.setContagionYearQuota(StringUtils.isBlank(insuranceSlip.getContagionYearQuota()) ? "0":insuranceSlip.getContagionYearQuota()); ;
			insuranceSlip.setOuoutsourcing(StringUtils.isBlank(insuranceSlip.getOuoutsourcing()) ? "0":insuranceSlip.getOuoutsourcing()); ;
			insuranceSlip.setPremiumFour(StringUtils.isBlank(insuranceSlip.getPremiumFour()) ? "0":insuranceSlip.getPremiumFour()); ;
			insuranceSlip.setAccidentPremium(StringUtils.isBlank(insuranceSlip.getAccidentPremium()) ? "0":insuranceSlip.getAccidentPremium());
			insuranceSlip.setContagionPremium(StringUtils.isBlank(insuranceSlip.getContagionPremium()) ? "0":insuranceSlip.getContagionPremium());


			//被保险人
            insuranceSlip.setTheInsured(insuranceSlip.getPolicyHolder());
//            insuranceSlip.setMold(DictUtils.getDictValue(insuranceSlip.getMold(),"category","")); //类型
            insuranceSlip.setGrade(DictUtils.getDictValue(insuranceSlip.getGrade(),"hospital_grade","")); //医疗机构等级：
            insuranceSlip.setCategory(DictUtils.getDictValue(insuranceSlip.getCategory(),"sys_office_grade","")); //医院类别

			System.out.println(insuranceSlip.getMold());
//			//保存附件
			this.savefj(request,insuranceSlip);
			dao.insert(insuranceSlip);
		}else {
			insuranceSlip.preUpdate();

			insuranceSlip.setReject(StringUtils.isBlank(insuranceSlip.getReject()) ? "":insuranceSlip.getReject()); ;
			insuranceSlip.setSickbedNumber(StringUtils.isBlank(insuranceSlip.getSickbedNumber()) ? "0":insuranceSlip.getSickbedNumber()); ;
			insuranceSlip.setPeopleNumber(StringUtils.isBlank(insuranceSlip.getPeopleNumber()) ? "0":insuranceSlip.getPeopleNumber()); ;
			insuranceSlip.setMedicalStaffNumber(StringUtils.isBlank(insuranceSlip.getMedicalStaffNumber()) ? "0":insuranceSlip.getMedicalStaffNumber()); ;
			insuranceSlip.setEveryoneQuota(StringUtils.isBlank(insuranceSlip.getEveryoneQuota()) ? "0":insuranceSlip.getEveryoneQuota()); ;
			insuranceSlip.setSpiritQuota(StringUtils.isBlank(insuranceSlip.getSpiritQuota()) ? "0":insuranceSlip.getSpiritQuota()); ;
			insuranceSlip.setEverytimeQuota(StringUtils.isBlank(insuranceSlip.getEverytimeQuota()) ? "0":insuranceSlip.getEverytimeQuota());
			insuranceSlip.setAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAccumulatedQuota()) ? "0":insuranceSlip.getAccumulatedQuota()); ;
			insuranceSlip.setLawEverytimeQuota(StringUtils.isBlank(insuranceSlip.getLawEverytimeQuota()) ? "0":insuranceSlip.getLawEverytimeQuota()); ;
			insuranceSlip.setLawAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAccumulatedQuota()) ? "0":insuranceSlip.getAccumulatedQuota()); ;
			insuranceSlip.setAppendEverytimeQuota(StringUtils.isBlank(insuranceSlip.getAppendEverytimeQuota()) ? "0":insuranceSlip.getAppendEverytimeQuota()); ;
			insuranceSlip.setAppendAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAppendAccumulatedQuota()) ? "0":insuranceSlip.getAppendAccumulatedQuota()); ;
			insuranceSlip.setBedPremium(StringUtils.isBlank(insuranceSlip.getBedPremium()) ? "0":insuranceSlip.getBedPremium()); ;
			insuranceSlip.setAllMedicalNumber(StringUtils.isBlank(insuranceSlip.getAllMedicalNumber()) ? "0":insuranceSlip.getAllMedicalNumber()); ;
			insuranceSlip.setAllEveryonePremium(StringUtils.isBlank(insuranceSlip.getAllEveryonePremium()) ? "0":insuranceSlip.getAllEveryonePremium()); ;
			insuranceSlip.setAllPremiumTotal(StringUtils.isBlank(insuranceSlip.getAllPremiumTotal()) ? "0":insuranceSlip.getAllPremiumTotal()); ;
			insuranceSlip.setSelEveryonePremium(StringUtils.isBlank(insuranceSlip.getSelEveryonePremium()) ? "0":insuranceSlip.getSelEveryonePremium()); ;
			insuranceSlip.setSelClinicOperationNumber(StringUtils.isBlank(insuranceSlip.getSelClinicOperationNumber()) ? "0":insuranceSlip.getSelClinicOperationNumber()); ;
			insuranceSlip.setSelClinicNotoperationNumber(StringUtils.isBlank(insuranceSlip.getSelClinicNotoperationNumber()) ? "0":insuranceSlip.getSelClinicNotoperationNumber()); ;
			insuranceSlip.setSelMedicalLaboratoryNumber(StringUtils.isBlank(insuranceSlip.getSelMedicalLaboratoryNumber()) ? "0":insuranceSlip.getSelMedicalLaboratoryNumber()); ;
			insuranceSlip.setSelPremiumTotal(StringUtils.isBlank(insuranceSlip.getSelPremiumTotal()) ? "0":insuranceSlip.getSelPremiumTotal()); ;
			insuranceSlip.setBasicPremiumTotal(StringUtils.isBlank(insuranceSlip.getBasicPremiumTotal()) ? "0":insuranceSlip.getBasicPremiumTotal()); ;
			insuranceSlip.setAddReduce(StringUtils.isBlank(insuranceSlip.getAddReduce()) ? "0":insuranceSlip.getAddReduce()); ;
			insuranceSlip.setEnchanceDeduction(StringUtils.isBlank(insuranceSlip.getEnchanceDeduction()) ? "0":insuranceSlip.getEnchanceDeduction()); ;
			insuranceSlip.setRiskFloat(StringUtils.isBlank(insuranceSlip.getRiskFloat()) ? "0":insuranceSlip.getRiskFloat()); ;
			insuranceSlip.setAsleftPremium(StringUtils.isBlank(insuranceSlip.getAsleftPremium()) ? "0":insuranceSlip.getAsleftPremium()); ;
			insuranceSlip.setAddittionPremium(StringUtils.isBlank(insuranceSlip.getAddittionPremium()) ? "0":insuranceSlip.getAddittionPremium()); ;
			insuranceSlip.setOddicialReceiptsPermium(StringUtils.isBlank(insuranceSlip.getOddicialReceiptsPermium())? "0":insuranceSlip.getOddicialReceiptsPermium()); ;
			insuranceSlip.setInsuranceDate(StringUtils.isBlank(insuranceSlip.getInsuranceDate()) ? "0":insuranceSlip.getInsuranceDate()); ;
			insuranceSlip.setRetroactiveDate(StringUtils.isBlank(insuranceSlip.getRetroactiveDate()) ? "0":insuranceSlip.getRetroactiveDate()); ;

            //附加险
            insuranceSlip.setAccidentInsuranceOne(StringUtils.isBlank(insuranceSlip.getAccidentInsuranceOne()) ? "0":insuranceSlip.getAccidentInsuranceOne()); ;
            insuranceSlip.setAccidentInsuranceTwo(StringUtils.isBlank(insuranceSlip.getAccidentInsuranceTwo()) ? "0":insuranceSlip.getAccidentInsuranceTwo()); ;
            insuranceSlip.setAccidentMedicalOne(StringUtils.isBlank(insuranceSlip.getAccidentMedicalOne()) ? "0":insuranceSlip.getAccidentMedicalOne()); ;
            insuranceSlip.setAccidentMedicalTwo(StringUtils.isBlank(insuranceSlip.getAccidentMedicalTwo()) ? "0":insuranceSlip.getAccidentMedicalTwo()); ;
            insuranceSlip.setContagionOneQuota(StringUtils.isBlank(insuranceSlip.getContagionOneQuota()) ? "0":insuranceSlip.getContagionOneQuota()); ;
            insuranceSlip.setContagionYearQuota(StringUtils.isBlank(insuranceSlip.getContagionYearQuota()) ? "0":insuranceSlip.getContagionYearQuota()); ;
            insuranceSlip.setOuoutsourcing(StringUtils.isBlank(insuranceSlip.getOuoutsourcing()) ? "0":insuranceSlip.getOuoutsourcing()); ;
            insuranceSlip.setPremiumFour(StringUtils.isBlank(insuranceSlip.getPremiumFour()) ? "0":insuranceSlip.getPremiumFour()); ;

            //被保险人
            insuranceSlip.setTheInsured(insuranceSlip.getPolicyHolder());
            insuranceSlip.setMold(DictUtils.getDictValue(insuranceSlip.getMold(),"category","")); //类型
            insuranceSlip.setGrade(DictUtils.getDictValue(insuranceSlip.getGrade(),"hospital_grade","")); //医疗机构等级：
            insuranceSlip.setCategory(DictUtils.getDictValue(insuranceSlip.getCategory(),"sys_office_grade","")); //医院类别
//			//保存附件
			this.savefj(request,insuranceSlip);
			dao.update(insuranceSlip);
		}

//		super.save(insuranceSlip);
	}
	
	@Transactional(readOnly = false)
	public void delete(InsuranceSlip insuranceSlip) {
		super.delete(insuranceSlip);
	}

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request, InsuranceSlip insuranceSlip){
		String files1 = request.getParameter("files1");
		String acceId = null;
		String itemId = insuranceSlip.getInsurancePolicyId();
		String fjtype1 = request.getParameter("fjtype1");


		if(StringUtils.isNotBlank(files1)){
			String acceId1=request.getParameter("acceId1");
			if(StringUtils.isNotBlank(acceId1)){
				preOperativeConsentService.updatefj(files1,itemId,fjtype1);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files1,fjtype1);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype1);
		}

	}
	
}