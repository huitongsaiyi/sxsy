/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sys.dao;

import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.common.persistence.TreeDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
    /**
     * 根据部门名称获得部门ID
     * @param name
     * @return 取不到返回null
     */
    public  Office officeId(String name);
    /**
     * 根据部门名称获得部门ID 简易 sql
     * @param name
     * @return 取不到返回null
     */
    public  Office getOfficeName(String name);
    /**
     * 根据部门名称获得部门ID like sql
     * @param name
     * @return 取不到返回null
     */
    public  Office getOfficeNameLike(String name);
    /**
     * 根据部门主键获得OFFICE实体类
     *  @param id
     * @return 取不到返回null
     */
    public Office getOfficeId(String id);
}
