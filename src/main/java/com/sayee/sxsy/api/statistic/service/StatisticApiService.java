package com.sayee.sxsy.api.statistic.service;

import com.sayee.sxsy.api.publicnotice.dao.PublicNoticeDao;
import com.sayee.sxsy.api.statistic.dao.StatisticDao;
import com.sayee.sxsy.api.statistic.entity.StatisticEntity;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.datatype.entity.DataType;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Arc2D;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author www.donxon.com
 * @Description
 */
@Service
public class StatisticApiService extends CrudService<StatisticDao, StatisticEntity> {

    @Autowired
    private StatisticDao statisticDao;

    /**
     * 返回到小程序 基础信息
     * @author zhangfan
     * @version 2019-12-24
     */
    public Map<String,Object> getStatisticalList() {
        List<Map> aa=statisticDao.findDataType();
        List<Map<String,Object>> list=new ArrayList<>();
        List<Map<String,Object>> list2=new ArrayList<>();
        Map<String,Object> firstMap=new HashMap<>();
        Map<String,Object> seMap=new HashMap<>();
        Map<String,Object> oneMap=new HashMap<>();
        Map<String,Object> twoMap=new HashMap<>();
        for (Map data : aa) {
            if ("complaint".equals(MapUtils.getString(data,"maintype",""))){
                Map<String,Object> small=new HashMap<>();
                small.put("icon",MapUtils.getString(data,"icon","")) ;
                small.put("name",MapUtils.getString(data,"name","")) ;
                small.put("type",MapUtils.getString(data,"type","")) ;
                oneMap.put("mainType",MapUtils.getString(data,"maintype","")) ;
                oneMap.put("name",MapUtils.getString(data,"title",""));
                list.add(small);
            }else if("mediate".equals(MapUtils.getString(data,"maintype",""))){
                Map<String,Object> smallTwo=new HashMap<>();
                smallTwo.put("icon",MapUtils.getString(data,"icon","")) ;
                smallTwo.put("name",MapUtils.getString(data,"name","")) ;
                smallTwo.put("type",MapUtils.getString(data,"type","")) ;
                twoMap.put("mainType",MapUtils.getString(data,"maintype","")) ;
                twoMap.put("name",MapUtils.getString(data,"title",""));
                list2.add(smallTwo);
            }
        }
        oneMap.put("list",list);
        twoMap.put("list",list2);
        seMap.put(MapUtils.getString(oneMap,"mainType","other"),oneMap);
        seMap.put(MapUtils.getString(twoMap,"mainType","other"),twoMap);
        firstMap.put("statisticalData",seMap);
        return seMap;
    }

    public List<Map<String,Object>> getStatisticalDatail(String type,String uid) {
        //最外层那个list
        User user= UserUtils.get(uid);
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> firstMap=new HashMap<>();
        if ("complaintQuantityAnalysis".equals(type)){//投诉统计 投诉数量分析
            //每月数据统计
            List<Map<String,Object>> MonthDataList=this.getEveryMonthData(user,"","","","ts");
            list.add(this.formatMap(MonthDataList,"month"));
            //各市数据统计
            List<Map<String,Object>> areaList=this.findAreaName(user,"","","","ts");
            list.add(this.formatMap(areaList,type));
        }else if("complaintTypeAnalysis".equals(type)){//投诉统计 投诉类型分析
            List<Map<String,Object>> typeList=this.findTypeInfo(user,"","","","ts");
            list.add(this.formatMap(typeList,type));
        }else if("professionalAnalysisOfComplaints".equals(type)){//投诉统计  专业
            List<Map<String,Object>> dList=this.findDepartment(user,"","","","ts");
            list.add(this.formatMap(dList,type));
        }else if("analysisOfMediationQuantity".equals(type)){//调解统计  调解数量
            //每月数据统计
            List<Map<String,Object>> MonthDataList=this.getEveryMonthData(UserUtils.getUser(),"","","","tj");
            list.add(this.formatMap(MonthDataList,"tjMonth"));
            //各市数据统计
            List<Map<String,Object>> areaList=this.findAreaName(user,"","","","tj");
            list.add(this.formatMap(areaList,type));
        }else if("focusAnalysisOfDisputes".equals(type)){//调解统计  纠纷焦点分析
            List<Map<String,Object>> typeList=this.findTypeInfo(user,"","","","tj");
            list.add(this.formatMap(typeList,type));
        }else if("professionalAnalysisInvolved".equals(type)){//调解统计  涉及专业分析
            List<Map<String,Object>> dList=this.findDepartment(user,"","","","tj");
            list.add(this.formatMap(dList,type));
        }else if("analysisOfResponsibilityProportion".equals(type)){//调解统计  责任比例分析
            List<Map<String,Object>> dList=this.findDuty(user,"","","","tj");
            list.add(this.formatMap(dList,type));
        }else {//调解 赔付额分析
            List<Map<String, Object>> amountList=this.findAmountRatio(user,"","","","tj");
            list.add(this.formatMap(amountList,type));
        }

        return list;
    }

    //对统计数据进行格式化
    public Map<String,Object> formatMap(List<Map<String,Object>> list,String type){
        Map<String,Object> firstMap=new HashMap<>();
        Map<String,Object> tableMap=new HashMap<>();
        List<Map<String,Object>> headerlist=new ArrayList<>();
        List<Object> valuelist=new ArrayList<>();
        Map<String,Object> xMap=new HashMap<>();
        Map<String,Object> yMap=new HashMap<>();
        if ("month".equals(type) || "tjMonth".equals(type)){//投诉统计 投诉数量分析
            xMap.put("field","'区域名称'");
            yMap.put("field","'数量'");
            firstMap.put("name","各月份投诉数量");
            firstMap.put("keys", ObjectUtils.convert(list.toArray(),"name",true) );
            valuelist=ObjectUtils.convert(list.toArray(),"value",true);
        }else if("complaintQuantityAnalysis".equals(type) || "analysisOfMediationQuantity".equals(type)){//投诉统计 投诉类型分析
            xMap.put("field","'时间'");
            yMap.put("field","'数量'");
            firstMap.put("name","山西省案件投拆量");
            firstMap.put("keys",ObjectUtils.convert(list.toArray(),"name",true) );
            valuelist=ObjectUtils.convert(list.toArray(),"value",true);
        }else if("complaintTypeAnalysis".equals(type) || "focusAnalysisOfDisputes".equals(type)){//投诉统计 投诉类型分析
            xMap.put("field","'焦点名称'");
            yMap.put("field","'数量'");
            firstMap.put("name","纠纷焦点统计");
            firstMap.put("keys",ObjectUtils.convert(list.toArray(),"name",true) );
            valuelist=ObjectUtils.convert(list.toArray(),"value",true);
        }else if("professionalAnalysisOfComplaints".equals(type) || "professionalAnalysisInvolved".equals(type)){//投诉统计  专业
            xMap.put("field","'专业名称'");
            yMap.put("field","'数量'");
            firstMap.put("name","各专业案件统计");
            firstMap.put("keys",ObjectUtils.convert(list.toArray(),"name",true) );
            valuelist=ObjectUtils.convert(list.toArray(),"value",true);
        }else if("analysisOfResponsibilityProportion".equals(type)){//调解统计  责任比例分析
            xMap.put("field","'责任名称'");
            yMap.put("field","'数量'");
            firstMap.put("name","责任度统计");
            firstMap.put("keys",ObjectUtils.convert(list.toArray(),"name",true) );
            valuelist=ObjectUtils.convert(list.toArray(),"value",true);
        }else {//调解 赔付额分析
            xMap.put("field","'赔付额'");
            yMap.put("field","'数量'");
            firstMap.put("name","赔付额统计");
            firstMap.put("keys",ObjectUtils.convert(list.toArray(),"name",true) );
            valuelist=ObjectUtils.convert(list.toArray(),"value",true);
        }
        //valuelist.add((long)999);
        Map<String, Object> max=radixCount(valuelist);
        firstMap.putAll(max);
        firstMap.put("unit","件");
        headerlist.add(xMap);headerlist.add(yMap);
        tableMap.put("record",list);
        tableMap.put("header",headerlist);
        firstMap.put("table",tableMap);
        return firstMap;
    }

    public  Map<String, Object> radixCount(List maxList){
        List<Double> values=new ArrayList<>();
        Map<String, Object> map=new HashMap<>();
        if (maxList.size()==0){
            map.put("radix","20");
            return map;
        }
        long maxNum= (Long) Collections.max(maxList);
           if (maxNum>20){
            double dou = (double)maxNum;
            BigDecimal bg = new BigDecimal(dou/3);
            String num = bg.setScale(0, BigDecimal.ROUND_UP).toString();
            int length=num.length()-1;//得到数字是 几位数
            double d = bg.setScale(-length, BigDecimal.ROUND_UP).doubleValue();// 最大位  进1
            int radix = (int) d;//获得十位
            //对 系数 进行 优化，为20 的倍数
            radix=this.optimize(radix);
            if(radix>20){//是否是 2 的倍数
                double cc=radix/20;
                for (Object object:maxList) {
                    long ccc= (Long) object;
                    values.add(ccc*cc);
                }
                map.put("radix", radix);
                map.put("values",values);
            }else {
                map.put("radix","20");
            }
           }else {
               map.put("radix","20");
           }
           if (StringUtils.isBlank(MapUtils.getString(map,"values",""))){
               map.put("values",maxList);
           }
        return map;
    }

    private int optimize(int radix) {
        if(radix%20!=0){
            radix+=10;
            this.optimize(radix);
        }
        return radix;
    }

    public List<Map<String, Object>> findTypeInfo(User user,String year,String beginMonthDate,String endMonthDate,String type) {
        String officeId="";
        if (user.getRoleList().contains("yydept")){
            officeId=user.getOffice().getId();
        }
        if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
            year= DateUtils.getYear();
        }
        List<Map<String, Object>> list=null;
        if ("tj".equals(type)){
            list=statisticDao.findTypeInfoTj(year,beginMonthDate,endMonthDate,officeId);
        }else{
            list=statisticDao.findTypeInfo(year,beginMonthDate,endMonthDate,officeId);
        }
        return list;
    }

    public List<Map<String, Object>> findGrade(User user,String year,String beginMonthDate,String endMonthDate,String type) {
        String officeId="";
        if (user.getRoleList().contains("yydept")){
            officeId=user.getOffice().getId();
        }
        if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
            year= DateUtils.getYear();
        }
        List<Map<String, Object>> list=null;
        if ("tj".equals(type)){
            list=statisticDao.findGradeTj(year,beginMonthDate,endMonthDate,officeId);
        }else{
            list=statisticDao.findGrade(year,beginMonthDate,endMonthDate,officeId);
        }
        return list;
    }

    public List<Map<String, Object>> getEveryMonthData(User user,String year,String beginMonthDate,String endMonthDate,String type) {
        String officeId="";
        if (user.getRoleList().contains("yydept")){
            officeId=user.getOffice().getId();
        }
        if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
            year= DateUtils.getYear();
        }
        List<Map<String, Object>> list=null;
        if ("tj".equals(type)){
            list=statisticDao.getEveryMonthDataTj(year,beginMonthDate,endMonthDate,officeId);
        }else{
            list=statisticDao.getEveryMonthData(year,beginMonthDate,endMonthDate,officeId);
        }
        return list;
    }

    /***
     * 获取山西省各市的案件数量
     * @param year
     * @param beginMonthDate,endMonthDate
     * @return
     */
    @Transactional(readOnly = false)
    public List<Map<String,Object>> findAreaName (User user,String year,String beginMonthDate,String endMonthDate,String type){
        String officeId="";
        if (user.getRoleList().contains("yydept")){
            officeId=UserUtils.getUser().getOffice().getId();
        }
        if(StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
            year =  DateUtils.getYear();
        }
        List<Map<String, Object>> list=null;
        if ("tj".equals(type)){
            list=statisticDao.findAreaNameTj(year,beginMonthDate,endMonthDate,officeId);
        }else{
            list=statisticDao.findAreaName(year,beginMonthDate,endMonthDate,officeId);
        }
        return list;
    }

    /***
     * 获取各个专业数据
     * @param year
     * @param beginMonthDate,endMonthDate
     * @return
     */
    public List<Map<String,Object>> findDepartment(User user,String year,String beginMonthDate,String endMonthDate,String type){
        String officeId="";
        if (user.getRoleList().contains("yydept")){
            officeId=UserUtils.getUser().getOffice().getId();
        }
        List<Map<String, Object>> list=null;
        if ("tj".equals(type)){
            list=statisticDao.findDepartmentTj(year,beginMonthDate,endMonthDate,officeId);
        }else{
            list=statisticDao.findDepartment(year,beginMonthDate,endMonthDate,officeId);
        }
        return list;
    }

    /***
     * 获取责任度 比例统计图
     * @param year
     * @param beginMonthDate,endMonthDate
     * @return
     */
    public List<Map<String, Object>> findDuty(User user,String year,String beginMonthDate,String endMonthDate,String type) {
        String officeId="";
        if (user.getRoleList().contains("yydept")){
            officeId=user.getOffice().getId();
        }
        if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
            year= DateUtils.getYear();
        }
        List<Map<String, Object>> list=null;
        if ("tj".equals(type)){
            list=statisticDao.findDutyTj(year,beginMonthDate,endMonthDate,officeId);
        }else{
            //list=complaintMainDao.findTypeInfo(year,beginMonthDate,endMonthDate);
        }
        return list;
    }

    /***
     * 获取 赔偿金额 比例
     * @param year
     * @param beginMonthDate,endMonthDate
     * @return
     */
    public  List<Map<String, Object>> findAmountRatio(User user,String year,String beginMonthDate,String endMonthDate,String type) {
        String officeId="";
        if (user.getRoleList().contains("yydept")){
            officeId=user.getOffice().getId();
        }
        if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
            year= DateUtils.getYear();
        }
        List<Map<String, Object>> list=null;
        if ("tj".equals(type)){
            list=statisticDao.findAmountRatioTj(year,beginMonthDate,endMonthDate,officeId);
        }else{
            //list=complaintMainDao.findTypeInfo(year,beginMonthDate,endMonthDate);
        }
        return list;
    }


}
