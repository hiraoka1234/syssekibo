package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class TourokuController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	          
	//INSERT（登録）用メソッド
		@RequestMapping(path = "/touroku", method = RequestMethod.POST)
		public String postIns(String ID,String password) {
			
			jdbcTemplate.update("insert into mobpro (ID,password) value (?,?)",ID,password);
			

			return "redirect:/touroku";
		}
	}


