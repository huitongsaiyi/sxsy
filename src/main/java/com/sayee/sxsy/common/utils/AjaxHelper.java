package com.sayee.sxsy.common.utils;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.core.BaseException;
import org.apache.commons.collections.MapUtils;
import org.springframework.web.servlet.ModelAndView;

public class AjaxHelper {
    public static ModelAndView opSuccess(HttpServletRequest request)
    {
        request.setAttribute("isSaveAndAdd", Boolean.valueOf(true));
        return new ModelAndView("/core/success/opSuccess.jsp");
    }

    public static ModelAndView opSuccess(HttpServletRequest request, String message)
    {
        request.setAttribute("isSaveAndAdd", Boolean.valueOf(true));
        return new ModelAndView("/core/success/opSuccess.jsp");
    }


//    public static String getIpAddr(HttpServletRequest request, boolean needAddress)
//    {
//        String ip = request.getHeader("x-forwarded-for");
//        if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
//            ip = request.getRemoteAddr();
//        }
//        if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
//            ip = request.getHeader("http_client_ip");
//        }
//        if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//
//        if ((ip != null) && (ip.indexOf(",") != -1)) {
//            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
//        }
//        ip = ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
//        if (needAddress)
//            try {
//                Map params = new HashMap();
//                params.put("ip", ip);
//                Map result = (Map)JsonUtil.toBean(CharsetSwitch.ascii2Native(HttpUtils.doGet("http://ip.taobao.com/service/getIpInfo.php", params)), HashMap.class);
//                if ("0".equals(MapUtils.getString(result, "code"))) {
//                    result = MapUtils.getMap(result, "data");
//                    ip = ip + "(" + MapUtils.getString(result, "country") + MapUtils.getString(result, "region") + MapUtils.getString(result, "city") + MapUtils.getString(result, "isp") + ")";
//                }
//            }
//            catch (Exception localException)
//            {
//            }
//        return ip;
//    }

    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getRemoteAddr();
        }
        if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("http_client_ip");
        }
        if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if ((ip != null) && (ip.indexOf(",") != -1)) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        ip = ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
        return ip;
    }



    public static void getParamFromRequest(HttpServletRequest request, Map param, String[] args)
    {
        getParamFromRequest(request, param, args == null ? null : Arrays.asList(args));
    }

    public static void getParamFromRequest(HttpServletRequest request, Map param, List args)
    {
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String)enu.nextElement();
            if ((args == null) || (args.indexOf(paraName) <= -1))
            {
                String[] params = request.getParameterValues(paraName);
                if (params.length == 1) {
                    param.put(paraName, params[0]);
                    if (StringUtils.isEmpty(params[0]))
                        param.put(paraName, null);
                    else if (StringUtils.equals(params[0], "null"))
                        param.put(paraName, null);
                }
                else
                {
                    param.put(paraName, params);
                }
            }
        }
    }

//    public static List<Map<String, Object>> dataRevise(String data)
//    {
//        if (StringUtils.isBlank(data)) return null;
//        List result = new ArrayList();
//        if (StringUtils.isNotBlank(data)) {
//            List array = JsonUtil.toList(data, Object.class);
//            for (Iterator localIterator = array.iterator(); localIterator.hasNext(); ) { Object object = localIterator.next();
//                Map element = new HashMap();
//                try {
//                    List elementArray = JsonUtil.toList(object.toString(), Object.class);
//                    int i = 0; for (int j = elementArray.size(); i < j; i++)
//                        element.put("self_" + i, elementArray.get(i));
//                    result.add(element);
//                } catch (Exception e) {
//                    element.put("self", object);
//                    result.add(element);
//                }
//            }
//        }
//        return result;
//    }

//    public static String getRequestData(HttpServletRequest request)
//    {
//        StringBuffer s = new StringBuffer();
//        try {
//            BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
//            if (bis != null) {
//                byte[] read = new byte[1024];
//                int len = -1;
//                while ((len = bis.read(read, 0, 1024)) != -1) {
//                    s.append(new String(read, 0, len, "UTF-8"));
//                    read = new byte[1024];
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String jSonStr = s.toString();
//        if (StringUtils.isEmpty(jSonStr)) {
//            try {
//                jSonStr = request.getParameter("data");
//                if (StringUtils.isEmpty(jSonStr)) {
//                    return null;
//                }
//                jSonStr = URLDecoder.decode(request.getParameter("data"), "utf-8");
//                jSonStr = CharsetSwitch.unicode2word(jSonStr);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return jSonStr == null ? null : jSonStr.trim();
//    }

    private static void responseWriteObject(HttpServletRequest request, HttpServletResponse response, Object message, boolean isString)
    {
        String callback = request.getParameter("callback");
        try {
            response.setCharacterEncoding("utf-8");
            if (message != null)
                if (StringUtils.isNotEmpty(callback)) {
                    if (isString)
                        response.getWriter().write(request.getParameter("callback") + "(\"" + message + "\")");
                    else
                        response.getWriter().write(request.getParameter("callback") + "(" + message + ")");
                }
                else if (isString)
                    response.getWriter().write("\"" + message + "\"");
                else
                    response.getWriter().write(message.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void jqGridRefresh(HttpServletRequest request, HttpServletResponse response, String message, String error)
    {
        Map data = new HashMap();
        data.put("forceRefresh", Boolean.valueOf(true));
        if (message != null)
            data.put("message", message);
        if (error != null)
            data.put("error", error);
        responseWrite(request, response, data);
    }

    public static void jqGridRefresh(HttpServletRequest request, HttpServletResponse response, boolean refresh, String message, String error)
    {
        Map data = new HashMap();
        data.put("forceRefresh", Boolean.valueOf(refresh));
        if (message != null)
            data.put("message", message);
        if (error != null)
            data.put("error", error);
        responseWrite(request, response, data);
    }

    public static void responseWrite(HttpServletRequest request, HttpServletResponse response, boolean num)
    {
        responseWriteObject(request, response, Boolean.valueOf(num), false);
    }

    public static void responseWrite(HttpServletRequest request, HttpServletResponse response, int num)
    {
        responseWriteObject(request, response, Integer.valueOf(num), false);
    }

    public static void responseWrite(HttpServletRequest request, HttpServletResponse response, double num)
    {
        responseWriteObject(request, response, Double.valueOf(num), false);
    }

    public static void responseWrite(HttpServletRequest request, HttpServletResponse response, String message)
    {
        responseWriteObject(request, response, message, true);
    }

    public static void responseWrite(HttpServletRequest request, HttpServletResponse response, String message, boolean isString)
    {
        responseWriteObject(request, response, message, isString);
    }

    public static void responseWrite(HttpServletRequest request, HttpServletResponse response, List list)
    {
        responseWriteObject(request, response, list == null ? "[]" : JsonUtil.toJson(list), false);
    }

    public static void responseWrite(HttpServletRequest request, HttpServletResponse response, Map map)
    {
        responseWriteObject(request, response, map == null ? "{}" : JsonUtil.toJson(map), false);
    }

    public static void responseWrite(HttpServletRequest request, HttpServletResponse response, String status, String msg, Object data)
    {
        Map map = new HashMap();
        map.put("status", status);
        map.put("msg", msg);
        map.put("data", data);
        responseWrite(request, response, map);
    }

//    public static void responseWrite(HttpServletRequest request, HttpServletResponse response, BaseBean map)
//    {
//        responseWriteObject(request, response, map == null ? "{}" : JsonUtil.toJson(map), false);
//    }

    public static void accessSubmitResponse(String result, String styleId, HttpServletResponse response)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<script>");
        builder.append("parent.$(\"#");
        builder.append(styleId);
        builder.append("\").upload(\"submitCallBack\",");
        builder.append(result);
        builder.append(");</script>");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String accessSubmitResponse(String result, String styleId)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<script>");
        builder.append("parent.$(\"#");
        builder.append(styleId);
        builder.append("\").upload(\"submitCallBack\",");
        builder.append(result);
        builder.append(");</script>");
        return builder.toString();
    }

    public static void asynFormResponse(String function, HttpServletResponse response)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<script>");
        builder.append("parent.");
        builder.append(function);
        builder.append("</script>");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
