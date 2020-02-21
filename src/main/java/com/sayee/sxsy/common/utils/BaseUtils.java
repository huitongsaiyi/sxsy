/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.common.utils;

import com.sayee.sxsy.modules.signtype.dao.SignTypeInfoDao;
import com.sayee.sxsy.modules.signtype.entity.SignTypeInfo;
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
import java.util.*;
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

    private static SignTypeInfoDao signTypeInfoDao = SpringContextHolder.getBean(SignTypeInfoDao.class);

    /**
     * 单位进位，中文默认为4位即（万、亿）
     */
    public static  int UNIT_STEP = 4;

    /**
     * 单位
     */
    public static String[] CN_UNITS = new String[] { "个", "十", "百", "千", "万", "十",
            "百", "千", "亿", "十", "百", "千", "万" };

    /**
     * 汉字
     */
    public static String[] CN_CHARS = new String[] { "零", "一", "二", "三", "四",
            "五", "六", "七", "八", "九" };


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
     * typeinfo
     *根据 类型 关联 sign_type  中的数据
     * @param relationModel
     */
    public static List<SignTypeInfo> getType(String relationModel,String signId) {
        if (StringUtils.isNotBlank(relationModel)) {
            List<SignTypeInfo> list = signTypeInfoDao.findSignTypeList(relationModel,signId);
            return list;
        } else {
            return new ArrayList<SignTypeInfo>();
        }
    }

    /**
     *
     * 功能描述: 去掉中文括号里面的内容
     *
     * @param: [context]
     * @return: java.lang.String
     * @date: 2018/7/12 0012 11:18
     */
    public static String clearZhBracket(String context) {

        // 修改原来的逻辑，防止右括号出现在左括号前面的位置
        int head = context.indexOf('（'); // 标记第一个使用左括号的位置
        if (head == -1) {
            return context; // 如果context中不存在括号，什么也不做，直接跑到函数底端返回初值str
        } else {
            int next = head + 1; // 从head+1起检查每个字符
            int count = 1; // 记录括号情况
            do {
                if (context.charAt(next) == '（') {
                    count++;
                } else if (context.charAt(next) == '）') {
                    count--;
                }
                next++; // 更新即将读取的下一个字符的位置
                if (count == 0) { // 已经找到匹配的括号
                    String temp = context.substring(head, next);
                    context = context.replace(temp, ""); // 用空内容替换，复制给context
                    head = context.indexOf('（'); // 找寻下一个左括号
                    next = head + 1; // 标记下一个左括号后的字符位置
                    count = 1; // count的值还原成1
                }
            } while (head != -1); // 如果在该段落中找不到左括号了，就终止循环
        }
        return context; // 返回更新后的context
    }

    /**
     *
     * 功能描述: 去掉中文括号里面的内容
     *
     * @param: [context]
     * @return: java.lang.String
     * @date: 2018/7/12 0012 11:18
     */
    public static String clearEhBracket(String context) {

        // 修改原来的逻辑，防止右括号出现在左括号前面的位置
        int head = context.indexOf('('); // 标记第一个使用左括号的位置
        if (head == -1) {
            return context; // 如果context中不存在括号，什么也不做，直接跑到函数底端返回初值str
        } else {
            int next = head + 1; // 从head+1起检查每个字符
            int count = 1; // 记录括号情况
            do {
                if (context.charAt(next) == '(') {
                    count++;
                } else if (context.charAt(next) == ')') {
                    count--;
                }
                next++; // 更新即将读取的下一个字符的位置
                if (count == 0) { // 已经找到匹配的括号
                    String temp = context.substring(head, next);
                    context = context.replace(temp, ""); // 用空内容替换，复制给context
                    head = context.indexOf('('); // 找寻下一个左括号
                    next = head + 1; // 标记下一个左括号后的字符位置
                    count = 1; // count的值还原成1
                }
            } while (head != -1); // 如果在该段落中找不到左括号了，就终止循环
        }
        return context; // 返回更新后的context
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
            String time = DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDay();
            StringBuffer code = new StringBuffer();
            if ("year".equals(type)){
                time = DateUtils.getYear();
            }
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

    /**
     * 将阿拉伯数字转换为中文数字123=》一二三
     *
     * @param srcNum
     * @return
     */
    public static String getCNNum(int srcNum) {
        String desCNNum = "";

        if (srcNum <= 0)
            desCNNum = "零";
        else {
            int singleDigit;
            while (srcNum > 0) {
                singleDigit = srcNum % 10;
                desCNNum = String.valueOf(CN_CHARS[singleDigit]) + desCNNum;
                srcNum /= 10;
            }
        }

        return desCNNum;
    }

    /**
     * 数值转换为中文字符串 如2转化为贰
     */
    public String cvt(long num) {
        return this.cvt(num, false);
    }

    /**
     * 数值转换为中文字符串(口语化)
     *
     * @param num
     *            需要转换的数值
     * @param isColloquial
     *            是否口语化。例如12转换为'十二'而不是'一十二'。
     * @return
     */
    public static String cvt(String num, boolean isColloquial) {
        int integer, decimal=0;
        StringBuffer strs = new StringBuffer(32);
        String[] splitNum = num.split("\\.");
        integer = Integer.parseInt(splitNum[0]);    //整数部分
        if (splitNum.length>1){
            decimal = Integer.parseInt(splitNum[1]);    //小数部分
        }
        String[] result_1 =convert(integer, isColloquial);
        for (String str1 : result_1)
            strs.append(str1);
        if(decimal==0){//小数部分为0时
            return strs.toString();
        }else{
            String result_2 =getCNNum(decimal);  //例如5.32，小数部分展示三二，而不是三十二
            strs.append("点");
            strs.append(result_2);
            return strs.toString();
        }
    }

    /*
     * 对于int,long类型的数据处理
     */
    public String cvt(long num, boolean isColloquial) {
        String[] result = this.convert(num, isColloquial);
        StringBuffer strs = new StringBuffer(32);
        for (String str : result) {
            strs.append(str);
        }
        return strs.toString();
    }

    /**
     * 将数值转换为中文
     *
     * @param num
     *            需要转换的数值
     * @param isColloquial
     *            是否口语化。例如12转换为'十二'而不是'一十二'。
     * @return
     */
    public static String[] convert(long num, boolean isColloquial) {
        if (num < 10) {// 10以下直接返回对应汉字
            return new String[] { CN_CHARS[(int) num] };// ASCII2int
        }

        char[] chars = String.valueOf(num).toCharArray();
        if (chars.length > CN_UNITS.length) {// 超过单位表示范围的返回空
            return new String[] {};
        }

        boolean isLastUnitStep = false;// 记录上次单位进位
        ArrayList<String> cnchars = new ArrayList<String>(chars.length * 2);// 创建数组，将数字填入单位对应的位置
        for (int pos = chars.length - 1; pos >= 0; pos--) {// 从低位向高位循环
            char ch = chars[pos];
            String cnChar = CN_CHARS[ch - '0'];// ascii2int 汉字
            int unitPos = chars.length - pos - 1;// 对应的单位坐标
            String cnUnit = CN_UNITS[unitPos];// 单位
            boolean isZero = (ch == '0');// 是否为0
            boolean isZeroLow = (pos + 1 < chars.length && chars[pos + 1] == '0');// 是否低位为0

            boolean isUnitStep = (unitPos >= UNIT_STEP && (unitPos % UNIT_STEP == 0));// 当前位是否需要单位进位

            if (isUnitStep && isLastUnitStep) {// 去除相邻的上一个单位进位
                int size = cnchars.size();
                cnchars.remove(size - 1);
                if (!CN_CHARS[0].equals(cnchars.get(size - 2))) {// 补0
                    cnchars.add(CN_CHARS[0]);
                }
            }

            if (isUnitStep || !isZero) {// 单位进位(万、亿)，或者非0时加上单位
                cnchars.add(cnUnit);
                isLastUnitStep = isUnitStep;
            }
            if (isZero && (isZeroLow || isUnitStep)) {// 当前位为0低位为0，或者当前位为0并且为单位进位时进行省略
                continue;
            }
            cnchars.add(cnChar);
            isLastUnitStep = false;
        }

        Collections.reverse(cnchars);
        // 清除最后一位的0
        int chSize = cnchars.size();
        String chEnd = cnchars.get(chSize - 1);
        if (CN_CHARS[0].equals(chEnd) || CN_UNITS[0].equals(chEnd)) {
            cnchars.remove(chSize - 1);
        }

        // 口语化处理
        if (isColloquial) {
            String chFirst = cnchars.get(0);
            String chSecond = cnchars.get(1);
            if (chFirst.equals(CN_CHARS[1]) && chSecond.startsWith(CN_UNITS[1])) {// 是否以'一'开头，紧跟'十'
                cnchars.remove(0);
            }
        }
        return cnchars.toArray(new String[] {});
    }

}
