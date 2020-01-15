package com.sayee.sxsy.api.complain.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.complain.entity.ComplaintApi;
import com.sayee.sxsy.api.complain.entity.WechatUserComplaint;
import com.sayee.sxsy.api.complain.service.WechatUserComplaintService;
import com.sayee.sxsy.api.mediate.entity.Mediate;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    @Autowired
    private WechatUserComplaintService wechatUserComplaintService;
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
    /*添加投诉*/
    @RequestMapping("addcomplaint")
    @ResponseBody
    public R addComplaint(@RequestBody JSONObject jsonObject){
        MediateApiEntity mediateApiEntity=new MediateApiEntity();
        mediateApiEntity.preInsert();
        String areaId=jsonObject.getString("areaId");
        String getUserId;
        String wechatUserId=jsonObject.getString("wechatUserId");
        if(null==mediateApiService.getDistributionUser(areaId)||mediateApiService.getDistributionUser(areaId).isEmpty()){
            getUserId="5387a4ce17b14df2b7756c8714af2ed6";
        }else{
            getUserId=null==mediateApiService.getDistributionUser(areaId)||mediateApiService.getDistributionUser(areaId).isEmpty()?"5387a4ce17b14df2b7756c8714af2ed6":mediateApiService.getDistributionUser(areaId);
        }

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String caseNumber=getCaseMumber();
        String complaintMainId=mediateApiEntity.getId();
        String patientName = jsonObject.getString("patientName");//接收参数：病人姓名
        String patientSex = jsonObject.getString("patientSex");//接收参数：病人性别
        int patientAge = jsonObject.getInteger("patientAge");//接收参数：病人年龄
        //String patientCard = jsonObject.getString("patientCard");//接收参数：病人身份证号
        String patientMobile =null==jsonObject.getString("patientMobile")||jsonObject.getString("patientMobile").isEmpty()?"":jsonObject.getString("patientMobile");//接收参数：病人电话
        String involveDepartment = jsonObject.getString("involveDepartmentId");//接收参数：涉及科室id
        String involveDepartmentName = jsonObject.getString("involveDepartmentName");//接收参数：涉及科室名称
        //String involveEmployee = jsonObject.getString("involveEmployee");//接收参数：涉及人员id
        //String procInsId = jsonObject.getString("procInsId");//接收参数：
        String involveHospitalId = jsonObject.getString("involveHospitalId");//接收参数：涉及医院id
        OrganizationApiEntity organizationApiEntity = organizationApiService.getOrganizationById(involveHospitalId);
        String hospitalLevel="";
        String hospitalGrade="";
        if(null!=organizationApiEntity) {
             hospitalLevel = organizationApiEntity.getGrade();
             hospitalGrade = organizationApiEntity.getHospitalGrade();
        }
        String visitorName = jsonObject.getString("visitorName");//接收参数：来访者姓名
        String visitorMobile = jsonObject.getString("visitorMobile");//接收参数：来访者电话
        String patientRelation = jsonObject.getString("patientRelation");//接收参数：与病人关系
        String treatmentProcess=jsonObject.getString("treatmentProcess");//治疗经过
        String fault=jsonObject.getString("fault");//存在过错

        String sumaryOfDisputes = treatmentProcess+fault;//概要
        //String visitorDate = sdf2.format(new Date());
        String visitorDate = jsonObject.getString("visitorDate");//纠纷发生时间
        String complaintMode = "0";
        String handleWay = "0";//接收参数：处理方式
        String shiftHandle = getDict(involveDepartmentName);//接收参数：拼音
        String isMajor = "0";//接收参数:是否重大
        String appeal = jsonObject.getString("appeal");//接收参数：诉求
        //String receptionEmployee = jsonObject.getString("receptionEmployee");//接收参数：
        String receptionDate = sdf2.format(new Date());//接待日期
        //String nextLink="";//下一环节
        //String nextLinkMan="";//下一环节处理人：
        //String isMediate="";//是否重大
        //String handleResult="";//处理结果
        String handlePass = null==jsonObject.getString("handlePass")||jsonObject.getString("handlePass").isEmpty()?"":jsonObject.getString("hanslePass");//处理过程
        String status = "0";
        String expectedClosure = null==jsonObject.getString("expectedClosure")||jsonObject.getString("expectedClosure").isEmpty()?"":jsonObject.getString("expectedClosure");//接收参数：结案预期
        String closingMethod = null==jsonObject.getString("closingMethod")||jsonObject.getString("closingMethod").isEmpty()?"":jsonObject.getString("closingMethod");//接收参数：结案方式
        //float amountInvoled="";涉案金额
        String complaintType = null==jsonObject.getString("complaintType")||jsonObject.getString("complaintType").isEmpty()?"":jsonObject.getString("complaintType");//接收参数：投诉类别
        //mediateApiEntity.preInsert();
        mediateApiEntity.setComplaintMainId(complaintMainId);
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
        mediateApiEntity.setCreateUser(getUserId);
        mediateApiEntity.setSource("0");
        mediateApiEntity.setCreateDate(new Date());
        mediateApiEntity.setUpdateDate(new Date());
        //投诉表信息
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
        mediateInfoApiEntity.setCreateUser(getUserId);
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
        WechatUserComplaint wechatUserComplaint=new WechatUserComplaint();
        wechatUserComplaint.setWechatUserId(wechatUserId);
        wechatUserComplaint.setComplaintMainId(complaintMainId);
        if(mediateApiService.saveMediate(mediateApiEntity)>0){
            if(mediateInfoApiService.saveMediate(mediateInfoApiEntity)>0){
                wechatUserComplaintService.save(wechatUserComplaint);
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
    /*获取用户投诉列表*/
    @RequestMapping("getcomplaintlist")
    @ResponseBody
    public R getComplaintList(@RequestBody JSONObject jsonObject){
        String wechatUserId=jsonObject.getString("wechatUserId");
        List<ComplaintApi> complaintApiList=mediateApiService.getComplaintList(wechatUserId);
        List<ComplaintApi> dataList=new ArrayList<>();
        for(int i=0;i<complaintApiList.size();i++){
            if(complaintApiList.get(i).getHandleWay().equals("2")){
                //dataList.add(complaintApiList.get(i));
            }else{
                dataList.add(complaintApiList.get(i));
            }
        }
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",dataList);
        return r;
    }
    /*获取医院被投诉列表*/
    @RequestMapping("complaintlistforhos")
    @ResponseBody
    public R complaintListForHos(@RequestBody JSONObject jsonObject){
        String wechatUserId=jsonObject.getString("wechatUserId");
        List<ComplaintApi> complaintApiList=mediateApiService.complaintListForHos(wechatUserId);
        List<ComplaintApi> dataList=new ArrayList<>();
        for(int i=0;i<complaintApiList.size();i++){
            if(complaintApiList.get(i).getHandleWay().equals("2")||complaintApiList.get(i).getHandleWay().equals(3)||complaintApiList.get(i).getHandleWay().equals(4)){
                //complaintApiList.remove(i);
            }else{
                dataList.add(complaintApiList.get(i));
            }
        }
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",dataList);
        return r;
    }
    /*获取医院被投诉转办处理列表*/
    @RequestMapping("complaintlisttoothersforhos")
    @ResponseBody
    public R complaintListToOthersForHos(@RequestBody JSONObject jsonObject){
        String wechatUserId=jsonObject.getString("wechatUserId");
        List<ComplaintApi> complaintApiList=mediateApiService.complaintListForHos(wechatUserId);
        List<ComplaintApi> dataList=new ArrayList<>();
        for(int i=0;i<complaintApiList.size();i++){
            if(complaintApiList.get(i).getHandleWay().equals("0")||complaintApiList.get(i).getHandleWay().equals(1)||complaintApiList.get(i).getHandleWay().equals(2)){
                //complaintApiList.remove(i);
            }else{
                dataList.add(complaintApiList.get(i));
            }
        }
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",dataList);
        return r;
    }
    /*获取用户投诉转医调委列表*/
    @RequestMapping("getcomplainttoytwlist")
    @ResponseBody
    public R getComplaintToytwList(@RequestBody JSONObject jsonObject){
        String wechatUserId=jsonObject.getString("wechatUserId");
        List<Mediate> complaintToytwList=mediateApiService.getComplaintToytwList(wechatUserId);
        for(int i=0;i<complaintToytwList.size();i++){
            String actName=complaintToytwList.get(i).getActName();
            String complaintMainId=complaintToytwList.get(i).getComplaintMainId();
            complaintToytwList.get(i).setIsAssess(mediateApiService.getIsAssess(complaintMainId));
            int actNum=0;
            switch (actName) {
                case "报案登记":
                    actNum=1;
                    complaintToytwList.get(i).setActNum(actNum);
                    break;
                case "审核受理":
                    actNum=2;
                    complaintToytwList.get(i).setActNum(actNum);
                    break;
                case "调查取证":
                    actNum=3;
                    complaintToytwList.get(i).setActNum(actNum);
                    break;
                case "质证调解":
                    actNum=4;
                    complaintToytwList.get(i).setActNum(actNum);
                    break;
                case "评估鉴定":
                    actNum=5;
                    complaintToytwList.get(i).setActNum(actNum);
                    break;
                case "达成调解":
                    actNum=6;
                    complaintToytwList.get(i).setActNum(actNum);
                    break;
                case "签署协议":
                    actNum=7;
                    complaintToytwList.get(i).setActNum(actNum);
                    break;
                case "履行协议":
                    actNum=8;
                    complaintToytwList.get(i).setActNum(actNum);
                    break;
                    default:
                    complaintToytwList.get(i).setActNum(actNum);
                    break;
            }
        }
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",complaintToytwList);
        return r;
    }
    /*获取案件编号*/
    private String getCaseMumber(){
        String caseNumber= mediateApiService.getCaseNumber();
        return caseNumber;
    }

    private String getDict(String involveHospitalName){
        return officeApiService.getDict(involveHospitalName);
    }

}
