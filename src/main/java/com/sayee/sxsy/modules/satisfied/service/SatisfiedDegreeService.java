/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.satisfied.service;

import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.satisfied.entity.SatisfiedDegree;
import com.sayee.sxsy.modules.satisfied.dao.SatisfiedDegreeDao;

/**
 * 满意度Service
 * @author zhangfan
 * @version 2019-12-03
 */
@Service
@Transactional(readOnly = true)
public class SatisfiedDegreeService extends CrudService<SatisfiedDegreeDao, SatisfiedDegree> {

	public SatisfiedDegree get(String id) {
		return super.get(id);
	}
	
	public List<SatisfiedDegree> findList(SatisfiedDegree satisfiedDegree) {
		return super.findList(satisfiedDegree);
	}
	
	public Page<SatisfiedDegree> findPage(Page<SatisfiedDegree> page, SatisfiedDegree satisfiedDegree) {
		//一般角色 看到自己的满意度  高角色看到 全部
		User user=UserUtils.getUser();
		System.out.println(user.getRoleList());
		if (user.getRoleList().contains("yydept")){

		}else {
			satisfiedDegree.setUid(UserUtils.getUser().getId());
		}
		return super.findPage(page, satisfiedDegree);
	}

	//小程序端的添加操作，后台不进行添加 满意度
	@Transactional(readOnly = false)
	public void save(SatisfiedDegree satisfiedDegree,JSONObject jsonObject) {
		String satisfiedId=jsonObject.getString("satisfiedId");//如果以后修改就需要传 主键，根据主键判断是添加 还是修改
		satisfiedDegree.preInsert();
		satisfiedDegree.setSatisfiedId(satisfiedDegree.getId());
		String uid=jsonObject.getString("uid");
		String complaintMainId=jsonObject.getString("complaintMainId");//主表 主键
		String ability=jsonObject.getString("ability");//调解能力
		String attitude=jsonObject.getString("attitude");//服务态度
		String meter=jsonObject.getString("meter");//仪容仪表
		String assess=jsonObject.getString("assess");//评价
		String proposal=jsonObject.getString("proposal");//建议
		satisfiedDegree.setComplaintMainId(complaintMainId);
		satisfiedDegree.setAbility(ability);
		satisfiedDegree.setAttitude(attitude);
		satisfiedDegree.setMeter(meter);
		satisfiedDegree.setAssess(assess);
		satisfiedDegree.setProposal(proposal);
		User user=UserUtils.get(uid);
		satisfiedDegree.setSatisfiedName(user.getName());
		satisfiedDegree.setCreateBy(user);
		satisfiedDegree.setUpdateBy(user);
		dao.insert(satisfiedDegree);
	}
	
	@Transactional(readOnly = false)
	public void delete(SatisfiedDegree satisfiedDegree) {
		super.delete(satisfiedDegree);
	}
	
}