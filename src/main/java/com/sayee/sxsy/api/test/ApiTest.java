package com.sayee.sxsy.api.test;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.common.security.shiro.session.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @Description
 */
@Controller
@RequestMapping("${adminPath}/api")
public class ApiTest {
    @RequestMapping("test")
    @ResponseBody
    public R test(@RequestBody JSONObject jsonObject){
        R r=new R();
        r.put("RtnCode","0");
        r.put("RtnMsg","success");
        r.put("RtnData",jsonObject);
        return r;
    }
}
