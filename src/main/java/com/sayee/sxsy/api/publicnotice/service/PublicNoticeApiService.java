package com.sayee.sxsy.api.publicnotice.service;

import com.sayee.sxsy.api.publicnotice.dao.PublicNoticeDao;
import com.sayee.sxsy.api.publicnotice.entity.PublicNotice;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.donxon.com
 * @Description
 */
@Service
public class PublicNoticeApiService extends CrudService<PublicNoticeDao,PublicNotice> {
    @Autowired
    private PublicNoticeDao publicNoticeDao;
    public List<PublicNotice> getPublicNotice(String type){
        return publicNoticeDao.getNoticeByType(type);
    }
}
