/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.test.dao;

import com.sayee.sxsy.common.persistence.TreeDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.test.entity.TestTree;
import org.springframework.transaction.annotation.Transactional;

/**
 * 树结构生成DAO接口
 * @author ThinkGem
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {

    /*删除子数据*/
    public int deleteChild(TestTree testTree);
}