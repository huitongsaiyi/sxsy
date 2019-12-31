package com.sayee.sxsy.modules.applet.web;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.modules.applet.entity.WxUser;
import com.sayee.sxsy.modules.applet.service.WxUserService;
import com.sayee.sxsy.modules.cms.entity.Site;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信用户
 */

@Controller
@RequestMapping(value = "${adminPath}/applet/user")
public class WxUserController extends BaseController {

    @Autowired
    WxUserService wxUserService;

    @ModelAttribute
    public WxUser get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return wxUserService.get(id);
        }else{
            return new WxUser();
        }
    }

    @RequiresPermissions("applet:user:view")
    @RequestMapping(value = {"list", ""})
    public String form(WxUser wxUser, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<WxUser> page = wxUserService.findPage(new Page<WxUser>(request, response), wxUser);
        logger.info(String.valueOf(wxUser));
        model.addAttribute("page", page);
        return "modules/applet/userList";
    }
}
