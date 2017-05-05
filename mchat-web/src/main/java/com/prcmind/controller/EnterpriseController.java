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
 * 管理员控制层
 * @author leichang
 *
 */
@Controller
public class EnterpriseController {

	@RequestMapping(value = "/web/v1/enterprise/oauth-server/oauth/token", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/web/v1/enterprise/getInformation", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> getInformation(HttpServletRequest request, String access_token) throws IOException {
//		Cookie cookie = CookieUtil.getCookieByName(request, "token");
//		if (StringUtils.isEmpty(cookie)) {
//			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
//		}
		String result = HttpClientUtil
				.get("https://api.prcmind.cn:1600/enterprise/getInformation?access_token=" + access_token);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		MedicView view = JSON.toJavaObject(jsonObj, MedicView.class);
		return new CodeMsgBean<Object>(1, "操作成功", view);
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/getMedicScaleDosageByMedicNo", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/listEnterpriseScaleDosage", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/enterprise/getArticle", method = RequestMethod.POST)
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/getArticle", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/listArticle", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/getMedicInfoByMedicNo", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
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
		param.put("newPassword", oldPassword);
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/updateLoginPwd", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/dismissUser", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/boundMedic", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/user/findEnterpriseLoginPwd", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/listMedic", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
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
		String result = HttpClientUtil
				.get("https://api.prcmind.cn:1600/enterprise/listAllScaleProducts?access_token=" + access_token);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		MedicView view = JSON.toJavaObject(jsonObj, MedicView.class);
		return new CodeMsgBean<Object>(1, "操作成功", view);
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterprise/user/findLoginPwd", param);
		System.out.println(result);
		JSONObject jsonObj = JSON.parseObject(result);
		if (jsonObj.containsKey("error")) {
			return new CodeMsgBean<Object>(10004, jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
}
