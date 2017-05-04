package com.prcmind.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prcmind.utils.PageData;

@Controller
public class SystemRouterController {

    private static Logger LOGGER = LoggerFactory.getLogger(SystemRouterController.class);

    @RequestMapping(value = "choices", method = RequestMethod.GET)
    public String choices(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info(request.getSession().getId());
        return "choices";
    }

    @RequestMapping(value = "choices/{system}", method = RequestMethod.GET)
    public String systemIndex(@PathVariable("system") @NotEmpty String system, HttpServletRequest request,
            HttpServletResponse response) {
        LOGGER.info(request.getSession().getId());
        //进入对应系统的首页
        return "mchat-pc/index";
    }
    
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public PageData<String> querylist(){
    	PageData<String> pageData = new PageData<String>();
    	pageData.setTotal(11);
    	List<String> strs = new ArrayList<String>();
    	strs.add("a");
    	strs.add("b");
    	strs.add("c");
    	strs.add("d");
    	pageData.setRows(strs);
    	return pageData;
    }
}
