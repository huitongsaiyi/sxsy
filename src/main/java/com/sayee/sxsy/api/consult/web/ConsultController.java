package com.sayee.sxsy.api.consult.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.consult.entity.ConsultApi;
import com.sayee.sxsy.api.consult.entity.ConsultApiEntity;
import com.sayee.sxsy.api.consult.entity.Station;
import com.sayee.sxsy.api.consult.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Description 咨询
 */
@Controller
@RequestMapping("${adminPath}/api")
public class ConsultController {
    @Autowired
    private ConsultService consultService;

    /*添加咨询*/
    @RequestMapping("consultadd")
    @ResponseBody
    public R addConsult(@RequestBody JSONObject jsonObject) {
        String wechatUserId = jsonObject.getString("wechatUserId");
        String content = jsonObject.getString("content");
        String name = jsonObject.getString("name");
        String tel = jsonObject.getString("tel");
        //1患者 2 医院
        String type = null == jsonObject.getString("type") || jsonObject.getString("type").isEmpty() ? "1" : jsonObject.getString("type");
        if (null == content || content.isEmpty()) {
            R r = new R();
            r.put("RtnCode", 2);
            r.put("RtnMsg", "内容不能为空");
            r.put("RtnData", "");
            return r;
        } else if (null == wechatUserId || wechatUserId.isEmpty()) {
            R r = new R();
            r.put("RtnCode", 3);
            r.put("RtnMsg", "用户id不能为空");
            r.put("RtnData", "");
            return r;
        } else {
            ConsultApiEntity consultApiEntity = new ConsultApiEntity();
            consultApiEntity.preInsert();
            consultApiEntity.setId(consultApiEntity.getId());
            consultApiEntity.setContent(content);
            consultApiEntity.setName(name);
            consultApiEntity.setPhone(tel);
            consultApiEntity.setType(type);
            consultApiEntity.setCreateDate(new Date());
            consultApiEntity.setUpdateDate(new Date());
            consultApiEntity.setCreateUser(wechatUserId);
            try {
                consultService.addConsult(consultApiEntity);
                R r = new R();
                r.put("RtnCode", 0);
                r.put("RtnMsg", "添加成功");
                r.put("RtnData", "");
                return r;
            } catch (Exception e) {
                System.out.println(e);
                R r = new R();
                r.put("RtnCode", 1);
                r.put("RtnMsg", "添加失败");
                r.put("RtnData", "");
                return r;
            }
        }
    }


    /*咨询列表*/
    @RequestMapping("consultlist")
    @ResponseBody
    public R consultList() {
        List<ConsultApi> dataList = consultService.getConsultList();
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", dataList);
        return r;
    }

    /*用户咨询列表*/
    @RequestMapping("getconsultlistbyuid")
    @ResponseBody
    public R getConsultListByUid(@RequestBody JSONObject jsonObject) {
        String wechatUserId = jsonObject.getString("wechatUserId");
        List<ConsultApi> dataList = consultService.getConsultListByUid(wechatUserId);
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", dataList);
        return r;
    }
    @RequestMapping("getconsultinfo")
    @ResponseBody
    public R getConsultInfo(){
        List list=new ArrayList();
        Station station1=new Station();
        station1.setName("太原工作站");
        station1.setPerson("牛主任");
        station1.setPhone("13834621075");

        Station station2=new Station();
        station2.setName("长治工作站");
        station2.setPerson("王主任");
        station2.setPhone("13903452608");

        Station station3=new Station();
        station3.setName("晋城工作站");
        station3.setPerson("杨主任");
        station3.setPhone("13753656289");

        Station station4=new Station();
        station4.setName("运城工作站");
        station4.setPerson("高主任");
        station4.setPhone("13834464498");

        Station station5=new Station();
        station5.setName("临汾工作站");
        station5.setPerson("王主任");
        station5.setPhone("13133188766");

        Station station6=new Station();
        station6.setName("晋中工作站");
        station6.setPerson("张主任");
        station6.setPhone("13015436110");

        Station station7=new Station();
        station7.setName("古交工作站");
        station7.setPerson("胡主任");
        station7.setPhone("18734167528");

        Station station8=new Station();
        station8.setName("阳泉工作站");
        station8.setPerson("郭主任");
        station8.setPhone("13994470436");

        Station station9=new Station();
        station9.setName("朔州工作站");
        station9.setPerson("张主任");
        station9.setPhone("15634903011");

        Station station10=new Station();
        station10.setName("忻州工作站");
        station10.setPerson("冯主任");
        station10.setPhone("15803500325");

        Station station11=new Station();
        station11.setName("大同工作站");
        station11.setPerson("李主任");
        station11.setPhone("13903427689");

        Station station12=new Station();
        station12.setName("吕梁工作站");
        station12.setPerson("史主任");
        station12.setPhone("13935885577");

        Station station13=new Station();
        station13.setName("孝义工作站");
        station13.setPerson("张主任");
        station13.setPhone("13935814484");

        list.add(station1);
        list.add(station2);
        list.add(station3);
        list.add(station4);
        list.add(station5);
        list.add(station6);
        list.add(station7);
        list.add(station8);
        list.add(station9);
        list.add(station10);
        list.add(station11);
        list.add(station12);
        list.add(station13);

        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", list);
        return r;
    }
}
