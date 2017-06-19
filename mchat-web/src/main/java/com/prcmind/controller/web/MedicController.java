package com.prcmind.controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.prcmind.facade.portal.service.PortalMedicFacade;
import com.prcmind.facade.user.entity.Article;
import com.prcmind.facade.user.entity.Certificate;
import com.prcmind.facade.user.entity.MedicInfo;
import com.prcmind.facade.user.entity.MedicOperator;
import com.prcmind.facade.user.entity.MedicScaleDosage;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.WebConstants;

/**
 * 施测者(医生)控制层
 * 
 * @author leichang
 *
 */
@Controller
public class MedicController {
	@Autowired
	PortalMedicFacade portalMedicFacade;
	public static Map<String, MedicInfo> medicInfoMaps = new HashMap<String, MedicInfo>();
	public static Map<String, MedicOperator> medicOperatorMaps = new HashMap<String, MedicOperator>();

	/**
	 * 登录获取施测者信息
	 * 
	 * @author leichang
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medic/oauth-server/oauth/token", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<MedicOperator> login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return new CodeMsgBean<MedicOperator>(10003, "参数异常");
		}
		try {
			MedicOperator mo = portalMedicFacade.getMedicOperator(username, password);
			if (mo != null) {
				HttpSession session = request.getSession();
				session.setAttribute(WebConstants.CURRENT_USER, mo);
				session.setMaxInactiveInterval(60*60);
				medicOperatorMaps.put(mo.getLoginName(), mo);
				MedicInfo info = portalMedicFacade.getMedicInfoByMedicNo(mo.getMedicNo());
				if (info != null) {
					request.getSession().setAttribute(WebConstants.MEDIC_INFO, info);
					medicInfoMaps.put(info.getLoginName(), info);
				}
			}
			return new CodeMsgBean<MedicOperator>(1, "操作成功", mo);
		} catch (PortalBizException e) {
			return new CodeMsgBean<MedicOperator>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 根据用户编号获取施测者详细信息
	 * 
	 * @author leichang
	 * @param request
	 * @param medicNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medic/getInformation", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<MedicInfo> getInformation(HttpServletRequest request) throws IOException {
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			 return new CodeMsgBean<MedicInfo>(10002,"登录失效，请重新登录");
		}
		try {
			MedicInfo info = portalMedicFacade.getMedicInfoByMedicNo(medicNo);
			if (info != null) {
				request.getSession().setAttribute(WebConstants.MEDIC_INFO, info);
				medicInfoMaps.put(info.getLoginName(), info);
			}
			return new CodeMsgBean<MedicInfo>(1, "操作成功", info);
		} catch (PortalBizException e) {
			return new CodeMsgBean<MedicInfo>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 修改密码
	 * 
	 * @author leichang
	 * @param oldPassword
	 * @param newPassword
	 * @param request
	 * @param medicNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medic/updateLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> updateLoginPwd(String oldPassword, String newPassword, HttpServletRequest request)
			throws IOException {
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			 return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			long status = portalMedicFacade.updateLoginPwd(medicNo, oldPassword, newPassword);
			return new CodeMsgBean<Object>(1, "操作成功", status);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	/**
	 * 找回密码
	 * 
	 * @author leichang
	 * @param loginName
	 * @param realName
	 * @param cardNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medic/findMedicLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> findMedicLoginPwd(String loginName, String realName, String cardNo,
			HttpServletRequest request) throws IOException {
		
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(realName) || StringUtils.isEmpty(cardNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			portalMedicFacade.findLoginPwd(loginName, realName, cardNo);
			return new CodeMsgBean<Object>(1, "操作成功");
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	/**
	 * 查询施测者在本单位所有量表使用量情况
	 * 
	 * @author leichang
	 * @param request
	 * @param enterpriseNo
	 * @param medicNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medic/listMedicScaleDosage", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> listMedicScaleDosage(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		MedicInfo info = (MedicInfo) session.getAttribute(WebConstants.MEDIC_INFO);
		String enterpriseNo = null;
		String medicNo = null;
		if (info != null) {
			enterpriseNo = info.getEnterpriseNo();
			medicNo = info.getMedicNo();
		} else {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			 return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			List<MedicScaleDosage> result = portalMedicFacade.listMedicScaleDosageByEnterpriseNoAndMedicNo(enterpriseNo,
					medicNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	/**
	 * 查询施测者在本单位其中一个量表使用量情况
	 * 
	 * @author leichang
	 * @param request
	 * @param medicNo
	 * @param enterpriseNo
	 * @param scaleNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medic/getMedicScaleDosageByScaleNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMedicScaleDosageByScaleNo(HttpServletRequest request, String scaleNo)
			throws IOException {
		if (StringUtils.isEmpty(scaleNo)) {
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
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			 return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			MedicScaleDosage medicScaleDosage = portalMedicFacade.getMedicScaleDosageByMedicNoAndScaleNo(enterpriseNo,
					medicNo, scaleNo);
			return new CodeMsgBean<Object>(1, "操作成功", medicScaleDosage);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	/**
	 * 查看公告通知列表
	 * 
	 * @author leichang
	 * @param request
	 * @param pageParam
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medic/listArticle", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listArticle(HttpServletRequest request, int pageNum, int numPerPage) throws IOException {
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
			PageBean PageBean = portalMedicFacade.listArticleListPage(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", PageBean);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	/**
	 * 获取公告文章详细信息
	 * 
	 * @author leichang
	 * @param request
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medic/getArticle", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> getArticle(HttpServletRequest request, Integer id) throws IOException {
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
			// medicNo = "937c2b21d3db406693c59a816614e26d";
			 return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(id)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			Article article = portalMedicFacade.getArticleById(id);
			return new CodeMsgBean<Object>(1, "操作成功", article);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	/**
	 * 查询拥有使用资格的量表产品列表
	 * 
	 * @author leichang
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/medic/listCertificate", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listCertificateByMedicNo(HttpServletRequest request) throws IOException {
		String medicNo = getMedicNo(request);
		if (StringUtils.isEmpty(medicNo)) {
//			 medicNo = "937c2b21d3db406693c59a816614e26d";
			 return new CodeMsgBean<Object>(10002,"登录失效，请重新登录");
		}
		try {
			List<Certificate> result = portalMedicFacade.listCertificateByMedicNo(medicNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	@RequestMapping(value = "/web/v1/medic/token/logout", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		return new CodeMsgBean<Object>(1, "操作成功");
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
}
