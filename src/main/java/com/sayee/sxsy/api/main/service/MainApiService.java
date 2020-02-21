package com.sayee.sxsy.api.main.service;

import com.sayee.sxsy.api.main.dao.MainApiDao;
import com.sayee.sxsy.api.main.entity.Banner;
import com.sayee.sxsy.api.main.entity.MainApi;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 首页
 */
@Service
public class MainApiService extends CrudService<MainApiDao, MainApi> {
    @Autowired
    private MainApiDao mainApiDao;
    public MainApi getMainInfo(){
        return mainApiDao.getMainInfo();
    }
    public List<Banner> getBanner(){
        return mainApiDao.getBanner();
    }
}
