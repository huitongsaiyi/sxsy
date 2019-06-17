/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessinfo.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.assessinfo.entity.AssessInfo;
import com.sayee.sxsy.modules.assessinfo.dao.AssessInfoDao;

/**
 * 案件评价Service
 * @author lyt
 * @version 2019-06-17
 */
@Service
@Transactional(readOnly = true)
public class AssessInfoService extends CrudService<AssessInfoDao, AssessInfo> {

	public AssessInfo get(String id) {
		return super.get(id);
	}
	
	public List<AssessInfo> findList(AssessInfo assessInfo) {
		return super.findList(assessInfo);
	}
	
	public Page<AssessInfo> findPage(Page<AssessInfo> page, AssessInfo assessInfo) {
		return super.findPage(page, assessInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(AssessInfo assessInfo) {
		if(StringUtils.isBlank((assessInfo.getCreateBy().getId()))){		//检索创建人ID是否已存在
			//若不存在进行插入
			assessInfo.preInsert();
			assessInfo.setAssessId(assessInfo.getId());
			if(StringUtils.isBlank(assessInfo.getAssessGrade()) || "".equals(assessInfo.getAssessGrade())){         //判断评论分数是否为空
                assessInfo.setAssessGrade("0");
            }
			dao.insert(assessInfo);
		}else{			//若存在进行更新
			assessInfo.preUpdate();
			dao.update(assessInfo);
		}
//	    super.save(assessInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(AssessInfo assessInfo) {
		super.delete(assessInfo);
	}
	
}