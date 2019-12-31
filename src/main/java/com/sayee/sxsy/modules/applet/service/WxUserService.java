package com.sayee.sxsy.modules.applet.service;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.applet.dao.WxUserDao;
import com.sayee.sxsy.modules.applet.entity.WxUser;
import com.sayee.sxsy.modules.cms.entity.Site;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 微信小程序用户管理
 */

@Service
@Transactional(readOnly = true)
public class WxUserService extends CrudService<WxUserDao, WxUser> {
    public Page<WxUser> findPage(Page<WxUser> page, WxUser wxUser) {
        wxUser.getSqlMap().put("wxUser", dataScopeFilter(wxUser.getCurrentUser(), "o", "u"));
        return super.findPage(page, wxUser);
    }
}
