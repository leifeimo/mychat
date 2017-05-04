package com.prcmind.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prcmind.form.ForgotPassWordForm;
import com.prcmind.form.UserLoginForm;

@Controller
public class AccountController {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showLogin(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info(request.getSession().getId());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doLogin(@Valid UserLoginForm userLoginForm, HttpServletResponse response) {
        return "forward:/choices"; //直接forward,隐藏URL
    }

    @RequestMapping(value = "logout/{username}", method = RequestMethod.POST)
    @ResponseBody
    public String doLogout(@PathVariable @NotEmpty String username, HttpServletRequest request,
            HttpServletResponse response) {
        if (request != null && request.getSession() != null) {
            request.getSession().invalidate();
        }
        return "ok";
    }

    @RequestMapping(value = "forgot", method = RequestMethod.GET)
    public String showForgot(HttpServletRequest request, HttpServletResponse response) {
        return "forgot";
    }

    @RequestMapping(value = "forgot", method = RequestMethod.POST)
    @ResponseBody
    public String doForgot(@Valid ForgotPassWordForm pwdform, HttpServletResponse response) {
        return "ok";
    }

}
