/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.alibaba.druid.support.json.JSONUtils;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.service.OfficeService;
import com.sayee.sxsy.modules.sys.service.SystemService;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import com.sayee.sxsy.newModules.utils.SendNoteUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sayee.sxsy.common.beanvalidator.BeanValidators;
import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.excel.ExportExcel;
import com.sayee.sxsy.common.utils.excel.ImportExcel;
import com.sayee.sxsy.common.web.BaseController;

/**
 * 用户Controller
 *
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {

    @Autowired
    private SystemService systemService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private SendNoteUtils sendNoteUtils;

    @ModelAttribute
    public User get(@RequestParam(required = false) String id, HttpServletRequest request) {
        if (StringUtils.isNotBlank(id)) {
            return systemService.getUser(id);
        } else {
            String officeType = request.getParameter("officeType");
            Office office = new Office();
            office.setOfficeType(officeType);
            User user = new User();
            user.setOffice(office);
            return user;
        }
    }

    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"index"})
    public String index(User user, Model model, HttpServletRequest request) {
        String sh = request.getParameter("class");
        model.addAttribute("sh", sh);
        model.addAttribute("officeType", user.getOffice().getOfficeType());
        return "modules/sys/userIndex";
    }

    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"list", ""})
    public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        String officeType = request.getParameter("officeType");
		String sh = request.getParameter("sh");
        if (StringUtils.isBlank(officeType)) {
            if (StringUtils.isNotBlank(user.getOffice().getId())) {
                Office office = officeService.get(user.getOffice().getId());
                officeType = office.getOfficeType();
            }
            //user.getOffice().setId("");
            //user.getCompany().setId("");
        }
        String officeId = request.getParameter("office.id");
        String companyId = request.getParameter("company.id");
        if (officeType != null && officeType != "") {
            Office office = new Office();
            office.setOfficeType(officeType);
            if (StringUtils.isNotBlank(officeId)) {
                if (StringUtils.isNotBlank(user.getOffice().getName())) {
                    office.setId(officeId);
                    user.setOffice(office);
                } else {
                    office.setId("");
                    user.setOffice(office);
                }
            }
            if (StringUtils.isNotBlank(companyId)) {
                if (StringUtils.isNotBlank(user.getCompany().getName())) {
                    office.setId(companyId);
                    user.setCompany(office);
                } else {
                    office.setId("");
                    user.setCompany(office);
                }
            }
            user.setAuditing(sh);
            Page<User> page = systemService.findUser(new Page<User>(request, response), user);
            model.addAttribute("page", page);
        }
        if(sh==null){
            sh = "";
        }
        if (sh.equals("sh")) {
            return "modules/sys/userList2";
        } else {
            return "modules/sys/userList";
        }

    }

    @ResponseBody
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"listData"})
    public Page<User> listData(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        return page;
    }

    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "form")
    public String form(User user, Model model, String officeType, HttpServletRequest request) {
        officeType = request.getParameter("officeType");
        String sh = request.getParameter("sh");
        if (StringUtils.isNotBlank(user.getId())) {
            Office office = officeService.get(user.getOffice().getId());
            model.addAttribute("off", office.getOfficeType());
        }
        if (user.getCompany() == null || user.getCompany().getId() == null) {
            user.setCompany(UserUtils.getUser().getCompany());
        }
        if (user.getOffice() == null || user.getOffice().getId() == null) {
            //user.setOffice(UserUtils.getUser().getOffice());
            Office office = new Office();
            office.setOfficeType(officeType);
            user.setOffice(office);
            model.addAttribute("officeType", officeType);
        }

        model.addAttribute("user", user);
        model.addAttribute("sh", sh);
        model.addAttribute("allRoles", systemService.findAllRole());
        if(sh==null){
            sh = "";
        }
        if (sh.equals("sh")) {
            return "modules/sys/userForm2";
        } else {
            return "modules/sys/userForm";
        }
    }

    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "save")
    public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes, String officeType, HttpServletResponse response) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/sys/user/list?repage";
        }
        // 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
        user.setCompany(new Office(request.getParameter("company.id")));
        user.setOffice(new Office(request.getParameter("office.id")));
        // 如果新密码为空，则不更换密码
        if (StringUtils.isNotBlank(user.getNewPassword())) {
            user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
        }
        if (!beanValidator(model, user)) {
            return form(user, model, officeType, request);
        }
        if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))) {
            addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
            return form(user, model, officeType, request);
        }
        // 角色数据有效性验证，过滤不在授权内的角色
        List<Role> roleList = Lists.newArrayList();
        List<String> roleIdList = user.getRoleIdList();
        for (Role r : systemService.findAllRole()) {
            if (roleIdList.contains(r.getId())) {
                roleList.add(r);
            }
        }
        user.setRoleList(roleList);
        // 保存用户信息
        String sh = request.getParameter("sh");
        String text = request.getParameter("text");
        if(text==null){
            text="";
        }
        //驳回
        Map map = new HashMap();
        if(sh.equals("sh")&&user.getLoginFlag().equals("0")&&text.equals("0")){
            //String str = "{\"name\":"+user.getName()+",\"remarks:\""+user.getRemarks()+"}";
            map.put("name",user.getName());
            map.put("remarks",user.getRemarks());
            String newMap = JSONUtils.toJSONString(map);
            System.out.println(newMap);
            //通知短信
            sendNoteUtils.sendNoteUtlis(user.getMobile(),"SMS_187561354",newMap);
            systemService.deleteUser(user);
        }else if(text.equals("0")){//通过
            systemService.saveUser(user);
            //String str = "{\"name\":"+user.getName()+",\"remarks:\""+user.getRemarks()+"}";
            map.put("name",user.getName());
            map.put("remarks",user.getRemarks());
            String newMap = JSONUtils.toJSONString(map);
            System.out.println(newMap);
            //通知短信
            sendNoteUtils.sendNoteUtlis(user.getMobile(),"SMS_187541234",newMap);
        } else{
            systemService.saveUser(user);
        }
        // 清除当前用户缓存
        if (user.getLoginName().equals(UserUtils.getUser().getLoginName())) {
            UserUtils.clearCache();
            //UserUtils.getCacheMap().clear();
        }
        addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
        //return "redirect:" + adminPath + "/sys/user?repage";
        String offtype = request.getParameter("officeType");

        user.setLoginName("");
        user.setName("");
		user.setAuditing(sh);
        String list = list(user, request, response, model);
        return list;
//        return "redirect:" + adminPath + "/sys/user/?repage";
    }

    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "delete")
    public String delete(User user, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model, HttpServletResponse response) {
        Office office = officeService.get(user.getOffice().getId());
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/sys/user/list?repage";
        }
        if (UserUtils.getUser().getId().equals(user.getId())) {
            addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
        } else if (User.isAdmin(user.getId())) {
            addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
        }  else {
            systemService.deleteUser(user);
            addMessage(redirectAttributes, "删除用户成功");
        }
//		return "redirect:" + adminPath + "/sys/user/list?id="+user.getId();
        user.setLoginName("");
        user.setName("");
        user.getOffice().setName("");
        user.getCompany().setName("");
        user.getOffice().setOfficeType(office.getOfficeType());
        String list = list(user, request, response, model);
        return list;
    }

    /**
     * 导出用户数据
     *
     * @param user
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String officeType = request.getParameter("officeType");
            Office office = new Office();
            office.setOfficeType(officeType);
            user.setOffice(office);
            String fileName = "用户数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Page<User> page = systemService.findUser(new Page<User>(request, response, -1), user);
            new ExportExcel("用户数据", User.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/sys/user/list?repage";
    }

    /**
     * 导入用户数据
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/sys/user/list?repage";
        }
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<User> list = ei.getDataList(User.class);
            for (User user : list) {
                try {
                    if ("true".equals(checkLoginName("", user.getLoginName()))) {
                        user.setPassword(SystemService.entryptPassword("123456"));
                        BeanValidators.validateWithException(validator, user);
                        systemService.saveUser(user);
                        successNum++;
                    } else {
                        failureMsg.append("<br/>登录名 " + user.getLoginName() + " 已存在; ");
                        failureNum++;
                    }
                } catch (ConstraintViolationException ex) {
                    failureMsg.append("<br/>登录名 " + user.getLoginName() + " 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    failureMsg.append("<br/>登录名 " + user.getLoginName() + " 导入失败：" + ex.getMessage());
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/sys/user/list?repage";
    }

    /**
     * 下载导入用户数据模板
     *
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "用户数据导入模板.xlsx";
            List<User> list = Lists.newArrayList();
            //list.add(UserUtils.getUser());
            new ExportExcel("用户数据", User.class, 2).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
            System.out.println(e.getMessage());
        }
        return "redirect:" + adminPath + "/sys/user/list?repage";
    }

    /**
     * 验证登录名是否有效
     *
     * @param oldLoginName
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "checkLoginName")
    public String checkLoginName(String oldLoginName, String loginName) {
        if (loginName != null && loginName.equals(oldLoginName)) {
            return "true";
        } else if (loginName != null && systemService.getUserByLoginName(loginName) == null) {
            return "true";
        }
        return "false";
    }

    /**
     * 用户信息显示及保存
     *
     * @param user
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "info")
    public String info(User user, HttpServletResponse response, Model model) {
        User currentUser = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getName())) {
            if (Global.isDemoMode()) {
                model.addAttribute("message", "演示模式，不允许操作！");
                return "modules/sys/userInfo";
            }
            currentUser.setEmail(user.getEmail());
            currentUser.setPhone(user.getPhone());
            currentUser.setMobile(user.getMobile());
            currentUser.setRemarks(user.getRemarks());
            currentUser.setPhoto(user.getPhoto());
            systemService.updateUserInfo(currentUser);
            model.addAttribute("message", "保存用户信息成功");
        }
        model.addAttribute("user", currentUser);
        //修改Global 没有私有构造函数，实现懒汉式单例模式.在第一次调用的时候实例化自己！
        model.addAttribute("Global", Global.getInstance());
        return "modules/sys/userInfo";
    }

    /**
     * 返回用户信息
     *
     * @return
     */
    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "infoData")
    public User infoData() {
        return UserUtils.getUser();
    }

    /**
     * 修改个人用户密码
     *
     * @param oldPassword
     * @param newPassword
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "modifyPwd")
    public String modifyPwd(String oldPassword, String newPassword, Model model) {
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {
            if (Global.isDemoMode()) {
                model.addAttribute("message", "演示模式，不允许操作！");
                return "modules/sys/userModifyPwd";
            }
            if (SystemService.validatePassword(oldPassword, user.getPassword())) {
                systemService.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
                model.addAttribute("message", "修改密码成功");
            } else {
                model.addAttribute("message", "修改密码失败，旧密码错误");
            }
        }
        model.addAttribute("user", user);
        return "modules/sys/userModifyPwd";
    }

    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String officeId, @RequestParam(required = false) String role, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<User> list = new ArrayList<User>();
        if (StringUtils.isNotBlank(role)) {
            list = systemService.findUserByOfficeRoleId(officeId, role);
        } else {
            list = systemService.findUserByOfficeId(officeId);
        }
        for (int i = 0; i < list.size(); i++) {
            User e = list.get(i);
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", "u_" + e.getId());
            map.put("pId", officeId);
            map.put("name", StringUtils.replace(e.getName(), " ", ""));
            mapList.add(map);
        }
        return mapList;
    }

//	@InitBinder
//	public void initBinder(WebDataBinder b) {
//		b.registerCustomEditor(List.class, "roleList", new PropertyEditorSupport(){
//			@Autowired
//			private SystemService systemService;
//			@Override
//			public void setAsText(String text) throws IllegalArgumentException {
//				String[] ids = StringUtils.split(text, ",");
//				List<Role> roles = new ArrayList<Role>();
//				for (String id : ids) {
//					Role role = systemService.getRole(Long.valueOf(id));
//					roles.add(role);
//				}
//				setValue(roles);
//			}
//			@Override
//			public String getAsText() {
//				return Collections3.extractToString((List) getValue(), "id", ",");
//			}
//		});
//	}
}
