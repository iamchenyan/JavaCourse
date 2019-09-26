package com.shirossm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shirossm.service.RoleService;

/**  
* <p>Title: RoleController</p>  
* @author chenyan  
* @date 2019年9月26日  
*/
@Controller
@SuppressWarnings("all")
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService ;
	
	/**
	 * 查询所有
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAll")
	public String findAll(Model model) {
		model.addAttribute("rolesList" ,roleService.findAll()) ;
		return "page/role" ;
	}
	
}
