/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.program.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.program.entity.MediateProgram;
import org.apache.ibatis.annotations.Param;

/**
 * 调解程序DAO接口
 * @author zhangfan
 * @version 2019-07-04
 */
@MyBatisDao
public interface MediateProgramDao extends CrudDao<MediateProgram> {
    /**
     * 得到调解程序 的最大次数
     */
    public int getMax(@Param("relationId") String relationId);

}