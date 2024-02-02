package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SearchController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	public String getSr() {


		return "search";
	}

	//検索用メソッド
	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public String postSr(HttpServletRequest request,HttpSession session, String id) {

		List<Map<String, Object>> resultList;

		resultList = jdbcTemplate.queryForList("SELECT * FROM seito WHERE id = ?",id);

		session.setAttribute("selectResult", resultList);
		

		if (!resultList.isEmpty()) {
			session.setAttribute("userId",id);
			session.setAttribute("fromSearch", true);
	        return "redirect:/home";
	    } else {
	        return "redirect:/search";
	    }
	}

}