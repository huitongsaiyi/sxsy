/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.record.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.record.entity.MediateRecord;

import java.util.List;

/**
 * 调解志DAO接口
 * @author lyt
 * @version 2019-06-10
 */
@MyBatisDao
public interface MediateRecordDao extends CrudDao<MediateRecord> {
    public List<MediateRecord> findReachMediateList(MediateRecord mediateRecord);      //返回达成调解时保存的调解志数据
}