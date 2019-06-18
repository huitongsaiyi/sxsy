/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintdetail.entity;

import com.sayee.sxsy.common.persistence.ActEntity;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 医调委投诉接待Entity
 * @author zhangfan
 * @version 2019-06-05
 */
public class ComplaintMainDetail extends DataEntity<ComplaintMainDetail> {
	
	private static final long serialVersionUID = 1L;
	private String complaintMainDetailId;		// 主键
	private ComplaintMain complaintMain; //主表
	private String complaintMainId;		// 主表逐渐
	private String complaintMode;		// 投诉方式
	private String visitorName;		// 访客姓名
	private String visitorMobile;		// 访客电话
	private String visitorDate;		// 来访日期
	private String visitorNumber;		// 来访 人数
	private String patientRelation;		// 与患者关系 字典维护
	private String isMajor;		// 是否重大
	private String summaryOfDisputes;		// 投诉纠纷概要
	private String appeal;		// 诉求
	private String receptionEmployee;		// 接待人员
	private User jdEmployee;		// 接待人员
	private User linkEmployee;		// 接待人员
	private String receptionDate;		// 接待日期
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	private String beginVisitorDate;		// 开始 来访日期
	private String endVisitorDate;		// 结束 来访日期

	private String patientRelationlabel;    //患者关系 字典中的label值

	private String isMajorlabel;        //是否重大 字典中的label值

	public ComplaintMainDetail() {
		super();
	}

	public ComplaintMainDetail(String id){
		super(id);
	}

	public String getIsMajorlabel() {
		return isMajorlabel;
	}

	public String getPatientRelationlabel() {
		return patientRelationlabel;
	}

	public void setIsMajorlabel(String isMajorlabel) {
		this.isMajorlabel = isMajorlabel;
	}

	public void setPatientRelationlabel(String patientRelationlabel) {
		this.patientRelationlabel = patientRelationlabel;
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	public User getJdEmployee() {
		return jdEmployee;
	}

	public void setJdEmployee(User jdEmployee) {
		this.jdEmployee = jdEmployee;
	}

	public User getLinkEmployee() {
		return linkEmployee;
	}

	public void setLinkEmployee(User linkEmployee) {
		this.linkEmployee = linkEmployee;
	}

	//@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getComplaintMainDetailId() {
		return complaintMainDetailId;
	}

	public void setComplaintMainDetailId(String complaintMainDetailId) {
		this.complaintMainDetailId = complaintMainDetailId;
	}
	
//	@Length(min=1, max=32, message="主表主键长度必须介于 1 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=0, max=1, message="投诉方式长度必须介于 0 和 1 之间")
	public String getComplaintMode() {
		return complaintMode;
	}

	public void setComplaintMode(String complaintMode) {
		this.complaintMode = complaintMode;
	}
	
	@Length(min=0, max=20, message="访客姓名长度必须介于 0 和 20 之间")
	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	@Length(min=11, max=15, message="访客电话长度必须介于 0 和 15 之间")
	public String getVisitorMobile() {
		return visitorMobile;
	}

	public void setVisitorMobile(String visitorMobile) {
		this.visitorMobile = visitorMobile;
	}
	
	@Length(min=0, max=20, message="来访日期长度必须介于 0 和 20 之间")
	public String getVisitorDate() {
		return visitorDate;
	}

	public void setVisitorDate(String visitorDate) {
		this.visitorDate = visitorDate;
	}
	
	@Length(min=0, max=10, message="来访 人数长度必须介于 0 和 10 之间")
	public String getVisitorNumber() {
		return visitorNumber;
	}

	public void setVisitorNumber(String visitorNumber) {
		this.visitorNumber = visitorNumber;
	}
	
	@Length(min=0, max=1, message="与患者关系 字典维护长度必须介于 0 和 1 之间")
	public String getPatientRelation() {
		return patientRelation;
	}

	public void setPatientRelation(String patientRelation) {
		this.patientRelation = patientRelation;
	}
	
	@Length(min=0, max=1, message="是否重大长度必须介于 0 和 1 之间")
	public String getIsMajor() {
		return isMajor;
	}

	public void setIsMajor(String isMajor) {
		this.isMajor = isMajor;
	}
	
	public String getSummaryOfDisputes() {
		return summaryOfDisputes;
	}

	public void setSummaryOfDisputes(String summaryOfDisputes) {
		this.summaryOfDisputes = summaryOfDisputes;
	}
	
	public String getAppeal() {
		return appeal;
	}

	public void setAppeal(String appeal) {
		this.appeal = appeal;
	}
	
	@Length(min=0, max=32, message="接待人员长度必须介于 0 和 32 之间")
	public String getReceptionEmployee() {
		return receptionEmployee;
	}

	public void setReceptionEmployee(String receptionEmployee) {
		this.receptionEmployee = receptionEmployee;
	}
	
	@Length(min=0, max=20, message="接待日期长度必须介于 0 和 20 之间")
	public String getReceptionDate() {
		return receptionDate;
	}

	public void setReceptionDate(String receptionDate) {
		this.receptionDate = receptionDate;
	}
	
	@Length(min=0, max=32, message="下一处理环节长度必须介于 0 和 32 之间")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
	
	@Length(min=0, max=32, message="下一环节处理人长度必须介于 0 和 32 之间")
	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}
	
	public String getBeginVisitorDate() {
		return beginVisitorDate;
	}

	public void setBeginVisitorDate(String beginVisitorDate) {
		this.beginVisitorDate = beginVisitorDate;
	}
	
	public String getEndVisitorDate() {
		return endVisitorDate;
	}

	public void setEndVisitorDate(String endVisitorDate) {
		this.endVisitorDate = endVisitorDate;
	}
		
}