package com.sayee.sxsy.api.publicnotice.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.publicnotice.entity.PublicNotice;
import com.sayee.sxsy.api.publicnotice.service.PublicNoticeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author www.donxon.com
 * @Description 精选、通知、常识
 */
@Controller
@RequestMapping("${adminPath}/api")
public class PublicNoticeController {
    @Autowired
    private PublicNoticeApiService publicNoticeApiService;
    @RequestMapping("getpublicnotice")
    @ResponseBody
    public R getPublicNotice(@RequestBody JSONObject jsonObject){
        List<PublicNotice> list=publicNoticeApiService.getPublicNotice(jsonObject.getString("type")==null?"1":jsonObject.getString("type"));
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",list);
        return r;
    }
    /*法律法规*/
    @RequestMapping("getlaws")
    @ResponseBody
    public R getLaws(){
        List list=new ArrayList();
        Map map1=new HashMap<>();
        map1.put("lawId","1");
        map1.put("lawTitle","中华人民共和国人民调节法");
        map1.put("lawContent","2010年8月28日第十一届全国人民代表大会常务委员会第十六次会议通过。");
        map1.put("createDate","2019年11月16日");
        Map map2=new HashMap<>();
        map2.put("lawId","2");
        map2.put("lawTitle","医疗纠纷预防和处理条例");
        map2.put("lawContent","2018年6月20日国务院第13词常务会议通过，现予公布，自2018年10月1日起执行。");
        map2.put("createDate","2019年11月16日");
        list.add(map1);
        list.add(map2);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",list);
        return r;
    }
    /*案例*/
    @RequestMapping("getcase")
    @ResponseBody
    public R getCase(){
        Map map=new HashMap<>();
        map.put("caseId","1");
        map.put("caseTitle","用法理为医患化解纠纷");
        map.put("caseContent","患者舒某某，男，54岁，系外地来山西务工人员，于2016年5月22日上午因上腹胀痛3天到山西省某三家医院急症科就诊(之前就诊于当地卫生所)。自述上腹胀痛，不思饮食，无其他...");
        map.put("createDate","2019年11月05日");
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",map);
        return r;
    }
}
