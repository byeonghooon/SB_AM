package com.kbh.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kbh.exam.demo.util.Ut;

import lombok.Getter;

public class Rq {
	@Getter
	public boolean isLogined;
	@Getter
	public int loginedMemberId;

	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession Session;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;

		this.Session = req.getSession();
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (Session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) Session.getAttribute("loginedMemberId");
		}
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
	}

	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");

		print(Ut.jsHistoryBack(msg));
	}

	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void println(String str) {
		print(str + "\n");
	}

	public void login(Member member) {
		Session.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		Session.removeAttribute("loginedMemberId");
	}

	public String jsHistoryBackOnView(String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "usr/common/js";
	}
}
