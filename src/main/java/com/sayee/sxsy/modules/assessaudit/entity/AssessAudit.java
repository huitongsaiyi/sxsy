/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessaudit.entity;

import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 评估鉴定审批Entity
 * @author lyt
 * @version 2019-06-13
 */
public class AssessAudit extends DataEntity<AssessAudit> {
	
	private static final long serialVersionUID = 1L;
	private String assessAuditId;		// 审批主键
	private String complaintMainId;		// 主表主键
	private ComplaintMain complaintMain;  //主表
	private String patientApplyer;		// 患方申请人
	private String patientRelation;		// 与患者关系
	private String patientMobile;		// 患方电话
	private String patientName;		// 患者姓名
	private String patientSex;		// 患方性别
	private String patientAge;		// 患方年龄
	private String involveHospital;		// 患方涉及医院
	private String patientApplyMatter;		// 患方申请事项
	private String hospitalApply;		// 申请医院
	private String agent;		// 代理人
	private String hospitalMobile;		// 医方电话
	private String hospitalName;		// 医方姓名
	private String hospitalAge;		// 医方年龄
	private String hospitalSex;		// 医方性别
	private String hospitalApplyMatter;		// 医方申请事项
	private String applyType;		// 申请类型
	private String auditAddress;		// 地点
	private String medicalExpert;		// 医学专家
	private String date;		// 日期
	private String legalExpert;		// 法律专家
	private String handlePeople;		// 处理人
	private String handleTime;		// 处理日期
	private String nextLink;		// next_link
	private String nextLinkMan;		// next_link_man
    private AuditAcceptance auditAcceptance;//保单号
    private Area area;
    private ReportRegistration reportRegistration;//报案人姓名
	private User user;  //当前登录人员
	private User dlEmployee;   //代理人实体类，先留着，后期又可能选择人员树
	private Office sqOffice; // 申请医院
	private Office sjOffice; // 涉及医院
	private User linkEmployee;		// 下一环节人员


	public AssessAudit() {
		super();
	}

	public AssessAudit(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getDlEmployee() {
		return dlEmployee;
	}

	public void setDlEmployee(User dlEmployee) {
		this.dlEmployee = dlEmployee;
	}

	public Office getSqOffice() {
		return sqOffice;
	}

	public void setSqOffice(Office sqOffice) {
		this.sqOffice = sqOffice;
	}

	public Office getSjOffice() {
		return sjOffice;
	}

	public void setSjOffice(Office sjOffice) {
		this.sjOffice = sjOffice;
	}

	public User getLinkEmployee() {
		return linkEmployee;
	}

	public void setLinkEmployee(User linkEmployee) {
		this.linkEmployee = linkEmployee;
	}

	public ReportRegistration getReportRegistration() {
        return reportRegistration;
    }

    public void setReportRegistration(ReportRegistration reportRegistration) {
        this.reportRegistration = reportRegistration;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public AuditAcceptance getAuditAcceptance() {
        return auditAcceptance;
    }

    public void setAuditAcceptance(AuditAcceptance auditAcceptance) {
        this.auditAcceptance = auditAcceptance;
    }

    public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	@Length(min=0, max=32, message="审批主键长度必须介于 0 和 32 之间")
	public String getAssessAuditId() {
		return assessAuditId;
	}

	public void setAssessAuditId(String assessAuditId) {
		this.assessAuditId = assessAuditId;
	}
	
	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=0, max=32, message="患方申请人长度必须介于 0 和 32 之间")
	public String getPatientApplyer() {
		return patientApplyer;
	}

	public void setPatientApplyer(String patientApplyer) {
		this.patientApplyer = patientApplyer;
	}
	
	@Length(min=0, max=1, message="与患者关系长度必须介于 0 和 1 之间")
	public String getPatientRelation() {
		return patientRelation;
	}

	public void setPatientRelation(String patientRelation) {
		this.patientRelation = patientRelation;
	}
	
	@Length(min=0, max=15, message="患方电话长度必须介于 0 和 15 之间")
	public String getPatientMobile() {
		return patientMobile;
	}

	public void setPatientMobile(String patientMobile) {
		this.patientMobile = patientMobile;
	}
	
	@Length(min=0, max=10, message="患者姓名长度必须介于 0 和 10 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Length(min=0, max=1, message="患方性别长度必须介于 0 和 1 之间")
	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	
	@Length(min=0, max=4, message="患方年龄长度必须介于 0 和 4 之间")
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	
	@Length(min=0, max=32, message="患方涉及医院长度必须介于 0 和 32 之间")
	public String getInvolveHospital() {
		return involveHospital;
	}

	public void setInvolveHospital(String involveHospital) {
		this.involveHospital = involveHospital;
	}
	
	@Length(min=0, max=200, message="患方申请事项长度必须介于 0 和 200 之间")
	public String getPatientApplyMatter() {
		return patientApplyMatter;
	}

	public void setPatientApplyMatter(String patientApplyMatter) {
		this.patientApplyMatter = patientApplyMatter;
	}
	
	@Length(min=0, max=32, message="申请医院长度必须介于 0 和 32 之间")
	public String getHospitalApply() {
		return hospitalApply;
	}

	public void setHospitalApply(String hospitalApply) {
		this.hospitalApply = hospitalApply;
	}
	
	@Length(min=0, max=10, message="代理人长度必须介于 0 和 10 之间")
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	@Length(min=0, max=15, message="医方电话长度必须介于 0 和 15 之间")
	public String getHospitalMobile() {
		return hospitalMobile;
	}

	public void setHospitalMobile(String hospitalMobile) {
		this.hospitalMobile = hospitalMobile;
	}
	
	@Length(min=0, max=10, message="医方姓名长度必须介于 0 和 10 之间")
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	@Length(min=0, max=4, message="医方年龄长度必须介于 0 和 4 之间")
	public String getHospitalAge() {
		return hospitalAge;
	}

	public void setHospitalAge(String hospitalAge) {
		this.hospitalAge = hospitalAge;
	}
	
	@Length(min=0, max=1, message="医方性别长度必须介于 0 和 1 之间")
	public String getHospitalSex() {
		return hospitalSex;
	}

	public void setHospitalSex(String hospitalSex) {
		this.hospitalSex = hospitalSex;
	}
	
	@Length(min=0, max=200, message="医方申请事项长度必须介于 0 和 200 之间")
	public String getHospitalApplyMatter() {
		return hospitalApplyMatter;
	}

	public void setHospitalApplyMatter(String hospitalApplyMatter) {
		this.hospitalApplyMatter = hospitalApplyMatter;
	}
	
	@Length(min=0, max=1, message="申请类型长度必须介于 0 和 1 之间")
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	@Length(min=0, max=200, message="地点长度必须介于 0 和 200 之间")
	public String getAuditAddress() {
		return auditAddress;
	}

	public void setAuditAddress(String auditAddress) {
		this.auditAddress = auditAddress;
	}
	
	@Length(min=0, max=32, message="医学专家长度必须介于 0 和 32 之间")
	public String getMedicalExpert() {
		return medicalExpert;
	}

	public void setMedicalExpert(String medicalExpert) {
		this.medicalExpert = medicalExpert;
	}
	
	@Length(min=0, max=10, message="日期长度必须介于 0 和 10 之间")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Length(min=0, max=32, message="法律专家长度必须介于 0 和 32 之间")
	public String getLegalExpert() {
		return legalExpert;
	}

	public void setLegalExpert(String legalExpert) {
		this.legalExpert = legalExpert;
	}
	
	@Length(min=0, max=32, message="处理人长度必须介于 0 和 32 之间")
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
	
	@Length(min=0, max=32, message="next_link长度必须介于 0 和 32 之间")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
	
	@Length(min=0, max=32, message="next_link_man长度必须介于 0 和 32 之间")
	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}
	
}