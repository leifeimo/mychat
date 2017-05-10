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
import com.prcmind.facade.portal.mchat.service.PortalMchatMedicFacade;
import com.prcmind.facade.scale.mchat.entity.MchatQuestionnaireResponse;
import com.prcmind.facade.scale.mchat.entity.MchatScore;
import com.prcmind.facade.user.entity.MedicInfo;
import com.prcmind.facade.user.entity.MedicOperator;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.WebConstants;
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
	 * @param access_token
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/nationwideSearch", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> nationwideSearch(int pageNum, int numPerPage, String testeeName, String cardNo,
			HttpServletRequest request, String access_token) throws IOException {
		if (pageNum == 0  ||  numPerPage == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		PageParam pageParam = new PageParam(pageNum, numPerPage);
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("testeeName", testeeName);
			paramMap.put("cardNo", cardNo);
			PageBean PageBean = portalMchatMedicFacade.listNationwideSearch(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", PageBean);
		} catch (PortalBizException e) {
			
			
			
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
		MedicInfo info=(MedicInfo) session.getAttribute(WebConstants.MEDIC_INFO);
		String enterpriseNo=null;
		String medicNo=null;
		if(info !=null){
			enterpriseNo=info.getEnterpriseNo();
			medicNo=info.getMedicNo();
		}else{
			enterpriseNo="20252a32e38c44f9ac02ca623f4ee503";
			medicNo="937c2b21d3db406693c59a816614e26d";
//			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
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
			paramMap.put("reportNo", enterpriseNo);
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
	 * @param access_token
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/listMchatScore", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScore(RecordReq req, HttpServletRequest request, String access_token)
			throws IOException {
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
			medicNo = "937c2b21d3db406693c59a816614e26d";
			// return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
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
	 * 获取某一问卷填写详细
	 * 
	 * @author leichang
	 * @param scoreNo
	 * @param request
	 * @param access_token
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
			medicNo = "937c2b21d3db406693c59a816614e26d";
			// return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatQuestionnaireResponse result = portalMchatMedicFacade.getMchatQuestionnaireResponse(scoreNo, medicNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 下载报告结果
	 * 
	 * @author leichang
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/downloadReport", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> downloadReport(String scoreNo, HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
			medicNo = "937c2b21d3db406693c59a816614e26d";
			// return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatScore result = portalMchatMedicFacade.downloadReport(scoreNo, medicNo);
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

}
