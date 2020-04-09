package com.es.springboot_es.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chenyan
 * @create 2020/4/8
 * @since 1.0.0
 */
@Controller
public class IndexController {

    @GetMapping({"/","/index"})
    public String index(){
        return "index" ;
    }


}
