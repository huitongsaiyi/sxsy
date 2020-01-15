/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.cms.service;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.user.service.UserApiService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.cms.dao.CommentDao;
import com.sayee.sxsy.modules.cms.entity.Comment;

import java.util.Date;

/**
 * 评论Service
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class CommentService extends CrudService<CommentDao, Comment> {
	@Autowired
	private UserApiService userApiService;

	public Page<Comment> findPage(Page<Comment> page, Comment comment) {
//		DetachedCriteria dc = commentDao.createDetachedCriteria();
//		if (StringUtils.isNotBlank(comment.getContentId())){
//			dc.add(Restrictions.eq("contentId", comment.getContentId()));
//		}
//		if (StringUtils.isNotEmpty(comment.getTitle())){
//			dc.add(Restrictions.like("title", "%"+comment.getTitle()+"%"));
//		}
//     dc.add(Restrictions.eq(Comment.FIELD_DEL_FLAG, comment.getDelFlag()));
//		dc.addOrder(Order.desc("id"));
//		return commentDao.find(page, dc);
		comment.getSqlMap().put("dsf", dataScopeFilter(comment.getCurrentUser(), "o", "u"));
		
		return super.findPage(page, comment);
	}
	
	@Transactional(readOnly = false)
	public void delete(Comment entity, Boolean isRe) {
		super.delete(entity);
	}

	@Transactional(readOnly = false)
	public void save(Comment comment, JSONObject jsonObject) {
		String satisfiedId=jsonObject.getString("id");//如果以后修改就需要传 主键，根据主键判断是添加 还是修改
		comment.preInsert();
		String wechatUserId = jsonObject.getString("wechatUserId");
		String uid = userApiService.getSysUserId(wechatUserId);
		//String uid=jsonObject.getString("uid");
		String content=jsonObject.getString("content");//投诉建议内容
		comment.setContent(content);

		User user= UserUtils.get(uid);
		comment.setName(user.getName());
		comment.setCreateBy(user);
		comment.setUpdateBy(user);
		comment.setCreateDate(new Date());
		comment.setUpdateDate(new Date());
		dao.insert(comment);
	}
}
