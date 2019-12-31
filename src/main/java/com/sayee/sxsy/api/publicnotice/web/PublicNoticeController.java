package com.sayee.sxsy.api.publicnotice.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.main.entity.MainApi;
import com.sayee.sxsy.api.main.service.MainApiService;
import com.sayee.sxsy.api.organ.entity.Organ;
import com.sayee.sxsy.api.organ.entity.OrganUser;
import com.sayee.sxsy.api.organ.service.OrganApiService;
import com.sayee.sxsy.api.publicnotice.entity.LawCaseApi;
import com.sayee.sxsy.api.publicnotice.entity.PublicNotice;
import com.sayee.sxsy.api.publicnotice.service.LawCaseApiService;
import com.sayee.sxsy.api.publicnotice.service.PublicNoticeApiService;
import org.activiti.engine.impl.bpmn.data.SimpleDataInputAssociation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 精选、通知、常识 机构信息、法律、法规
 */
@Controller
@RequestMapping("${adminPath}/api")
public class PublicNoticeController {
    @Autowired
    private PublicNoticeApiService publicNoticeApiService;
    @Autowired
    private LawCaseApiService lawCaseApiService;
    /*精选通知常识*/
    @RequestMapping("getpublicnotice")
    @ResponseBody
    public R getPublicNotice(){
        //List<PublicNotice> list=publicNoticeApiService.getPublicNotice(jsonObject.getString("type")==null?"1":jsonObject.getString("type"));
        List<PublicNotice> list1=publicNoticeApiService.getPublicNotice(1,10,"1");
        int count1=publicNoticeApiService.getTotal("1");
        List<PublicNotice> list2=publicNoticeApiService.getPublicNotice(1,10,"2");
        int count2=publicNoticeApiService.getTotal("2");
        List<PublicNotice> list3=publicNoticeApiService.getPublicNotice(1,10,"3");
        int count3=publicNoticeApiService.getTotal("3");
        Map map1=new HashMap();
        map1.put("pageNo",1);
        map1.put("pageSize",10);
        map1.put("totalPage",getTotalPage(count1,10));
        map1.put("totalCount",count1);
        map1.put("pageData",list1);

        Map map2=new HashMap();
        map2.put("pageNo",1);
        map2.put("pageSize",10);
        map2.put("totalPage",getTotalPage(count2,10));
        map2.put("totalCount",count2);
        map2.put("pageData",list2);

        Map map3=new HashMap();
        map3.put("pageNo",1);
        map3.put("pageSize",10);
        map3.put("totalPage",getTotalPage(count3,10));
        map3.put("totalCount",count3);
        map3.put("pageData",list3);

        Map map=new HashMap();
        map.put("jingxuan",map1);
        map.put("tongzhi",map2);
        map.put("changshi",map3);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",map);
        return r;
    }
    /*精选通知*/
    @ResponseBody
    @RequestMapping("getpublicnoticebytype")
    public R getPublicNoticeByType(@RequestBody JSONObject jsonObject){
        int page=1;
        if(null!=jsonObject.getInteger("page")) {
            page = jsonObject.getInteger("page");
        }
        int pageSize= 10;
        if(null!=jsonObject.getInteger("pageSize")){
            pageSize=jsonObject.getInteger("pageSize");
        }
        String type=jsonObject.getString("type");
        int totalPage=publicNoticeApiService.getTotal(type);
        List<PublicNotice> list=publicNoticeApiService.getPublicNotice(page,pageSize,type);
        Map map=new HashMap();
        map.put("pageNo",page);
        map.put("pageSize",pageSize);
        map.put("totalPage",totalPage);
        map.put("pageData",list);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",list);
        return r;
    }
    @RequestMapping("getpublicnoticeinfo")
    @ResponseBody
    public R getPublicNoticeInfo(@RequestBody JSONObject jsonObject){
        String id=jsonObject.getString("id");
        PublicNotice publicNotice=publicNoticeApiService.getPublicInfo(id);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",publicNotice);
        return r;
    }
    @Autowired
    private OrganApiService organApiService;
    @Autowired
    private MainApiService mainApiService;
    @RequestMapping("getlawslist")
    @ResponseBody
    public R getLawsList(){
        Organ organ=organApiService.getOrgan();
        if(null!=organ) {
            if (null != organ.getUserId() && !organ.getUserId().isEmpty()) {
                String users = organ.getUserId();
                String[] idList = users.split(",");
                List<OrganUser> list = organApiService.getUserList(idList);
                organ.setUserList(list);
            }
            String imagePath=organ.getIcon();
            organ.setIcon(imagePath.replace("|",""));
        }else{
            organ=new Organ();
        }
        Map map=new HashMap();
        map.put("organ",organ);
        Map map2=new HashMap<>();
        List<LawCaseApi> casePageData= lawCaseApiService.getPageLawCase(1,10,"2");
        int caseTotalCount=lawCaseApiService.getTotal("2");
        MainApi mainApi=mainApiService.getMainInfo();
        map2.put("pageNo",1);
        map2.put("pageSize",10);
        map2.put("totalPage",getTotalPage(caseTotalCount,10));
        map2.put("totalCount",caseTotalCount);
        map2.put("pageData",casePageData);
        if(null!=mainApi){
            String msgInfo=null==mainApi.getCaseTips()||mainApi.getCaseTips().isEmpty()?"": mainApi.getCaseTips();
            map2.put("msgInfo",msgInfo);
        }else{
            map2.put("msgInfo","建议市民如果发生了医患纠纷，在与医院协商无果的情况,可到医院所在区的医调委寻求帮助,调解免费");
        }
        Map map3=new HashMap<>();
        List<LawCaseApi> pageData= lawCaseApiService.getPageLawCase(1,10,"1");
        int totalCount=lawCaseApiService.getTotal("1");
        map3.put("pageNo",1);
        map3.put("pageSize",10);
        map3.put("totalPage",getTotalPage(totalCount,10));
        map3.put("pageData",pageData);
        map3.put("totalCount",totalCount);
        map.put("lawsData",map3);
        map.put("caseData",map2);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",map);
        return r;
    }
    /*法律法规*/
    @RequestMapping("getlaws")
    @ResponseBody
    public R getLaw(@RequestBody JSONObject jsonObject){
        int page=1;
        if(null!=jsonObject.getInteger("page")) {
            page = jsonObject.getInteger("page");
        }
        int pageSize= 10;
        if(null!=jsonObject.getInteger("pageSize")){
            pageSize=jsonObject.getInteger("pageSize");
        }
        //Page<LawCase> pageData= lawCaseApiService.findPage(new Page(pageNo,pageSize),new LawCase());
        List<LawCaseApi> pageData=lawCaseApiService.getPageLawCase(page,pageSize,"1");
        int totalCount=lawCaseApiService.getTotal("1");
        Double totalPage =getTotalPage(totalCount,pageSize);
        Map map=new HashMap();
        map.put("pageNo",page);
        map.put("pageSize",pageSize);
        map.put("totalPage",totalPage);
        map.put("pageData",pageData);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",map);
        return r;
    }
    /*案件*/
    @RequestMapping("getcase")
    @ResponseBody
    public R getCase(@RequestBody JSONObject jsonObject){
        int page=1;
        if(null!=jsonObject.getInteger("page")) {
            page = jsonObject.getInteger("page");
        }
        int pageSize= 10;
        if(null!=jsonObject.getInteger("pageSize")){
            pageSize=jsonObject.getInteger("pageSize");
        }
        //Page<LawCase> pageData= lawCaseApiService.findPage(new Page(pageNo,pageSize),new LawCase());
        List<LawCaseApi> pageData=lawCaseApiService.getPageLawCase(page,pageSize,"2");
        int totalCount=lawCaseApiService.getTotal("2");
        Double totalPage =getTotalPage(totalCount,pageSize);
        Map map=new HashMap();
        map.put("pageNo",page);
        map.put("pageSize",pageSize);
        map.put("totalPage",totalPage);
        map.put("pageData",pageData);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",map);
        return r;
    }
    /*案件详情*/
    @RequestMapping("getcaseinfo")
    @ResponseBody
    public R getCaseInfo(@RequestBody JSONObject jsonObject){
        String id= jsonObject.getString("id");
        LawCaseApi lawCase= lawCaseApiService.getLawCaseInfo(id);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",lawCase);
        return r;
    }
    /*行政调解法律诉讼*/
    @RequestMapping("getservicebytype")
    @ResponseBody
    public R getServiceByType(@RequestBody JSONObject jsonObject){
        String type=jsonObject.getString("type");
        LawCaseApi lawCase=lawCaseApiService.getServiceByType(type);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",lawCase);
        return r;
    }
    private Double getTotalPage(Integer totalCount,Integer pageSize){
        DecimalFormat df=new DecimalFormat("0.0");//设置保留位数
        String totalPageStr =df.format((float)totalCount/pageSize);
        float totalPage=Float.parseFloat(totalPageStr);
        return Math.ceil(totalPage);
    }
}
