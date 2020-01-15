package com.sayee.sxsy.api.consult.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.consult.entity.ConsultApi;
import com.sayee.sxsy.api.consult.entity.ConsultApiEntity;
import com.sayee.sxsy.api.consult.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

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
    public R addConsult(@RequestBody JSONObject jsonObject) {
        String wechatUserId = jsonObject.getString("wechatUserId");
        String content = jsonObject.getString("content");
        String name = jsonObject.getString("name");
        String tel = jsonObject.getString("tel");
        //1患者 2 医院
        String type = null == jsonObject.getString("type") || jsonObject.getString("type").isEmpty() ? "1" : jsonObject.getString("type");
        if (null == content || content.isEmpty()) {
            R r = new R();
            r.put("RtnCode", 2);
            r.put("RtnMsg", "内容不能为空");
            r.put("RtnData", "");
            return r;
        } else if (null == wechatUserId || wechatUserId.isEmpty()) {
            R r = new R();
            r.put("RtnCode", 3);
            r.put("RtnMsg", "用户id不能为空");
            r.put("RtnData", "");
            return r;
        } else {
            ConsultApiEntity consultApiEntity = new ConsultApiEntity();
            consultApiEntity.preInsert();
            consultApiEntity.setId(consultApiEntity.getId());
            consultApiEntity.setContent(content);
            consultApiEntity.setName(name);
            consultApiEntity.setPhone(tel);
            consultApiEntity.setType(type);
            consultApiEntity.setCreateDate(new Date());
            consultApiEntity.setUpdateDate(new Date());
            consultApiEntity.setCreateUser(wechatUserId);
            try {
                consultService.addConsult(consultApiEntity);
                R r = new R();
                r.put("RtnCode", 0);
                r.put("RtnMsg", "添加成功");
                r.put("RtnData", "");
                return r;
            } catch (Exception e) {
                System.out.println(e);
                R r = new R();
                r.put("RtnCode", 1);
                r.put("RtnMsg", "添加失败");
                r.put("RtnData", "");
                return r;
            }
        }
    }


    /*咨询列表*/
    @RequestMapping("consultlist")
    @ResponseBody
    public R consultList() {
        List<ConsultApi> dataList = consultService.getConsultList();
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", dataList);
        return r;
    }

    /*用户咨询列表*/
    @RequestMapping("getconsultlistbyuid")
    @ResponseBody
    public R getConsultListByUid(@RequestBody JSONObject jsonObject) {
        String wechatUserId = jsonObject.getString("wechatUserId");
        List<ConsultApi> dataList = consultService.getConsultListByUid(wechatUserId);
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", dataList);
        return r;
    }
}
