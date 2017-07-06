package com.prcmind.controller.api;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prcmind.view.ReportParamView;

@Controller
public class MobileController {
	
	@RequestMapping("/mobile/html/question")
	public String toQuestion(ReportParamView report,HttpServletRequest request,
			HttpServletResponse response,Model model){
		model.addAttribute("report", report);
		return "mchat-mobile/question";
	}
	
	@RequestMapping("/mobile/html/report")
	public String toReport(ReportParamView report,HttpServletRequest request,
			HttpServletResponse response,Model model) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException{
		if(!StringUtils.isEmpty(report.getTesteeName())){
			String testeeName = new String(report.getTesteeName().getBytes("iso-8859-1"),"utf-8");
			report.setTesteeName(testeeName);
		}
		report.checkShow();
		report.getBirthsSituation();
		model.addAttribute("report", report);
		return "mchat-mobile/guardian";
	}
	
}
