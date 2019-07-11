/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.machine.entity;

import com.sayee.sxsy.common.utils.excel.annotation.ExcelField;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import org.aspectj.lang.annotation.DeclareMixin;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 台账信息展示Entity
 * @author zhangfan
 * @version 2019-05-17
 */
public class MachineAccount extends DataEntity<MachineAccount> {

	private static final long serialVersionUID = 1L;
	private Office office;	// 归属部门
	private User user;	// 调解员
	private String machineAccountId;		// machine_account_id
	private String complaintMainId;			//主表主键
	private String reportingTime;		// 报案时间
	private String endReportingTime;		// 报案时间
	private String deptId;		// 部门主键
	private String mediatorId;		// 调解员ID，关联人员表主键
	private String patientName;		// 患者名称
	private String hospitalId;		// 医院名称。通过主键进行关联
	private String major;		// 重大
	private String insuranceCompany;		// 保险公司名称
	private String policyNumber;		// 保单号
	private String startInsuranceTime;		// 起保日期
	private String endInsuranceTime;		// 起保日期
	private String disputesTime;		// 纠纷发生日期
	private String riskTime;		// 出险日期
	private String summaryOfDisputes;		// 纠纷概要
	private String isMajor;		// 是否重大 1是 0否
	private String treatmentMode;		// 诊疗方式
	private String treatmentResult;		// 治疗结果
	private String patientsReflectFocus;		// 患方反映焦点
	private String relatedMajor;		// 涉及专业
	private String riskPeople;		// 出险医务人员
	private String assessTime;		// 评估时间
	private String assessNumber;		// 评估号
	private String dutyRatio;		// 责任比例
	private String feedbackTime;		// 反馈时间
	private String agreementNumber;		// 协议号
	private String ratifyAccord;		// 签署协议/判决时间
	private String agreementStampTime;		// 协议盖章时间
	private String agreementAmount;		// 协议金额 协议金额=保险金额+医院金额
	private String insuranceAmount;		// 保险赔付金额
	private String hospitalAmount;		// 医院赔付金额
	private String claimSettlementTime;		// 交理赔时间
	private String compensateTime;		// 赔付时间
	private String flowDays;		// 流转天数  流转天数设公式=交理赔时间-赔付时间
	private String handOver;		// 移交人
	private String archiveTime;		// 归档时间
	private String fileNumber;		// 卷宗编号
	private String remark;		// 备注
	private String delFlag;		//删除标识
	private Office hospital;	//涉及医院

	public MachineAccount() {
		super();
	}

	public MachineAccount(String id){
		super(id);
	}

	public Office getHospital() {
		return hospital;
	}

	public void setHospital(Office hospital) {
		this.hospital = hospital;
	}

	@Override
    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

	//@Length(min=1, max=32, message="machine_account_id长度必须介于 1 和 32 之间")
	public String getMachineAccountId() {
		return machineAccountId;
	}

	public void setMachineAccountId(String machineAccountId) {
		this.machineAccountId = machineAccountId;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min=0, max=20, message="报案时间不能为空，且长度必须介于 1 和 20 之间")
	@ExcelField(title="报案时间", align=2, sort=1)
	public String getReportingTime() {
		return reportingTime;
	}

	public void setReportingTime(String reportingTime) {
		this.reportingTime = reportingTime;
	}

	public String getEndReportingTime() {
		return endReportingTime;
	}

	public void setEndReportingTime(String endReportingTime) {
		this.endReportingTime = endReportingTime;
	}

	public String getEndInsuranceTime() {
		return endInsuranceTime;
	}

	public void setEndInsuranceTime(String endInsuranceTime) {
		this.endInsuranceTime = endInsuranceTime;
	}

	@Length(min=0, max=32, message="部门不能为空")
	@ExcelField(title = "部门", align=2,sort = 2)
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Length(min=0, max=32, message="调解员不能为空")
	@ExcelField(title = "调解员",align = 2,sort = 3)
	public String getMediatorId() {
		return mediatorId;
	}

	public void setMediatorId(String mediatorId) {
		this.mediatorId = mediatorId;
	}

	@Length(min=0, max=10, message="患者名称不能为空，长度必须介于 1 和 10 之间")
	@ExcelField(title="患者名称", align=2, sort=4)
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@Length(min=0, max=32, message="医院名称不能为空，且长度必须介于 0 和 32 之间")
	@ExcelField(title="医疗机构名称", align=2, sort=5)
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Length(min=0, max=50, message="重大长度必须介于 0 和 50 之间")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Length(min=0, max=100, message="保险公司名称长度必须介于 0 和 100 之间")
	@ExcelField(title="保险公司", align=2, sort=7)
	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	@Length(min=0, max=50, message="保单号长度必须介于 0 和 50 之间")
	@ExcelField(title="保单号", align=2, sort=8)
	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@Length(min=0, max=20, message="起保日期长度必须介于 0 和 20 之间")
	@ExcelField(title="起保日期", align=2, sort=9)
	public String getStartInsuranceTime() {
		return startInsuranceTime;
	}

	public void setStartInsuranceTime(String startInsuranceTime) {
		this.startInsuranceTime = startInsuranceTime;
	}

	@Length(min=0, max=20, message="纠纷发生日期长度必须介于 0 和 20 之间")
	@ExcelField(title="纠纷日期", align=2, sort=10)
	public String getDisputesTime() {
		return disputesTime;
	}

	public void setDisputesTime(String disputesTime) {
		this.disputesTime = disputesTime;
	}

	@Length(min=0, max=20, message="出险日期长度必须介于 0 和 20 之间")
	@ExcelField(title="出险时间", align=2, sort=11)
	public String getRiskTime() {
		return riskTime;
	}

	public void setRiskTime(String riskTime) {
		this.riskTime = riskTime;
	}

	@Length(min=0, max=500, message="纠纷概要长度必须介于 0 和 500 之间")
	@ExcelField(title="纠纷概要", align=2, sort=12)
	public String getSummaryOfDisputes() {
		return summaryOfDisputes;
	}

	public void setSummaryOfDisputes(String summaryOfDisputes) {
		this.summaryOfDisputes = summaryOfDisputes;
	}

	@Length(min=0, max=1, message="是否重大 1是 0否长度必须介于 0 和 1 之间")
	@ExcelField(title = "是否重大",align = 2,sort = 13)
	public String getIsMajor() {
		return isMajor;
	}

	public void setIsMajor(String isMajor) {
		this.isMajor = isMajor;
	}

	@Length(min=0, max=500, message="诊疗方式长度必须介于 0 和 500 之间")
	@ExcelField(title="诊疗方式", align=2, sort=14)
	public String getTreatmentMode() {
		return treatmentMode;
	}

	public void setTreatmentMode(String treatmentMode) {
		this.treatmentMode = treatmentMode;
	}

	@Length(min=0, max=500, message="治疗结果长度必须介于 0 和 500 之间")
	@ExcelField(title="治疗结果", align=2, sort=15)
	public String getTreatmentResult() {
		return treatmentResult;
	}

	public void setTreatmentResult(String treatmentResult) {
		this.treatmentResult = treatmentResult;
	}

	@Length(min=0, max=500, message="患方反映焦点长度必须介于 0 和 500 之间")
	@ExcelField(title="患方反映焦点", align=2, sort=16)
	public String getPatientsReflectFocus() {
		return patientsReflectFocus;
	}

	public void setPatientsReflectFocus(String patientsReflectFocus) {
		this.patientsReflectFocus = patientsReflectFocus;
	}

	@Length(min=0, max=255, message="涉及专业长度必须介于 0 和 255 之间")
	@ExcelField(title="涉及专业", align=2, sort=17)
	public String getRelatedMajor() {
		return relatedMajor;
	}

	public void setRelatedMajor(String relatedMajor) {
		this.relatedMajor = relatedMajor;
	}

	@Length(min=0, max=255, message="出险医务人员长度必须介于 0 和 255 之间")
	@ExcelField(title="出险医务人员", align=2, sort=17)
	public String getRiskPeople() {
		return riskPeople;
	}

	public void setRiskPeople(String riskPeople) {
		this.riskPeople = riskPeople;
	}

	@Length(min=0, max=20, message="评估时间长度必须介于 0 和 20 之间")
	@ExcelField(title="评估时间", align=2, sort=18)
	public String getAssessTime() {
		return assessTime;
	}

	public void setAssessTime(String assessTime) {
		this.assessTime = assessTime;
	}

	@Length(min=0, max=20, message="评估号长度必须介于 0 和 20 之间")
	@ExcelField(title="评估号", align=2, sort=19)
	public String getAssessNumber() {
		return assessNumber;
	}

	public void setAssessNumber(String assessNumber) {
		this.assessNumber = assessNumber;
	}

	@Length(min=0, max=50, message="责任比例长度必须介于 0 和 50 之间")
	@ExcelField(title="责任比例", align=2, sort=20)
	public String getDutyRatio() {
		return dutyRatio;
	}

	public void setDutyRatio(String dutyRatio) {
		this.dutyRatio = dutyRatio;
	}

	@Length(min=0, max=20, message="反馈时间长度必须介于 0 和 20 之间")
	@ExcelField(title="反馈函时间", align=2, sort=21)
	public String getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(String feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	@Length(min=0, max=20, message="协议号长度必须介于 0 和 20 之间")
	@ExcelField(title="协议号", align=2, sort=22)
	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	@Length(min=0, max=20, message="签署协议/判决时间长度必须介于 0 和 20 之间")
	@ExcelField(title="签署协议/判决时间", align=2, sort=23)
	public String getRatifyAccord() {
		return ratifyAccord;
	}

	public void setRatifyAccord(String ratifyAccord) {
		this.ratifyAccord = ratifyAccord;
	}

	@Length(min=0, max=20, message="协议盖章时间长度必须介于 0 和 20 之间")
	public String getAgreementStampTime() {
		return agreementStampTime;
	}

	public void setAgreementStampTime(String agreementStampTime) {
		this.agreementStampTime = agreementStampTime;
	}

	@Length(min=0,max=20,message = "协议金额不能为空，且长度必须介于 0 和 20 之间")
	@DecimalMin(value = "0",message = "协议金额最低为0,且不能为空")
	@ExcelField(title = "协议金额",align = 2,sort = 25)
	public String getAgreementAmount() {
		return agreementAmount;
	}

	public void setAgreementAmount(String agreementAmount) {
		this.agreementAmount = agreementAmount;
	}

	@Length(min=0,max=20,message = "保险赔付金额不能为空，长度必须介于 1 和 20 之间")
	@DecimalMin(value = "0",message = "保险赔付金额最低为0,且不能为空")
	@ExcelField(title = "保险赔付金额",align = 2,sort = 26)
	public String getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	@Length(min=0,max=20,message = "医院赔付金额不能为空，长度必须介于 1 和 20 之间")
	@DecimalMin(value = "0",message = "医院赔付金额最低为0,且不能为空")
	//@ExcelField(title = "医院赔付金额",align = 2,sort = 26)
	public String getHospitalAmount() {
		return hospitalAmount;
	}

	public void setHospitalAmount(String hospitalAmount) {
		this.hospitalAmount = hospitalAmount;
	}

	@Length(min=0, max=20, message="交理赔时间长度必须介于 0 和 20 之间")
	@ExcelField(title="交理赔时间", align=2, sort=27)
	public String getClaimSettlementTime() {
		return claimSettlementTime;
	}

	public void setClaimSettlementTime(String claimSettlementTime) {
		this.claimSettlementTime = claimSettlementTime;
	}

	@Length(min=0, max=20, message="赔付时间长度必须介于 0 和 20 之间")
	@ExcelField(title="赔付时间", align=2, sort=28)
	public String getCompensateTime() {
		return compensateTime;
	}

	public void setCompensateTime(String compensateTime) {
		this.compensateTime = compensateTime;
	}

	@Length(min=0, max=20, message="流转天数长度必须介于 0 和 20 之间")
	@ExcelField(title="流转天数", align=2, sort=29)
	public String getFlowDays() {
		return flowDays;
	}

	public void setFlowDays(String flowDays) {
		this.flowDays = flowDays;
	}

	@Length(min=0, max=20, message="移交人长度必须介于 0 和 20 之间")
	@ExcelField(title="移交人", align=2, sort=30)
	public String getHandOver() {
		return handOver;
	}

	public void setHandOver(String handOver) {
		this.handOver = handOver;
	}

	@Length(min=0, max=20, message="归档时间长度必须介于 0 和 20 之间")
	@ExcelField(title="归档日期", align=2, sort=31)
	public String getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(String archiveTime) {
		this.archiveTime = archiveTime;
	}

	@Length(min=0, max=20, message="卷宗编号不能为空，且长度必须介于 1 和 20 之间")
	@ExcelField(title="卷宗编号", align=2, sort=32)
	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	@Length(min=0, max=500, message="备注长度必须介于 0 和 500 之间")
	@ExcelField(title="备注", align=10, sort=500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}