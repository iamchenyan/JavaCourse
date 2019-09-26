package com.shirossm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shirossm.service.PermissionService;

/**  
* <p>Title: PermissionsController</p>  
* @author chenyan  
* @date 2019年9月26日  
*/
@Controller
@SuppressWarnings("all")
@RequestMapping("/permissions")
public class PermissionsController {

	@Autowired
	private PermissionService permissionService ;

	@RequestMapping("/findAll")
	public String findAll(Model model) {
		model.addAttribute("permissionsList" ,permissionService.findAll()) ;
		return "page/permission" ;
	}
	
	
}
