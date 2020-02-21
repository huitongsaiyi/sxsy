package com.sayee.sxsy.api.main.dao;

import com.sayee.sxsy.api.main.entity.Banner;
import com.sayee.sxsy.api.main.entity.MainApi;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * @Description 首页
 */
@MyBatisDao
public interface MainApiDao extends CrudDao<MainApi> {
    MainApi getMainInfo();
    List<Banner> getBanner();
}
