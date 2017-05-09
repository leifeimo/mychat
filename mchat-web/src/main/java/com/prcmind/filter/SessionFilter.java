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
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;  
//        StringBuffer url=httpServletRequest.getRequestURL();
//        if(url.toString().contains("oauth/token")){//过滤登录接口
//        	chain.doFilter(request, response);  
//        	return;
//        }
//        //过滤get请求、post请求session超时
//       if( httpServletRequest.getMethod().toLowerCase().equals("get")||httpServletRequest.getMethod().toLowerCase().equals("post")){
//    	   HttpSession session= httpServletRequest.getSession();
//           BaseUserView user = (BaseUserView) session.getAttribute(WebConstants.CURRENT_USER);
//           if(user == null){
//        	   if (httpServletRequest.getHeader("x-requested-with") != null  
//                       && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {  
//            	   httpServletResponse.setHeader("sessionstatus", "timeout");//在响应头设置session状态  
//               } 
//        	   httpServletResponse.setContentType("text/html; charset=utf-8");
//        	   PrintWriter writer=  httpServletResponse.getWriter();
//        	   writer.write("<script>alert('您太久离开页面，请重新登录');</script>");
//               return;  
//           }
//       }
        chain.doFilter(request, response);  
    }  
  
    @Override  
    public void destroy() {  
  
    }

	
  
} 