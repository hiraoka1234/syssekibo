package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

public class LoginController {
	//一覧表示用
	@RequestMapping(path = "/mylogin", method = RequestMethod.GET)
	public String viewPage() {

		return "mylogin";
	}

	//ログイン検証用
	@RequestMapping(path = "/mylogin", method = RequestMethod.POST)
	public String loginPost(String username, String password, HttpSession session) {

		session.setAttribute("username", username);

		return "redirect:/myhome";
	}
}

