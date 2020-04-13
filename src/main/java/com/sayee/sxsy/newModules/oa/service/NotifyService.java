package com.sayee.sxsy.newModules.oa.service;

import com.alibaba.fastjson.JSON;

import com.sayee.sxsy.newModules.oa.dao.OaNotifyMapper;
import com.sayee.sxsy.newModules.oa.entity.OaNotify;
import com.sayee.sxsy.newModules.oa.entity.OaNotifyExample;

import com.sayee.sxsy.newModules.utils.RedisUtil;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * 新闻动态
 */

@Service
@Transactional(readOnly = true)
public class NotifyService {
    @Autowired
    OaNotifyMapper mapper;

    @Autowired
    RedisUtil redisUtil;


    /**
     * 分页查询
     *
     * @param page 页码
     * @param size 每页数量
     *             放入缓存
     * @return
     */
    public ResponsesUtils getNotify(Integer page, Integer size) {
        page =page*size-1;
        size =page+size-1;

        if (!redisUtil.exists("notify")) {
            OaNotifyExample example = new OaNotifyExample();
            example.setOrderByClause("`UPDATE_DATE` DESC");
            List<OaNotify> list1 = mapper.selectByExampleWithBLOBs(example);
            redisUtil.set("notify",list1);
            System.out.println("写缓存");
        }
        System.out.println("读缓存");
        Object notify = redisUtil.get("notify");
        return ResponsesUtils.ok(notify);


    }


}
