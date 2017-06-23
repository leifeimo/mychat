package com.prcmind.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prcmind.common.page.PageBean;
import com.prcmind.facade.portal.exception.PortalBizException;
import com.prcmind.facade.portal.mchat.service.PortalMchatMedicFacade;
import com.prcmind.facade.portal.mchat.service.PortalMchatTesteeFacade;
import com.prcmind.facade.scale.mchat.entity.MchatQuestionnaire;
import com.prcmind.facade.scale.mchat.entity.MchatQuestionnaireResponse;
import com.prcmind.facade.scale.mchat.entity.MchatScore;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.view.MchatScoreView;

@Controller
public class MchatTesteeController {

	@Autowired
	PortalMchatTesteeFacade portalMchatTesteeFacade;
	@Autowired
	PortalMchatMedicFacade portalMchatMedicFacade;

	/**
	 * 创建R报告
	 * 
	 * @author leichang
	 * @param mchatScore
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/api/v1/medicMchat/createMchatReport", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> createMchatReport(MchatScoreView mchatScoreView, String testDay, String birthDay,
			final MchatQuestionnaireResponse mchatQuestionnaireResponse, HttpServletRequest request)
			throws IOException {
		if (mchatScoreView == null || StringUtils.isEmpty(testDay) || StringUtils.isEmpty(birthDay)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String ip = getIp(request);
		mchatScoreView = initMchatScore(mchatScoreView, birthDay, testDay);
		try {
			MchatScore mchat_score = MchatScoreView.toMchatScore(mchatScoreView);
			mchat_score.setIp(ip);
			Map<String, String> result = portalMchatTesteeFacade.createMchatScore(mchat_score,
					mchatQuestionnaireResponse);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	@RequestMapping(value = "/api/v1/medicMchat/listQuestionnaire", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> listQuestionnaire(Integer level, HttpServletRequest request) throws IOException {
		try {
			List<MchatQuestionnaire> result = portalMchatTesteeFacade.listQuestionnaire(1);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 获取施测者列表
	 * 
	 * @param level
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/api/v1/medicMchat/listMedic", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMedicByEnterpriseNoAndScaleNoAndMedicNo(String enterpriseNo, String scaleNo,
			HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(enterpriseNo) || StringUtils.isEmpty(scaleNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			PageBean result = portalMchatTesteeFacade.listMedicByEnterpriseNoAndScaleNoAndMedicNo(enterpriseNo,
					scaleNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	@RequestMapping(value = "/api/v1/medicMchat/getMchatScoreHistoryRecord", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> getMchatScoreHistoryRecord(Integer level, HttpServletRequest request)
			throws IOException {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// paramMap.put("enterpriseNo", enterpriseNo);
			// paramMap.put("testeeNo", testeeNo);
			// paramMap.put("cardNo", cardNo);
			MchatScore result = portalMchatTesteeFacade.getMchatScoreHistoryRecord(paramMap);
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
	@RequestMapping(value = "/api/v1/medicMchat/verifyBasicInformation", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> verifyBasicInformation(MchatScoreView mchatScoreView, String birthDay, String testDay,
			HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(mchatScoreView.getEnterpriseNo()) || StringUtils.isEmpty(mchatScoreView.getMedicNo())) {
			return new CodeMsgBean<Object>(10003, "请选择施测者");
		}
		if (mchatScoreView == null || StringUtils.isEmpty(testDay) || StringUtils.isEmpty(birthDay)) {
			return new CodeMsgBean<Object>(10003, "出生日期与测试日期不能为空");
		}
		mchatScoreView = initMchatScore(mchatScoreView, birthDay, testDay);
		try {
			MchatScore mchatScore = MchatScoreView.toMchatScore(mchatScoreView);
			boolean bl = portalMchatMedicFacade.verifyBasicInformation(mchatScore);
			return new CodeMsgBean<Object>(1, "操作成功", bl);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
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

	private MchatScoreView initMchatScore(MchatScoreView mchatScore, String birthDay, String testDay) {
		Map<String, Integer> mapBirthDate = initBirthMap(birthDay, "birthYear", "birthMonth", "birthToday");
		Map<String, Integer> mapTestDate = initBirthMap(testDay, "testYear", "testMonth", "testToday");
		mchatScore.setBirthMonth(mapBirthDate.get("birthMonth")+"");
		mchatScore.setBirthToday(mapBirthDate.get("birthToday")+"");
		mchatScore.setBirthYear(mapBirthDate.get("birthYear")+"");
		mchatScore.setTestYear(mapTestDate.get("testYear")+"");
		mchatScore.setTestMonth(mapTestDate.get("testMonth")+"");
		mchatScore.setTestToday(mapTestDate.get("testToday")+"");
		return mchatScore;
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
