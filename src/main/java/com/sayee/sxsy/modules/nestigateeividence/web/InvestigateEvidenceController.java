/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.nestigateeividence.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.modules.respondentinfo.dao.RespondentInfoDao;
import com.sayee.sxsy.modules.respondentinfo.service.RespondentInfoService;
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
		String type=request.getParameter("type");
		if("view".equals(type)){
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
		if (!beanValidator(model, investigateEvidence)||!beanValidator(model,investigateEvidence.getInvestigateEvidence())){
			return form(investigateEvidence, model,request);
		}
		try {
				investigateEvidenceService.save(investigateEvidence,request);
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