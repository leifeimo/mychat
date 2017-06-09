package com.prcmind.controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.prcmind.utils.DateUtil;
import com.prcmind.utils.ExportPdfUtil;
import com.prcmind.utils.RunnerUtils;
import com.prcmind.utils.WebConstants;
import com.prcmind.view.MchatScoreView;
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
	private static ResourceBundle resource = ResourceBundle.getBundle("mchat-config");
	private static String OUT_PATH = resource.getString("out_path");

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
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			 return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
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
			// enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		Map<String, Integer> birthMap = null;
		Map<String, Integer> testMap = null;
		if (!StringUtils.isEmpty(req.getBirth())) {
			birthMap = initBirthMap(req.getBirth(), "birthYear", "birthMonth", "birthToday");
			if (birthMap != null && birthMap.size() != 3) {
				return new CodeMsgBean<Object>(10003, "参数异常,请检查出生日期是否正确");
			}
		}
		if (!StringUtils.isEmpty(req.getTestDate())) {
			testMap = initBirthMap(req.getTestDate(), "testYear", "testMonth", "testToday");
			if (testMap != null && testMap.size() != 3) {
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
			paramMap.put("birthYear", birthMap != null ? birthMap.get("birthYear") : "");
			paramMap.put("birthMonth", birthMap != null ? birthMap.get("birthMonth") : "");
			paramMap.put("birthToday", birthMap != null ? birthMap.get("birthToday") : "");
			paramMap.put("testYear", testMap != null ? testMap.get("testYear") : "");
			paramMap.put("testMonth", testMap != null ? testMap.get("testMonth") : "");
			paramMap.put("testToday", testMap != null ? testMap.get("testToday") : "");
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
			// enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		Map<String, Integer> birthMap = null;
		Map<String, Integer> testMap = null;
		if (!StringUtils.isEmpty(req.getBirth())) {
			birthMap = initBirthMap(req.getBirth(), "birthYear", "birthMonth", "birthToday");
			if (birthMap != null && birthMap.size() != 3) {
				return new CodeMsgBean<Object>(10003, "参数异常,请检查出生日期是否正确");
			}
		}
		if (!StringUtils.isEmpty(req.getTestDate())) {
			testMap = initBirthMap(req.getTestDate(), "testYear", "testMonth", "testToday");
			if (testMap != null && testMap.size() != 3) {
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
			paramMap.put("birthYear", birthMap != null ? birthMap.get("birthYear") : "");
			paramMap.put("birthMonth", birthMap != null ? birthMap.get("birthMonth") : "");
			paramMap.put("birthToday", birthMap != null ? birthMap.get("birthToday") : "");
			paramMap.put("testYear", testMap != null ? testMap.get("testYear") : "");
			paramMap.put("testMonth", testMap != null ? testMap.get("testMonth") : "");
			paramMap.put("testToday", testMap != null ? testMap.get("testToday") : "");
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
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		final String medic_no = medicNo;
		final String score_no = scoreNo;
		try {
			RunnerUtils.submit(new Runnable() {
				@Override
				public void run() {
					portalMchatMedicFacade.deleteReportByMedicNoAndScoreNo(medic_no, score_no);
				}
			});
			return new CodeMsgBean<Object>(1, "操作成功");
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
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			 return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
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
			// medicNo = "937c2b21d3db406693c59a816614e26d";
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
	public CodeMsgBean<Object> downloadReport(String scoreNo, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatScore result = portalMchatMedicFacade.downloadReport(scoreNo, medicNo);
			if (result != null) {
				Map<String, String> content = initMap(null, result);
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition", "attachment; filename="+result.getReportNo()+".pdf");
				String path = "";
				if(result.getScore() != null){
					if (result.getScore() <= 2) {
						path = request.getSession().getServletContext().getRealPath("template\\A.pdf") ;
					} else if (result.getScore() >= 3 && result.getScore() <= 7) {
						path = request.getSession().getServletContext().getRealPath("template\\B.pdf") ;
					} else if (result.getScore() >= 8 && result.getScore() <= 20) {
						path = request.getSession().getServletContext().getRealPath("template\\C.pdf") ;
					}
				}
				ExportPdfUtil.exportpdf(OUT_PATH, path, content, response);
			}
			return new CodeMsgBean<Object>(1, "操作成功",result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			return new CodeMsgBean<Object>(10005, "操作失败");
		}
	}

	private Map<String, String> initMap(MchatScoreRevisedFollow mchatScoreRevisedFollow, MchatScore result) {
		Map<String, String> content = new HashMap<String, String>();
		content.put("name", result.getTesteeName());// 根据模板定义的输入域的名字（如：name），填充值
		content.put("sex", result.getSex() == 0 ? "男" : "女");
		String birthDate = result.getBirthYear() + "-" + result.getBirthMonth() + "-" + result.getBirthToday();
		content.put("birthDate", birthDate);
		// 缺少日期格式化类
		if (mchatScoreRevisedFollow == null) {
			String createTime = DateUtil.DateToStr(result.getCreateTime(), "yyyy-MM-dd");
			content.put("createTime", createTime);
			content.put("score", result.getScore() + "");
		} else {
			String createTime = DateUtil.DateToStr(mchatScoreRevisedFollow.getCreateTime(), "yyyy-MM-dd");
			content.put("createTime", createTime);
			content.put("r_score", result.getScore() + "");
			content.put("r_f_score", mchatScoreRevisedFollow.getScore() + "");
		}
		content.put("enterpriseName", result.getEnterpriseName());
		content.put("medicName", result.getMedicName());
		if(result.getGestationalWeeks() != 0){
			String gestationalWeeks = result.getGestationalWeeks() + "周"
					+ (result.getGestationalDays() == null ? "0" : result.getGestationalDays() + "天");
			content.put("gestationalWeeks", gestationalWeeks);
		}
		if(result.getLifeMonth() !=null && result.getLifeDay()!=null){
			content.put("age", result.getLifeMonth()+"月"+result.getLifeDay()+"天");
		}
		

		if (!StringUtils.isEmpty(result.getBirths())) {
			String births = result.getBirths();
			if (births.startsWith(",") || births.startsWith(";")) {
				births = births.substring(1, births.length());
			}
			if (births.endsWith(",") || births.endsWith(";")) {
				births = births.substring(0, births.length() - 1);
			}
			String[] arr = births.split(";");
			String birthsResult = "";
			for (int i = 0; i < arr.length; i++) {
				birthsResult += birthConvert(Integer.valueOf(arr[i])) + ";";
			}
			birthsResult = birthsResult.substring(0, birthsResult.length() - 1);
			content.put("births", birthsResult);
		}

		return content;
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
	public CodeMsgBean<Object> verifyBasicInformation(MchatScoreView mchatScoreView, String birthDay, String testDay,
			HttpServletRequest request) throws IOException {
		if (mchatScoreView == null || StringUtils.isEmpty(testDay) || StringUtils.isEmpty(birthDay)) {
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
			// enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		mchatScoreView = initMchatScore(mchatScoreView, enterpriseNo, medicNo, birthDay, testDay);
		try {
			MchatScore mchatScore = MchatScoreView.toMchatScore(mchatScoreView);
			boolean bl = portalMchatMedicFacade.verifyBasicInformation(mchatScore);
			return new CodeMsgBean<Object>(1, "操作成功", bl);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	private MchatScoreView initMchatScore(MchatScoreView mchatScore, String enterpriseNo, String medicNo, String birthDay,
			String testDay) {
		mchatScore.setEnterpriseNo(enterpriseNo);
		mchatScore.setMedicNo(medicNo);
		Map<String, Integer> mapBirthDate = initBirthMap(birthDay, "birthYear", "birthMonth", "birthToday");
		Map<String, Integer> mapTestDate = initBirthMap(testDay, "testYear", "testMonth", "testToday");
		mchatScore.setBirthMonth(mapBirthDate.get("birthMonth"));
		mchatScore.setBirthToday(mapBirthDate.get("birthToday"));
		mchatScore.setBirthYear(mapBirthDate.get("birthYear"));
		mchatScore.setTestYear(mapTestDate.get("testYear"));
		mchatScore.setTestMonth(mapTestDate.get("testMonth"));
		mchatScore.setTestToday(mapTestDate.get("testToday"));
		return mchatScore;
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
			// medicNo = "937c2b21d3db406693c59a816614e26d";
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
	public CodeMsgBean<Object> downloadRevisedFollowReport(String scoreNo, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatScoreRevisedFollow result = portalMchatMedicFacade.downloadRevisedFollowReport(scoreNo, medicNo);
			MchatScore mchatScore = portalMchatMedicFacade.downloadReport(result.getParentNo(), medicNo);
			if (result != null) {
				Map<String, String> content = initMap(result, mchatScore);
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition", "attachment; filename="+result.getReportNo()+".pdf");
				String path = "";
				if(result.getScore() !=null){
					if (result.getScore() < 2) {
						path = request.getSession().getServletContext().getRealPath("template\\D.pdf") ;
					} else if (result.getScore() >= 2) {
						path = request.getSession().getServletContext().getRealPath("template\\E.pdf") ;
					}
				}
				ExportPdfUtil.exportpdf(OUT_PATH, path, content, response);

			}
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			return new CodeMsgBean<Object>(10005, "操作失败");
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
	public CodeMsgBean<Object> createMchatReport(MchatScoreView mchatScoreview, String testDay, String birthDay,
			final MchatQuestionnaireResponse mchatQuestionnaireResponse, HttpServletRequest request)
			throws IOException {
		if (mchatScoreview == null || StringUtils.isEmpty(testDay) || StringUtils.isEmpty(birthDay)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HttpSession session = request.getSession();
		MedicInfo info = (MedicInfo) session.getAttribute(WebConstants.MEDIC_INFO);
		String enterpriseNo = null;
		String medicNo = null;
		String ip = getIp(request);
		if (info != null) {
			enterpriseNo = info.getEnterpriseNo();
			medicNo = info.getMedicNo();
		} else {
			// enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		mchatScoreview = initMchatScore(mchatScoreview, enterpriseNo, medicNo, birthDay, testDay);
		try {
			MchatScore mchat_score = MchatScoreView.toMchatScore(mchatScoreview);
			mchat_score.setIp(ip);
			Map<String, String> result = portalMchatMedicFacade.createMchatScore(mchat_score,
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
			// enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
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
			// paramMap.put("medicNo", medicNo);
			paramMap.put("deleted", req.getDeleted());
			PageBean result = portalMchatMedicFacade.listMchatScoreRevisedFollowListPage(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 创建MChat R/F报告
	 * 
	 * @param scoreNo
	 * @param mchatQuestionnaireResponseRevisedFollow
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/createMchatRevisedFollowReport", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> createMchatRevisedFollowReport(String scoreNo,
			MchatQuestionnaireResponseRevisedFollow mchatQuestionnaireResponseRevisedFollow, HttpServletRequest request)
			throws IOException {
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			 return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		if (mchatQuestionnaireResponseRevisedFollow == null || StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String ip = getIp(request);
		try {
			Map<String, String> result = portalMchatMedicFacade.createMchatScoreRevisedFollow(scoreNo, ip,
					mchatQuestionnaireResponseRevisedFollow);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 删除R/F报告
	 * 
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/deleteRevisedFollowReportByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> deleteRevisedFollowReportByScoreNo(String scoreNo, HttpServletRequest request)
			throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		HttpSession session = request.getSession();
		MedicInfo info = (MedicInfo) session.getAttribute(WebConstants.MEDIC_INFO);
		String medicNo = null;
		if (info != null) {
			medicNo = info.getMedicNo();
		} else {
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			long result = portalMchatMedicFacade.deleteRevisedFollowReportByMedicNoAndScoreNo(medicNo, scoreNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 获取某一条RF报告记录
	 * 
	 * @author leichang
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/getMchatScoreRevisedFollowByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatScoreRevisedFollowByScoreNo(String scoreNo, HttpServletRequest request)
			throws IOException {
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			 return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			MchatScoreRevisedFollow result = portalMchatMedicFacade.getMchatScoreRevisedFollowByScoreNo(scoreNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 施测者-查询所有R/F报告列表
	 * 
	 * @author leichang
	 * @param req
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medicMchat/listMchatScoreAndMchatScoreRevisedFollowListPage", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScoreAndMchatScoreRevisedFollowListPage(RecordReq req,
			HttpServletRequest request) throws IOException {
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
			// enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		Map<String, Integer> birthMap = null;
		Map<String, Integer> testMap = null;
		if (!StringUtils.isEmpty(req.getBirth())) {
			birthMap = initBirthMap(req.getBirth(), "birthYear", "birthMonth", "birthToday");
			if (birthMap != null && birthMap.size() != 3) {
				return new CodeMsgBean<Object>(10003, "参数异常,请检查出生日期是否正确");
			}
		}
		if (!StringUtils.isEmpty(req.getTestDate())) {
			testMap = initBirthMap(req.getTestDate(), "testYear", "testMonth", "testToday");
			if (testMap != null && testMap.size() != 3) {
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
			paramMap.put("birthYear", birthMap != null ? birthMap.get("birthYear") : "");
			paramMap.put("birthMonth", birthMap != null ? birthMap.get("birthMonth") : "");
			paramMap.put("birthToday", birthMap != null ? birthMap.get("birthToday") : "");
			paramMap.put("testYear", testMap != null ? testMap.get("testYear") : "");
			paramMap.put("testMonth", testMap != null ? testMap.get("testMonth") : "");
			paramMap.put("testToday", testMap != null ? testMap.get("testToday") : "");
			paramMap.put("deleted", req.getDeleted());
			paramMap.put("parentNo", req.getParentNo());
			PageBean PageBean = portalMchatMedicFacade.listMchatScoreAndMchatScoreRevisedFollowListPage(pageParam,
					paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", PageBean);
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
	private static Map<String, Integer> initBirthMap(String birth, String year, String month, String day) {
		Map<String, Integer> birthMap = new HashMap<String, Integer>();
		String[] array = birth.split("-");
		if (array.length == 3) {
			birthMap.put(year, isNumeric(array[0]) == true ? Integer.valueOf(array[0]) : 0);
			birthMap.put(month, isNumeric(array[1]) == true ? Integer.valueOf(array[1]) : 0);
			birthMap.put(day, isNumeric(array[2]) == true ? Integer.valueOf(array[2]) : 0);
		}
		return birthMap;
	}

	/**
	 * 校验是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	public static String birthConvert(int birthId) {
		String birth = "";
		switch (birthId) {
		case 0:
			birth = "足月";
			break;
		case 1:
			birth = "早产";
			break;
		case 2:
			birth = "顺产";
			break;
		case 3:
			birth = "剖腹产";
			break;
		case 4:
			birth = "产钳助产";
			break;
		case 5:
			birth = "吸引器助产";
			break;
		case 6:
			birth = "双胎";
			break;
		case 7:
			birth = "其他异常情况";
			break;
		default:
			birth = "";
			break;
		}
		return birth;
	}

}
