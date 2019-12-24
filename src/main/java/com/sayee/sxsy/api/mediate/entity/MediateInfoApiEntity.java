package com.sayee.sxsy.api.mediate.entity;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.Date;

/**
 * @author www.donxon.com
 * @Description
 */
public class MediateInfoApiEntity extends DataEntity<MediateInfoApiEntity> {
    //INSERT INTO COMPLAINT_INFO(case_number,complaint_main_id,visitor_name,visitor_mobile,patient_relation,patient_name,patient_sex,patient_age, visitor_number, involve_hospital,involve_department,complaint_id,involve_employee,summary_of_disputes,visitor_date,complaint_mode,handle_way,,is_major,appeal, reception_employee, reception_date, next_link, next_link_man, is_mediate, handle_result, handle_pass, status, expected_closure, closing_method, amount_involved, complaint_type, create_by, create_date, update_by, update_date, del_flag ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )
    private static final long serialVersionUID = 1L;
    private String caseNumber;//案件编号
    private String complaintMainId;//主表id
    private String visitorName;//访客姓名
    private String visitorMobile;//联系方式
    private String patientRelation;//与患者关系
    private String patientName;//患者姓名
    private String patientSex;//性别
    private Integer patientAge;//年龄
    private String visitorNumber;//来访人数
    private String involveHospital;//涉及医院
    private String involveDepartment;//涉及科室
    private String complaintId;//id
    private String involveEmployee;//涉及人员
    private String summaryOfDisputes;//纠纷概要
    private String visitorDate;//发生时间/来访时间
    private String complaintMode;//投诉方式
    private String handleWay;//处理方式
    private String shiftHandle;//转办科室（选择当前医院的科室）
    private String isMajor;//是否重大
    private String appeal;//诉求
    private String receptionEmployee;//接待人员
    private String receptionDate;//接待日期
    private String nextLink;//下一处理环节
    private String nextLinkMan;//下一环节处理人
    private String isMediate;//是否进入医调委调解
    private String handleResult;//处理结果
    private String handlePass;//处理经过
    private String status;//0处理中 1协调中 2结案(当面 处理时的状态) //0处理中 1审核受理 2调查取证 3执政调解 4 评估坚定 5达成调解 6签署协议 7履行协议
    private String expectedClosure;//结案预期
    private String closingMethod;//结案结果
    private float amountInvolved;//涉及金额
    private String complaintType;//complaint_type
    private String createUser;//来访者
    //private string mistake;//存在过错

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorMobile() {
        return visitorMobile;
    }

    public void setVisitorMobile(String visitorMobile) {
        this.visitorMobile = visitorMobile;
    }

    public String getPatientRelation() {
        return patientRelation;
    }

    public void setPatientRelation(String patientRelation) {
        this.patientRelation = patientRelation;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getVisitorNumber() {
        return visitorNumber;
    }

    public void setVisitorNumber(String visitorNumber) {
        this.visitorNumber = visitorNumber;
    }

    public String getInvolveHospital() {
        return involveHospital;
    }

    public void setInvolveHospital(String involveHospital) {
        this.involveHospital = involveHospital;
    }

    public String getInvolveDepartment() {
        return involveDepartment;
    }

    public void setInvolveDepartment(String involveDepartment) {
        this.involveDepartment = involveDepartment;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getInvolveEmployee() {
        return involveEmployee;
    }

    public void setInvolveEmployee(String involveEmployee) {
        this.involveEmployee = involveEmployee;
    }

    public String getSummaryOfDisputes() {
        return summaryOfDisputes;
    }

    public void setSummaryOfDisputes(String summaryOfDisputes) {
        this.summaryOfDisputes = summaryOfDisputes;
    }

    public String getVisitorDate() {
        return visitorDate;
    }

    public void setVisitorDate(String visitorDate) {
        this.visitorDate = visitorDate;
    }

    public String getComplaintMode() {
        return complaintMode;
    }

    public void setComplaintMode(String complaintMode) {
        this.complaintMode = complaintMode;
    }

    public String getHandleWay() {
        return handleWay;
    }

    public void setHandleWay(String handleWay) {
        this.handleWay = handleWay;
    }

    public String getShiftHandle() {
        return shiftHandle;
    }

    public void setShiftHandle(String shiftHandle) {
        this.shiftHandle = shiftHandle;
    }

    public String getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(String isMajor) {
        this.isMajor = isMajor;
    }

    public String getAppeal() {
        return appeal;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal;
    }

    public String getReceptionEmployee() {
        return receptionEmployee;
    }

    public void setReceptionEmployee(String receptionEmployee) {
        this.receptionEmployee = receptionEmployee;
    }

    public String getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(String receptionDate) {
        this.receptionDate = receptionDate;
    }

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

    public String getIsMediate() {
        return isMediate;
    }

    public void setIsMediate(String isMediate) {
        this.isMediate = isMediate;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public String getHandlePass() {
        return handlePass;
    }

    public void setHandlePass(String handlePass) {
        this.handlePass = handlePass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpectedClosure() {
        return expectedClosure;
    }

    public void setExpectedClosure(String expectedClosure) {
        this.expectedClosure = expectedClosure;
    }

    public String getClosingMethod() {
        return closingMethod;
    }

    public void setClosingMethod(String closingMethod) {
        this.closingMethod = closingMethod;
    }

    public float getAmountInvolved() {
        return amountInvolved;
    }

    public void setAmountInvolved(float amountInvolved) {
        this.amountInvolved = amountInvolved;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
