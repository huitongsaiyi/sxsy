/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.common.utils;

import com.sayee.sxsy.modules.sys.dao.UserDao;
import com.sayee.sxsy.modules.typeinfo.dao.TypeInfoDao;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.apache.fop.util.DataURIResolver;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.sayee.sxsy.common.utils.BeanUtils.getMethodName;

/**
 * 业务中可以共用的方法
 *
 * @author zf
 * @version 2019年6月14日
 */
public class BaseUtils {

    private static TypeInfoDao typeInfoDao = SpringContextHolder.getBean(TypeInfoDao.class);

    private final static int atomic = 1;

    /**
     * typeinfo
     *
     * @param relationModel
     */
    public static List<TypeInfo> getType(String relationModel) {
        if (StringUtils.isNotBlank(relationModel)) {
            List<TypeInfo> list = typeInfoDao.findTypeList(relationModel);
            return list;
        } else {
            return new ArrayList<TypeInfo>();
        }
    }

    /**
     * 对编号之类的数据 进行自增；像 案件编号
     *
     * @param type   类型；时间格式  默认时间格式
     * @param figure 自增序列 几位数  默认3位
     * @param table  数据库表名（大写）
     * @param field  字段名称
     * @author zf
     * @version 2019年6月14日
     */
    public static String getCode(String type, String figure, String table, String field) {
        if (StringUtils.isNotBlank(table) && StringUtils.isNotBlank(field)) {
            if (StringUtils.isBlank(type)) {
                type = "time";
            }
            if (StringUtils.isBlank(figure)) {
                figure = "3";
            }
            StringBuffer code = new StringBuffer();
            String time = DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDay();
            //先根据 表名和字段 查询到数据库 是否有数据
            String data = typeInfoDao.findCode(table, field, time);
            if (StringUtils.isNotBlank(data) && org.apache.commons.lang3.StringUtils.isNumeric(data)) {
                BigInteger num = new BigInteger(data);
                num = num.add(new BigInteger("1"));
                code.append(String.valueOf(num));
            } else {
                code.append(time).append(String.format("%0" + figure + "d", atomic));
            }
            return code.toString();
        } else {
            return "";
        }
    }


    public static List convert(Object[] list, String field, boolean skipNull) {
        Map map = new HashMap();
        List array = new ArrayList();
        Object[] arrayOfObject = list;
        int j = list.length;
        for (int i = 0; i < j; i++) {
            Object t = arrayOfObject[i];
            Object value = null;
            if (field != null) {
                Class c = t.getClass();
                if ((t instanceof Map)) {
                    value = ((Map) t).get(field);
                } else if (c.isArray()) {
                    value = ((Object[]) t)[Integer.valueOf(field).intValue()];
                } else {
                    String cls = c.getName();
                    Method getMethod = (Method) map.get(cls);
                    if (getMethod == null) getMethod = BeanUtils.getMethod(field, c);
                    if (getMethod != null) {
                        try {
                            value = getMethod.invoke(t, new Object[0]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        map.put(cls, getMethod);
                    }
                }
            } else {
                value = t;
            }
            if (((value == null) && (!skipNull)) || (value != null))
                array.add(value);
        }
        return array;
    }

}
