/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaint.service;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.UserAgentUtils;
import com.sayee.sxsy.modules.complaintdetail.dao.ComplaintMainDetailDao;
import com.sayee.sxsy.modules.complaintdetail.entity.ComplaintMainDetail;
import com.sayee.sxsy.modules.complaintdetail.service.ComplaintMainDetailService;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.surgicalconsentbook.dao.PreOperativeConsentDao;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.service.SystemService;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.complaint.dao.ComplaintInfoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 投诉接待Service
 *
 * @author zhangfan
 * @version 2019-05-27
 */
@Service
@Transactional(readOnly = true)
public class ComplaintInfoService extends CrudService<ComplaintInfoDao, ComplaintInfo> {

    @Autowired
    private ComplaintMainDao complaintMainService;

    @Autowired
    private ComplaintMainDetailDao complaintMainDetailDao;

    @Autowired
    private ComplaintInfoDao complaintInfoDao;

    @Autowired
    private PreOperativeConsentDao preOperativeConsentDao;
    public ComplaintInfo get(String id) {
        return super.get(id);
    }

    public List<ComplaintInfo> findList(ComplaintInfo complaintInfo) {
        complaintInfo.setUser(UserUtils.getUser());
        return super.findList(complaintInfo);
    }

    public Page<ComplaintInfo> findPage(Page<ComplaintInfo> page, ComplaintInfo complaintInfo) {
        //如果当前人员角色 是 医调委主任 则看全部数据
        List<String> aa=ObjectUtils.convert(UserUtils.getRoleList().toArray(),"enname",true);
        if (aa.contains("commission")){//韩主任 医调委主任 看全部数据

        }else if(aa.contains("zwjw")){
            //曹华磊 与韩主任 有全部数据的权限，为了看 看卫健委工作站人员 信息
            List<String> list=new ArrayList<String>();
            List<User> listUser=UserUtils.getUser("wjwgzzry");

            for (User user:listUser) {
                list.add(user.getId());
            }
            if (list.size()>0){
                complaintInfo.setList(list);
            }else {
                list.add(UserUtils.getUser().getId());
                complaintInfo.setList(list);
            }
        }else if(aa.contains("DepartmentDeputyDirector")){
            //风险管控部 部门副主任 看自己部门所有数据
            List<String> list=new ArrayList<String>();

            List<User> listUser=UserUtils.getUserByOffice(UserUtils.getUser().getOffice().getId());
            for (User user:listUser) {
                list.add(user.getId());
            }
            if (list.size()>0){
                complaintInfo.setList(list);
            }else {
                list.add(UserUtils.getUser().getId());
                complaintInfo.setList(list);
            }
        }else{
            complaintInfo.setUser(UserUtils.getUser());
        }
        return super.findPage(page, complaintInfo);
    }

    @Transactional(readOnly = false)
    public boolean save(ComplaintInfo complaintInfo, HttpServletRequest request) {
        boolean a = true;
        if (StringUtils.isBlank(complaintInfo.getComplaintId())) {
            if (true == this.checkcaseNumber(complaintInfo.getComplaintMain().getCaseNumber(), complaintInfo.getComplaintMain().getComplaintMainId())) {
                complaintInfo.preInsert();
                complaintInfo.setComplaintId(complaintInfo.getId());
                complaintInfo.setDelFlag("0");
                this.saveComplaint(complaintInfo);
                dao.insert(complaintInfo);
            } else {
                a = false;
            }
        } else {
            complaintInfo.preUpdate();
            this.saveComplaint(complaintInfo);
            dao.update(complaintInfo);
        }

        String flag = request.getParameter("flag");
        if ("yes".equals(flag)) {
            //如果是点击的下一步  则对医调委的投诉接待进行新增  新增子表
            ComplaintMainDetail complaintMainDetail = new ComplaintMainDetail();
            complaintMainDetail.preInsert();
            complaintMainDetail.setComplaintMainDetailId(complaintMainDetail.getId());
            complaintMainDetail.setReceptionEmployee(complaintInfo.getReceptionEmployee());        //接待人员
            complaintMainDetail.setComplaintMode(complaintInfo.getComplaintMode());            //投诉方式
            complaintMainDetail.setComplaintMainId(complaintInfo.getComplaintMainId());        //主表ID
            complaintMainDetail.setAppeal(complaintInfo.getAppeal());            //诉求
            complaintMainDetail.setVisitorDate(complaintInfo.getVisitorDate());            //来访日期
            complaintMainDetail.setVisitorMobile(complaintInfo.getVisitorMobile());        //访客电话
            complaintMainDetail.setVisitorName(complaintInfo.getVisitorName());            //访客姓名
            complaintMainDetail.setVisitorNumber(complaintInfo.getVisitorNumber());        //来访人数
            complaintMainDetail.setSummaryOfDisputes(complaintInfo.getSummaryOfDisputes());        //投诉纠纷概要
            complaintMainDetail.setReceptionDate(complaintInfo.getReceptionDate());            //接待日期
            complaintMainDetail.setPatientRelation(complaintInfo.getPatientRelation());        //患者关系
            complaintMainDetail.setIsMajor(complaintInfo.getIsMajor());        //是否重大
            User user=UserUtils.get(complaintInfo.getNextLinkMan());
            complaintMainDetail.setCreateBy(user);           //将创建人更改为从医院投诉接待拿到的下一步处理人
            complaintMainDetail.setUpdateBy(user);
            //保存子表
            complaintMainDetailDao.insert(complaintMainDetail);
        }
//		super.save(complaintInfo);
        return a;
    }

    @Transactional(readOnly = false)
    public void delete(ComplaintInfo complaintInfo) {
        super.delete(complaintInfo);
    }

    /**
     * 案件编号验重
     *
     * @param caseNumber
     * @return
     */
    @Transactional(readOnly = false)
    public boolean checkcaseNumber(String caseNumber, String complaintMainId) {
        if (StringUtils.isNotBlank(caseNumber)) {
            ComplaintInfo complaintInfo = dao.checkcaseNumber(caseNumber, complaintMainId);
            if (complaintInfo != null) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     *
     *
     */
    @Transactional(readOnly = false)
    public ComplaintInfo saveComplaint(ComplaintInfo complaintInfo) {
        ComplaintMain complaintMain = complaintMainService.get(complaintInfo.getComplaintMainId());
        if (org.springframework.util.StringUtils.isEmpty(complaintMain)) {
            //将信息保存到主表
            ComplaintMain complaintMain1 = new ComplaintMain();
                complaintMain1.preInsert();
                complaintMain1.setComplaintMainId(complaintMain1.getId());
                complaintMain1.setCaseNumber(complaintInfo.getCaseNumber());        //案件编号
                complaintMain1.setPatientName(complaintInfo.getPatientName());        //患者姓名
                complaintMain1.setPatientAge(complaintInfo.getPatientAge());            //患者年龄
                complaintMain1.setPatientSex(complaintInfo.getPatientSex());            //患者性别
                complaintMain1.setInvolveHospital(complaintInfo.getInvolveHospital());        //涉及医院
                complaintMain1.setInvolveDepartment(complaintInfo.getInvolveDepartment());        //涉及科室
                complaintMain1.setInvolveEmployee(complaintInfo.getInvolveEmployee());        //涉及人员
                complaintMain1.setSource("2");        //信息来源
                complaintInfo.setComplaintMain(complaintMain1);
                complaintMainService.insert(complaintInfo.getComplaintMain());            //保存主表
                complaintInfo.setComplaintMainId(complaintInfo.getComplaintMain().getComplaintMainId());
        } else {
            complaintMain.preUpdate();
            complaintMain.setComplaintMainId(complaintInfo.getComplaintMainId());
            complaintMain.setCaseNumber(complaintInfo.getCaseNumber());        //案件编号
            complaintMain.setPatientName(complaintInfo.getPatientName());        //患者姓名
            complaintMain.setPatientAge(complaintInfo.getPatientAge());            //患者年龄
            complaintMain.setPatientSex(complaintInfo.getPatientSex());            //患者性别
            complaintMain.setInvolveHospital(complaintInfo.getInvolveHospital());        //涉及医院
            complaintMain.setInvolveDepartment(complaintInfo.getInvolveDepartment());        //涉及科室
            complaintMain.setInvolveEmployee(complaintInfo.getInvolveEmployee());        //涉及人员
            complaintInfo.setComplaintMain(complaintMain);
            complaintMainService.update(complaintInfo.getComplaintMain());            //保存主表
        }

        return complaintInfo;
    }


    public Page<List> statementPage(Page<List> page,HttpServletRequest request, HttpServletResponse response) {
        String type=request.getParameter("export");
        String visitorDate=request.getParameter("visitorDate");
        String visitorDateEnd=request.getParameter("visitorDateEnd");
        String visitorMonthDate=request.getParameter("visitorMonthDate");
        String visitorMonthDateEnd=request.getParameter("visitorMonthDateEnd");
        String involveDepartment=request.getParameter("involveDepartment");
        String involveEmployee=request.getParameter("involveEmployee");
        if (StringUtils.isBlank(visitorDate) && StringUtils.isBlank(visitorMonthDate)){
            visitorDate= DateUtils.getDate();//"2019-07-09";
        }
        List list=new ArrayList();

        List<Map<String,Object>> every =complaintInfoDao.selectEveryOne(visitorDate,visitorDateEnd,visitorMonthDate,visitorMonthDateEnd,involveDepartment,involveEmployee);
        List<Map<String,Object>> book=preOperativeConsentDao.selectCreatby(visitorDate,visitorDateEnd,visitorMonthDate,visitorMonthDateEnd,involveDepartment,involveEmployee);
        if(CollectionUtils.isEmpty(every)){
            every=new ArrayList<Map<String,Object>>();
        }
        //根据拿到的所有人员数据 在进行 单个人员统计
        person(every,book,visitorDate,visitorDateEnd,visitorMonthDate,visitorMonthDateEnd);
        List<Map> maps = new ArrayList<Map>();
        if("'yes'".equals(type)){
            every.addAll(book);
            for (int i =0;i < every.size();i++){
                every.get(i).remove("create_by");
                every.get(i).remove("office_id");
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("部门",every.get(i).get("deptName"));
                map.put("接待日期",every.get(i).get("visitor_date"));
                map.put("投诉接待数量(总)",every.get(i).get("amount"));
                map.put("人员",every.get(i).get("name"));
                map.put("医院转办数量",MapUtils.getString(every.get(i),"zb","0"));
                map.put("转调解数量",MapUtils.getString(every.get(i),"ytw","0"));
                map.put("当面处理数量",MapUtils.getString(every.get(i),"dm","0"));
                //if(every.get(i).size()==7){
                map.put("同意书见证",every.get(i).get("sq"));
               // }
                maps.add(map);
            }
            //maps.addAll(book);
            list.add(maps);
            page.setList(list);
            return page;
        }else {
            //合并list
            every.addAll(book);
            list.add(every);
            page.setList(list);
            return page;//super.findPage(page, complaintInfo);
        }
    }

    public void person(List<Map<String,Object>> every,List<Map<String,Object>> book,String visitorDate,String visitorDateEnd,String visitorMonthDate,String visitorMonthDateEnd){
        List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
        if (every.size()>0){
                for (Map<String,Object> map: every) {
                    Map<String,Object> person=complaintInfoDao.selectPerson(MapUtils.getString(map,"create_by",""),visitorDate,visitorDateEnd,visitorMonthDate,visitorMonthDateEnd);
                        if (MapUtils.isNotEmpty(person)){
                            map.putAll(person);
                        }
                    //遍历术前同意书见证的list  有相同人员增加，且删除map
                    for (Map<String,Object> bookMap: book) {
                        if (MapUtils.getString(bookMap,"create_by").equals(MapUtils.getString(map,"create_by"))){
                            map.put("sq",MapUtils.getString(bookMap,"sq"));
                            newlist.add(bookMap);
                        }
                    }
                }
            }
        book.removeAll(newlist);
    }
}