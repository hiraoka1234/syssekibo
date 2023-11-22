package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class HomeController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//一覧表示用
		@RequestMapping(path = "/home", method = RequestMethod.GET)
		public String viewPage(Model model) {
			
			List<Map<String, Object>>resultList;
			
			resultList=jdbcTemplate.queryForList("SELECT * FROM syusseki");
			
			model.addAttribute("selectResult",resultList);
					
			return "home";
		}
}
