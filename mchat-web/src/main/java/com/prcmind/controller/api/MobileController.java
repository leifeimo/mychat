package com.prcmind.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			HttpServletResponse response,Model model){
		report.checkShow();
		report.getBirthsSituation();
		model.addAttribute("report", report);
		return "mchat-mobile/guardian";
	}
	
	@RequestMapping("/mobile/html/instructions")
	public String toInstructions(HttpServletRequest request,
			HttpServletResponse response){
		return "mchat-mobile/instructions";
	}
}
