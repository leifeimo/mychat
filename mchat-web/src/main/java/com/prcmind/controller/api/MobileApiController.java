package com.prcmind.controller.api;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prcmind.common.page.PageBean;
import com.prcmind.common.page.PageParam;
import com.prcmind.facade.portal.exception.PortalBizException;
import com.prcmind.facade.portal.mchat.service.PortalMchatMedicFacade;
import com.prcmind.facade.portal.service.PortalEnterpriseFacade;
import com.prcmind.facade.scale.mchat.entity.MchatScore;
import com.prcmind.facade.user.entity.MedicInfo;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.WebConstants;

@Controller
public class MobileApiController {
	@Autowired
	PortalEnterpriseFacade portalEnterpriseFacade;
	@Autowired
	PortalMchatMedicFacade portalMchatMedicFacade;
	/**
	 * 获取本单位的所有施测者列表
	 * 
	 * @author leichang
	 * @param pageNum
	 * @param numPerPage
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/api/v1/enterprise/listMedic", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMedic(int pageNum, int numPerPage, HttpServletRequest request) throws IOException {
		if (pageNum == 0 || numPerPage == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		PageParam pageParam = new PageParam(pageNum, numPerPage);
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			PageBean pageBean = portalEnterpriseFacade.listMedicListPage(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", pageBean);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}
	
	
	@RequestMapping(value = "/api/v1/medicMchat/verifyBasicInformation", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> verifyBasicInformation(MchatScore mchatScore, HttpServletRequest request) throws IOException {
		if (mchatScore ==null) {
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
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			medicNo = "937c2b21d3db406693c59a816614e26d";
			// return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
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
	
}
