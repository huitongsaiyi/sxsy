/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.auditacceptance.entity;

import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.mediateapplyinfo.entity.MediateApplyInfo;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 审核受理Entity
 * @author zhangfan
 * @version 2019-06-10
 */
public class AuditAcceptance extends DataEntity<AuditAcceptance> {
	
	private static final long serialVersionUID = 1L;
	private String auditAcceptanceId;		// 主键
	private String complaintMainId;		// 主表主键
	private ComplaintMain complaintMain;		// 关联主表
	private User user;  //当前登录人员
	private User linkEmployee;		// 下一环节人员
	private String caseSource;		// 案件来源 字典维护
	private String guaranteeTime;		// 起保日期
	private String insuranceCompany;		// 保险公司
	private String policyNumber;		// 保单号
	private String diagnosisMode;		// 诊疗方式
	private String treatmentOutcome;		// 治疗结果
	private String summaryOfDisputes;		// 纠纷概要
	private String handlePeople;		// 处理人
	private String handleTime;		// 处理日期
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	private String beginGuaranteeTime;		// 开始 起保日期
	private String endGuaranteeTime;		// 结束 起保日期
	private String hfsltzs;  //患方受理通知书
	private String yysltzs;  //医院受理通知书
	private MediateApplyInfo mediateApplyInfo;
    private MachineAccount machineAccount;    //台账信息
    private ReportRegistration reportRegistration;

	public AuditAcceptance() {
		super();
	}

	public AuditAcceptance(String id){
		super(id);
	}

    public MachineAccount getMachineAccount() {
        return machineAccount;
    }

    public void setMachineAccount(MachineAccount machineAccount) {
        this.machineAccount = machineAccount;
    }

    public ReportRegistration getReportRegistration() {
        return reportRegistration;
    }

    public void setReportRegistration(ReportRegistration reportRegistration) {
        this.reportRegistration = reportRegistration;
    }

	public MediateApplyInfo getMediateApplyInfo() {
		return mediateApplyInfo;
	}

	public void setMediateApplyInfo(MediateApplyInfo mediateApplyInfo) {
		this.mediateApplyInfo = mediateApplyInfo;
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getLinkEmployee() {
		return linkEmployee;
	}

	public void setLinkEmployee(User linkEmployee) {
		this.linkEmployee = linkEmployee;
	}

	public String getHfsltzs() {
		return hfsltzs;
	}

	public void setHfsltzs(String hfsltzs) {
		this.hfsltzs = hfsltzs;
	}

	public String getYysltzs() {
		return yysltzs;
	}

	public void setYysltzs(String yysltzs) {
		this.yysltzs = yysltzs;
	}

	//@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getAuditAcceptanceId() {
		return auditAcceptanceId;
	}

	public void setAuditAcceptanceId(String auditAcceptanceId) {
		this.auditAcceptanceId = auditAcceptanceId;
	}
	
	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=1, max=1, message="案件来源 字典维护长度必须介于 1 和 1 之间")
	public String getCaseSource() {
		return caseSource;
	}

	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}
	
	@Length(min=0, max=20, message="起保日期长度必须介于 0 和 20 之间")
	public String getGuaranteeTime() {
		return guaranteeTime;
	}

	public void setGuaranteeTime(String guaranteeTime) {
		this.guaranteeTime = guaranteeTime;
	}
	
	@Length(min=0, max=50, message="保险公司长度必须介于 0 和 50 之间")
	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	
	@Length(min=0, max=255, message="保单号长度必须介于 0 和 255 之间")
	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	
	public String getDiagnosisMode() {
		return diagnosisMode;
	}

	public void setDiagnosisMode(String diagnosisMode) {
		this.diagnosisMode = diagnosisMode;
	}

	@Length(min=1, max=32,message = "治疗结果长度必须介于 1 和 32 之间")
	public String getTreatmentOutcome() {
		return treatmentOutcome;
	}

	public void setTreatmentOutcome(String treatmentOutcome) {
		this.treatmentOutcome = treatmentOutcome;
	}

	public String getSummaryOfDisputes() {
		return summaryOfDisputes;
	}

	public void setSummaryOfDisputes(String summaryOfDisputes) {
		this.summaryOfDisputes = summaryOfDisputes;
	}

	@Length(min=1, max=32, message="处理人长度必须介于 1 和 32 之间")
	public String getHandlePeople() {
		return handlePeople;
	}

	public void setHandlePeople(String handlePeople) {
		this.handlePeople = handlePeople;
	}
	
	@Length(min=0, max=20, message="处理日期长度必须介于 0 和 20 之间")
	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	
	@Length(min=0, max=32, message="下一处理环节长度必须介于 0 和 32 之间")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
	

	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}

	@Length(min=1, max=32,message = "开始起保日期长度必须介于 0 和 32 之间")
	public String getBeginGuaranteeTime() {
		return beginGuaranteeTime;
	}

	public void setBeginGuaranteeTime(String beginGuaranteeTime) {
		this.beginGuaranteeTime = beginGuaranteeTime;
	}


	@Length(min=1, max=32,message = "结束起保日期长度必须介于 0 和 32之间")
	public String getEndGuaranteeTime() {
		return endGuaranteeTime;
	}

	public void setEndGuaranteeTime(String endGuaranteeTime) {
		this.endGuaranteeTime = endGuaranteeTime;
	}
		
}