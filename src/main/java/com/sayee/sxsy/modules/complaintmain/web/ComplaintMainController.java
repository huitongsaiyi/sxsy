/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.*;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Method;
import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;

import java.util.*;

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
	 * 根据身份证 和 医院  判断是否重复
	 */
	@RequestMapping(value = "getRepeat")
	public void getRepeat(HttpServletRequest request,HttpServletResponse response) {
		String card=request.getParameter("card");//前台传过来的状态
		String hospital=request.getParameter("hospital");//前台传过来的状态
		String complaintMainId=request.getParameter("complaintMainId");//前台传过来的状态
		List<ComplaintMain> list = complaintMainService.getRepeat(card,hospital,complaintMainId);
		list.removeAll(Collections.singleton(null));
		Map<String,Object> map=new HashMap<String,Object>();
		if (!CollectionUtils.isEmpty(list) && list!=null){
			StringBuffer number=new StringBuffer();
			for (ComplaintMain c:list) {
				number.append(c.getCaseNumber()+"，");
			}
			map.put("size",list.size());
			map.put("number",number);
			map.put("name",UserUtils.getOfficeId(hospital).getName());
			AjaxHelper.responseWrite(request,response,"1","success",map);
		}else {
			AjaxHelper.responseWrite(request,response,"0","error",map);
		}

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
		int ywc=0;
		for (ComplaintMain complaintMain : list) {
			if ("assess".equals(complaintMain.getTaskDefKey()) || "feedback".equals(complaintMain.getTaskDefKey())){
				ywc++;
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("ywc", ywc);
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
		String type=request.getParameter("type");//根据类型判断是 投诉数据  还是 调解数据
		String commonType=request.getParameter("commonType");//根据类型判断是 焦点 专业 医院等级 数量

		String year=request.getParameter("yearDate");
		String beginMonthDate=request.getParameter("beginMonthDate");
		String endMonthDate=request.getParameter("endMonthDate");
		String newType=request.getParameter("newType");
        User user=UserUtils.getUser();
        model.addAttribute("yearDate", year );
        model.addAttribute("beginMonthDate", beginMonthDate );
        model.addAttribute("endMonthDate", endMonthDate );
		if (StringUtils.isNotBlank(newType)){
		    if ("duty".equals(newType)){//责任度
                List<Map<String,Object>> list=complaintMainService.findDuty(user,year,beginMonthDate,endMonthDate,type);
//                Map<String,Object> map=new HashMap<>();
//                map.put("name","无责"); map.put("value","21");
//                Map<String,Object> map1=new HashMap<>();
//                map1.put("name","轻微责任");map1.put("value","45");
//                Map<String,Object> map2=new HashMap<>();
//                map2.put("name","次要责任");map2.put("value","78");
//                Map<String,Object> map3=new HashMap<>();
//                map3.put("name","对等责任");map3.put("value","12");
//                Map<String,Object> map4=new HashMap<>();
//                map4.put("name","主要责任");map4.put("value","34");
//                Map<String,Object> map5=new HashMap<>();
//                map5.put("name","全部责任");map5.put("value","55");
//                Map<String,Object> map6=new HashMap<>();
//                map6.put("name","无法判定");map6.put("value","1");
//                list.add(map);list.add(map1);list.add(map2);list.add(map3);list.add(map4);list.add(map5);list.add(map6);
                model.addAttribute("dutyName", this.convert(list.toArray(),"ratio",true) );
                String toJson = JsonUtil.toJson(list);
                model.addAttribute("dutyNum",toJson);
                model.addAttribute("dutyTableInfo",list);
                return "modules/home/duty";
            }else {
                Map<String,Object> map=complaintMainService.findAmountRatio(user,year,beginMonthDate,endMonthDate,type);
//                map.put("50万及以上","1");
//                map.put("10万到50万及以下","6");
//                map.put("2万到10万及以下","56");
//                map.put("2万及以下","121");
                List keyList = new ArrayList();
                List valueList = new ArrayList();
                List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				list.add(map);
                for(String key : map.keySet()){
                    keyList.add(key);
                    String value =MapUtils.getString(map,key,"0");
                    valueList.add(value);
                }
				model.addAttribute("amountTableInfo", list);
				model.addAttribute("keyList", JsonUtil.toJson(keyList));
				model.addAttribute("valueList", valueList);
                return "modules/home/amountRatio";
            }
        }else {
			model.addAttribute("type", type );
			if("jfjd".equals(commonType)){//调解数据统计下的  纠纷纠纷焦点 分析
				//查询当前登录人 有几条 数据时 在 结案总结 之后
				List<Map<String,Object>> list=complaintMainService.findTypeInfo(user,year,beginMonthDate,endMonthDate,type);
				model.addAttribute("list", this.convert(list.toArray(),"typeName",true) );
				model.addAttribute("jfjdTableInfo", list );
				model.addAttribute("list2", this.convert(list.toArray(),"num",true) );
				model.addAttribute("ids", this.convert(list.toArray(),"typeId",true) );
				return "modules/home/focus";
			}else if("sjzy".equals(commonType)){//涉及专业
				//各专业数据
				List<Map<String,Object>> dList=complaintMainService.findDepartment(user,year,beginMonthDate,endMonthDate,type);
//                Map<String,Object> map=new HashMap<>();
//                map.put("name","'呼吸内科专业'"); map.put("department","5");
//                Map<String,Object> map1=new HashMap<>();
//                map1.put("name","'骨科专业'");map1.put("department","16");
//                Map<String,Object> map2=new HashMap<>();
//                map2.put("name","'泌尿外科专业'");map2.put("department","34");
//                Map<String,Object> map3=new HashMap<>();
//                map3.put("name","'内分泌专业'");map3.put("department","36");
//                Map<String,Object> map4=new HashMap<>();
//                map4.put("name","'消化内科专业'");map4.put("department","42");
//                Map<String,Object> map5=new HashMap<>();
//                map5.put("name","'妇科专业'");map5.put("department","55");
//                Map<String,Object> map6=new HashMap<>();
//                map6.put("name","'其他'");map6.put("department","10");
//                dList.add(map);
//                dList.add(map1);
//                dList.add(map2);
//                dList.add(map3);
//                dList.add(map4);
//                dList.add(map5);
//                dList.add(map6);
				model.addAttribute("nameList", ObjectUtils.convert(dList.toArray(),"name",true) );
				model.addAttribute("departmentList", ObjectUtils.convert(dList.toArray(),"department",true) );
				model.addAttribute("sjzyTableInfo", dList );
				return "modules/home/major";
			}else if("yydj".equals(commonType)){//机构等级
				//各等级医院的案件数量统计
				List<Map<String, Object>> gradeList = complaintMainService.findGrade(user, year, beginMonthDate,endMonthDate,type);
//                Map<String,Object> map=new HashMap<>();
//                map.put("name","小学"); map.put("value","5");
//                Map<String,Object> map1=new HashMap<>();
//                map1.put("name","初中");map1.put("value","16");
//                Map<String,Object> map2=new HashMap<>();
//                map2.put("name","高中");map2.put("value","34");
//                Map<String,Object> map3=new HashMap<>();
//                map3.put("name","职高");map3.put("value","36");
//                Map<String,Object> map4=new HashMap<>();
//                map4.put("name","大专");map4.put("value","42");
//                Map<String,Object> map5=new HashMap<>();
//                map5.put("name","大学");map5.put("value","55");
//                Map<String,Object> map6=new HashMap<>();
//                map6.put("name","民营院校");map6.put("value","10");
//                gradeList.add(map);
//				gradeList.add(map1);
//				gradeList.add(map2);
//				gradeList.add(map3);
//				gradeList.add(map4);
//				gradeList.add(map5);
//				gradeList.add(map6);
				String toJson = JsonUtil.toJson(gradeList);
				model.addAttribute("asdf",toJson);
				model.addAttribute("yydjTableInfo",gradeList);
				return "modules/home/grade";
			}else {//调解数量分析
				//案件数量统计
				String loginName=UserUtils.getUser().getLoginName();
				List<ComplaintMain> listNum=complaintMainService.findCompleted(user, year, beginMonthDate,endMonthDate);
				Long allEvent = complaintMainService.findAllEvent(user, year, beginMonthDate,endMonthDate);
				model.addAttribute("ywc", listNum.size());//
				model.addAttribute("wwc", allEvent);//
				model.addAttribute("listZ", listNum.size()+allEvent);//
				//每月数据统计
				List<Map<String,Object>> MonthDataList=complaintMainService.getEveryMonthData(user,year,beginMonthDate,endMonthDate,type);
//                Map<String,Object> map=new HashMap<>();
//                map.put("monthDate","'2019-04'"); map.put("num","5");
//                Map<String,Object> map1=new HashMap<>();
//                map1.put("monthDate","'2019-05'");map1.put("num","16");
//                Map<String,Object> map2=new HashMap<>();
//                map2.put("monthDate","'2019-06'");map2.put("num","34");
//                Map<String,Object> map3=new HashMap<>();
//                map3.put("monthDate","'2019-07'");map3.put("num","36");
//                Map<String,Object> map4=new HashMap<>();
//                map4.put("monthDate","'2019-08'");map4.put("num","42");
//                Map<String,Object> map5=new HashMap<>();
//                map5.put("monthDate","'2019-09'");map5.put("num","55");
//                Map<String,Object> map6=new HashMap<>();
//                map6.put("monthDate","'2019-10'");map6.put("num","10");
//                MonthDataList.add(map);MonthDataList.add(map1);MonthDataList.add(map2);MonthDataList.add(map3);
//                MonthDataList.add(map4);MonthDataList.add(map5);MonthDataList.add(map6);
				model.addAttribute("monthData", this.convert(MonthDataList.toArray(),"monthDate",true) );
				model.addAttribute("number", this.convert(MonthDataList.toArray(),"num",true) );
				model.addAttribute("monthTableInfo", MonthDataList );
				//各市数据
				List<Map<String,Object>> areaList=complaintMainService.findAreaName(user,year,beginMonthDate,endMonthDate,type);
//                Map<String,Object> m=new HashMap<>();
//                m.put("name","临汾市"); m.put("value","5");
//                Map<String,Object> m1=new HashMap<>();
//                m1.put("name","阳泉市");m1.put("value","16");
//                Map<String,Object> m2=new HashMap<>();
//                m2.put("name","太原市");m2.put("value","34");
//                Map<String,Object> m3=new HashMap<>();
//                m3.put("name","晋中市");m3.put("value","36");
//                Map<String,Object> m4=new HashMap<>();
//                m4.put("name","运城市");m4.put("value","42");
//                Map<String,Object> m5=new HashMap<>();
//                m5.put("name","晋城市");m5.put("value","55");
//                Map<String,Object> m6=new HashMap<>();
//                m6.put("name","吕梁市");m6.put("value","10");
//                areaList.add(m);areaList.add(m1);areaList.add(m2);areaList.add(m3);
//                areaList.add(m4);areaList.add(m5);areaList.add(m6);
				areaList.sort(new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> o1, Map<String, Object> o2) {
						Integer i1 = MapUtils.getInteger(o1,"value",0);
						Integer i2 = MapUtils.getInteger(o2,"value",0);
						return i2.compareTo(i1);
					}
				});
				model.addAttribute("areaList", JsonUtil.toJson(areaList));
				model.addAttribute("areaTableInfo", areaList);
				if ("ts".equals(type)){
                    return "modules/home/number";
                }else {
                    return "modules/home/numberMediate";
                }

			}
        }
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