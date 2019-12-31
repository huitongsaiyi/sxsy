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
 * 内容管理
 * 焦点图\纠纷须知\使用须知\经验案例说明
 */

@Controller
@RequestMapping(value = "${adminPath}/applet/content")
public class ContentController extends BaseController {

    @Autowired
    private AppletService appletService;

    @ModelAttribute
    public Applet get() {
        Applet applet = appletService.getApplet();
        if (applet == null) {
            return new Applet();
        } else {
            return appletService.getApplet();
        }
    }

    @RequiresPermissions("applet:content:view")
    @RequestMapping(value = "setting")
    public String form(Applet applet, Model model) {

        model.addAttribute("content", applet);
        return "modules/applet/content";
    }

    @RequiresPermissions("applet:content:edit")
    @RequestMapping(value = "save")
    public String save(Applet applet, Model model, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/applet/content/setting";
        }
        if (!beanValidator(model, applet)) {
            return form(applet, model);
        }
        appletService.save(applet);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/applet/content/setting";
    }

}
