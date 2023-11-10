package com.example.demo.controller;


	import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
	public class SearchController {
		@Autowired
		JdbcTemplate jdbcTemplate;

		//一覧表示用
		@RequestMapping(path = "/search", method = RequestMethod.GET)
		public String viewPage(Model model) {
			
			List<Map<String, Object>>resultList;
			
			resultList=jdbcTemplate.queryForList("SELECT * FROM syussekibo");
			
			model.addAttribute("selectResult",resultList);
					
			return "mobpro";
		}
	    
	   
	    public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException
	    {
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        // HTMLから送信されたデータを取得
	        String searchInput = request.getParameter("searchInput");

	        // 学籍番号を検索
	        for (int i = 0; i < resultList.length; i++) {
	            if (searchInput.equals(resultList[i])) {
	                // 学生のホーム画面にリダイレクト
	                response.sendRedirect(studentHomePages[i]);
	                return;
	            }
	        }

	        // 学生が見つからない場合はエラーメッセージを表示
	        out.println("<html>");
	        out.println("<head><title>エラー</title></head>");
	        out.println("<body>");
	        out.println("<h2>エラー</h2>");
	        out.println("<p>学生が見つかりませんでした。</p>");
	        out.println("</body>");
	        out.println("</html>");
	    }
	}


