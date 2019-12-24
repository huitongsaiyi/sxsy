package com.sayee.sxsy.api.publicnotice.dao;

import com.sayee.sxsy.api.publicnotice.entity.PublicNotice;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * @author www.donxon.com
 * @Description
 */
@MyBatisDao
public interface PublicNoticeDao extends CrudDao<PublicNotice> {
    List<PublicNotice> getNoticeByType(String type);
}
