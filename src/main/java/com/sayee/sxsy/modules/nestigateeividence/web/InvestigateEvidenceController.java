/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.nestigateeividence.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.respondentinfo.dao.RespondentInfoDao;
import com.sayee.sxsy.modules.respondentinfo.service.RespondentInfoService;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.nestigateeividence.entity.InvestigateEvidence;
import com.sayee.sxsy.modules.nestigateeividence.service.InvestigateEvidenceService;

import java.util.List;
import java.util.Map;

/**
 * 调查取证Controller
 * @author gbq
 * @version 2019-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/nestigateeividence/investigateEvidence")
public class InvestigateEvidenceController extends BaseController {

	@Autowired
	private InvestigateEvidenceService investigateEvidenceService;
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private RespondentInfoService respondentInfoService;
    @Autowired
    private RespondentInfoDao respondentInfoDao;
	@Autowired
	SummaryInfoService summaryInfoService;
    @Autowired
    private MachineAccountService machineAccountService;
	@ModelAttribute
	public InvestigateEvidence get(@RequestParam(required=false) String id) {
		InvestigateEvidence entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = investigateEvidenceService.get(id);
		}
		if (entity == null){
			entity = new InvestigateEvidence();
		}
		return entity;
	}
	
	@RequiresPermissions("nestigateeividence:investigateEvidence:view")
	@RequestMapping(value = {"list", ""})
	public String list(InvestigateEvidence investigateEvidence, HttpServletRequest request, HttpServletResponse response, Model model) {
		//给实体类 set 数据 ，调查人
		Page<InvestigateEvidence> page = investigateEvidenceService.findPage(new Page<InvestigateEvidence>(request, response), investigateEvidence);
//        List<InvestigateEvidence> list = page.getList();
//        for (InvestigateEvidence inves: list) {
//            //遍历数据 拿到患方主键 查找调查人
//            List<RespondentInfo> respondentInfo=respondentInfoDao.getL(inves.getInvestigateEvidenceId());
//            for (RespondentInfo r: respondentInfo) {
//                if (inves.getRespondentInfo()==null){
//                    inves.setRespondentInfo(r);
//                }else {
//                    inves.setRespondentInfo2(r);
//                }
//            }
//            //查找医方调查人
//            List<RespondentInfo> YrespondentInfo=respondentInfoDao.getL(inves.getInvestigateEvidence().getInvestigateEvidenceId());
//            for (RespondentInfo yf: YrespondentInfo) {
//                if (inves.getRespondentInfo3()==null){
//                    inves.setRespondentInfo3(yf);
//                }else {
//                    inves.setRespondentInfo4(yf);
//                }
//            }
//        }
		model.addAttribute("page", page);
		return "modules/nestigateeividence/investigateEvidenceList";
	}

	@RequiresPermissions("nestigateeividence:investigateEvidence:view")
	@RequestMapping(value = "form")
	public String form(InvestigateEvidence investigateEvidence, Model model,HttpServletRequest request) {
	    //获取下调查人
        investigateEvidenceService.respondent(investigateEvidence);
        //获取附件
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(investigateEvidence.getId());
		for (Map<String, Object> map :filePath){
			if ("3".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("4".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("5".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files3",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId3",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("6".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files4",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId4",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}

		}
		if(StringUtils.isBlank(investigateEvidence.getInvestigateEvidence().getContent())){
			investigateEvidence.getInvestigateEvidence().setContent("调解员(以下简称“调”)、医方(以下简称“医”)、患方(以下简称“患”)\n调：请您陈述诊疗经过。\n医：\n调：医院是否进行过讨论？是否存在过错？\n医：\n调：是否向患方告知封存病历、尸检等相关情况？\n医：\n调：医院有何处理意见？\n医：\n调：请您核对以上内容是否属实，如无疑议，请签字确认。\n医：");
		}
		if(StringUtils.isBlank(investigateEvidence.getContent())){
			investigateEvidence.setContent("调解员(以下简称“调”)、医方(以下简称“医”)、患方(以下简称“患”)\n调：请您陈述诊疗的经过？\n患：\n调：您对诊疗方面有什么疑问吗？\n患 ：\n调：为了明确患者的具体死亡原因，建议做尸体解剖，以便更合理的处理纠纷，是否同意做尸检？\n患：\n调：医疗费有多少？请提供票据。\n患：\n调：您有什么诉求？\n患：\n调：请您核对以上内容是否属实，如无疑议，请签字确认。\n患：");
		}
		String type=request.getParameter("type");
		if("view".equals(type)){
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			Map<String, Object> map = summaryInfoService.getViewDetail(investigateEvidence.getComplaintMainId());
			model.addAttribute("map",map);
			model.addAttribute("investigateEvidence", investigateEvidence);
			return "modules/nestigateeividence/investigateEvidencesView";
		}else{
			model.addAttribute("investigateEvidence", investigateEvidence);
			return "modules/nestigateeividence/investigateEvidenceForm";
		}

	}

	@RequiresPermissions("nestigateeividence:investigateEvidence:edit")
	@RequestMapping(value = "save")
	public String save(InvestigateEvidence investigateEvidence, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, investigateEvidence)&&"yes".equals(investigateEvidence.getComplaintMain().getAct().getFlag())||!beanValidator(model,investigateEvidence.getInvestigateEvidence())&&"yes".equals(investigateEvidence.getComplaintMain().getAct().getFlag())){
			return form(investigateEvidence, model,request);
		}
		try {
				investigateEvidenceService.save(investigateEvidence,request);
            machineAccountService.savetz(investigateEvidence.getMachineAccount(), "b", investigateEvidence.getInvestigateEvidenceId());
			if ("yes".equals(investigateEvidence.getComplaintMain().getAct().getFlag())) {
				addMessage(redirectAttributes, "流程已启动，流程ID：" + investigateEvidence.getComplaintMain().getProcInsId());
			} else {
				addMessage(redirectAttributes, "保存调查取证成功");
			}
		}catch(Exception e){
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
		return "redirect:"+Global.getAdminPath()+"/nestigateeividence/investigateEvidence/?repage";
	}
	
	@RequiresPermissions("nestigateeividence:investigateEvidence:edit")
	@RequestMapping(value = "delete")
	public String delete(InvestigateEvidence investigateEvidence, RedirectAttributes redirectAttributes) {
		investigateEvidenceService.delete(investigateEvidence);
		addMessage(redirectAttributes, "删除成功成功");
		return "redirect:"+Global.getAdminPath()+"/nestigateeividence/investigateEvidence/?repage";
	}

}