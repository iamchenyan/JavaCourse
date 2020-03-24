package com.whoops.springbootlearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoops.springbootlearn.property.ApplicationProperty;
import com.whoops.springbootlearn.property.DeveloperProperty;

import cn.hutool.core.lang.Dict;

@RestController
public class PropertyController {

	private final ApplicationProperty applicationProperty ;
	private final DeveloperProperty developerProperty ;
	
	@Autowired
	public PropertyController(ApplicationProperty applicationProperty ,DeveloperProperty developerProperty) {
		this.applicationProperty = applicationProperty ;
		this.developerProperty = developerProperty ;
	}
	
	@GetMapping("/property")
	public Dict index() {
		return Dict.create().set("applicationProperty", applicationProperty).set("developerProperty", developerProperty) ;
	}
	
	
}
