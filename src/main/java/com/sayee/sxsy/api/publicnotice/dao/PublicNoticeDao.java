package com.sayee.sxsy.api.publicnotice.dao;

import com.sayee.sxsy.api.publicnotice.entity.PublicNotice;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * @Description
 */
@MyBatisDao
public interface PublicNoticeDao extends CrudDao<PublicNotice> {
    List<PublicNotice> getNoticeByType(Map map);
    List<PublicNotice> getMainPublicNotice();
    PublicNotice getPublicInfo(String id);
    int getTotal(String type);
}
