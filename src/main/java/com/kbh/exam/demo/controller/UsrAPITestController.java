package com.kbh.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrAPITestController {
	@RequestMapping("/usr/home/APITest")
	public String showTestPage() {
		return "usr/home/APITest";
	}



}