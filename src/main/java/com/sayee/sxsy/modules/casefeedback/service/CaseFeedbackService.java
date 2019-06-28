/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.casefeedback.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.service.OfficeService;
import com.sayee.sxsy.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.casefeedback.entity.CaseFeedback;
import com.sayee.sxsy.modules.casefeedback.dao.CaseFeedbackDao;

/**
 * 案件反馈Service
 * @author lyt
 * @version 2019-06-20
 */
@Service
@Transactional(readOnly = true)
public class CaseFeedbackService extends CrudService<CaseFeedbackDao, CaseFeedback> {

    @Autowired
    private SystemService systemService;
    @Autowired
	private OfficeService officeService;

	public CaseFeedback get(String id) {
		CaseFeedback caseFeedback = super.get(id);
		if(StringUtils.isNotBlank(caseFeedback.getFeedbackEmp())) {
            String feedback = caseFeedback.getFeedbackEmp();        //将反馈人数据赋值到属性
            List<String> list = Arrays.asList(feedback.split(","));        //将数据以逗号分隔，分别插入数组中
            List<User> listUser = new ArrayList<User>();
            for (int i = 0; i < list.size(); i++) {                //遍历循环将查出的用户信息插入到实体类的listUser中
                User user = systemService.getUser(list.get(i));
                listUser.add(user);
            }
            String feedbackOffice = caseFeedback.getFeedbackOffice();       //将反馈部门数据赋值到属性
            List<String> list1 = Arrays.asList(feedbackOffice.split(","));      //将数据以逗号分隔，分别插入数组中
            List<Office> listOffice = new ArrayList<Office>();
            for (int i = 0; i < list1.size(); i++) {            //遍历循环将查出的部门信息插入到实体类的listOffice中
                Office office = officeService.get(list1.get(i));
                listOffice.add(office);
            }
            caseFeedback.setListUser(listUser);         //将查询的信息插入
            caseFeedback.setListOffice(listOffice);
        }
	    return caseFeedback;
	}
	
	public List<CaseFeedback> findList(CaseFeedback caseFeedback) {
		return super.findList(caseFeedback);
	}
	
	public Page<CaseFeedback> findPage(Page<CaseFeedback> page, CaseFeedback caseFeedback) {
		return super.findPage(page, caseFeedback);
	}
	
	@Transactional(readOnly = false)
	public void save(CaseFeedback caseFeedback) {
		if(StringUtils.isBlank(caseFeedback.getCreateBy().getId())){
			caseFeedback.preInsert();
			caseFeedback.setFeedbackId(caseFeedback.getId());
			dao.insert(caseFeedback);
		}else{
			caseFeedback.preUpdate();
			dao.update(caseFeedback);
		}
//		super.save(caseFeedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(CaseFeedback caseFeedback) {
		super.delete(caseFeedback);
	}

	public Map<String,Object> getViewDetail(String complaintMainId) {
		Map<String,Object> map=dao.getViewDetail(complaintMainId);
		return map;
	}
}