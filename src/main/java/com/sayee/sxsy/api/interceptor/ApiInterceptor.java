package com.sayee.sxsy.api.interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description
 *
 * @author www.donxon.com
 */
public class ApiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String APPTOKEN = request.getHeader("APPTOKEN");
        if(null==APPTOKEN || APPTOKEN.isEmpty()){
            request.setAttribute("msg","请求token为空");
            request.getRequestDispatcher(request.getContextPath()+"/api/prohibit").forward(request,response);
            return false;
        }else{
            HttpSession session=request.getSession();
            if(null==session.getAttribute("APPTOKEN")||session.getAttribute("APPTOKEN").toString().isEmpty()){
                request.setAttribute("msg","session过期请重新获取验证");
                request.getRequestDispatcher(request.getContextPath()+"/api/prohibit").forward(request,response);
                return false;
            }else{
                if(APPTOKEN.equals(session.getAttribute("APPTOKEN").toString())) {
                    return true;
                }else{
                    request.setAttribute("msg","token值无效");
                    request.getRequestDispatcher(request.getContextPath()+"/api/prohibit").forward(request,response);
                    return false;
                }
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}