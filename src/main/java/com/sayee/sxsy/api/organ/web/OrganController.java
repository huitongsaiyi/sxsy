package com.sayee.sxsy.api.organ.web;

import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.organ.entity.Organ;
import com.sayee.sxsy.api.organ.entity.OrganUser;
import com.sayee.sxsy.api.organ.service.OrganApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author www.donxon.com
 * @Description 机构信息
 */
@Controller
@RequestMapping("${adminPath}/api")
public class OrganController {
    @Autowired
    private OrganApiService organApiService;
    @RequestMapping("getorgan")
    @ResponseBody
    public R getOrgan(){
        Organ organ=organApiService.getOrgan();
        String users=organ.getUserId();
        String[] idList= users.split(",");
        List<OrganUser> list=organApiService.getUserList(idList);
        organ.setUserList(list);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",organ);
        return r;
    }
}
