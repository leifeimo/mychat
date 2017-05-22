package com.prcmind.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prcmind.view.ReportParamView;

@Controller
public class MobilController {
	
	@RequestMapping("/mobile/html/question")
	public String toQuestion(ReportParamView report,HttpServletRequest request,
			HttpServletResponse response,Model model){
		System.out.println(request.getCharacterEncoding());
		System.out.println(report);
		model.addAttribute("report", report);
		return "mchat-mobile/question";
	}
}
