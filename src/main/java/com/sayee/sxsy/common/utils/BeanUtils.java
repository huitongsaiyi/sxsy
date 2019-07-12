package com.sayee.sxsy.common.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

    public static final Logger log = LoggerFactory.getLogger(BeanUtils.class);
    private static Map<Class, Field[]> classMapping = new ConcurrentHashMap();
    private static Map<String, Method> setMethodMapping = new ConcurrentHashMap();
    private static Map<String, Method> getMethodMapping = new ConcurrentHashMap();
    private static Map<String, Field> fieldMapping = new ConcurrentHashMap();

    static
    {
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        ConvertUtils.register(new BooleanConverter(null), Boolean.class);
        ConvertUtils.register(new FloatConverter(null), Float.class);
    }

    public static void copyProperties(Object dest, Object orig, boolean skipIfnull)
    {
        if (skipIfnull){}
//            try {
////                BeanUtilsBean.getInstance().copyProperties(dest, orig);
////            } catch (IllegalAccessException e) {
////                e.printStackTrace();
////            } catch (InvocationTargetException e) {
////                e.printStackTrace();
////            }
        else
            try {
                org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
    }

    public static List copyBeans(List list, Class classType, boolean skipIfnull)
    {
        if (list == null)
            return null;
        Iterator iterator = list.iterator();
        List returnColl = new ArrayList();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            try {
                Object o = classType.newInstance();
                copyProperties(o, object, skipIfnull);
                returnColl.add(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnColl;
    }

    public static Object changeCode(Object object, Class classType)
            throws Exception
    {
        Field[] fields = classType.getDeclaredFields();
        Object objectCopy = classType.getConstructor(new Class[0]).newInstance(new Object[0]);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getMethodName = ((!field.getType().getName().equals("java.lang.Boolean")) && (!field.getType().getName().equals("boolean")) ? "get" : "is") + firstLetter + fieldName.substring(1);
            String setMethodName = "set" + firstLetter + fieldName.substring(1);
            try {
                Method getMethod = classType.getMethod(getMethodName, new Class[0]);
                Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });
                Object value = null;
                if (field.getType().getName().equals("java.lang.String")) {
                    value = getMethod.invoke(object, new Object[0]);
                    //setMethod.invoke(objectCopy, new Object[] { CharsetSwitch.decode(value.toString()) });
                } else {
                    value = getMethod.invoke(object, new Object[0]);
                    setMethod.invoke(objectCopy, new Object[] { value });
                }
            } catch (Exception localException) {
            }
        }
        return objectCopy;
    }

    public static Object copyLazy(Object object, Class classType)
            throws Exception
    {
        Field[] fields = classType.getDeclaredFields();
        Object objectCopy = classType.getConstructor(new Class[0]).newInstance(new Object[0]);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            String fieldName = field.getName();
            String f = field.getType().getName();
            if ((f.indexOf("hibernate") <= -1) && (fieldName.indexOf("CGLIB") <= -1))
            {
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String getMethodName = ((!field.getType().getName().equals("java.lang.Boolean")) && (!field.getType().getName().equals("boolean")) ? "get" : "is") + firstLetter + fieldName.substring(1);
                String setMethodName = "set" + firstLetter + fieldName.substring(1);
                try
                {
                    Method getMethod = classType.getMethod(getMethodName, new Class[0]);
                    Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });
                    Object value = null;

                    if ((f.equals("java.lang.String")) || (f.equals("java.lang.Boolean")) || (f.equals("java.lang.Long")) || (f.equals("java.lang.Integer")) || (f.equals("java.lang.Double")) || (f.equals("java.lang.Float")) || (f.equals("java.math.BigInteger")) || (f.equals("java.util.Date")) || (f.equals("java.sql.Date")) || (f.equals("int")) || (f.equals("long")) || (f.equals("float")) || (f.equals("double")) || (f.equals("boolean"))) {
                        value = getMethod.invoke(object, new Object[0]);
                        setMethod.invoke(objectCopy, new Object[] { value });
                    }
                } catch (Exception localException) {  }

            }
        }
        return objectCopy;
    }

    public static Object exec(String className, String beanName, String methodName, Object[] args)
    {
        try
        {
            Method method = null;
            try {
                Class type = Class.forName(className);
                method = type.getMethod(methodName, new Class[0]);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return method.invoke(null, args);//SpringContextUtil.getBean(beanName)
        } catch (BeansException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean propertyReadable(Object bean, String property)
    {
        if ((bean instanceof Map))
            return ((Map)bean).containsKey(property);
        boolean isReadable;
        try
        {
            isReadable = PropertyUtils.isReadable(bean, property);
        }
        catch (IllegalArgumentException e)
        {
            isReadable = false;
        }

        return isReadable;
    }

    public static Object getPropertyValue(Object bean, String property)
    {
        Object result = null;
        try {
            if (propertyReadable(bean, property))
                result = PropertyUtils.getProperty(bean, property);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void setPropertyValue(Object bean, String property, Object value)
    {
        try
        {
            PropertyUtils.setProperty(bean, property, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean contains(String[] list, String src)
    {
        if (list == null)
            return false;
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(src))
                return true;
        }
        return false;
    }

    public static Object bytesToObject(byte[] bytes)
    {
        XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(bytes));
        return decoder.readObject();
    }

    public static byte[] objectToBytes(Object ob)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(out);
        encoder.writeObject(ob);
        encoder.close();
        return out.toByteArray();
    }

    public static String getAppPath(Class cls)
    {
        if (cls == null)
            throw new IllegalArgumentException("参数不能为空！");
        ClassLoader loader = cls.getClassLoader();

        String clsName = cls.getName() + ".class";

        Package pack = cls.getPackage();
        String path = null;

        if (pack != null) {
            String packName = pack.getName();

            if ((packName.startsWith("java.")) || (packName.startsWith("javax."))) {
                throw new IllegalArgumentException("不要传送系统类！");
            }
            clsName = clsName.substring(packName.length() + 1);

            if (packName.indexOf(".") < 0) {
                path = packName + File.separator;
            }
            else {
                path = packName.replaceAll("\\.", "\\/");
            }
        }
        URL url = loader.getResource(path + "/" + clsName);

        String realPath = url.getPath();

        int pos = realPath.indexOf("file:");
        if (pos > -1) {
            realPath = realPath.substring(pos + 5);
        }
        pos = realPath.indexOf(path + clsName);
        realPath = realPath.substring(0, pos - 1);

        if (realPath.endsWith("!"))
            realPath = realPath.substring(0, realPath.lastIndexOf("/"));
        if (realPath.startsWith("/")) {
            realPath = realPath.substring(1, realPath.length());
        }

        try
        {
            realPath = URLDecoder.decode(realPath, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return realPath;
    }

    public static String getClassPath(Class cls)
    {
        if (cls == null)
            throw new IllegalArgumentException("参数不能为空！");
        ClassLoader loader = cls.getClassLoader();

        String clsName = cls.getName() + ".class";

        Package pack = cls.getPackage();
        String path = null;

        if (pack != null) {
            String packName = pack.getName();

            if (loader == null) {
                throw new IllegalArgumentException("不要传送系统类！");
            }
            clsName = clsName.substring(packName.length() + 1);

            if (packName.indexOf(".") < 0) {
                path = packName + File.separator;
            }
            else {
                path = packName.replaceAll("\\.", "\\/");
            }
        }
        URL url = loader.getResource(path + "/" + clsName);

        String realPath = url.getPath();

        int pos = realPath.indexOf("file:");
        if (pos > -1) {
            realPath = realPath.substring(pos + 5);
        }
        pos = realPath.indexOf(clsName);
        realPath = realPath.substring(0, pos - 1);

        if (realPath.endsWith("!"))
            realPath = realPath.substring(0, realPath.lastIndexOf("/"));
        if (realPath.startsWith("/")) {
            realPath = realPath.substring(1, realPath.length());
        }

        try
        {
            realPath = URLDecoder.decode(realPath, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return realPath;
    }

    public static String getPkName(Class c)
    {
//        Set fds = new HashSet();
//        loadSuperFields(c, fds);
//        String fname = null;
//        for (Field fd : fds) {
//            if (fd.isAnnotationPresent(PrimaryKey.class)) {
//                fname = fd.getName();
//                break;
//            }
//        }
//        if (StringUtils.isBlank(fname)) {
//            fname = HibernateConfigurationHelper.getPkProperyName(c);
//        }
        return null;
    }

    public static boolean isPrimitive(Class clz)
    {
        if ((clz == Byte.class) || (clz == Byte.TYPE))
            return true;
        if ((clz == Integer.class) || (clz == Integer.TYPE))
            return true;
        if ((clz == Short.class) || (clz == Short.TYPE))
            return true;
        if ((clz == Long.class) || (clz == Long.TYPE))
            return true;
        if ((clz == Float.class) || (clz == Float.TYPE))
            return true;
        if ((clz == Double.class) || (clz == Double.TYPE))
            return true;
        if ((clz == Character.class) || (clz == Character.TYPE))
            return true;
        if ((clz == Boolean.class) || (clz == Boolean.TYPE))
            return true;
        if (clz == String.class)
            return true;
        return false;
    }

    public static <T> T getBeanFromRequest(HttpServletRequest request, Class<T> clz)
    {
//        Set fset = new HashSet();
//        loadSuperFields(clz, fset);
//        try
//        {
//            Object bean = clz.newInstance();
//            String fname = null;
//            for (Field fd : fset) {
//                fname = fd.getName();
//                setProperty(bean, fname, request.getParameter(fname));
//            }
//        } catch (Exception e) {
//            throw new BaseException(e, "Request参数获取异常");
//        }
        Object bean;
        return null;
    }

    public static void loadSuperFields(Class clz, Set<Field> fset)
    {
        Field[] fds = getDeclaredFields(clz);
        for (Field fd : fds) {
            fset.add(fd);
            if (clz.getSuperclass() != Object.class)
                loadSuperFields(clz.getSuperclass(), fset);
        }
    }

    public static Field[] getDeclaredFields(Class clz)
    {
        Field[] fds = (Field[])classMapping.get(clz);
        if (fds == null) {
            fds = clz.getDeclaredFields();
            List fls = new ArrayList();
            for (Field fd : fds) {
                switch (fd.getModifiers()) {
                    case 8:
                    case 16:
                    case 256:
                        break;
                    default:
                        fls.add(fd);
                }
            }
            fds = (Field[])fls.toArray(new Field[fls.size()]);
            classMapping.put(clz, fds);
        }
        return fds;
    }

    public static String getMethodName(String fieldName)
    {
        return "get" + firstToUpper(fieldName);
    }

    public static String setMethodName(String fieldName)
    {
        return "set" + firstToUpper(fieldName);
    }

    public static String firstToUpper(String str)
    {
        if (str == null)
            return null;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String firstToLower(String str)
    {
        if (str == null)
            return null;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static Field getField(String fieldName, Class clz)
    {
        if (fieldMapping.containsKey(fieldName + clz.getName())) {
            return (Field)fieldMapping.get(fieldName + clz.getName());
        }
        if (HibernateProxy.class.isAssignableFrom(clz)) {
            clz = clz.getSuperclass();
        }
        Field field = null;
        while (clz != Object.class) {
            try {
                field = clz.getDeclaredField(fieldName);
                fieldMapping.put(fieldName + clz.getName(), field);
                return field;
            }
            catch (Exception localException)
            {
                clz = clz.getSuperclass();
            }

        }

        log.debug("未匹配到" + clz.getName() + "的字段：[" + fieldName + "]");
        return null;
    }

    public static Method getMethod(String fieldName, Class clz)
    {
        if (getMethodMapping.containsKey(fieldName + clz.getName())) {
            return (Method)getMethodMapping.get(fieldName + clz.getName());
        }
        if (HibernateProxy.class.isAssignableFrom(clz)) {
            clz = clz.getSuperclass();
        }
        Method method = null;
        String name = getMethodName(fieldName);
        while (clz != Object.class) {
            try {
                method = clz.getMethod(name, new Class[0]);
                getMethodMapping.put(fieldName + clz.getName(), method);
                return method;
            }
            catch (Exception localException)
            {
                clz = clz.getSuperclass();
            }

        }

        log.debug("未匹配到" + clz.getName() + "字段为：[" + fieldName + "]的Get方法");
        return null;
    }

    public static Method setMethod(Field field, Class clz)
    {
        if (setMethodMapping.containsKey(field.getName() + clz.getName())) {
            return (Method)setMethodMapping.get(field.getName() + clz.getName());
        }
        if (HibernateProxy.class.isAssignableFrom(clz)) {
            clz = clz.getSuperclass();
        }
        Method method = null;
        String name = setMethodName(field.getName());
        while (clz != Object.class) {
            try {
                method = clz.getMethod(name, new Class[] { field.getType() });
                setMethodMapping.put(field.getName() + clz.getName(), method);
                return method;
            }
            catch (Exception localException)
            {
                clz = clz.getSuperclass();
            }

        }

        log.debug("未匹配到" + clz.getName() + "字段为：[" + field.getName() + "]的Set方法");
        return null;
    }

    public static Method setMethod(String fieldName, Class clz)
    {
        if (setMethodMapping.containsKey(fieldName + clz.getName())) {
            return (Method)setMethodMapping.get(fieldName + clz.getName());
        }
        Method method = null;
        String name = setMethodName(fieldName);
        while (clz != Object.class) {
            try {
                method = clz.getMethod(name, new Class[] { getField(fieldName, clz).getType() });
                setMethodMapping.put(fieldName + clz.getName(), method);
                return method;
            }
            catch (Exception localException)
            {
                clz = clz.getSuperclass();
            }

        }

        log.debug("未匹配到" + clz.getName() + "字段为：[" + fieldName + "]的Set方法");
        return null;
    }

    public static Class<?> getCollectionType(Field fd)
    {
        Class[] ts = getCollectionTypes(fd);
        return ts.length > 0 ? ts[0] : Object.class;
    }

    public static Class<?>[] getCollectionTypes(Field fd)
    {
        Type t = fd.getGenericType();
        if ((t instanceof ParameterizedType)) {
            ParameterizedType pt = (ParameterizedType)t;
            Type[] ts = pt.getActualTypeArguments();
            if ((ts != null) && (ts.length > 0)) {
                Class[] clss = new Class[ts.length];
                for (int i = 0; i < ts.length; i++) {
                    clss[i] = ((Class)ts[i]);
                }
                return clss;
            }
        }
        return new Class[0];
    }

    public static Class<?> hibernateClass2Common(Class<?> clz)
    {
        if (HibernateProxy.class.isAssignableFrom(clz)) {
            clz = clz.getSuperclass();
        }
        return clz;
    }

    public static <T> T clone(Object o, Class<T> t)
    {
        ByteArrayOutputStream byteOut = null;
        ObjectOutputStream objOut = null;
        ByteArrayInputStream byteIn = null;
        ObjectInputStream objIn = null;
        try
        {
            byteOut = new ByteArrayOutputStream();
            objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(o);

            byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            objIn = new ObjectInputStream(byteIn);

            return (T) objIn.readObject();
        } catch (IOException e) {
            throw new RuntimeException("Clone Object failed in IO.", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found.", e);
        } finally {
            try {
                byteIn = null;
                byteOut = null;
                if (objOut != null)
                    objOut.close();
                if (objIn != null)
                    objIn.close();
            }
            catch (IOException localIOException2)
            {
            }
        }
    }

    public static int compare(Object A, Object B)
    {
        return new CompareAny(A, B).exec();
    }
    private static class CompareAny {
        Object fieldA;
        Object fieldB;

        public CompareAny(Object A, Object B) { this.fieldA = A;
            this.fieldB = B; }

        public int exec()
        {
            if ((this.fieldB == null) && (this.fieldA == null)) {
                return 0;
            }
            if (this.fieldA == null) {
                return -1;
            }
            if (this.fieldB == null) {
                return 1;
            }

            Class clz = this.fieldA.getClass();
            if ((clz == Short.class) || (clz == Short.TYPE))
                return compareShort(this.fieldA, this.fieldB);
            if ((clz == Integer.class) || (clz == Integer.TYPE))
                return compareInteger(this.fieldA, this.fieldB);
            if ((clz == Long.class) || (clz == Long.TYPE))
                return compareLong(this.fieldA, this.fieldB);
            if ((clz == Float.class) || (clz == Float.TYPE))
                return compareFloat(this.fieldA, this.fieldB);
            if ((clz == Double.class) || (clz == Double.TYPE))
                return compareDouble(this.fieldA, this.fieldB);
            if (clz == BigDecimal.class)
                return compareBigDecimal(this.fieldA, this.fieldB);
            if (clz == Date.class) {
                return compareDate(this.fieldA, this.fieldB);
            }
            return compareString(this.fieldA, this.fieldB);
        }

        private int compareString(Object fieldA, Object fieldB) {
            String strA = fieldA.toString();
            String strB = fieldB.toString();

            int length = strA.length() < strB.length() ? strA.length() : strB.length();
            for (int i = 0; i < length; i++)
            {
                String s1 = strA.substring(i, i + 1);
                String s2 = strB.substring(i, i + 1);

                if ((s1.length() != s1.getBytes().length) && (s2.length() != s2.getBytes().length)) {
                    if (!s1.equals(s2))
                    {
                        return 0;//concatPinyinStringArray(PinyinHelper.toHanyuPinyinStringArray(s1.charAt(0))).compareTo(concatPinyinStringArray(PinyinHelper.toHanyuPinyinStringArray(s2.charAt(0))));
                    }
                } else if (!s1.equals(s2))
                {
                    return s1.compareTo(s2);
                }
            }
            return strA.length() - strB.length();
        }

        private int compareShort(Object a, Object b) {
            return ((Short)a).shortValue() - ((Short)b).shortValue();
        }

        private int compareInteger(Object a, Object b) {
            return ((Integer)a).intValue() - ((Integer)b).intValue();
        }

        private int compareLong(Object a, Object b) {
            return (int)(((Long)a).longValue() - ((Long)b).longValue());
        }

        private int compareFloat(Object a, Object b) {
            return (int)(((Float)a).floatValue() - ((Float)b).floatValue());
        }

        private int compareDouble(Object a, Object b) {
            return (int)(((Double)a).doubleValue() - ((Double)b).doubleValue());
        }

        private int compareBigDecimal(Object a, Object b) {
            return ((BigDecimal)a).compareTo((BigDecimal)b);
        }

        private int compareDate(Object a, Object b) {
            return ((Date)a).compareTo((Date)b);
        }

        private String concatPinyinStringArray(String[] pinyinArray) {
            StringBuffer sb = new StringBuffer();
            if ((pinyinArray != null) && (pinyinArray.length > 0)) {
                for (int i = 0; i < pinyinArray.length; i++) {
                    sb.append(pinyinArray[i]);
                }
            }
            return sb.toString();
        }
    }

}
