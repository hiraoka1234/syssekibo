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
	@RequestMapping(path = "/touroku", method = RequestMethod.GET)
	public String postIns() {
		
	
		return "touroku";
	}
	
	//INSERT（登録）用メソッド
		@RequestMapping(path = "/touroku", method = RequestMethod.POST)
		public String postIns(int id,String name,int password,String sclass,int career,int scareer) {
			
			jdbcTemplate.update("insert into seito (id,name,password,sclass,career,scareer) value (?,?,?,?,?,?)",id,name,password,sclass,career,scareer);
			

			return "redirect:/touroku";
		}
	}


