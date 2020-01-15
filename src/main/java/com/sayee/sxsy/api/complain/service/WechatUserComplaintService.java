package com.sayee.sxsy.api.complain.service;

import com.sayee.sxsy.api.complain.dao.WechatUserComplaintDao;
import com.sayee.sxsy.api.complain.entity.WechatUserComplaint;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class WechatUserComplaintService extends CrudService<WechatUserComplaintDao,WechatUserComplaint> {
    @Autowired
    private WechatUserComplaintDao wechatUserComplaintDao;
    public void save(WechatUserComplaint wechatUserComplaint){
        wechatUserComplaintDao.insert(wechatUserComplaint);
    }
}
