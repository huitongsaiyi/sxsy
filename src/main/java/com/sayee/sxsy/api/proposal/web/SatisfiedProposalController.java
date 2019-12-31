package com.sayee.sxsy.api.proposal.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.proposal.entity.CommentEntity;
import com.sayee.sxsy.api.proposal.entity.Satisfied;
import com.sayee.sxsy.api.proposal.service.SatisfiedProposalService;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author www.donxon.com
 * @Description
 */
@Controller
@RequestMapping("${adminPath}/api")

public class SatisfiedProposalController {
    @Autowired
    private SatisfiedProposalService satisfiedProposalService;

    //满意度 微信小程序添加
    @RequestMapping("satisfiedAdd")
    @ResponseBody
    public R satisfiedAdd(@RequestBody JSONObject jsonObject){
        Satisfied satisfied=new Satisfied();
        try {
            satisfiedProposalService.save(satisfied,jsonObject);
            R r=new R();
            r.put("RtnCode","0");
            r.put("RtnMsg","添加成功");
            r.put("RtnData","");
            return r;
        }catch (Exception e){
            R r=new R();
            r.put("RtnCode","1");
            r.put("RtnMsg","添加失败");
            r.put("RtnData",e);
            return r;
        }
    }

    //满意度列表 微信小程序
    @RequestMapping("satisfiedList")
    @ResponseBody
    public R satisfiedList(@RequestBody JSONObject jsonObject){
        int pageNo=1;
        if(null!=jsonObject.getInteger("pageNo")) {
            pageNo = jsonObject.getInteger("pageNo");
        }
        int pageSize= 5;
        if(null!=jsonObject.getInteger("pageSize")){
            pageSize=jsonObject.getInteger("pageSize");
        }
        String uid=jsonObject.getString("uid");
        User user=UserUtils.get(uid);
        if(StringUtils.isNotBlank(uid) && user!=null){
            Satisfied satisfied=new Satisfied();
            //一般角色 看到自己的满意度  高角色看到 全部
            if (user.getRoleList().contains("yydept")){

            }else {
                satisfied.setUid(user.getId());
            }
            Page<Satisfied> page=satisfiedProposalService.findPage(new Page<>(pageNo,pageSize),satisfied);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",page);
            return r;
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","error");
            r.put("RtnData","请先选择满意度!");
            return r;
        }
    }





    //投诉建议 微信小程序添加
    @RequestMapping("proposalAdd")
    @ResponseBody
    public R proposalAdd(@RequestBody JSONObject jsonObject) {
        CommentEntity commentEntity = new CommentEntity();
        if (StringUtils.isNotBlank(jsonObject.getString("content"))) {
            try {
                satisfiedProposalService.saveComment(commentEntity, jsonObject);
                R r = new R();
                r.put("RtnCode", "0");
                r.put("RtnMsg", "添加成功");
                r.put("RtnData", "");
                return r;
            } catch (Exception e) {
                R r = new R();
                r.put("RtnCode", "1");
                r.put("RtnMsg", "添加失败");
                r.put("RtnData", e);
                return r;
            }
        } else {
            R r = new R();
            r.put("RtnCode", "1");
            r.put("RtnMsg", "添加失败");
            r.put("RtnData", "请输入投诉或者建议的内容!");
            return r;
        }

    }

}
