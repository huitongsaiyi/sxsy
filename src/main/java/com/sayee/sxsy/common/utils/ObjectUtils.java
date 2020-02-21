/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

/**
 * 对象操作工具类, 继承org.apache.commons.lang3.ObjectUtils类
 * @author ThinkGem
 * @version 2014-6-29
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

	/**
	 * 注解到对象复制，只复制能匹配上的方法。
	 * @param annotation
	 * @param object
	 */
	public static void annotationToObject(Object annotation, Object object){
		if (annotation != null){
			Class<?> annotationClass = annotation.getClass();
			if (null == object) {
				return;
			}
			Class<?> objectClass = object.getClass();
			for (Method m : objectClass.getMethods()){
				if (StringUtils.startsWith(m.getName(), "set")){
					try {
						String s = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
						Object obj = annotationClass.getMethod(s).invoke(annotation);
						if (obj != null && !"".equals(obj.toString())){
							m.invoke(object, obj);
						}
					} catch (Exception e) {
						// 忽略所有设置失败方法
					}
				}
			}
		}
	}
	
	/**
	 * 序列化对象
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			if (object != null){
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				return baos.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			if (bytes != null && bytes.length > 0){
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List convert(Object[] list, String field, boolean skipNull)
	{
		Map map = new HashMap();
		List array = new ArrayList();
		Object[] arrayOfObject = list; int j = list.length; for (int i = 0; i < j; i++) { Object t = arrayOfObject[i];
		Object value = null;
		if (field != null) {
			Class c = t.getClass();
			if ((t instanceof Map)) {
				value = ((Map)t).get(field);
			} else if (c.isArray()) {
				value = ((Object[])t)[Integer.valueOf(field).intValue()];
			} else {
				String cls = c.getName();
				Method getMethod = (Method)map.get(cls);
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
		} else { value = t; }
		if (((value == null) && (!skipNull)) || (value != null)) array.add(value);
	}
		return array;
	}

	/**
	 * 通过身份证号码获取出生日期、性别、年龄
	 * @param certificateNo
	 * @return 返回的出生日期格式：1990-01-01   性别格式：F-女，M-男
	 */
	public static Map<String, String> getBirAgeSex(String certificateNo) {
		String birthday = "";
		String age = "";
		String sexCode = "";
		int year = Calendar.getInstance().get(Calendar.YEAR);
		char[] number = certificateNo.toCharArray();
		boolean flag = true;
		if (number.length == 15) {
			for (int x = 0; x < number.length; x++) {
				if (!flag) return new HashMap<String, String>();
				flag = Character.isDigit(number[x]);
			}
		} else if (number.length == 18) {
			for (int x = 0; x < number.length - 1; x++) {
				if (!flag) return new HashMap<String, String>();
				flag = Character.isDigit(number[x]);
			}
		}
		if (flag && certificateNo.length() == 15) {
			birthday = "19" + certificateNo.substring(6, 8);
			sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3, certificateNo.length())) % 2 == 0 ? "F" : "M";
			age = (year - Integer.parseInt("19" + certificateNo.substring(6, 8))) + "";
		} else if (flag && certificateNo.length() == 18) {
			birthday = certificateNo.substring(6, 10)+"年"+certificateNo.substring(10, 12)+"月"+certificateNo.substring(12, 14)+"日" ;

			sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2 == 0 ? "F" : "M";
			age = (year - Integer.parseInt(certificateNo.substring(6, 10))) + "";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("birthday", birthday);
		map.put("age", age);
		map.put("sexCode",sexCode);
		return map;
	}

}
