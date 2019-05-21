/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.machine.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.UserAgentUtils;
import com.sayee.sxsy.modules.sys.entity.Menu;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
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
 * @author zhangfan
 * @version 2019-05-17
 */
@Service
@Transactional(readOnly = true)
public class MachineAccountService extends CrudService<MachineAccountDao, MachineAccount> {

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
		User user=UserUtils.getId(machineAccount.getMediatorId());
		machineAccount.setMediatorId(user!=null ?  user.getId() :machineAccount.getMediatorId());
		//转换部门OFFICE
//		Office office=UserUtils.officeId(machineAccount.getDeptId());
//		machineAccount.setDeptId(office!=null ?  office.getId() :machineAccount.getDeptId());
		machineAccount.setDeptId(user != null ? user.getOffice().getId() : machineAccount.getDeptId());
		//是否重大
		if ("是".equals(machineAccount.getIsMajor()) || "1".equals(machineAccount.getIsMajor())){
			machineAccount.setIsMajor("1");
		}else {
			machineAccount.setIsMajor("0");
		}
		machineAccount.setIsNewRecord(true);
		//
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
		if (StringUtils.isNotBlank(fileNumber)){
			MachineAccount machineAccount=dao.checkFileNumber(fileNumber);
			if (machineAccount!=null){
				return false;
			}else {
				return true;
			}
		}else {
			return false;
		}
	}
}