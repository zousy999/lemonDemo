package com.lemon.exceptionhandler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2020/12/12 11:13.
 *
 * @author lemon
 */
@Controller
public class DemoController {

    @RequestMapping("/go")
    public String redirect(){

        return "redirect:http://www.baidu.com";

    }
}
