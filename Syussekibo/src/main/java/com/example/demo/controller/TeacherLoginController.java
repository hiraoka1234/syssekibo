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
public class TeacherLoginController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/teacherlogin", method = RequestMethod.GET)
	public String TealoginGET() {

		return "teacherlogin";
	}
	
	//ログイン検証用
		@RequestMapping(path = "/teacherlogin", method = RequestMethod.POST)
		public String TealoginPost(String password, HttpSession session) {
			
			 List<Map<String, Object>> resultList;
			    
			    // IDとパスワードの両方を条件とするSQLクエリを実行
			 resultList = jdbcTemplate.queryForList("SELECT * FROM teacher WHERE password = ?",password);
			    
			    session.setAttribute("resultList", resultList);
			    System.out.print(resultList);

			    if (!resultList.isEmpty()) {
			        return "redirect:/search";
			    } else {
			        return "redirect:/teacherlogin";
			    }
		}

}