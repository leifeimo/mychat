package com.prcmind.controller;

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
import com.prcmind.facade.portal.service.PortalEnterpriseFacade;
import com.prcmind.facade.user.entity.Article;
import com.prcmind.facade.user.entity.EnterpriseInfo;
import com.prcmind.facade.user.entity.EnterpriseOperator;
import com.prcmind.facade.user.entity.EnterpriseScaleDosage;
import com.prcmind.facade.user.entity.MedicInfo;
import com.prcmind.facade.user.entity.MedicScaleDosage;
import com.prcmind.facade.user.entity.ScaleProducts;
import com.prcmind.utils.CodeMsgBean;
import com.prcmind.utils.WebConstants;

/**
 * 管理员控制层
 * 
 * @author leichang
 *
 */
@Controller
public class EnterpriseController {
	@Autowired
	PortalEnterpriseFacade portalEnterpriseFacade;

	public static Map<String, EnterpriseInfo> enterpriseInfoMaps = new HashMap<String, EnterpriseInfo>();
	public static Map<String, EnterpriseOperator> enterpriseOperatorMaps = new HashMap<String, EnterpriseOperator>();

	/**
	 * 根据登陆名查找企业用户 (适用于登陆系统)
	 * 
	 * @author leichang
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/oauth-server/oauth/token", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			EnterpriseOperator eo = portalEnterpriseFacade.getEnterpriseOperator(username, password);
			HttpSession session = request.getSession();
			session.setAttribute(WebConstants.MANAGER_USER, eo);
			return new CodeMsgBean<Object>(1, "操作成功", eo);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	/**
	 * 根据企业编号获取企业用户详细信息
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/getInformation", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> getInformation(HttpServletRequest request) throws IOException {
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<MedicInfo>(10002,"登录失效，请重新登录");
		}
		try {
			EnterpriseInfo info = portalEnterpriseFacade.getEnterpriseInfoByEnterpriseNo(enterpriseNo);
			if (info != null) {
				request.getSession().setAttribute(WebConstants.ENTERPRISE_INFO, info);
				enterpriseInfoMaps.put(info.getLoginName(), info);
			}
			return new CodeMsgBean<Object>(1, "操作成功", info);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 查询某一施测者的量表使用量情况
	 * 
	 * @author leichang
	 * @param request
	 * @param medicNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/getMedicScaleDosageByMedicNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> getMedicScaleDosageByMedicNo(HttpServletRequest request, String medicNo)
			throws IOException {
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<MedicInfo>(10002,"登录失效，请重新登录");
		}
		if (StringUtils.isEmpty(medicNo)) {
			return new CodeMsgBean<Object>(10002, "参数异常");
		}
		try {
			List<MedicScaleDosage> result = portalEnterpriseFacade
					.listMedicScaleDosageByEnterpriseNoAndMedicNo(enterpriseNo, medicNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 获取所有量表使用量和剩余量
	 * 
	 * @author leichang
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/listEnterpriseScaleDosage", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> listEnterpriseScaleDosage(HttpServletRequest request) throws IOException {
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<MedicInfo>(10002,"登录失效，请重新登录");
		}
		try {
			List<EnterpriseScaleDosage> result = portalEnterpriseFacade.listEnterpriseScaleDosage(enterpriseNo);
			return new CodeMsgBean<Object>(1, "操作成功", result);
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
	@RequestMapping(value = "/web/v1/enterprise/getArticle", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> getArticle(HttpServletRequest request, Integer id) throws IOException {
		if (StringUtils.isEmpty(id)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			Article result = portalEnterpriseFacade.getArticleById(id);
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 查看公告通知列表
	 * 
	 * @author leichang
	 * @param pageNum
	 * @param numPerPage
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/listArticle", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> queryListArticle(int pageNum, int numPerPage, HttpServletRequest request)
			throws IOException {
		if (pageNum == 0 || numPerPage == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		PageParam pageParam = new PageParam(pageNum, numPerPage);
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			PageBean PageBean = portalEnterpriseFacade.listArticleListPage(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", PageBean);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 获取已绑定本单位的施测者详细
	 * 
	 * @param request
	 * @param medicNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/getMedicInfoByMedicNo", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<MedicInfo> getMedicInfoByMedicNo(HttpServletRequest request, String medicNo) throws IOException {
		if (StringUtils.isEmpty(medicNo)) {
			return new CodeMsgBean<MedicInfo>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<MedicInfo>(10002,"登录失效，请重新登录");
		}
		try {
			MedicInfo info = portalEnterpriseFacade.getMedicInfoByMedicNo(medicNo, enterpriseNo);
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
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/updateLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> updateLoginPwd(String oldPassword, String newPassword, HttpServletRequest request)
			throws IOException {
		if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<MedicInfo>(10002,"登录失效，请重新登录");
		}
		try {
			long status = portalEnterpriseFacade.updateLoginPwd(enterpriseNo, oldPassword, newPassword);
			return new CodeMsgBean<Object>(1, "操作成功", status);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 解绑定施测者
	 * 
	 * @author leichang
	 * @param medicNo
	 * @param managerPwd
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/dismissUser", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> dismissUser(String medicNo, String managerPwd, HttpServletRequest request)
			throws IOException {
		if (StringUtils.isEmpty(managerPwd) || StringUtils.isEmpty(medicNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<MedicInfo>(10002,"登录失效，请重新登录");
		}
		try {
			long status = portalEnterpriseFacade.dismissMedic(medicNo, managerPwd, enterpriseNo);
			return new CodeMsgBean<Object>(1, "操作成功", status);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 绑定施测者
	 * 
	 * @author leichang
	 * @param realName
	 * @param cardNo
	 * @param managerPwd
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/boundMedic", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> boundMedic(String realName, String cardNo, String managerPwd, HttpServletRequest request)
			throws IOException {
		if (StringUtils.isEmpty(realName) || StringUtils.isEmpty(managerPwd) || StringUtils.isEmpty(cardNo)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<MedicInfo>(10002,"登录失效，请重新登录");
		}
		try {
			long status = portalEnterpriseFacade.boundMedic(realName, cardNo, managerPwd, enterpriseNo);
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
	 * @param newLoginPwd
	 * @param puk
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/user/findEnterpriseLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> findEnterpriseLoginPwd(String loginName, String newLoginPwd, String puk,
			HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(newLoginPwd) || StringUtils.isEmpty(puk)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			long status = portalEnterpriseFacade.findLoginPwd(loginName, newLoginPwd, puk);
			return new CodeMsgBean<Object>(1, "操作成功", status);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

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
	@RequestMapping(value = "/web/v1/enterprise/listMedic", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> listMedic(int pageNum, int numPerPage,String loginName, HttpServletRequest request) throws IOException {
		if (pageNum == 0 || numPerPage == 0) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<MedicInfo>(10002,"登录失效，请重新登录");
		}
		PageParam pageParam = new PageParam(pageNum, numPerPage);
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loginName", loginName);
			paramMap.put("enterpriseNo", enterpriseNo);
			PageBean pageBean = portalEnterpriseFacade.listMedicListPage(pageParam, paramMap);
			return new CodeMsgBean<Object>(1, "操作成功", pageBean);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}

	}

	

	/**
	 * 获取所有量表产品
	 * 
	 * @author leichang
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/listAllScaleProducts", method = RequestMethod.GET)
	@ResponseBody
	public CodeMsgBean<Object> listAllScaleProducts(HttpServletRequest request) throws IOException {
		try {
			List<ScaleProducts> result = portalEnterpriseFacade.listAllScaleProducts();
			return new CodeMsgBean<Object>(1, "操作成功", result);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}

	@RequestMapping(value = "/web/v1/enterprise/user/findLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> findLoginPwd(String loginName, String newLoginPwd, String puk,
			HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(newLoginPwd) || StringUtils.isEmpty(puk)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		try {
			long status = portalEnterpriseFacade.findLoginPwd(loginName, newLoginPwd, puk);
			return new CodeMsgBean<Object>(1, "操作成功", status);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}


	/**
	 * 修改施测者密码
	 * @author leichang
	 * @param oldPassword
	 * @param newPassword
	 * @param managerPwd
	 * @param medicNo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/updateMedicLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> updateMedicLoginPwd( String newPassword, String managerPwd,String medicNo,
			HttpServletRequest request) throws IOException {
		if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(medicNo) || StringUtils.isEmpty(managerPwd)) {
			return new CodeMsgBean<Object>(10003, "参数异常");
		}
		String enterpriseNo = getEnterpriseNo(request);
		if (StringUtils.isEmpty(enterpriseNo)) {
			enterpriseNo = "20252a32e38c44f9ac02ca623f4ee503";
			// return new CodeMsgBean<MedicInfo>(10002,"登录失效，请重新登录");
		}
		try {
			long status = portalEnterpriseFacade.updateMedicLoginPwd(enterpriseNo, medicNo, newPassword, managerPwd);
			return new CodeMsgBean<Object>(1, "操作成功", status);
		} catch (PortalBizException e) {
			return new CodeMsgBean<Object>(e.getCode(), e.getMsg());
		}
	}
	
	/**
	 * 退出登录
	 * 
	 * @author leichang
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/web/v1/enterprise/token/logout", method = RequestMethod.POST)
	@ResponseBody
	public CodeMsgBean<Object> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		return new CodeMsgBean<Object>(1, "操作成功");
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
}
