package com.sayee.sxsy.api.main.web;

import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.main.entity.MainApi;
import com.sayee.sxsy.api.main.service.MainApiService;
import com.sayee.sxsy.api.publicnotice.entity.PublicNotice;
import com.sayee.sxsy.api.publicnotice.service.PublicNoticeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 首页数据
 */
@RequestMapping("${adminPath}/api")
@Controller
public class MainController {
    @Autowired
    private PublicNoticeApiService publicNoticeApiService;
    @Autowired
    private MainApiService mainApiService;
    /*首页展示数据*/
    @RequestMapping("getmaininfo")
    @ResponseBody
    public R getMainInfo(){
        List<PublicNotice> list=publicNoticeApiService.getMainPublicNotice();
        MainApi mainApi=mainApiService.getMainInfo();
        if(null!=mainApi){
            Map map=new HashMap<>();
            String indexBanner=null==mainApi.getFocusPicture()||mainApi.getFocusPicture().isEmpty()?"/sxsy/userfiles/1/images/applet/content/2019/12/2.png":mainApi.getFocusPicture();
            String indexText=null==mainApi.getUseTips()||mainApi.getUseTips().isEmpty()?"使用说明":mainApi.getUseTips();
            map.put("indexBanner",indexBanner);
            map.put("indexText",indexText);
            map.put("isOpenPop",mainApi.getIsOpenPop());
            map.put("noticeList",list);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",map);
            return r;
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","内容未配置");
            r.put("RtnData","");
            return r;
        }

    }
    /*咨询展示数据*/
    @RequestMapping("getconsultcontent")
    @ResponseBody
    public R getConsultContent(){
        MainApi mainApi=mainApiService.getMainInfo();
        if(null!=mainApi){
            String caseTips=null==mainApi.getConsultTips()||mainApi.getConsultTips().isEmpty()?"1.本平台旨在征集医疗纠纷调解服务方面的咨询。<br/>2.为了你的咨询得到及时有效处理和回应,请填写真实信息。<br/>3.请自觉遵守中华人民共和国宪法和法律。<br/>感谢您的理解与支持":mainApi.getConsultTips();
            Map map=new HashMap<>();
            map.put("content",caseTips);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",map);
            return r;
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","内容未配置");
            r.put("RtnData","");
            return r;
        }

    }
    /*经典案例提示内容*/
    @RequestMapping("getcasecontent")
    @ResponseBody
    public R getCaseContent(){
        Map map=new HashMap<>();
        map.put("content","建议市民如果发生了医患纠纷，在与医院协商无果的情况，可到医院所在区的医调委寻求帮助，调解免费");
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",map);
        return r;
    }
}
