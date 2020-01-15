package com.sayee.sxsy.api.upload;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.common.UploadUtil;
import com.sayee.sxsy.api.mediate.service.MediateApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description
 */
@Controller
@RequestMapping("${adminPath}/api")

public class UploadController {
    /*头像*/
    @RequestMapping("headimage")
    @ResponseBody
    public R headImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String path = "/headimage";
        String fileName=UploadUtil.upload(request,file,path);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",fileName);
            return r;
    }
    /*组织机构图片*/
    @RequestMapping("organizationimage")
    @ResponseBody
    public R organizationimage(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
        String path = "/organizationimage";
        String fileName=UploadUtil.upload(request,file,path);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",fileName);
        return r;
    }
    /*调解图片*/
    @RequestMapping("mediateimage")
    @ResponseBody
    public R mediateimage(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
        String path = "/mediateimage";
        String fileName=UploadUtil.upload(request,file,path);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",fileName);
        return r;
    }
    /*取证图片*/
    @RequestMapping("evidenceimage")
    @ResponseBody
    public R evidenceimage(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
        String path = "/evidenceimage";
        String fileName=UploadUtil.upload(request,file,path);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",fileName);
        return r;
    }
    /*调解资料*/
    @RequestMapping("evidencedocument")
    @ResponseBody
    public R evidencedocument(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
        String path = "/evidencedocument";
        String fileName=UploadUtil.upload(request,file,path);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",fileName);
        return r;
    }
    @Autowired
    private MediateApiService mediateApiService;
    @RequestMapping("mediateupload")
    @ResponseBody
    public R mediateUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException{
        String complaintMainId=request.getParameter("complaintMainId");
        String status=mediateApiService.getStatus(complaintMainId);
        String tableName;
        String tableId;
        switch (status){
            case "报案登记":
                tableName="REPORT_REGISTRATION";
                tableId="REPORT_REGISTRATION_ID";
                break;
            case "审核受理":
                tableName ="AUDIT_ACCEPTANCE";
                tableId="AUDIT_ACCEPTANCE_ID";
                break;
            case "调查取证":
                tableName ="INVESTIGATE_EVIDENCE";
                tableId="AUDIT_ACCEPTANCE_ID";
                break;
            case "质证调解":
                tableName ="MEDIATE_EVIDENCE";
                tableId="AUDIT_ACCEPTANCE_ID";
                break;
            case "评估鉴定":
                tableName="MEDIATE_EVIDENCE";
                tableId="AUDIT_ACCEPTANCE_ID";
                break;
            case "达成调解":
                tableName ="ASSESS_APPRAISAL";
                tableId="AUDIT_ACCEPTANCE_ID";
                break;
            case "签署协议":
                tableName ="REACH_MEDIATE";
                tableId="AUDIT_ACCEPTANCE_ID";
                break;
            case "履行协议":
                tableName ="SIGN_AGREEMENT";
                tableId="AUDIT_ACCEPTANCE_ID";
                break;
        }
        String path = "/evidencedocument";
        String fileName=UploadUtil.upload(request,file,path);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",fileName);
        return r;
    }
}
