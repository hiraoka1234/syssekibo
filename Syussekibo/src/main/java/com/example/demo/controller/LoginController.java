package com.example.demo.controller;

import java.util.List;
import java.util.Map;

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
	public String viewPage(String id,String password) {
		
	
		return "stulogin";
	}

	//ログイン検証用
	@RequestMapping(path = "/stulogin", method = RequestMethod.POST)
	public String loginPost(String id, String password, HttpSession session) {
		
		 List<Map<String, Object>> resultList;
		    
		    // IDとパスワードの両方を条件とするSQLクエリを実行
		 resultList = jdbcTemplate.queryForList("SELECT * FROM seito WHERE id = ? AND password = ?",id, password);
		    
		    session.setAttribute("resultList", resultList);
		    System.out.print(resultList);

		    if (!resultList.isEmpty()) {
		    	session.setAttribute("fromLogin", true);
		    	session.setAttribute("userId",id);
		        return "redirect:/home";
		    } else {
		        return "redirect:/stulogin";
		    }
	}
}