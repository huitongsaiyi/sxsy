package com.sayee.sxsy.api.casetype.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.casetype.service.CaseTypeApiService;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.officeapi.entity.OfficeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description 案件分类
 */
@Controller
@RequestMapping("${adminPath}/api")
public class CaseTypeController{
    /*案件分类父列表*/
    @Autowired
    private CaseTypeApiService caseTypeApiService;
    @RequestMapping("getparentcasetype")
    @ResponseBody
    public R getParentCaseType(){
        List<OfficeApi> dataList = caseTypeApiService.getParentList();
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",dataList);
        return r;
    }
    /*案件分类二级列表*/
    @RequestMapping("getcasetype")
    @ResponseBody
    public R getCaseType(@RequestBody JSONObject jsonObject){
        String id=jsonObject.getString("id");
        if(null!= id){
            List<OfficeApi> dataList=caseTypeApiService.getListByParentId(id);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",dataList);
            return r;
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","error");
            r.put("RtnData","请先选择案件分类!");
            return r;
        }
    }
}
