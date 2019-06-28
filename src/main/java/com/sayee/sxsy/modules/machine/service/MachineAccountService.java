/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.machine.service;

import java.text.SimpleDateFormat;
import java.util.List;

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

    public MachineAccount get(String id) {
        return super.get(id);
    }

    public List<MachineAccount> findList(MachineAccount machineAccount) {
        return super.findList(machineAccount);
    }

    public Page<MachineAccount> findPage(Page<MachineAccount> page, MachineAccount machineAccount) {
        return super.findPage(page, machineAccount);
    }

    @Transactional(readOnly = false)
    public void save(MachineAccount machineAccount) {
        machineAccount.preInsert();
        machineAccount.setMachineAccountId(machineAccount.getId());
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
        if (StringUtils.isBlank(machineAccount.getAgreementAmount()) || machineAccount.getAgreementAmount() == null) {
            machineAccount.setAgreementAmount("0");
        }
        if (StringUtils.isBlank(machineAccount.getInsuranceAmount()) || machineAccount.getInsuranceAmount() == null) {
            machineAccount.setInsuranceAmount("0");
        }
        machineAccount.setIsNewRecord(true);
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
            if (StringUtils.isBlank(machineAccount.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.get(auditAcceptance.getComplaintMainId());
                if (org.springframework.util.StringUtils.isEmpty(machineAccount1)) {
                    machineAccount.preInsert();
                    machineAccount.setMachineAccountId(machineAccount.getId());
                    machineAccount.setComplaintMainId(auditAcceptance.getComplaintMainId());
                    machineAccount.setPatientName(auditAcceptance.getMediateApplyInfo().getPatientName());
                    machineAccount.setPolicyNumber(auditAcceptance.getPolicyNumber());
                    machineAccount.setInsuranceCompany(auditAcceptance.getInsuranceCompany());
                    machineAccount.setIsMajor(auditAcceptance.getReportRegistration().getIsMajor());
                    machineAccount.setReportingTime(auditAcceptance.getReportRegistration().getReportTime());
                    machineAccount.setDisputesTime(auditAcceptance.getReportRegistration().getDisputeTime());
                    machineAccount.setSummaryOfDisputes(auditAcceptance.getMediateApplyInfo().getSummaryOfDisputes());
                    machineAccount.setTreatmentMode(auditAcceptance.getDiagnosisMode());
                    machineAccount.setTreatmentResult(auditAcceptance.getTreatmentOutcome());
                    machineAccount.setHospitalId(auditAcceptance.getMediateApplyInfo().getInvolveHospital());
                    machineAccount.setDelFlag("0");
                    dao.insert(machineAccount);
                } else {
                    machineAccount1.preUpdate();
                    machineAccount1.setMachineAccountId(machineAccount.getId());
                    machineAccount1.setComplaintMainId(auditAcceptance.getComplaintMainId());
                    machineAccount1.setPatientName(auditAcceptance.getMediateApplyInfo().getPatientName());
                    machineAccount1.setPolicyNumber(auditAcceptance.getPolicyNumber());
                    machineAccount1.setInsuranceCompany(auditAcceptance.getInsuranceCompany());
                    machineAccount1.setIsMajor(auditAcceptance.getReportRegistration().getIsMajor());
                    machineAccount1.setReportingTime(auditAcceptance.getReportRegistration().getReportTime());
                    machineAccount1.setSummaryOfDisputes(auditAcceptance.getMediateApplyInfo().getSummaryOfDisputes());
                    machineAccount1.setTreatmentMode(auditAcceptance.getDiagnosisMode());
                    machineAccount1.setTreatmentResult(auditAcceptance.getTreatmentOutcome());
                    machineAccount1.setHospitalId(auditAcceptance.getMediateApplyInfo().getInvolveHospital());
                    dao.update(machineAccount1);
                }
            }
        } else if ("b".equals(node)) {
            InvestigateEvidence investigateEvidence = investigateEvidenceService.get(id);
            if (StringUtils.isNotBlank(investigateEvidence.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.get(investigateEvidence.getComplaintMainId());
                    machineAccount1.preUpdate();
                    machineAccount1.setPatientsReflectFocus(investigateEvidence.getFocus());
                    dao.update(machineAccount1);

            }
        } else if ("c".equals(node)) {
            MediateEvidence mediateEvidence = mediateEvidenceService.get(id);
            if (StringUtils.isNotBlank(mediateEvidence.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.get(mediateEvidence.getComplaintMainId());
                machineAccount1.preUpdate();
                machineAccount1.setMediatorId(mediateEvidence.getRecordInfo().getHost());
                machineAccount1.setDeptId(mediateEvidence.getRecordInfo().getYtwHost().getOffice().getId());
                dao.update(machineAccount1);
            }
        } else if ("d".equals(node)) {
            AssessAppraisal assessAppraisal = assessAppraisalService.get(id);
            if (StringUtils.isNotBlank(assessAppraisal.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.get(assessAppraisal.getComplaintMainId());
                machineAccount1.preUpdate();
                machineAccount1.setDutyRatio(assessAppraisal.getResponsibilityRatio());
                dao.update(machineAccount1);
            }
        } else if ("e".equals(node)) {
            SignAgreement signAgreement = signAgreementService.get(id);
            if (StringUtils.isNotBlank(signAgreement.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.get(signAgreement.getComplaintMainId());
                machineAccount1.preUpdate();
                machineAccount1.setAgreementNumber(signAgreement.getAgreementNumber());
                machineAccount1.setRatifyAccord(signAgreement.getRatifyAccord());
                if (StringUtils.isNotBlank(signAgreement.getAgreementAmount()) || "0".equals(signAgreement.getAgreementAmount()) || "".equals(signAgreement.getAgreementAmount())) {
                    machineAccount1.setAgreementAmount(signAgreement.getAgreementAmount());
                } else {
                    machineAccount1.setAgreementAmount("0");
                }
                if (StringUtils.isNotBlank(signAgreement.getInsuranceAmount()) || "0".equals(signAgreement.getInsuranceAmount()) || "".equals(signAgreement.getInsuranceAmount())) {
                    machineAccount1.setInsuranceAmount(signAgreement.getInsuranceAmount());
                } else {
                    machineAccount1.setInsuranceAmount("0");
                }
                machineAccount1.setClaimSettlementTime(signAgreement.getClaimSettlementTime());
                machineAccount1.setCompensateTime(signAgreement.getCompensateTime());
                dao.update(machineAccount1);
            }
        } else if ("f".equals(node)) {
            SummaryInfo summaryInfo = summaryInfoService.get(id);
            if (StringUtils.isNotBlank(summaryInfo.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.get(summaryInfo.getComplaintMainId());
                machineAccount1.preUpdate();
                machineAccount1.setArchiveTime(summaryInfo.getFilingTime());
                machineAccount1.setFileNumber(summaryInfo.getFileNumber());
                dao.update(machineAccount1);
            }
        }
    }
}