package com.sayee.sxsy.modules.applet.web;


import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.modules.applet.entity.Applet;
import com.sayee.sxsy.modules.applet.service.AppletService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 微信小程序信息模板
 * 调解/投诉状态
 */

@Controller
@RequestMapping(value = "${adminPath}/applet/information")
public class InformationController extends BaseController {

    @Autowired
    AppletService appletService;

    @ModelAttribute
    public Applet get() {
        Applet applet = appletService.getApplet();
        if (applet == null) {
            return new Applet();
        } else {
            return appletService.getApplet();
        }
    }

    @RequiresPermissions("applet:information:view")
    @RequestMapping(value = "form")
    public String form(Applet applet, Model model) {
        model.addAttribute("information", applet);
        return "modules/applet/information";
    }

    @RequiresPermissions("applet:information:edit")
    @RequestMapping(value = "save")
    public String save(Applet applet, Model model, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/applet/information/form";
        }
        if (!beanValidator(model, applet)) {
            return form(applet, model);
        }

        Applet existsApplet = appletService.getApplet();
        logger.info(String.valueOf(existsApplet));
        logger.info(String.valueOf(applet));

        appletService.save(applet);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/applet/information/form";
    }
}
