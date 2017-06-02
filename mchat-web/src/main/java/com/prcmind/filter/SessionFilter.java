//package com.prcmind.filter;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.prcmind.facade.user.entity.EnterpriseOperator;
//import com.prcmind.facade.user.entity.MedicOperator;
//import com.prcmind.utils.HttpClient;
//import com.prcmind.utils.WebConstants;
//
//public class SessionFilter implements Filter {
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//		StringBuffer url = httpServletRequest.getRequestURL();
//		if (url.toString().contains("oauth/token") || url.toString().contains("/user")
//				|| url.toString().contains("/findMedicLoginPwd")|| url.toString().contains("/logout")) {// 过滤登录接口,找回密码接口,
//			chain.doFilter(request, response);
//			return;
//		}
//		// 过滤get请求、post请求session超时
//		if (httpServletRequest.getMethod().toLowerCase().equals("get")
//				|| httpServletRequest.getMethod().toLowerCase().equals("post")) {
//			HttpSession session = httpServletRequest.getSession();
//			MedicOperator  user = (MedicOperator ) session.getAttribute(WebConstants.CURRENT_USER);
//	    	   EnterpriseOperator sysUser= (EnterpriseOperator)session.getAttribute(WebConstants.MANAGER_USER); 
//	           if(user == null && sysUser ==null){
//	        	   if (httpServletRequest.getHeader("x-requested-with") != null  
//	                       && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {  
//	            	   httpServletResponse.setHeader("sessionstatus", "timeout");//在响应头设置session状态  
//	            	   httpServletResponse.setHeader("code", "100002");
//	            	   return;
//	               } 
//	        	   if(httpServletRequest.getMethod().toLowerCase().equals("post")){
//	        		   HttpClient http=new HttpClient (httpServletResponse);
//	        		   http.setParameter("message","登录失效，请重新登录");
//	        		   http.sendByPost("/mchat-web/html/login.html");
//	        	   }else{
//	        		   httpServletResponse.setContentType("text/html; charset=utf-8");
//		        	   PrintWriter writer=  httpServletResponse.getWriter();
//		        	   writer.write("<script>alert('登录失效，请重新登录');</script>");
//		        	   httpServletResponse.sendRedirect("/mchat-web/html/login.html");
//		               return;   
//	        	   }
//	        	 
//	           }
//		}
//		chain.doFilter(request, response);
//	}
//
//	@Override
//	public void destroy() {
//
//	}
//
//}