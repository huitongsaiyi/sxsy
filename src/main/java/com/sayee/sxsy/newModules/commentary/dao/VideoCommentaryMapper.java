package com.sayee.sxsy.newModules.commentary.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.commentary.entity.VideoCommentary;
import com.sayee.sxsy.newModules.commentary.entity.VideoCommentaryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface VideoCommentaryMapper {
    int countByExample(VideoCommentaryExample example);

    int deleteByExample(VideoCommentaryExample example);

    int deleteByPrimaryKey(String commentId);

    int insert(VideoCommentary record);

    int insertSelective(VideoCommentary record);

    List<VideoCommentary> selectByExample(VideoCommentaryExample example);

    VideoCommentary selectByPrimaryKey(String commentId);

    int updateByExampleSelective(@Param("record") VideoCommentary record, @Param("example") VideoCommentaryExample example);

    int updateByExample(@Param("record") VideoCommentary record, @Param("example") VideoCommentaryExample example);

    int updateByPrimaryKeySelective(VideoCommentary record);

    int updateByPrimaryKey(VideoCommentary record);
}