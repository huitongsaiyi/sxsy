/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.summaryinfo.entity;

import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 案件总结Entity
 * @author gbq
 * @version 2019-06-17
 */
public class SummaryInfo extends DataEntity<SummaryInfo> {
	
	private static final long serialVersionUID = 1L;
	private String summaryId;		// 总结主键
	private String complaintMainId;		// 主表主键
	private String summaryEmp;		// 总结人 关联用户
	private String summaryTime;		// 总结时间
	private String summary;		// 总结
	private String filingTime;		// 归档时间
	private String fileNumber;		// 卷宗编号
	private String handlePeople;		// 处理人
	private String handleTime;		// 处理日期
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	private AuditAcceptance auditAcceptance;//保单号
	private ComplaintMain complaintMain;		// 关联主表主键
	private User user;  //当前登录人员
	private Area area;
	private User linkEmployee;		// 下一环节人员
	private User dcEmployee;		// 登记人员
	private ReportRegistration reportRegistration;//报案人姓名
    private MachineAccount machineAccount;//台账信息

	public SummaryInfo() {
		super();
	}

	public SummaryInfo(String id){
		super(id);
	}

    public MachineAccount getMachineAccount() {
        return machineAccount;
    }

    public void setMachineAccount(MachineAccount machineAccount) {
        this.machineAccount = machineAccount;
    }

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}

	@Length(min = 1,max = 32,message = "下一环节处理人不能为空")
	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	public String getHandlePeople() {
		return handlePeople;
	}

	public void setHandlePeople(String handlePeople) {
		this.handlePeople = handlePeople;
	}

	public ReportRegistration getReportRegistration() {
		return reportRegistration;
	}

	public void setReportRegistration(ReportRegistration reportRegistration) {
		this.reportRegistration = reportRegistration;
	}

	public User getDcEmployee() {
		return dcEmployee;
	}

	public void setDcEmployee(User dcEmployee) {
		this.dcEmployee = dcEmployee;
	}

	public User getLinkEmployee() {
		return linkEmployee;
	}

	public void setLinkEmployee(User linkEmployee) {
		this.linkEmployee = linkEmployee;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	public AuditAcceptance getAuditAcceptance() {
		return auditAcceptance;
	}

	public void setAuditAcceptance(AuditAcceptance auditAcceptance) {
		this.auditAcceptance = auditAcceptance;
	}


	public String getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}
	
	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=1, max=32, message="总结人 关联用户长度必须介于 0 和 32 之间")
	public String getSummaryEmp() {
		return summaryEmp;
	}

	public void setSummaryEmp(String summaryEmp) {
		this.summaryEmp = summaryEmp;
	}
	
	@Length(min=1, max=20, message="总结时间长度必须介于 0 和 20 之间")
	public String getSummaryTime() {
		return summaryTime;
	}

	public void setSummaryTime(String summaryTime) {
		this.summaryTime = summaryTime;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Length(min=1, max=20, message="归档时间长度必须介于 0 和 20 之间")
	public String getFilingTime() {
		return filingTime;
	}

	public void setFilingTime(String filingTime) {
		this.filingTime = filingTime;
	}
	
	@Length(min=1, max=10, message="卷宗编号长度必须介于 0 和 10 之间")
	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}
	
}