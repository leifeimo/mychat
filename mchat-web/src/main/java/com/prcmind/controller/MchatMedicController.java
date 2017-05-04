package com.prcmind.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.HttpClientUtil;
import com.prcmind.view.req.RecordReq;

@Controller
public class MchatMedicController {
	
//	public static String access_token = "e9db7ad0-6eb0-4e83-b278-5641572e3e79";
	
	@RequestMapping(value = "/web/v1/medicMchat/nationwideSearch", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> nationwideSearch(String pageNum, String numPerPage,String testeeName,String cardNo,HttpServletRequest request) throws IOException {
		String access_token = getSession(request);
		if (StringUtils.isEmpty(access_token)) {
			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(numPerPage) || StringUtils.isEmpty(cardNo)|| StringUtils.isEmpty(testeeName)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("pageNum", pageNum);
		param.put("numPerPage", numPerPage);
		param.put("testeeName", pageNum);
		param.put("cardNo", numPerPage);
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medicMchat/listArticle", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		
		return new CodeMsgBean<Object>(1, "操作成功", null);
	}
	
	@RequestMapping(value = "/web/v1/medicMchat/listMchatScoreUnique", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScoreUnique(RecordReq req,HttpServletRequest request) throws IOException {
		String access_token = getSession(request);
		if (StringUtils.isEmpty(access_token)) {
			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(req.getPageNum()) || StringUtils.isEmpty(req.getNumPerPage())) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("pageNum", req.getPageNum()+"");
		param.put("numPerPage", req.getNumPerPage()+"");
		param.put("testeeName", req.getTesteeName());
		param.put("reportNo", req.getReportNo());
		param.put("cardNo", req.getCardNo());
		param.put("birthYear", req.getBirthYear()+"");
		param.put("birthMonth", req.getBirthMonth()+"");
		param.put("birthToday", req.getBirthToday()+"");
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medicMchat/listMchatScoreUnique", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	
	@RequestMapping(value = "/web/v1/medicMchat/listMchatScore", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScore(RecordReq req,HttpServletRequest request) throws IOException {
		String access_token = getSession(request);
		if (StringUtils.isEmpty(access_token)) {
			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(req.getPageNum()) || StringUtils.isEmpty(req.getNumPerPage())) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("pageNum", req.getPageNum()+"");
		param.put("numPerPage", req.getNumPerPage()+"");
		param.put("testeeName", req.getTesteeName());
		param.put("reportNo", req.getReportNo());
		param.put("deleted", req.getDeleted()+"");
		param.put("birthYear", req.getBirthYear()+"");
		param.put("birthMonth", req.getBirthMonth()+"");
		param.put("birthToday", req.getBirthToday()+"");
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medicMchat/listMchatScore", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	
	@RequestMapping(value = "/web/v1/medicMchat/deleteReportByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> deleteReportByScoreNo(String scoreNo,HttpServletRequest request) throws IOException {
		String access_token = getSession(request);
		if (StringUtils.isEmpty(access_token)) {
			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("scoreNo", scoreNo);
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medicMchat/deleteReportByScoreNo", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/medicMchat/getMchatScoreByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatScoreByScoreNo(String scoreNo,HttpServletRequest request) throws IOException {
		String access_token = getSession(request);
		if (StringUtils.isEmpty(access_token)) {
			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("scoreNo", scoreNo);
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medicMchat/getMchatScoreByScoreNo", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/medicMchat/getMchatQuestionnaireResponse", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatQuestionnaireResponse(String scoreNo,HttpServletRequest request) throws IOException {
		String access_token = getSession(request);
		if (StringUtils.isEmpty(access_token)) {
			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("scoreNo", scoreNo);
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/medicMchat/getMchatQuestionnaireResponse", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/medicMchat/downloadReport", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> downloadReport(String scoreNo,HttpServletRequest request) throws IOException {
		String access_token = getSession(request);
		if (StringUtils.isEmpty(access_token)) {
			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("scoreNo", scoreNo);
		param.put("access_token", access_token);
		String result = HttpClientUtil.get("https://api.prcmind.cn:1600/medicMchat/downloadReport?scoreNo="+scoreNo+"&access_token="+access_token);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", null);
	}
	
	private String getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String access_token =(String) session.getAttribute("token");
		return access_token;
	}
}
