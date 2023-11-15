package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
@Controller
public class HomeController {
	//DBへつなぐために必要
		@Autowired
		JdbcTemplate jdbcTemplate;

}
