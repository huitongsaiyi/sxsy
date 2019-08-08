/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.sys.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;

import javax.servlet.jsp.jstl.core.Config;

/**
 * 纠纷调解Service
 * @author lyt
 * @version 2019-06-04
 */
@Service
@Transactional(readOnly = true)
public class ComplaintMainService extends CrudService<ComplaintMainDao, ComplaintMain> {
	@Autowired
	private ComplaintMainDao complaintMainDao;

	public ComplaintMain get(String id) {
		return super.get(id);
	}
	
	public List<ComplaintMain> findList(ComplaintMain complaintMain) {
		return super.findList(complaintMain);
	}
	
	public Page<ComplaintMain> findPage(Page<ComplaintMain> page, ComplaintMain complaintMain) {
		return super.findPage(page, complaintMain);
	}
	
	@Transactional(readOnly = false)
	public void save(ComplaintMain complaintMain) {
		if(StringUtils.isBlank(complaintMain.getComplaintMainId())){
			complaintMain.preInsert();
			complaintMain.setComplaintMainId(complaintMain.getId());
			dao.insert(complaintMain);
		}else{
			complaintMain.preUpdate();
			dao.update(complaintMain);
		}
//		super.save(complaintMain);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComplaintMain complaintMain) {
		super.delete(complaintMain);
	}
	/**
	 * 获取我的待办数目
	 */
    public Long findCount(User user) {
		return  complaintMainDao.findCount(user.getLoginName());
    }

	public Page<ComplaintMain> selfList(Page<ComplaintMain> page, ComplaintMain complaintMain) {
		List<ComplaintMain> list=complaintMainDao.selfList(complaintMain.getUser().getLoginName());
		//对 集合进行处理，把节点的 主键拿到 可是使其点击 详情与处理 按钮
		this.format(list);
		complaintMain.setPage(page);
		page.setList(list);
		return page;
	}

//	public List<Map<String,Object>> selfList(User user) {
//		List<Map<String,Object>> list=complaintMainDao.selfList(user.getLoginName());
//		//对 集合进行处理，把节点的 主键拿到 可是使其点击 详情与处理 按钮
//		this.format(list);
//    	return list;
//	}

	public void format(List<ComplaintMain> list){
    	String key="";
    	String path="";
		for (ComplaintMain complaintMain: list) {
			if ("enrollment".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				 if (StringUtils.isBlank(key)){
					 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"complaint_main_detail_id","COMPLAINT_MAIN_DETAIL");
				 }
				 path="/registration/reportRegistration/";
			}else if("check".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"audit_acceptance_id","AUDIT_ACCEPTANCE");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				 path="/auditacceptance/auditAcceptance/";
			}else if("forensics".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"investigate_evidence_id","INVESTIGATE_EVIDENCE");
				 if (StringUtils.isBlank(key)){
					 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				 }
				 path="/nestigateeividence/investigateEvidence/";
			}else if("mediation".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"mediate_evidence_id","MEDIATE_EVIDENCE");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/mediate/mediateEvidence/";
			}else if("apply".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"assess_apply_id","ASSESS_APPLY");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				 path="/assessapply/assessApply/";
			}else if("approval".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"assess_audit_id","ASSESS_AUDIT");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"assess_apply_id","ASSESS_APPLY");
				}
				 path="/assessaudit/assessAudit/";
			}else if("evaluation".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"assess_appraisal_id","ASSESS_APPRAISAL");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				 path="/assessappraisal/assessAppraisal/";
			}else if("reach".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"reach_mediate_id","REACH_MEDIATE");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				 path="/reachmediate/reachMediate/";
			}else if("sign".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"sign_agreement_id","SIGN_AGREEMENT");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				 path="/sign/signAgreement/";
			}else if("fulfill".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"perform_agreement_id","PERFORM_AGREEMENT");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				 path="/perform/performAgreement/";
			}else if("summary".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"summary_id","SUMMARY_INFO");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				 path="/summaryinfo/summaryInfo/";
			}else if("assess".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"assess_id","ASSESS_INFO");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				 path="/assessinfo/assessInfo/";
			}else if("feedback".equals(complaintMain.getTaskDefKey())){
				 key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"feedback_id","CASE_FEEDBACK");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/casefeedback/caseFeedback/";
			}
			if (StringUtils.isNotBlank(key)){
				complaintMain.setKey(key);
				complaintMain.setUrl(path);
			}
		}

	}


    public List<ComplaintMain > getMyDone(String loginName) {
    	//根据登录人 取得登陆人已经办理完成的数据
		List<ComplaintMain> list=complaintMainDao.getMyDone(loginName);
		//清除查到的  null  值
		list.removeAll(Collections.singleton(null));
		this.format(list);
    	return list;
    }

    public List<Map<String, Object>> findTypeInfo(User user,String year,String month) {
        if ("".equals(user.getRole())){

        }
        if (StringUtils.isBlank(year) && StringUtils.isBlank(month)){
        	year= DateUtils.getYear();
		}
        List<Map<String, Object>> list=complaintMainDao.findTypeInfo(year,month);
        return list;
    }

	public List<Map<String, Object>> findGrade(User user,String year,String month) {
		if ("".equals(user.getRole())){

		}
		if (StringUtils.isBlank(year) && StringUtils.isBlank(month)){
			year= DateUtils.getYear();
		}
		List<Map<String, Object>> list=complaintMainDao.findGrade(year,month);
		return list;
	}

    public List<Map<String, Object>> getEveryMonthData(User user,String year,String month) {
        if ("".equals(user.getRole())){

        }
        if (StringUtils.isBlank(year) && StringUtils.isBlank(month)){
            year= DateUtils.getYear();
        }
        List<Map<String, Object>> list=complaintMainDao.getEveryMonthData(year,month);
        return list;
    }

    /***
     * 获取山西省各市的案件数量
     * @param year
     * @param month
     * @return
     */
    @Transactional(readOnly = false)
    public List<Map<String,Object>> findAreaName (String year,String month){
        if(StringUtils.isBlank(year) && StringUtils.isBlank(month)){
            year =  DateUtils.getYear();
        }
        List<Map<String,Object>> list = complaintMainDao.findAreaName(year,month);
        return list;
    }

	public List<ComplaintMain > findCompleted(User user,String year,String month) {
		if ("".equals(user.getRole())){

		}
		if (StringUtils.isBlank(year) && StringUtils.isBlank(month)){
			year= DateUtils.getYear();
		}
		//根据登录人 取得登陆人已经办理完成的数据
		List<ComplaintMain> list=complaintMainDao.findCompleted(year,month);
		//清除查到的  null  值
		list.removeAll(Collections.singleton(null));
		this.format(list);
		return list;
	}
	public Long findAllEvent(User user,String year,String month) {
		if ("".equals(user.getRole())){

		}
		if (StringUtils.isBlank(year) && StringUtils.isBlank(month)){
			year= DateUtils.getYear();
		}
		Long allEvent = complaintMainDao.findAllEvent(year, month);
		return allEvent;
	}
}