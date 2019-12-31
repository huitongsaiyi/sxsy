package com.sayee.sxsy.modules.applet.service;

import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.applet.dao.AppletDao;
import com.sayee.sxsy.modules.applet.entity.Applet;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 微信小程序设置
 */

@Service
@Transactional(readOnly = true)
public class AppletService extends CrudService<AppletDao, Applet> {

    @Transactional(readOnly = false)
    public Applet getApplet() {
        return dao.getApplet();
    }

    @Transactional(readOnly = false)
    public void save(Applet applet) {
        if (applet.getUseTips()!=null){
            applet.setUseTips(StringEscapeUtils.unescapeHtml4(applet.getUseTips()));
        }
        if (applet.getConsultTips() != null) {
            applet.setConsultTips(StringEscapeUtils.unescapeHtml4(applet.getConsultTips()));
        }
        if (applet.getCaseTips() != null) {
            applet.setCaseTips(StringEscapeUtils.unescapeHtml4(applet.getCaseTips()));
        }
        if (applet.getIsOpenPop() == null) {
            applet.setIsOpenPop("0");
        }
        super.save(applet);
    }
}
