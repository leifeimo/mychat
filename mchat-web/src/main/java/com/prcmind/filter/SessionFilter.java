package com.prcmind.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SessionFilter implements Filter {  
    
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
  
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,  
            ServletException {  
       /* HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;  
        StringBuffer url=httpServletRequest.getRequestURL();
        String path =httpServletRequest.getRequestURI();
		if (url.toString().contains("oauth/token") || path.contains("login.html") || path.contains("images")
				|| path.contains("js") || path.contains("css")) {// 过滤登录接口
			chain.doFilter(request, response);
			return;
		}
        //过滤get请求、post请求session超时
       if( httpServletRequest.getMethod().toLowerCase().equals("get")||httpServletRequest.getMethod().toLowerCase().equals("post")){
    	   HttpSession session= httpServletRequest.getSession();
    	   MedicOperator  user = (MedicOperator ) session.getAttribute(WebConstants.CURRENT_USER);
    	   EnterpriseOperator sysUser= (EnterpriseOperator)session.getAttribute(WebConstants.MANAGER_USER); 
           if(user == null && sysUser ==null){
        	   if (httpServletRequest.getHeader("x-requested-with") != null  
                       && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {  
            	   httpServletResponse.setHeader("sessionstatus", "timeout");//在响应头设置session状态  
            	   httpServletResponse.setHeader("code", "100002");
            	   return;
               } 
        	   httpServletResponse.setContentType("text/html; charset=utf-8");
        	   PrintWriter writer=  httpServletResponse.getWriter();
        	   writer.write("<script>alert('您太久离开页面，请重新登录');</script>");
        	   httpServletResponse.sendRedirect("/html/login.html");
               return;  
           }
       }*/
        chain.doFilter(request, response);  
    }  
  
    @Override  
    public void destroy() {  
  
    }

	
  
} 