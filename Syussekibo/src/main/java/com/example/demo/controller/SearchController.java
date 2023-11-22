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
public class SearchController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	public String getSr() {


		return "search";
	}

	//検索用メソッド
	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public String postSr(Model model, String ID) {

		List<Map<String, Object>> resultList;

		resultList = jdbcTemplate.queryForList("SELECT * FROM syussekibo where ID = ?", ID);

		model.addAttribute("selectResult", resultList);

		return "search";
	}

}
