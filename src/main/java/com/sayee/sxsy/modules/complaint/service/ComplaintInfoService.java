/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaint.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaintdetail.dao.ComplaintMainDetailDao;
import com.sayee.sxsy.modules.complaintdetail.entity.ComplaintMainDetail;
import com.sayee.sxsy.modules.complaintdetail.service.ComplaintMainDetailService;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.complaint.dao.ComplaintInfoDao;

import javax.servlet.http.HttpServletRequest;

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


    public ComplaintInfo get(String id) {
        return super.get(id);
    }

    public List<ComplaintInfo> findList(ComplaintInfo complaintInfo) {
        return super.findList(complaintInfo);
    }

    public Page<ComplaintInfo> findPage(Page<ComplaintInfo> page, ComplaintInfo complaintInfo) {
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
            complaintMainDetail.getCreateBy().setId(complaintInfo.getNextLinkMan());           //将创建人更改为从医院投诉接待拿到的下一步处理人
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
}