package com.prcmind.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.CookieUtil;
import com.prcmind.utils.HttpClientUtil;
import com.prcmind.view.LoginSucceedView;
import com.prcmind.view.MedicView;

/**
 * 医生控制层
 * @author leichang
 *
 */
@Controller
public class MedicController {

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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/oauth/token", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		LoginSucceedView view = JSON.toJavaObject(jsonObj, LoginSucceedView.class);
		CookieUtil.addCookie(request, response, "token", view.getAccess_token(), view.getExpires_in());
		return new CodeMsgBean<Object>(1, "操作成功", view);
	}

	@RequestMapping(value = "/web/v1/medic/getInformation", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> getInformation(HttpServletRequest request, String access_token) throws IOException {
//		Cookie cookie = CookieUtil.getCookieByName(request, "token");
//		if (StringUtils.isEmpty(cookie)) {
//			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
//		}
		String result = HttpClientUtil
				.get("https://api.prcmind.cn:1600/medic/getInformation?access_token=" + access_token);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		MedicView view = JSON.toJavaObject(jsonObj, MedicView.class);
		return new CodeMsgBean<Object>(1, "操作成功", view);
	}

	@RequestMapping(value = "/web/v1/medic/listArticle", method = RequestMethod.POST)
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medic/listArticle", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}

	@RequestMapping(value = "/web/v1/medic/updateLoginPwd", method = RequestMethod.POST)
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
		param.put("newPassword", oldPassword);
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medic/updateLoginPwd", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}

	@RequestMapping(value = "/web/v1/medic/findMedicLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> findMedicLoginPwd(String loginName, String realName, String cardNo,
			HttpServletRequest request, String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medic/findMedicLoginPwd", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}

	@RequestMapping(value = "/web/v1/medic/listCertificate", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listCertificate(HttpServletRequest request, String access_token) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medic/listCertificate", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}

	@RequestMapping(value = "/web/v1/medic/listMedicScaleDosage", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMedicScaleDosage(HttpServletRequest request, String access_token)
			throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medic/listMedicScaleDosage", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}

	@RequestMapping(value = "/web/v1/medic/getMedicScaleDosageByScaleNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMedicScaleDosageByScaleNo(HttpServletRequest request, String access_token,
			String scaleNo) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(scaleNo)) {
			return new CodeMsgBean<Object>(10002, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("scaleNo", scaleNo);
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medic/getMedicScaleDosageByScaleNo", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	
	@RequestMapping(value = "/web/v1/medic/listArticle", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listArticle(HttpServletRequest request, String access_token,
			int pageNum,int numPerPage) throws IOException {
		// Cookie cookie=CookieUtil.getCookieByName(request, "token");
		// if (StringUtils.isEmpty(cookie)) {
		// return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		// }
		if (StringUtils.isEmpty(pageNum)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("pageNum", pageNum+"");
		param.put("numPerPage", pageNum+"");
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medic/listArticle", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/medic/getArticle", method = RequestMethod.POST)
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medic/getArticle", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
}
