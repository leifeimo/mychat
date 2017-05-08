package com.prcmind.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.CookieUtil;
import com.prcmind.utils.HttpClientUtil;
import com.prcmind.utils.WebConstants;
import com.prcmind.view.BaseUserView;
import com.prcmind.view.LoginSucceedView;

/**
 * 管理员控制层
 * @author leichang
 *
 */
@Controller
public class EnterpriseController {
	private  ResourceBundle resource = ResourceBundle.getBundle("mchat-config");
	private  String API_URL = resource.getString("api-url");  
	
	@RequestMapping(value = "/web/v1/enterprise/oauth-server/oauth/token", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("client_id", "enterprise-client");
		param.put("client_secret", "enterprise");
		param.put("grant_type", "password");
		param.put("scope", "read write");
		param.put("username", username);
		param.put("password", password);
		
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/oauth/token", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		LoginSucceedView view = JSON.toJavaObject(jsonObj, LoginSucceedView.class);
		HttpSession session = request.getSession();
		BaseUserView user = new BaseUserView();
		user.setAccountId(username);
		user.setToken(view.getAccess_token());
		session.setAttribute(WebConstants.CURRENT_USER,user);
		session.setMaxInactiveInterval(view.getExpires_in());
		CookieUtil.addCookie(request, response, "token", view.getAccess_token(), view.getExpires_in());
		return new CodeMsgBean<Object>(1, "操作成功", view);
	}
	
	@RequestMapping(value = "/web/v1/enterprise/getInformation", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> getInformation(HttpServletRequest request, String access_token) throws IOException {
//		Cookie cookie = CookieUtil.getCookieByName(request, "token");
//		if (StringUtils.isEmpty(cookie)) {
//			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
//		}
		
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil
					.get(API_URL+"/enterprise/getInformation?access_token=" + access_token);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/enterprise/getMedicScaleDosageByMedicNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMedicScaleDosageByMedicNo(HttpServletRequest request, String access_token,
			String medicNo) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(medicNo)) {
			return new CodeMsgBean<Object>(10002, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("medicNo", medicNo);
		param.put("access_token", access_token);
		
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/enterprise/getMedicScaleDosageByMedicNo", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/enterprise/listEnterpriseScaleDosage", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> listEnterpriseScaleDosage(HttpServletRequest request, String access_token
			) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("access_token", access_token);
		
		JSONObject jsonObj =null;
		String result=null;
		try {
			result= HttpClientUtil.get(API_URL+"/enterprise/listEnterpriseScaleDosage?access_token="+access_token);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
			return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
		} catch (Exception e) {
			JSONArray arr=JSON.parseArray(result);
			return new CodeMsgBean<Object>(1,"操作成功",arr);
		}
	}
	
	@RequestMapping(value = "/web/v1/enterprise/getArticle", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> getArticle(HttpServletRequest request, String access_token,
			String id) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(id)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		param.put("access_token", access_token);
		
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.get(API_URL+"/enterprise/getArticle?access_token="+access_token+"&id="+id);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/enterprise/listArticle", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> queryListArticle(int pageNum, int numPerPage, HttpServletRequest request,
			String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(numPerPage)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("pageNum", pageNum+"");
		param.put("numPerPage", numPerPage+"");
		// param.put("access_token", cookie.getValue());
		param.put("access_token", access_token);
	
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/enterprise/listArticle", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/enterprise/getMedicInfoByMedicNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMedicInfoByMedicNo(HttpServletRequest request, String access_token,
			String medicNo) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(medicNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("medicNo", medicNo);
		param.put("access_token", access_token);
	
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/enterprise/getMedicInfoByMedicNo", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/enterprise/updateLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> updateLoginPwd(String oldPassword, String newPassword, HttpServletRequest request,
			String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("oldPassword", oldPassword);
		param.put("newPassword", newPassword);
		param.put("access_token", access_token);
		
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/enterprise/updateLoginPwd", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/enterprise/dismissUser", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> dismissUser(String medicNo, String managerPwd, HttpServletRequest request,
			String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(medicNo) || StringUtils.isEmpty(managerPwd)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("medicNo", medicNo);
		param.put("managerPwd", managerPwd);
		param.put("access_token", access_token);
		
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/enterprise/dismissUser", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/enterprise/boundMedic", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> boundMedic(String realName,String cardNo, String managerPwd, HttpServletRequest request,
			String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(realName) || StringUtils.isEmpty(managerPwd)|| StringUtils.isEmpty(cardNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("realName", realName);
		param.put("cardNo", cardNo);
		param.put("managerPwd", managerPwd);
		param.put("access_token", access_token);
		
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/enterprise/boundMedic", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	

	@RequestMapping(value = "/web/v1/enterprise/user/findEnterpriseLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> findEnterpriseLoginPwd(String loginName,String newLoginPwd, String puk, HttpServletRequest request
			) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(newLoginPwd)|| StringUtils.isEmpty(puk)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("loginName", loginName);
		param.put("newLoginPwd", newLoginPwd);
		param.put("puk", puk);
		
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/enterprise/user/findEnterpriseLoginPwd", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	
	@RequestMapping(value = "/web/v1/enterprise/listMedic", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMedic(int pageNum, int numPerPage, HttpServletRequest request,
			String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(numPerPage)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("pageNum", pageNum+"");
		param.put("numPerPage", numPerPage+"");
		// param.put("access_token", cookie.getValue());
		param.put("access_token", access_token);
		
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/enterprise/listMedic", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/enterprise/listAllScaleProducts", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> listAllScaleProducts(HttpServletRequest request,String access_token) throws IOException {
//		Cookie cookie = CookieUtil.getCookieByName(request, "token");
//		if (StringUtils.isEmpty(cookie)) {
//			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
//		}
		
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil
					.get(API_URL+"/enterprise/listAllScaleProducts?access_token=" + access_token);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	
	@RequestMapping(value = "/web/v1/enterprise/user/findLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> findLoginPwd(String loginName,String newLoginPwd, String puk, HttpServletRequest request,
			String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(newLoginPwd)|| StringUtils.isEmpty(puk)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("loginName", loginName);
		param.put("newLoginPwd", newLoginPwd);
		param.put("puk", puk);
		param.put("access_token", access_token);
	
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/enterprise/user/findLoginPwd", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}

	@RequestMapping(value = "/web/v1/enterprise/token/logout", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> logout( HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		return new CodeMsgBean<Object>(1, "操作成功");
	}
}
