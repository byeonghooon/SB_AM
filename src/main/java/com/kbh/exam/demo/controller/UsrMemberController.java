package com.kbh.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbh.exam.demo.service.MemberService;

@Controller
public class UsrMemberController {

	private MemberService memberService;

	@RequestMapping("usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		return "가입!";
	}
}