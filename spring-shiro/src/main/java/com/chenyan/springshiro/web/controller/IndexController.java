package com.chenyan.springshiro.web.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenyan.springshiro.entity.Resource;
import com.chenyan.springshiro.entity.User;
import com.chenyan.springshiro.service.ResourceService;
import com.chenyan.springshiro.service.UserService;
import com.chenyan.springshiro.web.bind.annotation.CurrentUser;

/**  
* <p>Title: IndexController</p>  
* @author chenyan  
* @date 2019年9月3日  
*/
@Controller
public class IndexController {

	@Autowired
	private ResourceService resourceService ;
	
	@Autowired
	private UserService userService ;
	
	@RequestMapping("/")
	public String index(@CurrentUser User loginUser ,Model model) {
		Set<String> permissions = userService.findPermissions(loginUser.getUsername()) ;
		List<Resource> menus = resourceService.findMenus(permissions) ;
		model.addAttribute("menus" ,menus) ;
		return "index" ;
	}
	
	@RequestMapping("/welcome")
	public String welcame() {
		return "welcome" ;
	}
	
}
