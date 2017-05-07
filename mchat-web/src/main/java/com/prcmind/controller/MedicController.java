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
import com.prcmind.listener.SessionAttributeListener;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.CookieUtil;
import com.prcmind.utils.HttpClientUtil;
import com.prcmind.utils.WebConstants;
import com.prcmind.view.BaseUserView;
import com.prcmind.view.LoginSucceedView;
import com.prcmind.view.MedicView;

/**
 * 施测者(医生)控制层
 * @author leichang
 *
 */
@Controller
public class MedicController {
	private  ResourceBundle resource = ResourceBundle.getBundle("mchat-config");
	private  String API_URL = resource.getString("api-url");  
	
	@RequestMapping(value = "/web/v1/medic/oauth-server/oauth/token", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("client_id", "medic-client");
		param.put("client_secret", "medic");
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

	@RequestMapping(value = "/web/v1/medic/getInformation", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> getInformation(HttpServletRequest request, String access_token) throws IOException {
//		Cookie cookie = CookieUtil.getCookieByName(request, "token");
//		if (StringUtils.isEmpty(cookie)) {
//			return new CodeMsgBean<Object>(10002, "鐧诲綍澶辨晥锛岃閲嶆柊鐧诲綍");
//		}
		HttpSession session=SessionAttributeListener.sessionMap.get("test002@qq.com ");
		if(session !=null){
			BaseUserView user =(BaseUserView) session.getAttribute(WebConstants.CURRENT_USER);
			System.out.println(user);
		}
		
		MedicView view=null;
		try {
			String result = HttpClientUtil
					.get(API_URL+"/medic/getInformation?access_token=" + access_token);
			JSONObject jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) { 
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
			view= JSON.toJavaObject(jsonObj, MedicView.class);
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005, e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", view);
	}

	

	@RequestMapping(value = "/web/v1/medic/updateLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> updateLoginPwd(String oldPassword, String newPassword, HttpServletRequest request,
			String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "鐧诲綍澶辨晥锛岃閲嶆柊鐧诲綍");
		// }
		if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("oldPassword", oldPassword);
		param.put("newPassword", oldPassword);
		param.put("access_token", access_token);
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/medic/updateLoginPwd", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}

	@RequestMapping(value = "/web/v1/medic/findMedicLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> findMedicLoginPwd(String loginName, String realName, String cardNo,
			HttpServletRequest request, String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "鐧诲綍澶辨晥锛岃閲嶆柊鐧诲綍");
		// }
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(realName) || StringUtils.isEmpty(cardNo)
				|| StringUtils.isEmpty(access_token)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("loginName", loginName);
		param.put("realName", realName);
		param.put("cardNo", cardNo);
		param.put("access_token", access_token);
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/medic/findMedicLoginPwd", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}

	@RequestMapping(value = "/web/v1/medic/listCertificate", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listCertificate(HttpServletRequest request, String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "鐧诲綍澶辨晥锛岃閲嶆柊鐧诲綍");
		// }
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("access_token", access_token);
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/medic/listCertificate", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}

	@RequestMapping(value = "/web/v1/medic/listMedicScaleDosage", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> listMedicScaleDosage(HttpServletRequest request, String access_token)
			throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "鐧诲綍澶辨晥锛岃閲嶆柊鐧诲綍");
		// }
		JSONObject jsonObj =null;
		String result=null;
		try {
			result= HttpClientUtil.get(API_URL+"/medic/listMedicScaleDosage?access_token="+ access_token);
			jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (Exception e) {
			JSONArray arr=JSON.parseArray(result);
			return new CodeMsgBean<Object>(1,"操作成功",arr);
		}
	}

	@RequestMapping(value = "/web/v1/medic/getMedicScaleDosageByScaleNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMedicScaleDosageByScaleNo(HttpServletRequest request, String access_token,
			String scaleNo) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "鐧诲綍澶辨晥锛岃閲嶆柊鐧诲綍");
		// }
		if (StringUtils.isEmpty(scaleNo)) {
			return new CodeMsgBean<Object>(10002, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("scaleNo", scaleNo);
		param.put("access_token", access_token);
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/medic/getMedicScaleDosageByScaleNo", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	
	@RequestMapping(value = "/web/v1/medic/listArticle", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listArticle(HttpServletRequest request, String access_token,
			int pageNum,int numPerPage) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "鐧诲綍澶辨晥锛岃閲嶆柊鐧诲綍");
		// }
		if (StringUtils.isEmpty(pageNum)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("pageNum", pageNum+"");
		param.put("numPerPage", numPerPage+"");
		param.put("access_token", access_token);
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.post(API_URL+"/medic/listArticle", param);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/medic/getArticle", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> getArticle(HttpServletRequest request, String access_token,
			String id) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "鐧诲綍澶辨晥锛岃閲嶆柊鐧诲綍");
		// }
		if (StringUtils.isEmpty(id)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		param.put("access_token", access_token);
		JSONObject jsonObj =null;
		try {
			String result = HttpClientUtil.get(API_URL+"/medic/getArticle?id="+id+"&access_token="+access_token);
			 jsonObj = JSON.parseObject(result);
			if (jsonObj.containsKey("error")) {
				return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
			}
		} catch (Exception e) {
			return new CodeMsgBean<Object>(10005,e.getMessage());
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	
	@RequestMapping(value = "/web/v1/medic/token/logout", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> logout( HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		return new CodeMsgBean<Object>(1, "操作成功");
	}
}
