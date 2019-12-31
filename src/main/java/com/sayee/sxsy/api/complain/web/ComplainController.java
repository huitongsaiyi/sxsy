package com.sayee.sxsy.api.complain.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.mediate.entity.MediateApiEntity;
import com.sayee.sxsy.api.mediate.entity.MediateInfoApiEntity;
import com.sayee.sxsy.api.mediate.service.MediateApiService;
import com.sayee.sxsy.api.mediate.service.MediateInfoApiService;
import com.sayee.sxsy.api.officeapi.entity.OrganizationApiEntity;
import com.sayee.sxsy.api.officeapi.service.OfficeApiService;
import com.sayee.sxsy.api.officeapi.service.OrganizationApiService;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.complaint.service.ComplaintInfoService;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Description 投诉
 */
@Controller
@RequestMapping("${adminPath}/api")
public class ComplainController {
    @Autowired
    private ComplaintInfoService complaintInfoService;
    @Autowired
    private MediateApiService mediateApiService;
    @Autowired
    private MediateInfoApiService mediateInfoApiService;
    @Autowired
    private OrganizationApiService organizationApiService;
    @Autowired
    private OfficeApiService officeApiService;
    @RequestMapping("complainlist")
    @ResponseBody
    public R complainList(@RequestBody JSONObject jsonObject){
        int pageNo=1;
        if(null!=jsonObject.getInteger("pageNo")) {
            pageNo = jsonObject.getInteger("pageNo");
        }
        int pageSize= 5;
        if(null!=jsonObject.getInteger("pageSize")){
            pageSize=jsonObject.getInteger("pageSize");
        }
        Page<ComplaintMain> page = complaintInfoService.findPage(new Page(pageNo,pageSize), new ComplaintInfo());
        R r=new R();
        r.put("RtnCode",1);
        r.put("RtnMsg","请求成功");
        r.put("RtnData",page);
        return r;
    }
    @RequestMapping("addcomplaint")
    @ResponseBody
    public R addComplaint(@RequestBody JSONObject jsonObject){
        MediateApiEntity mediateApiEntity=new MediateApiEntity();
        mediateApiEntity.preInsert();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String caseNumber=getCaseMumber();
        String complaintMainId=mediateApiEntity.getId();
        String patientName = jsonObject.getString("patientName");//接收参数：病人姓名
        String patientSex = jsonObject.getString("patientSex");//接收参数：病人性别
        int patientAge = jsonObject.getInteger("patientAge");//接收参数：病人年龄
        //String patientCard = jsonObject.getString("patientCard");//接收参数：病人身份证号
        String patientMobile = jsonObject.getString("patientMobile");//接收参数：病人电话
        String involveDepartment = jsonObject.getString("involveDepartmentId");//接收参数：涉及科室id
        String involveDepartmentName = jsonObject.getString("involveDepartmentName");//接收参数：涉及科室名称
        //String involveEmployee = jsonObject.getString("involveEmployee");//接收参数：涉及人员id
        //String procInsId = jsonObject.getString("procInsId");//接收参数：
        String involveHospitalId = jsonObject.getString("involveHospitalId");//接收参数：涉及医院id
        OrganizationApiEntity organizationApiEntity = organizationApiService.getOrganizationById(involveHospitalId);
        String hospitalLevel = organizationApiEntity.getGrade();
        String hospitalGrade = organizationApiEntity.getHospitalGrade();
        String visitorName = jsonObject.getString("visitorName");//接收参数：来访者姓名
        String visitorMobile = jsonObject.getString("visitorMobile");//接收参数：来访者电话
        String patientRelation = jsonObject.getString("patientRelation");//接收参数：与病人关系
        String sumaryOfDisputes = jsonObject.getString("sumaryOfDisputes");//接收参数：概要
        String visitorDate = sdf2.format(new Date());
        String complaintMode = "0";
        String handleWay = "0";//接收参数：处理方式
        String shiftHandle = getDict(involveDepartmentName);//接收参数：拼音
        String isMajor = "0";//接收参数:是否重大
        String appeal = jsonObject.getString("appeal");//接收参数：诉求
        //String receptionEmployee = jsonObject.getString("receptionEmployee");//接收参数：
        String receptionDate = sdf2.format(new Date());//添加日期
        //String nextLink="";//下一环节
        //String nextLinkMan="";//下一环节处理人：
        //String isMediate="";//是否重大
        //String handleResult="";//处理结果
        String handlePass = jsonObject.getString("handlePass");//处理过程
        String status = "0";
        String expectedClosure = jsonObject.getString("expectedClosure");//接收参数：结案预期
        String closingMethod = jsonObject.getString("closingMethod");//接收参数：结案方式
        //float amountInvoled="";涉案金额
        String complaintType = jsonObject.getString("complaintType");//接收参数：投诉类别
        mediateApiEntity.preInsert();
        mediateApiEntity.setComplaintMainId(mediateApiEntity.getId());
        mediateApiEntity.setCaseNumber(caseNumber);
        mediateApiEntity.setPatientName(patientName);
        mediateApiEntity.setPatientSex(patientSex);
        mediateApiEntity.setPatientAge(patientAge);
        //mediateApiEntity.setPATIENT_CARD(patientCard);
        mediateApiEntity.setPatientMobile(patientMobile);
        mediateApiEntity.setInvolveHospital(involveHospitalId);
        mediateApiEntity.setHospitalLevel(hospitalLevel);
        mediateApiEntity.setHospitalGrade(hospitalGrade);
        mediateApiEntity.setInvolveDepartment(involveDepartment);
        //mediateApiEntity.setINVOLVEEMPLOYEE(involveEmployee);
        //mediateApiEntity.setPROCINSID(procInsId);
        mediateApiEntity.setSource("0");
        mediateApiEntity.setCreateDate(new Date());
        mediateApiEntity.setUpdateDate(new Date());
        MediateInfoApiEntity mediateInfoApiEntity=new MediateInfoApiEntity();
        mediateInfoApiEntity.preInsert();
        String complaintId=mediateInfoApiEntity.getId();
        mediateInfoApiEntity.setCaseNumber(caseNumber);
        mediateInfoApiEntity.setComplaintMainId(complaintMainId);
        mediateInfoApiEntity.setVisitorName(visitorName);
        mediateInfoApiEntity.setVisitorMobile(visitorMobile);
        mediateInfoApiEntity.setPatientRelation(patientRelation);
        mediateInfoApiEntity.setPatientName(patientName);
        mediateInfoApiEntity.setPatientSex(patientSex);
        mediateInfoApiEntity.setPatientAge(patientAge);
        mediateInfoApiEntity.setVisitorNumber("1");
        mediateInfoApiEntity.setInvolveHospital(involveHospitalId);
        mediateInfoApiEntity.setInvolveDepartment(involveDepartment);
        mediateInfoApiEntity.setComplaintId(complaintId);
        //mediateInfoApiEntity.setINVOLVEEMPLOYEE(involveEmployee);
        mediateInfoApiEntity.setSummaryOfDisputes(sumaryOfDisputes);
        mediateInfoApiEntity.setVisitorDate(visitorDate);
        mediateInfoApiEntity.setComplaintMode(complaintMode);
        mediateInfoApiEntity.setHandleWay(handleWay);
        mediateInfoApiEntity.setShiftHandle(shiftHandle);
        mediateInfoApiEntity.setIsMajor(isMajor);
        mediateInfoApiEntity.setAppeal(appeal);
        //mediateInfoApiEntity.setRECEPTIONEMPLOYEE(receptionEmployee);
        mediateInfoApiEntity.setReceptionDate(receptionDate);
        mediateInfoApiEntity.setCreateDate(new Date());
        mediateInfoApiEntity.setUpdateDate(new Date());
        //mediateInfoApiEntity.setNEXTLINK(nextLink);
        //mediateInfoApiEntity.setNEXTLINKMAN(nextLinkMan);
        //mediateInfoApiEntity.setISMEDIATE(isMediate);
        //mediateInfoApiEntity.setHANDLERESULT(handleResult);
        mediateInfoApiEntity.setHandlePass(handlePass);
        mediateInfoApiEntity.setStatus(status);
        mediateInfoApiEntity.setExpectedClosure(expectedClosure);
        mediateInfoApiEntity.setClosingMethod(closingMethod);
        mediateInfoApiEntity.setComplaintType(complaintType);
        if(mediateApiService.saveMediate(mediateApiEntity)>0){
            if(mediateInfoApiService.saveMediate(mediateInfoApiEntity)>0){
                R r=new R();
                r.put("RtnCode",0);
                r.put("RtnMsg","success");
                r.put("RtnData","data");
                return r;
            }else{
                R r=new R();
                r.put("RtnCode",1);
                r.put("RtnMsg","error");
                r.put("RtnData","");
                return r;
            }
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","error");
            r.put("RtnData","");
            return r;
        }
    }
    private String getCaseMumber(){
        String caseNumber= mediateApiService.getCaseNumber();
        return caseNumber;
    }
    private String getDict(String involveHospitalName){
        return officeApiService.getDict(involveHospitalName);
    }

}
