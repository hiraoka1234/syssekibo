package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
@Controller

public class HomeController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	 Calendar cl = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
    String record = sdf.format(cl.getTime());

	//一覧表示用
		@RequestMapping(path = "/home", method = RequestMethod.GET)
		public String viewPage(Model model,Model model2) {
			
			List<Map<String, Object>>resultList;
			
			resultList=jdbcTemplate.queryForList("SELECT seito.id, seito.name, seito.career, seito.scareer ,kaisu.subjectid, kaisu.frequency, kaisu.status, kamoku.subject "
					+ "FROM seito "
					+ "LEFT JOIN kaisu ON seito.id = kaisu.id "
					+ "LEFT JOIN kamoku ON kaisu.subjectid = kamoku.subjectid "
					+ "ORDER BY kaisu.subjectid ASC ,kaisu.frequency ASC;");
			
			model.addAttribute("selectResult",resultList);
			
			List<Map<String, Object>> kamokuList = jdbcTemplate.queryForList("SELECT  DISTINCT subjectid,subject FROM kamoku ");
			
			model.addAttribute("selectResult2",kamokuList);
	        
			List<Map<String, Object>> kaisuList = jdbcTemplate.queryForList("SELECT DISTINCT id, frequency FROM kaisu ORDER BY frequency ASC;");
			
			model.addAttribute("selectResult3",kaisuList);

			return "home";
		}
		//UPDATE(更新)用メソッド
		@RequestMapping(path = "/home", method = RequestMethod.POST)
		public String postUpd(String latitude,String longitude,HttpSession session) {
			
			String userid=(String)session.getAttribute("ID");
			System.out.println("userid=" +userid);
			  System.out.println(record);
			System.out.println(latitude);
			System.out.println(longitude);
			jdbcTemplate.update("insert into record (ID,record,latitude,longitude) value (?,?,?,?)",userid,record,latitude,longitude);

			return "redirect:/home";
		}
		
}
