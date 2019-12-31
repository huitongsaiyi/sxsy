package com.sayee.sxsy.api.publicnotice.service;

import com.sayee.sxsy.api.publicnotice.dao.LawCaseApiDao;
import com.sayee.sxsy.api.publicnotice.entity.LawCaseApi;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 */
@Service
public class LawCaseApiService extends CrudService<LawCaseApiDao, LawCaseApi> {
    @Autowired
    private LawCaseApiDao lawCaseApiDao;
    public Page<LawCaseApi> findPage(Page<LawCaseApi> page, LawCaseApi lawCase) {
        return findPage(page, lawCase);
    }
    public List<LawCaseApi> getPageLawCase(Integer page,Integer pageSize,String type){
        int pageLimit=(page-1)*pageSize;
        Map map=new HashMap<>();
        map.put("pageLimit",pageLimit);
        map.put("pageSize",pageSize);
        map.put("type",type);
        return lawCaseApiDao.getPageLawCase(map);
    }
    public LawCaseApi getServiceByType(String type){
        return lawCaseApiDao.getServiceByType(type);
    }
    public int getTotal(String type){
        return lawCaseApiDao.getTotal(type);
    }
    public LawCaseApi getLawCaseInfo(String id){
        return lawCaseApiDao.getLawCaseInfo(id);
    }
}
