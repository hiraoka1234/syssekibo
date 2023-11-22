package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
@Controller

public class LoginController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	//一覧表示用
	@RequestMapping(path = "/stulogin", method = RequestMethod.GET)
	public String viewPage() {
		
		return "stulogin";
	}
	@RequestMapping(path = "/teacherlogin", method = RequestMethod.GET)
	public String viewPage2() {

		return "teacherlogin";
	}

	//ログイン検証用
	@RequestMapping(path = "/stulogin", method = RequestMethod.POST)
	public String loginPost(String ID, String password, HttpSession session) {

		session.setAttribute("ID", ID);

		return "redirect:/home";
	}
	@RequestMapping(path = "/teacherlogin", method = RequestMethod.POST)
	public String loginPost( String password, HttpSession session) {

		session.setAttribute("password",password);

		return "redirect:/teacherlogin";
	}
}

