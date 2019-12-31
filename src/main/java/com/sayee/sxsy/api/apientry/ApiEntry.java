package com.sayee.sxsy.api.apientry;

import com.sayee.sxsy.api.common.R;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description api入口
 */
@Controller
@RequestMapping("${adminPath}/api")
public class ApiEntry {
    @RequestMapping("laissezpasser")
    @ResponseBody
    public R laissezPasser(HttpServletRequest request){
        Date date = new Date();
        date.setTime(date.getTime()+1000*3600*30);
        String salt="asdfQWer1234$#@!";
        String token = UUID.randomUUID().toString().replace("-", "")+date+salt;
        String laissezPasser=DigestUtils.md5DigestAsHex(token.getBytes());
        HttpSession session=request.getSession();
        session.setAttribute("APPTOKEN",laissezPasser);
        String sessionId=session.getId();
        Map dataMap=new HashMap<>();
        dataMap.put("laissezPasser",laissezPasser);
        dataMap.put("sessionId",sessionId);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",dataMap);
        return r;
    }
    @RequestMapping("prohibit")
    @ResponseBody
    public R prohibit(HttpServletRequest request){
        System.out.println(request);
        String msg=request.getAttribute("msg").toString();
        String code=request.getAttribute("code").toString();
        int rtnCode=Integer.valueOf(code);
        R r=new R();
        r.put("RtnCode",-1);
        r.put("RtnMsg","验证失败");
        r.put("RtnData",msg);
        return r;
    }
}
