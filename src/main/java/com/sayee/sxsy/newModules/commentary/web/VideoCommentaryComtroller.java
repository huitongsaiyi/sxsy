package com.sayee.sxsy.newModules.commentary.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.newModules.commentary.entity.VideoCommentary;
import com.sayee.sxsy.newModules.commentary.service.VideoCommentaryService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 视频评论表
 */
@Controller
public class VideoCommentaryComtroller {
    @Autowired
    VideoCommentaryService service;

    /**
     * 保存用户评论
     * videocommentary
     * @param jsonObject
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ysj/saveCommentary",method = RequestMethod.POST)
    public ResponsesUtils savecommentary(@RequestBody JSONObject jsonObject){

        VideoCommentary videoCommentary = jsonObject.toJavaObject(VideoCommentary.class);

        Integer integer = service.saveCommentById(videoCommentary);

        if (integer==0) {
            return ResponsesUtils.build(500,"评论失败");
        }

        return ResponsesUtils.ok();
    }

    /**
     * 查询视频评论
     * parentid
     * videoid
     * @param jsonObject
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ysj/fandcommentary")
    public ResponsesUtils fandcommentary(@RequestBody JSONObject jsonObject){
        VideoCommentary videoCommentary = jsonObject.toJavaObject(VideoCommentary.class);
        return ResponsesUtils.ok(service.fandComment(videoCommentary));

    }
}
