package com.sayee.sxsy.api.train.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.train.entity.TrainEntity;
import com.sayee.sxsy.api.train.service.TrainApiService;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.modules.train.entity.Train;
import com.sayee.sxsy.modules.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description 机构信息
 */
@Controller
@RequestMapping("${adminPath}/api")
public class TrainApiController {
    @Autowired
    private TrainApiService trainApiService;

    @Autowired
    private TrainService trainService;
    /*
    *返回list页面
    * */
    @RequestMapping("getVideo")
    @ResponseBody
    public R getVideo(@RequestBody JSONObject jsonObject){
        int pageNo=1;
        if(null!=jsonObject.getInteger("pageNo")) {
            pageNo = jsonObject.getInteger("pageNo");
        }
        int pageSize= 5;
        if(null!=jsonObject.getInteger("pageSize")){
            pageSize=jsonObject.getInteger("pageSize");
        }
        String keyWord=jsonObject.getString("keyWord");
        TrainEntity trainEntity=new TrainEntity();
        trainEntity.setSend("1");
        trainEntity.setKeyWord(keyWord);
        Page<TrainEntity> train=trainApiService.getVideo(new Page(pageNo,pageSize), trainEntity);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg",train.getList().size()>0?"success":"error");
        r.put("RtnData",train);
        return r;
    }
    /*播放视频 详情页面 展示 介绍 课程路径*/
    @RequestMapping("videoDetail")
    @ResponseBody
    public R videoDetail(@RequestBody JSONObject jsonObject){
        String id=jsonObject.getString("id");
        if(null!= id){
            TrainEntity train=trainApiService.videoDetail(id);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",train);
            return r;
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","error");
            r.put("RtnData","请先选择科室!");
            return r;
        }
    }
}
