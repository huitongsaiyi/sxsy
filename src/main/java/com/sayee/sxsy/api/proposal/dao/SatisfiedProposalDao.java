package com.sayee.sxsy.api.proposal.dao;

import com.sayee.sxsy.api.proposal.entity.CommentEntity;
import com.sayee.sxsy.api.proposal.entity.Satisfied;
import com.sayee.sxsy.api.statistic.entity.StatisticEntity;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author www.donxon.com
 * @Description
 */
@MyBatisDao
public interface SatisfiedProposalDao extends CrudDao<StatisticEntity> {
    /*满意度 添加*/
    void save(Satisfied satisfied);

    /*投诉意见 添加*/
    void saveComment(CommentEntity commentEntity);
   /*满意度 列表*/
    List<Satisfied> findList(Satisfied satisfied);
}
