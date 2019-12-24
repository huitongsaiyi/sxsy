package com.sayee.sxsy.api.mediate.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.mediate.entity.*;
import com.sayee.sxsy.api.mediate.service.*;
import com.sayee.sxsy.api.officeapi.entity.OrganizationApiEntity;
import com.sayee.sxsy.api.officeapi.service.OfficeApiService;
import com.sayee.sxsy.api.officeapi.service.OrganizationApiService;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.assessappraisal.service.AssessAppraisalService;
import com.sayee.sxsy.modules.auditacceptance.service.AuditAcceptanceService;
import com.sayee.sxsy.modules.complaintdetail.service.ComplaintMainDetailService;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.mediate.service.MediateEvidenceService;
import com.sayee.sxsy.modules.nestigateeividence.service.InvestigateEvidenceService;
import com.sayee.sxsy.modules.perform.service.PerformAgreementService;
import com.sayee.sxsy.modules.reachmediate.service.ReachMediateService;
import com.sayee.sxsy.modules.sign.service.SignAgreementService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 调解
 *
 * @author www.donxon.com
 */
@Controller
@RequestMapping("${adminPath}/api")
public class MediateApiController {
    @Autowired
    private MediateApiService mediateApiService;
    @Autowired
    private MediateInfoApiService mediateInfoApiService;
    @Autowired
    private MediateDetailService mediateDetailService;
    @Autowired
    private OrganizationApiService organizationApiService;
    @Autowired
    private OfficeApiService officeApiService;
    @Autowired
    private AuditAcceptanceService auditAcceptanceService;
    @Autowired
    private ComplaintMainDetailService complaintMainDetailService;//投诉接待
    @Autowired
    private InvestigateEvidenceService investigateEvidenceService;//调查取证
    @Autowired
    private MediateEvidenceService mediateEvidenceService;//质证调解
    @Autowired
    private AssessAppraisalService assessAppraisalService;//评估鉴定
    @Autowired
    private ReachMediateService reachMediateService;//达成调解
    @Autowired
    private SignAgreementService signAgreementService;//签署协议
    @Autowired
    private PerformAgreementService performAgreementService;//履行协议
    @Autowired
    private ActTaskService actTaskService;//流程
    /*投诉接待*/
    @RequestMapping("mediateadd")
    @ResponseBody
    public R mediateAdd(@RequestBody JSONObject jsonObject){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        MediateApiEntity mediateApiEntity=JSON.toJavaObject(jsonObject,MediateApiEntity.class);
        mediateApiEntity.preInsert();
        String caseNumber=getCaseMumber();
        String complaintMainId=mediateApiEntity.getId();
        String involveHospital = jsonObject.getString("involveHospital");//接收参数：涉及医院id
        OrganizationApiEntity organizationApiEntity = organizationApiService.getOrganizationById(involveHospital);
        String hospitalLevel = organizationApiEntity.getGrade();
        String hospitalGrade = organizationApiEntity.getHospitalGrade();
        String visitorDate = sdf.format(new Date());
        String complaintMode = "0";
        String isMajor = "0";//接收参数:案件分类
        String receptionDate = sdf.format(new Date());//添加日期
        mediateApiEntity.setComplaintMainId(mediateApiEntity.getId());
        mediateApiEntity.setCaseNumber(caseNumber);
        mediateApiEntity.setHospitalLevel(hospitalLevel);
        mediateApiEntity.setHospitalGrade(hospitalGrade);
        mediateApiEntity.setSource("1");
        mediateApiEntity.setCreateDate(new Date());
        mediateApiEntity.setUpdateDate(new Date());

        MediateDetail mediateDetail=JSON.toJavaObject(jsonObject,MediateDetail.class);
        mediateDetail.preInsert();
        mediateDetail.setComplaintMainDetailId(mediateDetail.getId());
        mediateDetail.setComplaintMainId(complaintMainId);
        mediateDetail.setComplaintMode(complaintMode);
        mediateDetail.setVisitorDate(visitorDate);
        mediateDetail.setIsMajor(isMajor);
        mediateDetail.setReceptionDate(receptionDate);
        mediateDetail.setCaseNumber(caseNumber);
        String nextLinkMan=mediateDetail.getNextLinkMan();
        mediateDetail.setCreateUser(jsonObject.getString("createUser"));
        if(mediateApiService.saveMediate(mediateApiEntity)>0){
            if(mediateDetailService.save(mediateDetail)>0){
                Map<String,Object> var=new HashMap<String, Object>();
                var.put("pass","1");
                User assigness= UserUtils.get(nextLinkMan);
                //logger.debug("用户名======="+assigness);
                var.put("enrollment_user", null==assigness ? "weChat" : assigness.getLoginName());
                var.put("id","complaint_main_id");
                // 启动流程
                String actid=actTaskService.startProcess("complaint", "COMPLAINT_MAIN", complaintMainId, caseNumber,var);
                Map map=new HashMap();
                map.put("complaintMainId",complaintMainId);
                map.put("procInsId",actid);
                mediateApiService.setProInstId(map);
                //启动流程的时候 创建一个 隐藏的角色
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
    @Autowired
    private MediateReportApiService mediateReportApiService;
    @Autowired
    private MachineAccountService machineAccountService;
   /*报案登记*/
    @RequestMapping("doreportregistration")
    @ResponseBody
    public R doReportRegistration(@RequestBody JSONObject jsonObject){
        MediateReport mediateReport=JSON.toJavaObject(jsonObject,MediateReport.class);
        String complaintMainId=jsonObject.getString("complaintMainId");
        String nextLinkMan=jsonObject.getString("nextLinkMan");
        String caseNumber=jsonObject.getString("caseNumber");
        mediateReport.preInsert();
        mediateReport.setReportRegistrationId(mediateReport.getId());
        mediateReportApiService.saveMediateReport(mediateReport);
        process("0",nextLinkMan,complaintMainId,"",caseNumber,"check_user");
        //ReportRegistration reportRegistration=JSON.toJavaObject(jsonObject,ReportRegistration.class);
        //machineAccountService.savetz(null, "a", reportRegistration);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData","data");
        return r;
    }
    /*审核受理*/
   @Autowired
   private AcceptApiService acceptApiService;
   @RequestMapping("doaccept")
   @ResponseBody
   public R doAccept(@RequestBody JSONObject jsonObject){
       Accept accept= JSON.toJavaObject(jsonObject,Accept.class);
       accept.preInsert();
       accept.setAuditAcceptanceId(accept.getId());
       String nextLinkMan=accept.getNextLinkMan();
       String complaintMainId=accept.getComplaintMainId();
       String caseNumber=jsonObject.getString("caseNumber");
       acceptApiService.save(accept);
       TaskEntity taskEntity=getTaskId(complaintMainId);
       String taskId=taskEntity.getTaskId();
       String procInsId=taskEntity.getProcInsId();
       Map<String,Object> var=new HashMap<String, Object>();
       var.put("pass","0");
       User assigness= UserUtils.get(nextLinkMan);
       var.put("forensics_user",assigness.getLoginName());
       // 执行流程
       actTaskService.complete(taskId, procInsId,"", caseNumber, var);
       R r=new R();
       r.put("RtnCode",0);
       r.put("RtnMsg","success");
       r.put("RtnData","data");
       return r;
   }

   @Autowired
   private InvestitageApiService investitageApiService;
   /*调查取证*/
   @RequestMapping("doInvestigate")
   @ResponseBody
   public R doInvestigate(@RequestBody JSONObject jsonObject){
       //investigateEvidenceService.save(investigateEvidence);
       String complaintMainId = jsonObject.getString("complaintMainId");
       String caseNumber=jsonObject.getString("caseNumber");//接收参数:案件编号
       Investitage investitage=JSON.toJavaObject(jsonObject,Investitage.class);
       investitage.preInsert();
       investitage.setInvestigateEvidenceId(investitage.getId());
       investitage.setCreateDate(new Date());
       investitage.setUpdateDate(new Date());
       String nextLinkMan=investitage.getNextLinkMan();
       try{
           investitageApiService.save(investitage);
           Map<String, Object> var = new HashMap<String, Object>();
           TaskEntity taskEntity=getTaskId(complaintMainId);
           String taskId=taskEntity.getTaskId();
           String procInsId=taskEntity.getProcInsId();
           var.put("pass", "0");
           User assigness = UserUtils.get(nextLinkMan);
           var.put("mediation_user", assigness.getLoginName());
           //执行流程
           actTaskService.complete(taskId, procInsId, "", caseNumber, var);
           R r=new R();
           r.put("RtnCode",0);
           r.put("RtnMsg","添加成功");
           r.put("RtnData","");
           return r;
       }catch (Exception e){
           R r=new R();
           r.put("RtnCode",1);
           r.put("RtnMsg","添加失败");
           r.put("RtnData",e.getMessage());
           return r;
       }
   }
    @Autowired
    private EvidenceApiService evidenceApiService;
    /*质证调解*/
    @RequestMapping("domediateevidence")
    @ResponseBody
    public R doMediateEvidence(@RequestBody JSONObject jsonObject){
        //mediateEvidenceService.save(mediateEvidence);
        String complaintMainId = jsonObject.getString("complaintMainId");
        String caseNumber=jsonObject.getString("caseNumber");
        Evidence evidence=JSON.toJavaObject(jsonObject,Evidence.class);
        String nextLinkMan=evidence.getNextLinkMan();
        try{
            evidenceApiService.save(evidence);
            Map<String,Object> var=new HashMap<String, Object>();

            //var.put("pass",StringUtils.isBlank(mediateEvidence.getNextLink()) ? "0" : mediateEvidence.getNextLink());
            var.put("pass",0);
            TaskEntity taskEntity=getTaskId(complaintMainId);
            String taskId=taskEntity.getTaskId();
            String procInsId=taskEntity.getProcInsId();
            User assigness= UserUtils.get(nextLinkMan);
            //var.put("0".equals(MapUtils.getString(var,"pass","0")) ? "evaluation_user" : "sign_user",assigness.getLoginName());
            var.put("evaluation_user",assigness.getLoginName());
            // 执行流程
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加成功");
            r.put("RtnData","");
            return r;
        }catch (Exception e){
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","添加失败");
            r.put("RtnData",e.getMessage());
            return r;
        }
    }
    @Autowired
    private AppraisalApiService appraisalApiService;
    /*评估鉴定*/
    @RequestMapping("doassessappraisal")
    @ResponseBody
    public R doAssessappraisal(@RequestBody JSONObject jsonObject){
        //assessAppraisalService.save(assessAppraisal);
        String complaintMainId = jsonObject.getString("complaintMainId");
        MediateCommon mediateCommon=mediateApiService.findMediateById(complaintMainId);
        String caseNumber=mediateCommon.getCaseNumber();
        String patientAge=mediateCommon.getPatientAge();
        String patientName=mediateCommon.getPatientName();
        String patientSex=mediateCommon.getPatientSex();
        Appraisal appraisal=JSON.toJavaObject(jsonObject,Appraisal.class);
        appraisal.preInsert();
        appraisal.setAssessAppraisalId(appraisal.getId());
        appraisal.setPatientAge(patientAge);
        appraisal.setPatientName(patientName);
        appraisal.setPatientSex(patientSex);
        appraisal.setCreateDate(new Date());
        appraisal.setUpdateDate(new Date());
        String nextLinkMan=appraisal.getNextLinkMan();
        try{
            appraisalApiService.save(appraisal);
            Map<String,Object> var=new HashMap<String, Object>();
            var.put("pass","0");
            User assigness= UserUtils.get(nextLinkMan);
            var.put("reach_user",assigness.getLoginName());
            TaskEntity taskEntity=getTaskId(complaintMainId);
            String taskId=taskEntity.getTaskId();
            String procInsId=taskEntity.getProcInsId();
            // 执行流程
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加成功");
            r.put("RtnData","");
            return r;
        }catch (Exception e){
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","添加失败");
            r.put("RtnData",e.getMessage());
            return r;
        }
    }
    /*达成调解*/
    @Autowired
    private ReachApiService reachApiService;
    @RequestMapping("doreachmediate")
    @ResponseBody
    public R doReachMediate(@RequestBody JSONObject jsonObject){
        //reachMediateService.save(reachMediate);
        Reach reach=JSON.toJavaObject(jsonObject,Reach.class);
        reach.preInsert();
        reach.setReachMediateId(reach.getId());
        String nextLinkMan=reach.getNextLinkMan();
        String complaintMainId=reach.getComplaintMainId();
        String caseNumber=jsonObject.getString("caseNumber");
        try{
            reachApiService.save(reach);
            Map<String,Object> var = new HashMap<String,Object>();
            var.put("pass","0");
            User assigness = UserUtils.get(nextLinkMan);
            var.put("sign_user",assigness.getLoginName());
            //执行流程
            TaskEntity taskEntity=getTaskId(complaintMainId);
            String taskId=taskEntity.getTaskId();
            String procInsId=taskEntity.getProcInsId();
            actTaskService.complete(taskId,procInsId,"",caseNumber,var);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加成功");
            r.put("RtnData","");
            return r;
        }catch (Exception e){
            e.getMessage();
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加失败");
            r.put("RtnData",e.getMessage());
            return r;
        }
    }
    /*签署协议*/
    @Autowired
    private SignApiService signApiService;
    @RequestMapping("dosignagreement")
    @ResponseBody
    public R doSignAgreement(@RequestBody JSONObject jsonObject){
        //signAgreementService.save(signAgreement);
        Sign sign=JSON.toJavaObject(jsonObject,Sign.class);
        sign.preInsert();
        sign.setSignAgreementId(sign.getId());
        sign.setAgreementNumber(getAgreementNumber());
        String nextLinkMan=sign.getNextLinkMan();
        String complaintMainId=sign.getComplaintMainId();
        String caseNumber=jsonObject.getString("caseNumber");
        try{
            signApiService.save(sign);
            Map<String,Object> var=new HashMap<String, Object>();
            var.put("pass","0");
            User assigness= UserUtils.get(nextLinkMan);
            var.put("fulfill_user",assigness.getLoginName());
            // 执行流程
            TaskEntity taskEntity=getTaskId(complaintMainId);
            String taskId=taskEntity.getTaskId();
            String procInsId=taskEntity.getProcInsId();
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加成功");
            r.put("RtnData","");
            return r;
        }catch (Exception e){
            e.getMessage();
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加失败");
            r.put("RtnData",e.getMessage());
            return r;
        }
    }
    /*履行协议*/
    @Autowired
    private PerformApiService performApiService;
    @RequestMapping("doperformagreement")
    @ResponseBody
    public R doPerformAgreement(@RequestBody JSONObject jsonObject){
        //performAgreementService.save(performAgreement);
        Perform perform =JSON.toJavaObject(jsonObject,Perform.class);
        perform.preInsert();
        perform.setPerformAgreementId(perform.getId());
        String nextLinkMan=perform.getNextLinkMan();
        String complaintMainId=perform.getComplaintMainId();
        String caseNumber=jsonObject.getString("caseNumber");
        try{
            performApiService.save(perform);
            Map<String,Object> var=new HashMap<String, Object>();
            var.put("pass","0");
            User assigness= UserUtils.get(nextLinkMan);
            TaskEntity taskEntity=getTaskId(complaintMainId);
            String taskId=taskEntity.getTaskId();
            String procInsId=taskEntity.getProcInsId();
            var.put("summary_user",assigness.getLoginName());
            // 执行流程
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加成功");
            r.put("RtnData","");
            return r;
        }catch (Exception e){
            e.getMessage();
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加失败");
            r.put("RtnData",e.getMessage());
            return r;
        }
    }
    /*案件总结*/
    @Autowired
    private SummaryApiService summaryApiService;
    @RequestMapping("dosummary")
    @ResponseBody
    public R doSummary(@RequestBody JSONObject jsonObject){
        //performAgreementService.save(performAgreement);
        Summary summary =JSON.toJavaObject(jsonObject,Summary.class);
        summary.preInsert();
        summary.setSummaryId(summary.getId());
        String nextLinkMan=summary.getNextLinkMan();
        String complaintMainId=summary.getComplaintMainId();
        String caseNumber=jsonObject.getString("caseNumber");
        try{
            summaryApiService.save(summary);
            Map<String,Object> var=new HashMap<String, Object>();
            var.put("pass","0");
            User assigness= UserUtils.get(nextLinkMan);
            var.put("assess_user",assigness.getLoginName());
            // 执行流程
            TaskEntity taskEntity=getTaskId(complaintMainId);
            String taskId=taskEntity.getTaskId();
            String procInsId=taskEntity.getProcInsId();
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加成功");
            r.put("RtnData","");
            return r;
        }catch (Exception e){
            e.getMessage();
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加失败");
            r.put("RtnData",e.getMessage());
            return r;
        }
    }
    /*案件评价*/
    @Autowired
    private AssessApiService assessApiService;
    @RequestMapping("doassess")
    @ResponseBody
    public R doAssess(@RequestBody JSONObject jsonObject){
        //performAgreementService.save(performAgreement);
        Assess assess =JSON.toJavaObject(jsonObject,Assess.class);
        assess.preInsert();
        assess.setAssessId(assess.getId());
        String nextLinkMan=assess.getNextLinkMan();
        String complaintMainId=assess.getComplaintMainId();
        String caseNumber=jsonObject.getString("caseNumber");
        try{
            assessApiService.save(assess);
            Map<String,Object> var=new HashMap<String, Object>();
            var.put("pass","0");
            TaskEntity taskEntity=getTaskId(complaintMainId);
            String taskId=taskEntity.getTaskId();
            String procInsId=taskEntity.getProcInsId();
            User assigness= UserUtils.get(nextLinkMan);
            var.put("feedback_user",assigness.getLoginName());
            // 执行流程
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加成功");
            r.put("RtnData","");
            return r;
        }catch (Exception e){
            e.getMessage();
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加失败");
            r.put("RtnData",e.getMessage());
            return r;
        }
    }
    /*流程操作*/
    private void process(String pass,String nextLinkMan,String complaintMainId,String title,String caseNumber,String varName){
        Map<String,Object> var=new HashMap<String, Object>();
        var.put("pass",pass);
        TaskEntity taskEntity=getTaskId(complaintMainId);
        String taskId=taskEntity.getTaskId();
        String procInsId=taskEntity.getProcInsId();
        User assigness=UserUtils.get(nextLinkMan);
        //var.put("enrollment_user", null==assigness ? "weChat" : assigness.getLoginName());
        var.put("check_user",null==assigness ? "weChat" : assigness.getLoginName());
        // 执行流程
        actTaskService.complete(taskId, procInsId, title, caseNumber, var);
    }
    /**/
    @RequestMapping("medialist")
    @ResponseBody
    public R mediateList(){
        List<Mediate> dataList= mediateApiService.getMediateList();
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",dataList);
        return r;
   }

   @RequestMapping("getmediateinfo")
   @ResponseBody
   public R getMediateInfo(@RequestBody JSONObject jsonObject){
        String complaintMainId=jsonObject.getString("complaintMainId");
        String status=mediateApiService.getStatus(complaintMainId);
        MediateCommon mediateCommon =mediateApiService.getMediateInfo(complaintMainId);
        List<ActInst> actInstList=mediateApiService.getActInfo(complaintMainId);
        mediateApiService.findMediateById(complaintMainId);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",status);
        return r;
   }
   private String getDict(String involveHospitalName){
        return officeApiService.getDict(involveHospitalName);
   }
   private TaskEntity getTaskId(String complaintMainId){
        return mediateReportApiService.getTaskId(complaintMainId);
   }
   private String getCaseMumber(){
        String caseNumber= mediateApiService.getCaseNumber();
        return caseNumber;
   }
   private String getAgreementNumber(){
       return signApiService.getMax();
   }
   @RequestMapping("getorganization")
   @ResponseBody
   private OrganizationApiEntity getOrganization(){
        return organizationApiService.getOrganizationById("1f4cf5433c6f44e993590cacca362b11");
   }
}