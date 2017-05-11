package com.prcmind.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prcmind.common.exceptions.BizException;
import com.prcmind.common.page.PageBean;
import com.prcmind.common.page.PageParam;
import com.prcmind.facade.portal.exception.PortalBizException;
import com.prcmind.facade.portal.mchat.service.PortalMchatMedicFacade;
import com.prcmind.facade.scale.mchat.entity.MchatQuestionnaireResponse;
import com.prcmind.facade.scale.mchat.entity.MchatQuestionnaireResponseRevisedFollow;
import com.prcmind.facade.scale.mchat.entity.MchatScore;
import com.prcmind.facade.scale.mchat.entity.MchatScoreRevisedFollow;
import com.prcmind.facade.user.entity.MedicInfo;
import com.prcmind.facade.user.entity.MedicOperator;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.WebConstants;
import com.prcmind.view.req.FollowReq;
import com.prcmind.view.req.RecordReq;

/**
 * mchat-施测者(医生)控制层
 * 
 * @author leichang
 *
 */
@Controller
public class MchatMedicController {
	@Autowired
	PortalMchatMedicFacade portalMchatMedicFacade;

	/**
	 * 施测者-全国查询
	 * 
	 * @author leichang
	 * @param pageNum
	 * @param numPerPage
	 * @param testeeName
	 * @param cardNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/nationwideSearch", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> nationwideSearch(int pageNum, int numPerPage, String testeeName, String cardNo,
			HttpServletRequest request) throws IOException {
		if (pageNum == 0 || numPerPage == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		PageParam pageParam = new PageParam(pageNum, numPerPage);
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("testeeName", testeeName);
			paramMap.put("cardNo", cardNo);
			PageBean PageBean = portalMchatMedicFacade.listNationwideSearch(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", PageBean);
		} catch (BizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 施测者-儿童档案查询
	 * 
	 * @author leichang
	 * @param req
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/listMchatScoreUnique", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScoreUnique(RecordReq req, HttpServletRequest request) throws IOException {
		if (req.getPageNum() == 0 || req.getNumPerPage() == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HttpSession session = request.getSession();
		MedicInfo info = (MedicInfo) session.getAttribute(WebConstants.MEDIC_INFO);
		String enterpriseNo = null;
		String medicNo = null;
		if (info != null) {
			enterpriseNo = info.getEnterpriseNo();
			medicNo = info.getMedicNo();
		} else {
		

			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
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
			paramMap.put("enterpriseNo", enterpriseNo);
			paramMap.put("medicNo", medicNo);
			paramMap.put("birthYear", map != null ? map.get("birthYear") : "");
			paramMap.put("birthMonth", map != null ? map.get("birthMonth") : "");
			paramMap.put("birthToday", map != null ? map.get("birthToday") : "");
			PageBean PageBean = portalMchatMedicFacade.listMchatScoreUniqueListPage(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", PageBean);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 施测者-查询所有报告列表
	 * 
	 * @author leichang
	 * @param req
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/listMchatScore", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScore(RecordReq req, HttpServletRequest request) throws IOException {
		if (req.getPageNum() == 0 || req.getNumPerPage() == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HttpSession session = request.getSession();
		MedicInfo info = (MedicInfo) session.getAttribute(WebConstants.MEDIC_INFO);
		String enterpriseNo = null;
		String medicNo = null;
		if (info != null) {
			enterpriseNo = info.getEnterpriseNo();
			medicNo = info.getMedicNo();
		} else {
		

			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
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
			paramMap.put("enterpriseNo", enterpriseNo);
			paramMap.put("medicNo", medicNo);
			paramMap.put("birthYear", map != null ? map.get("birthYear") : "");
			paramMap.put("birthMonth", map != null ? map.get("birthMonth") : "");
			paramMap.put("birthToday", map != null ? map.get("birthToday") : "");
			paramMap.put("deleted", req.getDeleted());

			PageBean PageBean = portalMchatMedicFacade.listMchatScoreListPage(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", PageBean);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	/**
	 * 作废报告
	 * 
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/deleteReportByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> deleteReportByScoreNo(String scoreNo, HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {

			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			long status = portalMchatMedicFacade.deleteReportByMedicNoAndScoreNo(medicNo, scoreNo);
			return new CodeMsgBean<Object>(1, "操作成功", status);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 获取某一条报告记录
	 * 
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/getMchatScoreByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatScoreByScoreNo(String scoreNo, HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			MchatScore mchatScore = portalMchatMedicFacade.getMchatScoreByScoreNo(scoreNo);
			return new CodeMsgBean<Object>(1, "操作成功", mchatScore);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 获取某一问R报告卷填写详细
	 * 
	 * @author leichang
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/getMchatQuestionnaireResponse", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatQuestionnaireResponse(String scoreNo, HttpServletRequest request)
			throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {

			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatQuestionnaireResponse result = portalMchatMedicFacade.getMchatQuestionnaireResponse(scoreNo, medicNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 下载R报告结果
	 * 
	 * @author leichang
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/downloadReport", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> downloadReport(String scoreNo, HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {

			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatScore result = portalMchatMedicFacade.downloadReport(scoreNo, medicNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 验证基本信息
	 * 
	 * @author leichang
	 * @param mchatScore
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/verifyBasicInformation", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> verifyBasicInformation(MchatScore mchatScore, HttpServletRequest request)
			throws IOException {
		if (mchatScore == null) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HttpSession session = request.getSession();
		MedicInfo info = (MedicInfo) session.getAttribute(WebConstants.MEDIC_INFO);
		String enterpriseNo = null;
		String medicNo = null;
		if (info != null) {
			enterpriseNo = info.getEnterpriseNo();
			medicNo = info.getMedicNo();
		} else {
		

			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		mchatScore.setEnterpriseNo(enterpriseNo);
		mchatScore.setMedicNo(medicNo);
		try {
			boolean bl = portalMchatMedicFacade.verifyBasicInformation(mchatScore);
			return new CodeMsgBean<Object>(1, "操作成功", bl);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 获取某一问R/F报告卷填写详细
	 * 
	 * @author leichang
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/getMchatQuestionnaireResponseRevisedFollow", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatQuestionnaireResponseRevisedFollow(String scoreNo, HttpServletRequest request)
			throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {

			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatQuestionnaireResponseRevisedFollow result = portalMchatMedicFacade
					.getMchatQuestionnaireResponseRevisedFollow(scoreNo, medicNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 下载R/F报告结果
	 * 
	 * @author leichang
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/downloadRevisedFollowReport", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> downloadRevisedFollowReport(String scoreNo, HttpServletRequest request)
			throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {

			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatScoreRevisedFollow result = portalMchatMedicFacade.downloadRevisedFollowReport(scoreNo, medicNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 创建R报告
	 * 
	 * @author leichang
	 * @param mchatScore
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/createMchatReport", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> createMchatReport(MchatScore mchatScore,
			MchatQuestionnaireResponse mchatQuestionnaireResponse, HttpServletRequest request) throws IOException {
		if (mchatScore == null) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HttpSession session = request.getSession();
		MedicInfo info = (MedicInfo) session.getAttribute(WebConstants.MEDIC_INFO);
		String enterpriseNo = null;
		String medicNo = null;
		if (info != null) {
			enterpriseNo = info.getEnterpriseNo();
			medicNo = info.getMedicNo();
		} else {
		

			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		mchatScore.setEnterpriseNo(enterpriseNo);
		mchatScore.setMedicNo(medicNo);
		try {
			Map<String, String> result = portalMchatMedicFacade.createMchatScore(mchatScore,
					mchatQuestionnaireResponse);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 施测者-查询R/F所有报告列表
	 * 
	 * @author leichang
	 * @param mchatScore
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/listMchatScoreRevisedFollow", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScoreRevisedFollow(FollowReq req, HttpServletRequest request)
			throws IOException {
		if (req.getPageNum() == 0 || req.getNumPerPage() == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HttpSession session = request.getSession();
		MedicInfo info = (MedicInfo) session.getAttribute(WebConstants.MEDIC_INFO);
		String enterpriseNo = null;
		if (info != null) {
			enterpriseNo = info.getEnterpriseNo();
		} else {
		
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		PageParam pageParam = new PageParam(req.getPageNum(), req.getNumPerPage());
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pageNum", req.getPageNum() + "");
			paramMap.put("numPerPage", req.getNumPerPage() + "");
			paramMap.put("testeeName", req.getTesteeName());
			paramMap.put("reportNo", req.getReportNo());
			paramMap.put("testeeNo", req.getTesteeNo());
			 paramMap.put("enterpriseNo", enterpriseNo);
//			 paramMap.put("medicNo", medicNo);
			paramMap.put("deleted", req.getDeleted());
			PageBean result = portalMchatMedicFacade.listMchatScoreRevisedFollowListPage(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 *  创建MChat R/F报告
	 * @param scoreNo
	 * @param mchatQuestionnaireResponseRevisedFollow
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/createMchatRevisedFollowReport", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> createMchatRevisedFollowReport(String scoreNo,
			MchatQuestionnaireResponseRevisedFollow mchatQuestionnaireResponseRevisedFollow, HttpServletRequest request) throws IOException {
		if (mchatQuestionnaireResponseRevisedFollow == null || StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String ip = getIp(request);
		try {
			Map<String, String> result = portalMchatMedicFacade.createMchatScoreRevisedFollow(scoreNo, ip, mchatQuestionnaireResponseRevisedFollow);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}
	
	/**
	 * 删除R/F报告
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/deleteRevisedFollowReportByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> deleteRevisedFollowReportByScoreNo(String scoreNo,
			 HttpServletRequest request) throws IOException {
		if ( StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HttpSession session = request.getSession();
		MedicInfo info = (MedicInfo) session.getAttribute(WebConstants.MEDIC_INFO);
		String enterpriseNo = null;
		String medicNo = null;
		if (info != null) {
			enterpriseNo = info.getEnterpriseNo();
			medicNo = info.getMedicNo();
		} else {
		

			 return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			long result=portalMchatMedicFacade.deleteRevisedFollowReportByMedicNoAndScoreNo(medicNo, scoreNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}
	
	
	/**
	 *  获取某一条RF报告记录
	 *  @author leichang
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/getMchatScoreRevisedFollowByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatScoreRevisedFollowByScoreNo(String scoreNo,
			 HttpServletRequest request) throws IOException {
		if ( StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			MchatScoreRevisedFollow result=portalMchatMedicFacade.getMchatScoreRevisedFollowByScoreNo(scoreNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}
	
	/**
	 * 获取用户编号
	 * 
	 * @param request
	 * @return
	 */
	private String getMedicNo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MedicOperator mo = (MedicOperator) session.getAttribute(WebConstants.CURRENT_USER);
		if (mo != null) {
			return mo.getMedicNo();
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
	private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");  
	    if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip))  
	    {  
	        return ip;  
	    }  
	    ip = request.getHeader("X-Forwarded-For");  
	    if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip))  
	    {  
	        // 多次反向代理后会有多个IP值，第一个为真实IP。  
	        int index = ip.indexOf(',');  
	        if (index != -1)  
	        {  
	            return ip.substring(0, index);  
	        }  
	        else  
	        {  
	            return ip;  
	        }  
	    }  
	    else  
	    {  
	        return request.getRemoteAddr();  
	    }  
	}
}
