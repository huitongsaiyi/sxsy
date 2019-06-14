/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.common.utils;

import com.sayee.sxsy.modules.sys.dao.UserDao;
import com.sayee.sxsy.modules.typeinfo.dao.TypeInfoDao;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务中可以共用的方法
 * @author zf
 * @version 2019年6月14日
 */
public class BaseUtils {

	private static TypeInfoDao typeInfoDao = SpringContextHolder.getBean(TypeInfoDao.class);
	/**
	 * typeinfo
	 * @param relationModel
	 *
	 */
	public static List<TypeInfo> getType(String relationModel){
		if (StringUtils.isNotBlank(relationModel)){
			List<TypeInfo> list=typeInfoDao.findTypeList(relationModel);
			return  list;
		}else {
			return new ArrayList<TypeInfo>();
		}
	}

}
