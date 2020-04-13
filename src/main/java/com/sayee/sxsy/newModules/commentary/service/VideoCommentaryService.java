package com.sayee.sxsy.newModules.commentary.service;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.newModules.commentary.dao.VideoCommentaryMapper;
import com.sayee.sxsy.newModules.commentary.entity.VideoCommentary;
import com.sayee.sxsy.newModules.commentary.entity.VideoCommentaryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)

public class VideoCommentaryService {
    @Autowired
    VideoCommentaryMapper mapper;

    /**
     * 保存评论
     *
     * @param videoCommentary
     * @return
     */
    @Transactional(readOnly = false)
    public Integer saveCommentById(VideoCommentary videoCommentary) {
        //生成uuid主键
        videoCommentary.setCommentId(IdGen.uuid());
        if (videoCommentary.getParentId() == null) {
            videoCommentary.setParentId("0");
            return mapper.insertSelective(videoCommentary);
        }

        return mapper.insertSelective(videoCommentary);

    }

    /**
     * 查询评论 通过 父级id+视频id
     *
     * @param videoCommentary
     * @return
     */
    public List<VideoCommentary> fandComment(VideoCommentary videoCommentary) {
        VideoCommentaryExample example = new VideoCommentaryExample();

        example.createCriteria().andVideoIdEqualTo(videoCommentary.getVideoId());

        example.createCriteria().andParentIdEqualTo(videoCommentary.getParentId());
        return mapper.selectByExample(example);

    }
}
