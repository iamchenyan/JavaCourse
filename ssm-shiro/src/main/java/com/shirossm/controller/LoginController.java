package com.shirossm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**  
* <p>Title: LoginController</p>  
* @author chenyan  
* @date 2019年9月19日  
*/
@Controller
public class LoginController {

	
	@RequestMapping("/login.do")
	public String login(
			@RequestParam(value = "username" ,required = false) String username ,
			@RequestParam(value = "password" ,required = false) String password ,Model model ) {
		
		String error = null ;
		if(username != null && password != null) {
			Subject subject = SecurityUtils.getSubject() ;
			UsernamePasswordToken token = new UsernamePasswordToken(username ,password) ;
			
			try {
				//登录，即身份校验，由通过Spring注入的UserRealm会自动校验输入的用户名和密码在数据库中是否有对应的值
				subject.login(token) ;
				//重定向到 index
				return "redirect:index.do" ;
			} catch (Exception e) {
				e.printStackTrace() ;
				error = "未知错误，错误信息：" + e.getMessage();
			} 
		} else {
			error = "请输入用户名或密码" ;
		}
		//登陆失败
		model.addAttribute("error", error);
		return "login" ;
	}
	
}
