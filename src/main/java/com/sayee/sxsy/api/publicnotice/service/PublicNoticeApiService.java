package com.sayee.sxsy.api.publicnotice.service;

import com.sayee.sxsy.api.publicnotice.dao.PublicNoticeDao;
import com.sayee.sxsy.api.publicnotice.entity.PublicNotice;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 */
@Service
public class PublicNoticeApiService extends CrudService<PublicNoticeDao,PublicNotice> {
    @Autowired
    private PublicNoticeDao publicNoticeDao;
    public List<PublicNotice> getPublicNotice(Integer page,Integer pageSize,String type){
        int pageLimit=(page-1)*pageSize;
        Map map=new HashMap<>();
        map.put("pageLimit",pageLimit);
        map.put("pageSize",pageSize);
        map.put("type",type);
        return publicNoticeDao.getNoticeByType(map);
    }
    public List<PublicNotice> getMainPublicNotice(){
        return publicNoticeDao.getMainPublicNotice();
    }
    public int getTotal(String type){
        return publicNoticeDao.getTotal(type);
    }

    public PublicNotice getPublicInfo(String id){
        return publicNoticeDao.getPublicInfo(id);
    }
}
