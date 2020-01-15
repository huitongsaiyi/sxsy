package com.sayee.sxsy.api.statistic.web;

import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.officeapi.entity.OfficeApi;
import com.sayee.sxsy.api.officeapi.service.OfficeApiService;
import com.sayee.sxsy.api.statistic.service.StatisticApiService;
import com.sayee.sxsy.api.user.service.UserApiService;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.datatype.service.DataTypeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author www.donxon.com
 * @Description
 */
@Controller
@RequestMapping("${adminPath}/api")
public class StatisticController {
    @Autowired
    private UserApiService userApiService;
    @Autowired
    private StatisticApiService statisticApiService;

    /*统计 表头*/
    @RequestMapping("statisticalList")
    @ResponseBody
    public R statisticalList(){
        Map<String,Object> map = statisticApiService.getStatisticalList();
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",map);
        return r;
    }

    /*统计 详情*/
    @RequestMapping("statisticalDetail")
    @ResponseBody
    public R statisticalDetail(@RequestBody JSONObject jsonObject){
        String type=jsonObject.getString("type");
        String wechatUserId = jsonObject.getString("wechatUserId");
        String uid = userApiService.getSysUserId(wechatUserId);
        //String uid=jsonObject.getString("uid");
        if(StringUtils.isNotBlank(type)){
            List<Map<String,Object>> list=statisticApiService.getStatisticalDatail(type,uid);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",list);
            return r;
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","error");
            r.put("RtnData","权限不足!");
            return r;
        }
    }


}
