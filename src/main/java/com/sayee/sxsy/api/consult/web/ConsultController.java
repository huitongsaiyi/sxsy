package com.sayee.sxsy.api.consult.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.consult.entity.ConsultEntity;
import com.sayee.sxsy.api.consult.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 咨询
 */
@Controller
@RequestMapping("${adminPath}/api")
public class ConsultController {
    @Autowired
    private ConsultService consultService;
    /*添加咨询*/
    @RequestMapping("consultadd")
    @ResponseBody
    public R addConsult(@RequestBody JSONObject jsonObject){
        ConsultEntity consultEntity=new ConsultEntity();
        consultEntity.preInsert();
        consultEntity.setConsultId(consultEntity.getId());
        String uid=jsonObject.getString("uid");
        String content=jsonObject.getString("content");
        String name=jsonObject.getString("name");
        consultEntity.setUid(uid);
        consultEntity.setContent(content);
        consultEntity.setName(name);
        consultEntity.setCreateDate(new Date());
        consultEntity.setUpdateDate(new Date());
        try {
            consultService.addConsult(consultEntity);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","添加成功");
            r.put("RtnData","daf");
            return r;
        }catch (Exception e){
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","添加失败");
            r.put("RtnData",e);
            return r;
        }
    }
    /*咨询回复*/
    @RequestMapping("consultreply")
    @ResponseBody
    public R consultReply(@RequestBody JSONObject jsonObject){
        String reply=jsonObject.getString("reply");
        String replyUid=jsonObject.getString("replyUid");
        String replyDate=jsonObject.getString("replyDate");
        Map map=new HashMap<>();
        map.put("reply",reply);
        map.put("replyUid",replyUid);
        map.put("replyDate",replyDate);
        try{
            consultService.reply(map);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","回复成功");
            r.put("RtnData","");
            return r;
        }catch (Exception e){
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","error");
            r.put("RtnData",e.getStackTrace());
            return r;
        }
    }
    /*咨询列表*/
    @RequestMapping("consultlist")
    @ResponseBody
    public R consultList(@RequestBody JSONObject jsonObject){
            List<ConsultEntity> dataList=consultService.getConsultList();
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",dataList);
            return r;
    }
    /*用户咨询列表*/
    @RequestMapping("getconsultlistbyuid")
    @ResponseBody
    public R getConsultListByUid(@RequestBody JSONObject jsonObject){
        String uid=jsonObject.getString("uid");
        List<ConsultEntity> dataList=consultService.getConsultListByUid(uid);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",dataList);
        return r;
    }
}
