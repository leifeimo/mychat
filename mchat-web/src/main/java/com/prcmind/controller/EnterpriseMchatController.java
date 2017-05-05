package com.prcmind.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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

/**
 * mchat-管理员控制层
 * @author leichang
 *
 */
@Controller
public class EnterpriseMchatController {

	

	@RequestMapping(value = "/web/v1/enterpriseMchat/downloadReport", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> downloadReport(String scoreNo,HttpServletRequest request, String access_token) throws IOException {
//		Cookie cookie=CookieUtil.getCookieByName(request, "token");
//		if (StringUtils.isEmpty(cookie)) {
//			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
//		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("scoreNo", scoreNo);
		param.put("access_token", access_token);
		String result = HttpClientUtil.get("https://api.prcmind.cn:1600/enterpriseMchat/downloadReport?scoreNo="+scoreNo+"&access_token="+access_token);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", null);
	}
	

	@RequestMapping(value = "/web/v1/enterpriseMchat/getMchatQuestionnaireResponse", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatQuestionnaireResponse(String scoreNo,HttpServletRequest request, String access_token) throws IOException {
//		Cookie cookie=CookieUtil.getCookieByName(request, "token");
//		if (StringUtils.isEmpty(cookie)) {
//			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
//		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("scoreNo", scoreNo);
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterpriseMchat/getMchatQuestionnaireResponse", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	@RequestMapping(value = "/web/v1/enterpriseMchat/listMchatScoreListPage", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScoreListPage(RecordReq req,HttpServletRequest request, String access_token) throws IOException {
//		Cookie cookie=CookieUtil.getCookieByName(request, "token");
//		if (StringUtils.isEmpty(cookie)) {
//			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
//		}
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
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterpriseMchat/listMchatScore", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
	
	
	@RequestMapping(value = "/web/v1/enterpriseMchat/getMchatScoreByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatScoreByScoreNo(String scoreNo,HttpServletRequest request, String access_token) throws IOException {
//		Cookie cookie=CookieUtil.getCookieByName(request, "token");
//		if (StringUtils.isEmpty(cookie)) {
//			return new CodeMsgBean<Object>(10002, "登录失效，请重新登录");
//		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("scoreNo", scoreNo);
		param.put("access_token", access_token);
		String result = HttpClientUtil.post("https://api.prcmind.cn:1600/enterpriseMchat/getMchatScoreByScoreNo", param);
		JSONObject jsonObj = JSON.parseObject(result);
		if(jsonObj.containsKey("error")){
			return new CodeMsgBean<Object>(10004,jsonObj.getString("error_description"));
		}
		return new CodeMsgBean<Object>(1, "操作成功", jsonObj);
	}
}
