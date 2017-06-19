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

import com.prcmind.common.page.PageBean;
import com.prcmind.common.page.PageParam;
import com.prcmind.facade.portal.exception.PortalBizException;
import com.prcmind.facade.portal.mchat.service.PortalMchatEnterpriseFacade;
import com.prcmind.facade.scale.mchat.entity.MchatQuestionnaireResponse;
import com.prcmind.facade.scale.mchat.entity.MchatQuestionnaireResponseRevisedFollow;
import com.prcmind.facade.scale.mchat.entity.MchatScore;
import com.prcmind.facade.scale.mchat.entity.MchatScoreRevisedFollow;
import com.prcmind.facade.user.entity.EnterpriseOperator;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.DateUtil;
import com.prcmind.utils.ExportPdfUtil;
import com.prcmind.utils.WebConstants;
import com.prcmind.view.req.FollowReq;
import com.prcmind.view.req.RecordReq;

/**
 * mchat-管理员控制层
 * 
 * @author leichang
 *
 */
@Controller
public class EnterpriseMchatController {
	@Autowired
	PortalMchatEnterpriseFacade portalMchatEnterpriseFacade;
	private static ResourceBundle resource = ResourceBundle.getBundle("mchat-config");
	private static String OUT_PATH = resource.getString("out_path");
	/**
	 * 下载报告结果
	 * 
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterpriseMchat/downloadReport", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> downloadReport(String scoreNo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			// enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatScore result = portalMchatEnterpriseFacade.downloadReport(scoreNo, enterpriseNo);
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

	
	/**
	 * 下载报告结果
	 * 
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterpriseMchat/downloadRevisedFollowReport", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> downloadRevisedFollowReport(String scoreNo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
//			 enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatScoreRevisedFollow result = portalMchatEnterpriseFacade.downloadRevisedFollowReport(scoreNo, enterpriseNo);
			MchatScore mchatScore = portalMchatEnterpriseFacade.downloadReport(result.getParentNo(), enterpriseNo);
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
	 * 获取某一问卷填写详细
	 * 
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterpriseMchat/getMchatQuestionnaireResponse", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatQuestionnaireResponse(String scoreNo, HttpServletRequest request)
			throws IOException {
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			// enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MchatQuestionnaireResponse result = portalMchatEnterpriseFacade.getMchatQuestionnaireResponse(scoreNo,
					enterpriseNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 管理员-查询所有报告列表
	 * 
	 * @author leichang
	 * @param req
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterpriseMchat/listMchatScoreListPage", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScoreListPage(RecordReq req, HttpServletRequest request) throws IOException {
		if (req.getPageNum() == 0 || req.getNumPerPage() == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
//			 enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		Map<String, Integer> map = null;
		if (!StringUtils.isEmpty(req.getBirth())) {
			map = initBirthMap(req.getBirth(),"birthYear", "birthMonth", "birthToday");
			if (map != null && map.size() != 3) {
				return new CodeMsgBean<Object>(10003, "参数异常,请检查出生日期是否正确");
			}
		}
		Map<String, Integer> testMap = null;
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
			paramMap.put("birthYear", map != null ? map.get("birthYear") : "");
			paramMap.put("birthMonth", map != null ? map.get("birthMonth") : "");
			paramMap.put("birthToday", map != null ? map.get("birthToday") : "");
			paramMap.put("deleted", req.getDeleted());
			paramMap.put("testYear", testMap != null ? testMap.get("testYear") : "");
			paramMap.put("testMonth", testMap != null ? testMap.get("testMonth") : "");
			paramMap.put("testToday", testMap != null ? testMap.get("testToday") : "");
			paramMap.put("cardNo", req.getCardNo());
			paramMap.put("medicName", req.getMedicName());
			PageBean PageBean = portalMchatEnterpriseFacade.listMchatScoreAndMchatScoreRevisedFollowListPage(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", PageBean);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	/**
	 * 获取某一条报告记录
	 * 
	 * @author leichang
	 * @param scoreNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterpriseMchat/getMchatScoreByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatScoreByScoreNo(String scoreNo, HttpServletRequest request) throws IOException {
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
//			 enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			MchatScore mchatScore = portalMchatEnterpriseFacade.getMchatScoreByScoreNo(scoreNo);
			return new CodeMsgBean<Object>(1, "操作成功", mchatScore);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}
	
	
	
	/**
	 * 管理员-查询R/F所有报告列表
	 * 
	 * @author leichang
	 * @param mchatScore
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterpriseMchat/listMchatScoreRevisedFollow", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMchatScoreRevisedFollow(FollowReq req, HttpServletRequest request)
			throws IOException {
		if (req.getPageNum() == 0 || req.getNumPerPage() == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
//			 enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		Map<String, Integer> map = null;
		if (!StringUtils.isEmpty(req.getBirth())) {
			map = initBirthMap(req.getBirth(),"birthYear", "birthMonth", "birthToday");
			if (map != null && map.size() != 3) {
				return new CodeMsgBean<Object>(10003, "参数异常,请检查出生日期是否正确");
			}
		}
		Map<String, Integer> testMap = null;
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
			paramMap.put("testeeNo", req.getTesteeNo());
			paramMap.put("enterpriseNo", enterpriseNo);
			 paramMap.put("parentNo", req.getParentNo());
			paramMap.put("birthYear", map != null ? map.get("birthYear") : "");
			paramMap.put("birthMonth", map != null ? map.get("birthMonth") : "");
			paramMap.put("birthToday", map != null ? map.get("birthToday") : "");
			paramMap.put("deleted", req.getDeleted());
			paramMap.put("testYear", testMap != null ? testMap.get("testYear") : "");
			paramMap.put("testMonth", testMap != null ? testMap.get("testMonth") : "");
			paramMap.put("testToday", testMap != null ? testMap.get("testToday") : "");
			PageBean result = portalMchatEnterpriseFacade.listMchatScoreRevisedFollowListPage(pageParam, paramMap);
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
	@RequestMapping(value = "/web/v1/enterpriseMchat/getMchatScoreRevisedFollowByScoreNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatScoreRevisedFollowByScoreNo(String scoreNo, HttpServletRequest request)
			throws IOException {
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
//			 enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			MchatScoreRevisedFollow result = portalMchatEnterpriseFacade.getMchatScoreRevisedFollowByScoreNo(scoreNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
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
	@RequestMapping(value = "/web/v1/enterpriseMchat/getMchatQuestionnaireResponseRevisedFollow", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMchatQuestionnaireResponseRevisedFollow(String scoreNo, HttpServletRequest request)
			throws IOException {
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
//			 enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(scoreNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		
		try {
			MchatQuestionnaireResponseRevisedFollow result = portalMchatEnterpriseFacade
					.getMchatQuestionnaireResponseRevisedFollow(scoreNo, enterpriseNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}
	
	
//	/**
//	 * 管理员-儿童档案查询
//	 * 
//	 * @author leichang
//	 * @param req
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/web/v1/enterpriseMchat/listMchatScoreUnique", method = RequestMethod.POST)
//	@ResponseBody
//	public CodeMsgBean<Object> listMchatScoreUnique(RecordReq req, HttpServletRequest request) throws IOException {
//		if (req.getPageNum() == 0 || req.getNumPerPage() == 0) {
//			return new CodeMsgBean<Object>(10003, "参数异常");
//		}
//		String enterpriseNo = getEnterpriseNo(request);
//		if (StringUtils.isEmpty(enterpriseNo)) {
//			 enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
////			return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
//		}
//		Map<String, Integer> birthMap = null;
//		Map<String, Integer> testMap = null;
//		if (!StringUtils.isEmpty(req.getBirth())) {
//			birthMap = initBirthMap(req.getBirth(), "birthYear", "birthMonth", "birthToday");
//			if (birthMap != null && birthMap.size() != 3) {
//				return new CodeMsgBean<Object>(10003, "参数异常,请检查出生日期是否正确");
//			}
//		}
//		if (!StringUtils.isEmpty(req.getTestDate())) {
//			testMap = initBirthMap(req.getTestDate(), "testYear", "testMonth", "testToday");
//			if (testMap != null && testMap.size() != 3) {
//				return new CodeMsgBean<Object>(10003, "参数异常,请检查出生日期是否正确");
//			}
//		}
//
//		PageParam pageParam = new PageParam(req.getPageNum(), req.getNumPerPage());
//		try {
//			HashMap<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("pageNum", req.getPageNum() + "");
//			paramMap.put("numPerPage", req.getNumPerPage() + "");
//			paramMap.put("testeeName", req.getTesteeName());
//			paramMap.put("reportNo", req.getReportNo());
//			paramMap.put("cardNo", req.getCardNo());
//			paramMap.put("enterpriseNo", enterpriseNo);
////			paramMap.put("medicNo", medicNo);
//			paramMap.put("birthYear", birthMap != null ? birthMap.get("birthYear") : "");
//			paramMap.put("birthMonth", birthMap != null ? birthMap.get("birthMonth") : "");
//			paramMap.put("birthToday", birthMap != null ? birthMap.get("birthToday") : "");
//			paramMap.put("testYear", testMap != null ? testMap.get("testYear") : "");
//			paramMap.put("testMonth", testMap != null ? testMap.get("testMonth") : "");
//			paramMap.put("testToday", testMap != null ? testMap.get("testToday") : "");
//			PageBean PageBean = portalMchatEnterpriseFacade.listMchatScoreUniqueListPage(pageParam, paramMap);
//			return new CodeMsgBean<Object>(1, "操作成功", PageBean);
//		} catch (PortalBizException e) {
//			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
//		}
//	}

	
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
	private static Map<String, Integer> initBirthMap(String birth, String year, String month, String day) {
		Map<String, Integer> birthMap = new HashMap<String, Integer>();
		String[] array = birth.split("-");
		if (array.length == 3) {
			birthMap.put(year, isNumeric(array[0]) == true ? (Integer.valueOf(array[0]) == 0 ? null :Integer.valueOf(array[0]) ) : null);
			birthMap.put(month, isNumeric(array[1]) == true ? (Integer.valueOf(array[1]) == 0 ? null :Integer.valueOf(array[1]) ) : null);
			birthMap.put(day, isNumeric(array[2]) == true ? (Integer.valueOf(array[2]) == 0 ? null :Integer.valueOf(array[2]) ) : null);
		}
		return birthMap;
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
			String reportNo="报告编号"+"  "+result.getReportNo();
			content.put("reportNo",reportNo);
		} else {
			String createTime = DateUtil.DateToStr(mchatScoreRevisedFollow.getCreateTime(), "yyyy-MM-dd");
			content.put("createTime", createTime);
			content.put("r_score", result.getScore() + "");
			content.put("r_f_score", mchatScoreRevisedFollow.getScore() + "");
			String reportNo="报告编号"+"  "+mchatScoreRevisedFollow.getReportNo();
			content.put("reportNo",reportNo);
		}
		content.put("enterpriseName", result.getEnterpriseName());
		content.put("medicName", result.getMedicName());
		String gestationalWeeks = result.getGestationalWeeks() + "周"
				+ (result.getGestationalDays() == null ? "0" : result.getGestationalDays() + "天");
		content.put("gestationalWeeks", gestationalWeeks);
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
}
