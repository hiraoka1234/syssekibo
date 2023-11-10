package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

public class TourokuController {
	    public static void main(String[] args) {
			@Autowired
			JdbcTemplate jdbcTemplate;
	        String url = "jdbc:mysql://localhost:3306/syussekibo";
	        String id = "ID";
	        String password = "PASSWORD";

	        try {
	            Connection connection = DriverManager.getConnection(url, user, password);
	            Statement statement = connection.createStatement();

	          
	            // データベースに情報を挿入
	            String sql = ("INSERT INTO students VALUES(?,?)",id, password);
	            statement.executeUpdate(sql);

	            System.out.println("データベースへの登録が完了しました。");

	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
