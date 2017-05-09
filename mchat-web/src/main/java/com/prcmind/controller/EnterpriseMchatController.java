package com.prcmind.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prcmind.common.page.PageBean;
import com.prcmind.common.page.PageParam;
import com.prcmind.facade.portal.exception.PortalBizException;
import com.prcmind.facade.portal.mchat.service.PortalMchatEnterpriseFacade;
import com.prcmind.facade.scale.mchat.entity.MchatQuestionnaireResponse;
import com.prcmind.facade.scale.mchat.entity.MchatScore;
import com.prcmind.facade.user.entity.EnterpriseOperator;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.WebConstants;
import com.prcmind.view.req.RecordReq;

/**
 * mchat-管理员控制层
 * @author leichang
 *
 */
@Controller
public class EnterpriseMchatController {
	@Autowired
	PortalMchatEnterpriseFacade portalMchatEnterpriseFacade;
	

	/**
	 * 下载报告结果
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterpriseMchat/downloadReport", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> downloadReport(String scoreNo,HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
		return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo =getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatScore result=portalMchatEnterpriseFacade.downloadReport(scoreNo, enterpriseNo);
			return new CodeMsgBean<Object>(1, "操作成功",result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}
	
	

	/**
	 *  获取某一问卷填写详细
	 * @param scoreNo
	 * @param request
	 * @param access_token
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterpriseMchat/getMchatQuestionnaireResponse", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatQuestionnaireResponse(String scoreNo,HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo =getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatQuestionnaireResponse result=portalMchatEnterpriseFacade.getMchatQuestionnaireResponse(scoreNo, enterpriseNo);
			return new CodeMsgBean<Object>(1, "操作成功",result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}
	
	/**
	 * 管理员-查询所有报告列表
	 * @author leichang
	 * @param req
	 * @param request
	 * @param access_token
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterpriseMchat/listMchatScoreListPage", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScoreListPage(RecordReq req,HttpServletRequest request) throws IOException {
		if (req.getPageNum() == 0 || req.getNumPerPage() == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HashMap<String, String> map = null;
		if (!StringUtils.isEmpty(req.getBirth())) {
			map = initBirthMap(req.getBirth());
			if (map != null && map.size() != 3) {
				return new CodeMsgBean<Object>(10003, "参数异常,请检查出生日期是否正确");
			}
		}
		PageParam pageParam = new PageParam(req.getPageNum(), req.getNumPerPage());
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pageNum", req.getPageNum() + "");
			paramMap.put("numPerPage", req.getNumPerPage() + "");
			paramMap.put("testeeName", req.getTesteeName());
			paramMap.put("reportNo", req.getReportNo());
			paramMap.put("cardNo", req.getCardNo());
			paramMap.put("birthYear", map != null ? map.get("birthYear") : "");
			paramMap.put("birthMonth", map != null ? map.get("birthMonth") : "");
			paramMap.put("birthToday", map != null ? map.get("birthToday") : "");
			paramMap.put("deleted", req.getDeleted());

			PageBean PageBean = portalMchatEnterpriseFacade.listMchatScoreListPage(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", PageBean);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}
	
	
	@RequestMapping(value = "/web/v1/enterpriseMchat/getMchatScoreByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatScoreByScoreNo(String scoreNo,HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			MchatScore mchatScore=portalMchatEnterpriseFacade.getMchatScoreByScoreNo(scoreNo);
			return new CodeMsgBean<Object>(1, "操作成功",mchatScore);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}
	
	/**
	 * 获取企业编号
	 * 
	 * @param request
	 * @return
	 */
	private String getEnterpriseNo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		EnterpriseOperator eo = (EnterpriseOperator) session.getAttribute(WebConstants.MANAGER_USER);
		if (eo != null) {
			return eo.getEnterpriseNo();
		}
		return null;
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param birth
	 * @return
	 */
	private HashMap<String, String> initBirthMap(String birth) {
		HashMap<String, String> birthMap = new HashMap<String, String>();
		String[] array = birth.split("-");
		if (array.length == 3) {
			birthMap.put("birthYear", array[0]);
			birthMap.put("birthMonth", array[1]);
			birthMap.put("birthToday", array[2]);
		}
		return birthMap;
	}

}
