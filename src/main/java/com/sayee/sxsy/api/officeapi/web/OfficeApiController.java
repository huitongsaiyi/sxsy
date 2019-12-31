package com.sayee.sxsy.api.officeapi.web;


import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.officeapi.entity.OfficeApi;
import com.sayee.sxsy.api.officeapi.entity.Organization;
import com.sayee.sxsy.api.officeapi.service.OfficeApiService;
import com.sayee.sxsy.api.officeapi.service.OrganizationApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description 机构、科室
 */
@Controller
@RequestMapping("${adminPath}/api")
public class OfficeApiController {
    @Autowired
    private OfficeApiService officeApiService;
    @Autowired
    private OrganizationApiService organizationApiService;
    /*科室父级列表*/
    @RequestMapping("parentofficelist")
    @ResponseBody
    public R parentOfficeList(){
        List<OfficeApi> dataList = officeApiService.getParentList();
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",dataList);
        return r;
    }
    /*科室二级列表*/
    @RequestMapping("officelist")
    @ResponseBody
    public R officeList(@RequestBody JSONObject jsonObject){
        String id=jsonObject.getString("id");
        if(null!= id){
            List<OfficeApi> dataList=officeApiService.getListByParentId(id);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",dataList);
            return r;
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","error");
            r.put("RtnData","请先选择科室!");
            return r;
        }

    }
    /*机构地区列表*/
    @RequestMapping("arealist")
    @ResponseBody
    public R areaList(){
        List<String> dataList=organizationApiService.getAreaList();
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",dataList);
        return r;
    }
    /*医院机构列表*/
    @RequestMapping("organizationlist")
    @ResponseBody
    public R organizationList(@RequestBody JSONObject jsonObject){
        String areaId=jsonObject.getString("areaId");
        if(null!=areaId){
            List<Organization> dataList=organizationApiService.getListByAreaId(areaId);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",dataList);
            return r;
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","error");
            r.put("RtnData","请先选择地区!");
            return r;
        }

    }
}