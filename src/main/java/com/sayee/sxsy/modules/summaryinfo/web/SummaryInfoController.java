/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.summaryinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.common.utils.*;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.record.dao.MediateRecordDao;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.sign.entity.SignAgreement;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.service.SystemService;
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
import com.sayee.sxsy.modules.summaryinfo.entity.SummaryInfo;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 案件总结Controller
 * @author gbq
 * @version 2019-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/summaryinfo/summaryInfo")
public class SummaryInfoController extends BaseController {
    @Autowired
    private SystemService systemService;
	@Autowired
	private MediateRecordDao mediateRecordDao;		//调解志DAO层
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private SummaryInfoService summaryInfoService;
    @Autowired
    private MachineAccountService machineAccountService;
	@ModelAttribute
	public SummaryInfo get(@RequestParam(required=false) String id) {
		SummaryInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = summaryInfoService.get(id);
		}
		if (entity == null){
			entity = new SummaryInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("summaryinfo:summaryInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(SummaryInfo summaryInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SummaryInfo> page = summaryInfoService.findPage(new Page<SummaryInfo>(request, response), summaryInfo); 
		model.addAttribute("page", page);
		return "modules/summaryinfo/summaryInfoList";
	}

	@RequiresPermissions("summaryinfo:summaryInfo:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request,SummaryInfo summaryInfo, Model model) {
		String type = request.getParameter("type");

		summaryInfo.setMediateResult( StringUtils.isBlank(summaryInfo.getMediateResult()) ?  (StringUtils.isBlank(summaryInfo.getIsStop()) ? "1" : "2") : summaryInfo.getMediateResult()   );
		List<Act> actList=actTaskService.histoicFlowList(summaryInfo.getComplaintMain().getProcInsId(), "check", "sign");
		long flow=0;
		for (Act act:actList) {
			if (!"evaluation".equals(act.getHistIns().getActivityId()) && act.getHistIns().getDurationInMillis()!=null){
				flow+=act.getHistIns().getDurationInMillis();
			}
		}
		String flowDays=DateUtils.formatDateTime(flow,"chinese",false,false,false);
		summaryInfo.setFlowDays(StringUtils.isBlank(summaryInfo.getFlowDays()) ? (!flowDays.contains("天") ?"1天" : flowDays) :summaryInfo.getFlowDays());
		//根据得到的 执政调解 达成调解的主键  获取 调解志的次数
		//调解志 明细查询
		MediateRecord mediateRecord = new MediateRecord();
		mediateRecord.setRelationId(StringUtils.isBlank(summaryInfo.getMediateEvidenceId())  ? "无" : summaryInfo.getMediateEvidenceId());
		List<MediateRecord> aa=mediateRecordDao.findReachMediateList(mediateRecord);
		mediateRecord.setRelationId(StringUtils.isBlank(summaryInfo.getReachMediateId())  ? "无" : summaryInfo.getReachMediateId());
		aa.addAll(mediateRecordDao.findReachMediateList(mediateRecord));
		summaryInfo.setMeetingFrequency(StringUtils.isBlank(summaryInfo.getMeetingFrequency()) ?   String.valueOf(aa.size())  : summaryInfo.getMeetingFrequency());
		//下一步处理人 默认为 raters 角色
        if (StringUtils.isBlank(summaryInfo.getNextLinkMan())){
            List<User> list=systemService.findUserByOfficeRoleId("","raters");
            if (list.size()>0){
                for (User u:list) {
                    summaryInfo.setNextLinkMan(u.getId());
                    summaryInfo.setLinkEmployee(u);
                }
            }
        }
        //附件
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(summaryInfo.getSummaryId());
		for(Map<String, Object> map:filePath){
			if("25".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("26".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("27".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files3",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId3",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("28".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files4",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId4",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("29".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files5",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId5",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("30".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files6",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId6",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("31".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files7",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId7",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("32".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files8",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId8",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("33".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files9",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId9",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("34".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files10",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId10",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("35".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files11",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId11",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("36".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files12",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId12",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("37".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files13",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId13",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("38".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files14",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId14",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("39".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files15",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId15",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("40".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files16",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId16",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("41".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files17",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId17",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("42".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files18",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId18",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("43".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files19",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId19",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
		if("view".equals(type)){
			String show2=request.getParameter("show2");
			String complaintId=request.getParameter("complaintId");
			model.addAttribute("show2",show2);
			Map<String,Object> map=summaryInfoService.getViewDetail(summaryInfo.getComplaintMainId());
			model.addAttribute("summaryInfo", summaryInfo);
			model.addAttribute("map", map);
			model.addAttribute("complaintId", complaintId);
			return "modules/summaryinfo/summaryInfoView";
		}else {
			model.addAttribute("summaryInfo", summaryInfo);
			return "modules/summaryinfo/summaryInfoForm";
		}
	}

	@RequiresPermissions("summaryinfo:summaryInfo:edit")
	@RequestMapping(value = "save")
	public String save(SummaryInfo summaryInfo, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) {
		String export=request.getParameter("export");
		if (StringUtils.isNotBlank(export) && !export.equals("no")){
//		    SignAgreement signAgreement1 = signAgreementService.get(signAgreement.getSignAgreementId());
			String path = summaryInfoService.exportWord(summaryInfo,export,"false",request,response);
			return path;
		}else {
			if (!beanValidator(model, summaryInfo) && "yes".equals(summaryInfo.getComplaintMain().getAct().getFlag())){
				return form(request,summaryInfo, model);
			}
			try{
				summaryInfoService.save(summaryInfo,request);
				machineAccountService.savetz(summaryInfo.getMachineAccount(), "f", summaryInfo);
				if("yes".equals(summaryInfo.getComplaintMain().getAct().getFlag())){
					addMessage(redirectAttributes, "流程已启动，流程ID：" + summaryInfo.getComplaintMain().getProcInsId());
					return "redirect:"+Global.getAdminPath()+"/summaryinfo/summaryInfo/?repage";
				}else {
					model.addAttribute("message","保存结案总结成功");
					return form(request,this.get(summaryInfo.getSummaryId()), model);
				}

			}catch(Exception e){
				logger.error("启动鉴定评估流程失败：",e);
				addMessage(redirectAttributes,"系统内部错误");
				return "redirect:"+Global.getAdminPath()+"/summaryinfo/summaryInfo/?repage";
			}
		}


	}
	
	@RequiresPermissions("summaryinfo:summaryInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(SummaryInfo summaryInfo, RedirectAttributes redirectAttributes) {
		summaryInfoService.delete(summaryInfo);
		addMessage(redirectAttributes, "删除案件总结成功");
		return "redirect:"+Global.getAdminPath()+"/summaryinfo/summaryInfo/?repage";
	}

    @RequestMapping(value = "getFileNum")
    public void getFileNum(HttpServletRequest request,HttpServletResponse response) {
        Map map=new HashMap();
        map.put("number",summaryInfoService.getFileNum(request));
        AjaxHelper.responseWrite(request,response,"1","success",map);
    }

	@RequestMapping(value = "pass")
	public void pass(HttpServletRequest request,HttpServletResponse response) {
		String code="";//1.成功 0失败
		String summaryId=request.getParameter("summaryId");//前台传过来的状态
		String export=request.getParameter("export");//前台传过来的状态
		String print=request.getParameter("print");//前台传过来的状态
		SummaryInfo summaryInfo = summaryInfoService.get(summaryId);
		code=summaryInfoService.exportWord(summaryInfo,export,print,request,response);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("url",code);
		AjaxHelper.responseWrite(request,response,"1","success",map);
	}

}