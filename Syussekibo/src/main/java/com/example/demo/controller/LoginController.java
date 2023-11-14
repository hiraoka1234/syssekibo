package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

public class LoginController {
	//一覧表示用
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String viewPage() {

		return "login";
	}

	//ログイン検証用
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginPost(String ID, String password, HttpSession session) {

		session.setAttribute("ID", ID);

		return "redirect:/home";
	}
}

