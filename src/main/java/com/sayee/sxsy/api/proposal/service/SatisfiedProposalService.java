package com.sayee.sxsy.api.proposal.service;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.proposal.dao.SatisfiedProposalDao;
import com.sayee.sxsy.api.proposal.entity.CommentEntity;
import com.sayee.sxsy.api.proposal.entity.Satisfied;
import com.sayee.sxsy.api.statistic.entity.StatisticEntity;
import com.sayee.sxsy.api.user.entity.UserInfo;
import com.sayee.sxsy.api.user.service.UserApiService;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author www.donxon.com
 * @Description
 */
@Service
public class SatisfiedProposalService extends CrudService<SatisfiedProposalDao, StatisticEntity> {
    @Autowired
    private UserApiService userApiService;

    @Autowired
    private SatisfiedProposalDao satisfiedProposalDao;

    //小程序端的添加操作，后台不进行添加 满意度
    @Transactional(readOnly = false)
    public void save(Satisfied satisfied, JSONObject jsonObject) {
        String satisfiedId=jsonObject.getString("satisfiedId");//如果以后修改就需要传 主键，根据主键判断是添加 还是修改
        satisfied.preInsert();
        satisfied.setSatisfiedId(satisfied.getId());
        String wechatUserId = jsonObject.getString("wechatUserId");
        String uid = userApiService.getSysUserId(wechatUserId);
        //String uid=jsonObject.getString("uid");
        String complaintMainId=jsonObject.getString("complaintMainId");//主表 主键
        String ability=jsonObject.getString("ability");//调解能力
        String attitude=jsonObject.getString("attitude");//服务态度
        String meter=jsonObject.getString("meter");//仪容仪表
        String assess=jsonObject.getString("assess");//评价
        String proposal=jsonObject.getString("proposal");//建议
        satisfied.setComplaintMainId(complaintMainId);
        satisfied.setAbility(ability);
        satisfied.setAttitude(attitude);
        satisfied.setMeter(meter);
        satisfied.setAssess(assess);
        satisfied.setProposal(proposal);
        User user=UserUtils.get(uid);
        if(user!=null){
            satisfied.setSatisfiedName(user.getName());
            satisfied.setCreateBy(user);
            satisfied.setUpdateBy(user);
        }else{
            UserInfo userInfo=userApiService.getUserInfoByUserId(wechatUserId);
            if(userInfo!=null){
                satisfied.setSatisfiedName(userInfo.getNickName());
                satisfied.setCreateBy(new User(wechatUserId));
                satisfied.setUpdateBy(new User(wechatUserId));
            }
        }
        satisfiedProposalDao.save(satisfied);
    }
    public Page<Satisfied> findPage(Page<Satisfied> page, Satisfied satisfied) {
        List<Satisfied> list=satisfiedProposalDao.findList(satisfied);
        for (Satisfied ss:list) {
            List<Integer> score=new ArrayList<>();
            score.add(StringUtils.toInteger(ss.getAbility()));
            score.add(StringUtils.toInteger(ss.getAttitude()));
            score.add(StringUtils.toInteger(ss.getMeter()));
            ss.setScores(score);
        }
        satisfied.setPage(page);
        page.setList(list);
        return page;
    }


    public void saveComment(CommentEntity commentEntity, JSONObject jsonObject) {
        String satisfiedId=jsonObject.getString("id");//如果以后修改就需要传 主键，根据主键判断是添加 还是修改
        commentEntity.preInsert();
        String wechatUserId = jsonObject.getString("wechatUserId");
        String uid = userApiService.getSysUserId(wechatUserId);
        //String uid=jsonObject.getString("uid");
        String content=jsonObject.getString("content");//投诉建议内容
        commentEntity.setContent(content);
        User user= UserUtils.get(uid);
        if(user!=null){
            commentEntity.setName(user.getName());
            commentEntity.setCreateBy(user);
            commentEntity.setUpdateBy(user);
        }else{
            UserInfo userInfo=userApiService.getUserInfoByUserId(wechatUserId);
            if(userInfo!=null){
                commentEntity.setName(userInfo.getNickName());
                commentEntity.setCreateBy(new User(wechatUserId));
                commentEntity.setUpdateBy(new User(wechatUserId));
            }
        }
        commentEntity.setCreateDate(new Date());
        commentEntity.setUpdateDate(new Date());
        satisfiedProposalDao.saveComment(commentEntity);
    }


}
