/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sayee.sxsy.common.utils.BeanUtils;
import com.sayee.sxsy.common.utils.JsonUtil;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 纠纷调解Controller
 * @author lyt
 * @version 2019-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/complaintmain/complaintMain")
public class ComplaintMainController extends BaseController {

	@Autowired
	private ComplaintMainService complaintMainService;
	
	@ModelAttribute
	public ComplaintMain get(@RequestParam(required=false) String id) {
		ComplaintMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = complaintMainService.get(id);
		}
		if (entity == null){
			entity = new ComplaintMain();
		}
		return entity;
	}
	
	@RequiresPermissions("complaintmain:complaintMain:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComplaintMain complaintMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComplaintMain> page = complaintMainService.findPage(new Page<ComplaintMain>(request, response), complaintMain); 
		model.addAttribute("page", page);
		return "modules/complaintmain/complaintMainList";
	}

	@RequiresPermissions("complaintmain:complaintMain:view")
	@RequestMapping(value = "form")
	public String form(ComplaintMain complaintMain, Model model) {
		model.addAttribute("complaintMain", complaintMain);
		return "modules/complaintmain/complaintMainForm";
	}

	@RequiresPermissions("complaintmain:complaintMain:edit")
	@RequestMapping(value = "save")
	public String save(ComplaintMain complaintMain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, complaintMain)){
			return form(complaintMain, model);
		}
		complaintMainService.save(complaintMain);
		addMessage(redirectAttributes, "保存纠纷调解成功");
		return "redirect:"+Global.getAdminPath()+"/complaintmain/complaintMain/?repage";
	}
	
	@RequiresPermissions("complaintmain:complaintMain:edit")
	@RequestMapping(value = "delete")
	public String delete(ComplaintMain complaintMain, RedirectAttributes redirectAttributes) {
		complaintMainService.delete(complaintMain);
		addMessage(redirectAttributes, "删除纠纷调解成功");
		return "redirect:"+Global.getAdminPath()+"/complaintmain/complaintMain/?repage";
	}
	/**
	 * 我的待办列表
	 */
	@RequestMapping(value = "self")
	public String selfList(ComplaintMain complaintMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		//在 task 表中 根据 处理人信息 来查询 当前登录人有几条待办信息，不同的节点返回不同的页面
		complaintMain.setUser(UserUtils.getUser());
		Page<ComplaintMain> page = complaintMainService.selfList(new Page<ComplaintMain>(request, response), complaintMain);
		model.addAttribute("page", page);
//		List<Map<String,Object>> list=complaintMainService.selfList(UserUtils.getUser());
//		model.addAttribute("complaintMain", list);
		return "modules/complaintmain/complaintMainList";
	}

	//我的已办 列表
	@RequestMapping(value = "home")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询当前登录人 有几条 数据时 在 结案总结 之后
		String loginName=UserUtils.getUser().getLoginName();
		List<ComplaintMain> list=complaintMainService.getMyDone(loginName);
		model.addAttribute("list", list);
		model.addAttribute("ywc", list.size());
		model.addAttribute("wwc", complaintMainService.findCount(UserUtils.getUser()));
		return "modules/home/homePage";
	}

	/**
	 * 获取我的待办数目
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount( Model model) {
		User user= UserUtils.getUser();
		return String.valueOf(complaintMainService.findCount(user));
	}

	//主任页面
	@RequestMapping(value = "head")
	public String head(HttpServletRequest request, HttpServletResponse response, Model model) {
		String year=request.getParameter("yearDate");
		String month=request.getParameter("monthDate");
		//查询当前登录人 有几条 数据时 在 结案总结 之后
        User user=UserUtils.getUser();
		List<Map<String,Object>> list=complaintMainService.findTypeInfo(user,year,month);
		model.addAttribute("list", this.convert(list.toArray(),"typeName",true) );
		model.addAttribute("list2", this.convert(list.toArray(),"num",true) );
		model.addAttribute("yearDate", year );
		model.addAttribute("monthDate", month );
		//各等级医院的案件数量统计
        List<Map<String, Object>> gradeList = complaintMainService.findGrade(user, year, month);
		String toJson = JsonUtil.toJson(gradeList);
		model.addAttribute("asdf",toJson);
		//案件数量统计
		String loginName=UserUtils.getUser().getLoginName();
		List<ComplaintMain> listNum=complaintMainService.getMyDone(loginName);
		model.addAttribute("ywc", listNum.size());
		model.addAttribute("wwc", complaintMainService.findCount(UserUtils.getUser()));
		model.addAttribute("listZ", listNum.size()+complaintMainService.findCount(UserUtils.getUser()));
        //每月数据统计
        List<Map<String,Object>> MonthDataList=complaintMainService.getEveryMonthData(user,year,month);
        model.addAttribute("monthData", this.convert(MonthDataList.toArray(),"monthDate",true) );
        model.addAttribute("number", this.convert(MonthDataList.toArray(),"num",true) );
        //各市数据
        List<Map<String,Object>> areaList=complaintMainService.findAreaName(year,month);
        model.addAttribute("areaList", JsonUtil.toJson(areaList));
		return "modules/home/headPage";
	}

	public static List convert(Object[] list, String field, boolean skipNull)
	{
		Map map = new HashMap();
		List array = new ArrayList();
		Object[] arrayOfObject = list; int j = list.length; for (int i = 0; i < j; i++) { Object t = arrayOfObject[i];
		Object value = null;
		if (field != null) {
			Class c = t.getClass();
			if ((t instanceof Map)) {
				value = ((Map)t).get(field);
			} else if (c.isArray()) {
				value = ((Object[])t)[Integer.valueOf(field).intValue()];
			} else {
				String cls = c.getName();
				Method getMethod = (Method)map.get(cls);
				if (getMethod == null) getMethod = BeanUtils.getMethod(field, c);
				if (getMethod != null) {
					try {
						value = getMethod.invoke(t, new Object[0]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					map.put(cls, getMethod);
				}
			}
		} else { value = t; }
		if (((value == null) && (!skipNull)) || (value != null)) array.add(value);
	}
		return array;
	}
}