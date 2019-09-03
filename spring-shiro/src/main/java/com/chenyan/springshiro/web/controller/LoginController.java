package com.chenyan.springshiro.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
* <p>Title: LoginController</p>  
* @author chenyan  
* @date 2019年9月3日  
*/
@Controller
public class LoginController {

	@RequestMapping(value="/login")
	public String showLoginFrom(HttpServletRequest request ,Model model) {
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure") ;
		String error = null ;
		if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
		return "login" ;
	}
	
	
}
