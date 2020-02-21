package com.sayee.sxsy.api.dailyreport.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.dailyreport.entity.EgressInfoApi;
import com.sayee.sxsy.api.dailyreport.entity.EpidemicDailyApi;
import com.sayee.sxsy.api.dailyreport.service.EgressInfoApiService;
import com.sayee.sxsy.api.dailyreport.service.EpidemicDailyApiService;
import com.sayee.sxsy.api.user.service.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description 疫情报告
 */
@Controller
@RequestMapping("${adminPath}/api")
public class DailyReportApiController {
    @Autowired
    private EgressInfoApiService egressInfoApiService;
    @Autowired
    private EpidemicDailyApiService epidemicDailyApiService;
    @Autowired
    private UserApiService userApiService;
    @RequestMapping("savedailyreport")
    @ResponseBody
    public R saveDailyReport(@RequestBody JSONObject jsonObject){
            /*
                private String epidemicId;
                private String wechatUserId;//用户id
                private String workUnit;//工作单位
                private String fillingDate;//填表日期
                private String userName;//姓名
                private String userSex;//性别
                private String userAge;//年龄
                private String userAddress;//住址
                private String temperature;//体温
                private String isEgress;
                private String healthCondition;//健康状况
                private String familyHealthCondition;//家人健康状况
                private String workRemark;//工作情况备注
                private String workSituation;//是否完成
            */
        //JSONObject getData=jsonObject.getJSONObject("epidemicDaily");
        JSONObject jsonObject1=jsonObject.getJSONObject("epidemicDailyApi");
        System.out.println(jsonObject);
        if(null!=jsonObject1&&!jsonObject.isEmpty()) {
            EpidemicDailyApi epidemicDailyApi = new EpidemicDailyApi();
            epidemicDailyApi.preInsert();
            String wechatUserId=jsonObject1.getString("wechatUserId");
            String epidemicId = epidemicDailyApi.getId();
            epidemicDailyApi.setEpidemicId(epidemicDailyApi.getId());
            epidemicDailyApi.setWechatUserId(wechatUserId);
            epidemicDailyApi.setWorkUnit(jsonObject1.getString("workUnit"));
            String fillingDate=jsonObject1.getString("fillingDate");
            epidemicDailyApi.setFillingDate(fillingDate.replace("/","-"));
            epidemicDailyApi.setUserName(jsonObject1.getString("userName"));
            epidemicDailyApi.setUserSex("0".equals(jsonObject1.getString("userSex")) ? "1" :"2");
            epidemicDailyApi.setUserAge(jsonObject1.getString("userAge"));
            epidemicDailyApi.setUserAddress(jsonObject1.getString("userAddress"));
            epidemicDailyApi.setTemperature(jsonObject1.getString("temperature"));
            epidemicDailyApi.setIsEgress(jsonObject1.getString("isEgress"));
            epidemicDailyApi.setHealthCondition(jsonObject1.getString("healthCondition"));
            epidemicDailyApi.setFamilyHealthCondition(jsonObject1.getString("familyHealthCondition"));
            epidemicDailyApi.setWorkRemark("true");
            epidemicDailyApi.setWorkSituation(jsonObject1.getString("workRemark"));
            epidemicDailyApi.setCreateDate(new Date());
            epidemicDailyApi.setUpdateDate(new Date());
            String sysUserId=userApiService.getSysUserId(wechatUserId);
            epidemicDailyApi.setCreateUser(sysUserId);
            epidemicDailyApiService.save(epidemicDailyApi);
            JSONArray jsonArray = jsonObject.getJSONArray("egressInfoApi");
            if (null!=jsonArray||jsonArray.size() > 0) {
                for (int j = 0; j < jsonArray.size(); j++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(j);
                    /*
                    private String egressId;
                    private String epidemicId;//日报表id
                    private String egressName;//人员姓名
                    private String egressSex;//性别
                    private String egressAge;//年龄
                    private String relation;//与填表人关系
                    private String trainFlight;//车次/航班
                    private String userAddress;//住址
                    private String temperature;//体温
                    private String healthCondition;//健康状况
                    private String isEgress;//是否外出
                    private String egressTime;//外出时间
                    private String egressMode;//外出方式
                    private String egressPlace;//外出地点
                    private String returnTime;//返回时间
                    private String returnMode;//返回方式
                    private String returnPlace;//返回地点
                    */
                    EgressInfoApi egressInfoApi = new EgressInfoApi();
                    egressInfoApi.preInsert();
                    egressInfoApi.setEgressId(egressInfoApi.getId());
                    egressInfoApi.setEpidemicId(epidemicId);
                    egressInfoApi.setEgressName(jsonObject2.getString("egressName"));
                    egressInfoApi.setEgressAge(jsonObject2.getString("egressAge"));
                    egressInfoApi.setEgressSex("0".equals(jsonObject2.getString("egressSex")) ? "1" :"2"   );
                    String relation=jsonObject2.getString("relation");
                    Integer getRelation;
                    if(relation.equals("9")){
                        getRelation=1;
                    }else{
                        getRelation=Integer.valueOf(relation)+2;
                    }
                    egressInfoApi.setRelation(getRelation.toString());
                    egressInfoApi.setTrainFlight(jsonObject2.getString("trainFlight"));
                    egressInfoApi.setUserAddress(jsonObject2.getString("userAddress"));
                    egressInfoApi.setTemperature(jsonObject2.getString("temperature"));
                    egressInfoApi.setHealthCondition(jsonObject2.getString("healthCondition"));
                    egressInfoApi.setIsEgress(jsonObject2.getString("isEgress"));
                    egressInfoApi.setEgressTime(jsonObject2.getString("egressTime"));
                    egressInfoApi.setEgressMode(jsonObject2.getString("egressMode"));
                    egressInfoApi.setEgressPlace(jsonObject2.getString("egressPlace"));
                    egressInfoApi.setReturnMode(jsonObject2.getString("returnMode"));
                    egressInfoApi.setReturnPlace(jsonObject2.getString("returnPlace"));
                    egressInfoApi.setReturnTime(jsonObject2.getString("returnTime"));
                    egressInfoApi.setCreateDate(new Date());
                    egressInfoApi.setUpdateDate(new Date());
                    egressInfoApi.setCreateUser(sysUserId);
                    egressInfoApiService.save(egressInfoApi);
                }
            }
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData","");
            return r;
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","提交失败");
            r.put("RtnData","");
            return r;
        }

        /*
            private String egressId;
            private String epidemicId;//日报表id
            private String egressName;//人员姓名
            private String egressSex;//性别
            private String egressAge;//年龄
            private String relation;//与填表人关系
            private String trainFlight;//车次/航班
            private String userAddress;//住址
            private String temperature;//体温
            private String healthCondition;//健康状况
            private String isEgress;//是否外出
            private String egressTime;//外出时间
            private String egressMode;//外出方式
            private String egressPlace;//外出地点
            private String returnTime;//返回时间
            private String returnMode;//返回方式
            private String returnPlace;//返回地点
            */
        /*
            {
              "epidemicDaily": {
                  "wechatUserId": "用户id",
                  "workUnit": "工作单位",
                  "fillingDate": "填表日期",
                  "userName": "姓名",
                  "userSex": "性别",
                  "userAge": "年龄",
                  "userAddress": "住址",
                  "temperature": "体温",
                  "isEgress":"",
                  "healthCondition":"健康状况",
                  "familyHealthCondition":"家人健康状况",
                  "workRemark":"工作情况备注",
                  "workSituation":"是否完成"
              },
              " egressInfo":[
                {
                  "relation":"关系",
                  "egressName": "人员姓名",
                  "egressSex":"性别",
                  "egressAge":"年龄",
                  "trainFlight":"车次/航班号"，
                  "userAddress":"住址",
                  "temperature":"体温",
                  "healthCondition":"健康状况",
                  "isEgress": "是否外出",
                  "egressMode": "外出方式",
                  "egressTime":"外出时间",
                  "egressPlace": "外出地点",
                  "returnTime": "返回时间",
                  "returnMode": "返回方式",
                  "returnPlace": "返回地点"
                },
                {
                  "relation": "关系",
                  "egressName": "人员姓名",
                  "egressSex":"性别",
                  "egressAge":"年龄",
                  "trainFlight":"车次/航班号"，
                  "userAddress":"住址",
                  "temperature":"体温",
                  "healthCondition":"健康状况",
                  "isEgress": "是否外出",
                  "egressMode": "外出方式",
                  "egressTime":"外出时间",
                  "egressPlace": "外出地点",
                  "returnTime": "返回时间",
                  "returnMode": "返回方式",
                  "returnPlace": "返回地点"
                }
              ]
            }
        */

    }
    @RequestMapping("getdailyreportinfo")
    @ResponseBody
    public R getDailyReportInfo(@RequestBody JSONObject jsonObject){
        String wechatUserId=jsonObject.getString("wechatUserId");
        String[] strings=epidemicDailyApiService.getDailyReportInfo(wechatUserId);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",strings);
        return r;
    }
    @RequestMapping("getisreport")
    @ResponseBody
    public R getIsReport(@RequestBody JSONObject jsonObject){
        String wechatUserId=jsonObject.getString("wechatUserId");
        int count=epidemicDailyApiService.getIsReport(wechatUserId);
        boolean isReport;
        if(count>0){
            isReport=true;
        }else{
            isReport=false;
        }
        Map map=new HashMap<>();
        map.put("isReport",isReport);
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",map);
        return r;
    }
}
