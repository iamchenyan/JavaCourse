package com.shirossm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shirossm.pojo.User;
import com.shirossm.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/showname",method=RequestMethod.GET)
    public String showUserName(@RequestParam("uid") int uid,HttpServletRequest request,Model model){
        System.out.println("showname");
        User user = userService.getUserById(uid);
        if(user != null){
            request.setAttribute("name", user.getUserName());
            model.addAttribute("mame", user.getUserName());
            return "showName";
        }
        request.setAttribute("error", "没有找到该用户！");
        return "error";
    }
	
	
}
