package com.bzdgs.dms.auth;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MyAuthenFilter extends FormAuthenticationFilter {

    /**
     * 访问来的时候检测是不是OPTIONS请求，如果是就直接返回true
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
            setHeader(httpRequest,httpResponse);
            return true;
        }
        return super.preHandle(request,response);
    }

    /**
     * 这个方法在验证失败后才调用，因为是前后端分离，后台不会控制页面跳转
     * 因此重写改成传输JSON数据
     */
    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        saveRequest(request);
        setHeader((HttpServletRequest) request,(HttpServletResponse) response);
        PrintWriter out = response.getWriter();
        //自己控制返回的json数据
        String restStr = "{\"success\":\"false\",\"msg\":\"对不起，请先认证！\",\"errorCode\":10086}";
        out.println(restStr);
        out.flush();
        out.close();
    }

    /**
     * 为response设置header，实现跨域
     */
    private void setHeader(HttpServletRequest request, HttpServletResponse response){
        // 添加参数，允许指定域名和端口访问
        String origin = request.getHeader("Origin");
        if("http://localhost:6001".equalsIgnoreCase(origin)
                ||"http://127.0.0.1".equalsIgnoreCase(origin)){
            response.setHeader("Access-Control-Allow-Origin", origin);
        }
        // 这个allow-headers要配为*，这样才能允许所有的请求头
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age", String.valueOf(20*24*60*60));

        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(200);
    }
}
