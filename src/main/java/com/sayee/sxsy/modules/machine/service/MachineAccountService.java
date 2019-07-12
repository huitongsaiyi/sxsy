/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.machine.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.UserAgentUtils;
import com.sayee.sxsy.modules.assessappraisal.entity.AssessAppraisal;
import com.sayee.sxsy.modules.assessappraisal.service.AssessAppraisalService;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.auditacceptance.service.AuditAcceptanceService;
import com.sayee.sxsy.modules.mediate.entity.MediateEvidence;
import com.sayee.sxsy.modules.mediate.service.MediateEvidenceService;
import com.sayee.sxsy.modules.nestigateeividence.entity.InvestigateEvidence;
import com.sayee.sxsy.modules.nestigateeividence.service.InvestigateEvidenceService;
import com.sayee.sxsy.modules.perform.entity.PerformAgreement;
import com.sayee.sxsy.modules.perform.service.PerformAgreementService;
import com.sayee.sxsy.modules.sign.entity.SignAgreement;
import com.sayee.sxsy.modules.sign.service.SignAgreementService;
import com.sayee.sxsy.modules.summaryinfo.entity.SummaryInfo;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
import com.sayee.sxsy.modules.sys.entity.Menu;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.machine.dao.MachineAccountDao;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 台账信息展示Service
 *
 * @author zhangfan
 * @version 2019-05-17
 */
@Service
@Lazy(value = true)
@Transactional(readOnly = true)
public class MachineAccountService extends CrudService<MachineAccountDao, MachineAccount> {
    @Autowired
    private AuditAcceptanceService auditAcceptanceService;
    @Autowired
    private InvestigateEvidenceService investigateEvidenceService;
    @Autowired
    private MediateEvidenceService mediateEvidenceService;
    @Autowired
    private AssessAppraisalService assessAppraisalService;
    @Autowired
    private SignAgreementService signAgreementService;
    @Autowired
    private SummaryInfoService summaryInfoService;
    @Autowired
    private MachineAccountDao machineAccountDao;
    @Autowired
    private PerformAgreementService performAgreementService;
    public MachineAccount get(String id) {
        String machineAccountId=id;
        return machineAccountDao.getDetail(machineAccountId);
    }
    public MachineAccount getM(String id){
     MachineAccount machineAccount = super.get(id);
        return machineAccount;
    }
    public List<MachineAccount> findList(MachineAccount machineAccount) {
        return super.findList(machineAccount);
    }

    public Page<MachineAccount> findPage(Page<MachineAccount> page, MachineAccount machineAccount) {
        return super.findPage(page, machineAccount);
    }

    @Transactional(readOnly = false)
    public void save(MachineAccount machineAccount) {
//        machineAccount.preInsert();
        machineAccount.setMachineAccountId(IdGen.uuid());
        //转换调解员
        User user = UserUtils.getId(machineAccount.getMediatorId());
        machineAccount.setMediatorId(user != null ? user.getId() : machineAccount.getMediatorId());
        //转换部门OFFICE
//		Office office=UserUtils.officeId(machineAccount.getDeptId());
//		machineAccount.setDeptId(office!=null ?  office.getId() :machineAccount.getDeptId());
        machineAccount.setDeptId(user != null ? user.getOffice().getId() : machineAccount.getDeptId());
        //是否重大
        if ("是".equals(machineAccount.getIsMajor()) || "1".equals(machineAccount.getIsMajor())) {
            machineAccount.setIsMajor("1");
        } else {
            machineAccount.setIsMajor("0");
        }
        //对金额 字段 为空的时候进行处理
        //协议金额=保险金额+医院金额
        if (StringUtils.isBlank(machineAccount.getAgreementAmount()) || machineAccount.getAgreementAmount() == null) {
            machineAccount.setAgreementAmount("0");
        }
        if (StringUtils.isBlank(machineAccount.getInsuranceAmount()) || machineAccount.getInsuranceAmount() == null) {
            machineAccount.setInsuranceAmount("0");
        }
        if (StringUtils.isBlank(machineAccount.getHospitalAmount()) || machineAccount.getHospitalAmount() == null) {
            machineAccount.setHospitalAmount(String.valueOf(Float.valueOf(machineAccount.getAgreementAmount())-Float.valueOf(machineAccount.getInsuranceAmount())));
        }

        //流转天数”设公式=交理赔时间-赔付时间，按天计算、时间格式统一  流转天数为空，且交理赔时间和赔付时间不为空
        if (StringUtils.isNotBlank(machineAccount.getClaimSettlementTime()) && StringUtils.isNotBlank(machineAccount.getCompensateTime())  && (StringUtils.isBlank(machineAccount.getFlowDays()) || machineAccount.getFlowDays() == null)){
            //将字符串专为日期格式
            if(DateUtils.parseDate(machineAccount.getCompensateTime())!=null && DateUtils.parseDate(machineAccount.getClaimSettlementTime())!=null){
                machineAccount.setFlowDays(String.valueOf(DateUtils.getDistanceOfTwoDate(DateUtils.parseDate(machineAccount.getCompensateTime()),DateUtils.parseDate(machineAccount.getClaimSettlementTime()))));
            }
        }
//        machineAccount.setIsNewRecord(true);
        machineAccount.setDelFlag("0");
        super.save(machineAccount);
    }

    @Transactional(readOnly = false)
    public void delete(MachineAccount machineAccount) {
        super.delete(machineAccount);
    }

    /*
     *卷宗编号验重
     *
     * */
    @Transactional(readOnly = false)
    public boolean checkFileNumber(String fileNumber) {
        if (StringUtils.isNotBlank(fileNumber)) {
            MachineAccount machineAccount = dao.checkFileNumber(fileNumber);
            if (machineAccount != null) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Transactional(readOnly = false)
    public void savetz(MachineAccount machineAccount, String node, String id) {
        if ("a".equals(node)) {
            AuditAcceptance auditAcceptance = auditAcceptanceService.get(id);
            machineAccount = this.getM(auditAcceptance.getComplaintMainId());
            MachineAccount machineAccount1=new MachineAccount();
                if (machineAccount==null) {

                    machineAccount1.setMachineAccountId(IdGen.uuid());
                    machineAccount1.setComplaintMainId(auditAcceptance.getComplaintMainId());
                    machineAccount1.setPatientName(auditAcceptance.getMediateApplyInfo().getPatientName());
                    machineAccount1.setPolicyNumber(auditAcceptance.getPolicyNumber());
                    machineAccount1.setInsuranceCompany(auditAcceptance.getInsuranceCompany());
                    machineAccount1.setIsMajor(auditAcceptance.getReportRegistration().getIsMajor());
                    machineAccount1.setReportingTime(auditAcceptance.getReportRegistration().getReportTime());
                    machineAccount1.setDisputesTime(auditAcceptance.getReportRegistration().getDisputeTime());
                    machineAccount1.setSummaryOfDisputes(auditAcceptance.getMediateApplyInfo().getSummaryOfDisputes());
                    machineAccount1.setTreatmentMode(auditAcceptance.getDiagnosisMode());
                    machineAccount1.setTreatmentResult(auditAcceptance.getTreatmentOutcome());
                    machineAccount1.setHospitalId(auditAcceptance.getMediateApplyInfo().getInvolveHospital());
                    machineAccount1.setStartInsuranceTime(auditAcceptance.getGuaranteeTime());
                    machineAccount1.setDelFlag("0");
                    machineAccount1.preInsert();
                    dao.insert(machineAccount1);
                }
            else{
                machineAccount.preUpdate();
                machineAccount.setMachineAccountId(machineAccount.getMachineAccountId());
                machineAccount.setComplaintMainId(auditAcceptance.getComplaintMainId());
                machineAccount.setPatientName(auditAcceptance.getMediateApplyInfo().getPatientName());
                machineAccount.setPolicyNumber(auditAcceptance.getPolicyNumber());
                machineAccount.setInsuranceCompany(auditAcceptance.getInsuranceCompany());
                machineAccount.setIsMajor(auditAcceptance.getReportRegistration().getIsMajor());
                machineAccount.setReportingTime(auditAcceptance.getReportRegistration().getReportTime());
                machineAccount.setSummaryOfDisputes(auditAcceptance.getMediateApplyInfo().getSummaryOfDisputes());
                machineAccount.setTreatmentMode(auditAcceptance.getDiagnosisMode());
                machineAccount.setTreatmentResult(auditAcceptance.getTreatmentOutcome());
                machineAccount.setHospitalId(auditAcceptance.getMediateApplyInfo().getInvolveHospital());
                machineAccount.setStartInsuranceTime(auditAcceptance.getGuaranteeTime());
                dao.update(machineAccount);
            }
        } else if ("b".equals(node)) {
            InvestigateEvidence investigateEvidence = investigateEvidenceService.get(id);
            if (StringUtils.isNotBlank(investigateEvidence.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(investigateEvidence.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
                    machineAccount1.setPatientsReflectFocus(investigateEvidence.getFocus());
                    dao.update(machineAccount1);
                }
            }
        } else if ("c".equals(node)) {
            MediateEvidence mediateEvidence = mediateEvidenceService.get(id);
            if (StringUtils.isNotBlank(mediateEvidence.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(mediateEvidence.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();

                    machineAccount1.setMediatorId(mediateEvidence.getRecordInfo().getHost());
                    machineAccount1.setDeptId(UserUtils.get(mediateEvidence.getRecordInfo().getHost()).getOffice().getId());
                    dao.update(machineAccount1);
                }
            }
        } else if ("d".equals(node)) {
            AssessAppraisal assessAppraisal = assessAppraisalService.get(id);
            if (StringUtils.isNotBlank(assessAppraisal.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(assessAppraisal.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
                    machineAccount1.setDutyRatio(assessAppraisal.getResponsibilityRatio());
                    machineAccount1.setAssessTime(assessAppraisal.getRecordInfo1().getStartTime());
                    machineAccount1.setAssessNumber(assessAppraisal.getProposal().getProposalCode());
                    dao.update(machineAccount1);
                }
            }
        } else if ("si".equals(node)) {
            SignAgreement signAgreement = signAgreementService.get(id);
            if (StringUtils.isNotBlank(signAgreement.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(signAgreement.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
                    machineAccount1.setAgreementNumber(signAgreement.getAgreementNumber());
                    machineAccount1.setRatifyAccord(signAgreement.getRatifyAccord());
                    dao.update(machineAccount1);
                }
            }
        }else if("per".equals(node)){
            PerformAgreement performAgreement = performAgreementService.get(id);
            if(StringUtils.isNotBlank(performAgreement.getComplaintMainId())){
                MachineAccount m = this.getM(performAgreement.getComplaintMainId());
                if(m!=null){
                    m.preUpdate();
                    m.setClaimSettlementTime(performAgreement.getClaimSettlementTime());
                    m.setCompensateTime(performAgreement.getInsurancePayTime());
                    this.TianShu(m);
                    if (StringUtils.isNotBlank(performAgreement.getAgreementPayAmount()) || "0".equals(performAgreement.getAgreementPayAmount()) || "".equals(performAgreement.getAgreementPayAmount())) {
                        m.setAgreementAmount(performAgreement.getAgreementPayAmount());
                    } else {
                        m.setAgreementAmount("0");
                    }
                    if (StringUtils.isNotBlank(performAgreement.getInsurancePayAmount()) || "0".equals(performAgreement.getInsurancePayAmount()) || "".equals(performAgreement.getInsurancePayAmount())) {
                        m.setInsuranceAmount(performAgreement.getInsurancePayAmount());
                    } else {
                        m.setInsuranceAmount("0");
                    }
                    m.setHospitalAmount(performAgreement.getHospitalPayAmount());
                    dao.update(m);
                }
            }
        }else if ("f".equals(node)) {
            SummaryInfo summaryInfo = summaryInfoService.get(id);
            if (StringUtils.isNotBlank(summaryInfo.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(summaryInfo.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
                    machineAccount1.setArchiveTime(summaryInfo.getFilingTime());
                    machineAccount1.setFileNumber(summaryInfo.getFileNumber());
                    dao.update(machineAccount1);
                }

            }
        }
    }

    public MachineAccount TianShu(MachineAccount machineAccount){
        String strat = machineAccount.getClaimSettlementTime();
        String end = machineAccount.getCompensateTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(strat);
            d2 = format.parse(end);

            long diff = d1.getTime() - d2.getTime();

            Integer total = (int)(diff/1000);
            Integer day = total/(3600*24);
            machineAccount.setFlowDays(String.valueOf(day));
        }catch (Exception e){
            e.printStackTrace();
        }

        return machineAccount;
    }

    public Page<MachineAccount> getMachine(Page<MachineAccount> page,MachineAccount machineAccount){
//        machineAccount.getSqlMap().get("",);
        machineAccount.setPage(page);
        page.setList(machineAccountDao.findMachine(machineAccount));
        return page;
    }
}