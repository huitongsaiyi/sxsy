/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessinfo.entity;

import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.summaryinfo.entity.SummaryInfo;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 案件评价Entity
 * @author lyt
 * @version 2019-06-17
 */
public class AssessInfo extends DataEntity<AssessInfo> {
	
	private static final long serialVersionUID = 1L;
	private String assessId;		// 评价主键
	private String complaintMainId;		// 主键
	private String appraiser;		// 评价人,关联人员ID
	private String assessTime;		// 评价时间
	private String assessGrade;		// 评价分数
	private String assessContent;		// 评价内容
	private String handlePeople;		// 处理人
	private String handleTime;		// 处理时间
	private String nextLink;		// 下一环节
	private String nextLinkMan;		// 下一环节处理人
    private ComplaintMain complaintMain;        //主表
	private User user;      //评价人
	private Area area;		//所属地市
	private User linkEmployee;		// 下一环节人员
	private AuditAcceptance auditAcceptance;
	private ReportRegistration reportRegistration;
	private SummaryInfo summaryInfo;
	private String createUser;  //创建人员id

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public AssessInfo() {
		super();
	}

	public AssessInfo(String id){
		super(id);
	}

	public SummaryInfo getSummaryInfo() {
		return summaryInfo;
	}

	public void setSummaryInfo(SummaryInfo summaryInfo) {
		this.summaryInfo = summaryInfo;
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

	public AuditAcceptance getAuditAcceptance() {
		return auditAcceptance;
	}

	public void setAuditAcceptance(AuditAcceptance auditAcceptance) {
		this.auditAcceptance = auditAcceptance;
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

    @Length(min=0, max=32, message="评价主键长度必须介于 0 和 32 之间")
	public String getAssessId() {
		return assessId;
	}

	public void setAssessId(String assessId) {
		this.assessId = assessId;
	}
	
	@Length(min=0, max=32, message="主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=1, max=32, message="评价人,关联人员ID长度必须介于 0 和 32 之间")
	public String getAppraiser() {
		return appraiser;
	}

	public void setAppraiser(String appraiser) {
		this.appraiser = appraiser;
	}
	
	@Length(min=1, max=20, message="评价时间长度必须介于 0 和 20 之间")
	public String getAssessTime() {
		return assessTime;
	}

	public void setAssessTime(String assessTime) {
		this.assessTime = assessTime;
	}
	
	@Length(min=1, max=3, message="评价分数长度必须介于 0 和 3 之间")
	@Min(value = 0)
	@Max(value = 100)
	public String getAssessGrade() {
		return assessGrade;
	}

	public void setAssessGrade(String assessGrade) {
		this.assessGrade = assessGrade;
	}
	
	public String getAssessContent() {
		return assessContent;
	}

	public void setAssessContent(String assessContent) {
		this.assessContent = assessContent;
	}
	
	@Length(min=0, max=32, message="处理人长度必须介于 0 和 32 之间")
	public String getHandlePeople() {
		return handlePeople;
	}

	public void setHandlePeople(String handlePeople) {
		this.handlePeople = handlePeople;
	}
	
	@Length(min=0, max=20, message="处理时间长度必须介于 0 和 20 之间")
	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	
	@Length(min=0, max=32, message="下一环节长度必须介于 0 和 32 之间")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
	
	@Length(min=1, max=32, message="下一环节处理人长度必须介于 0 和 32 之间")
	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}
	
}