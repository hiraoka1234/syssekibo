package com.example.demo.controller;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TourokuController {
	    
			@Autowired
			JdbcTemplate jdbcTemplate;
	        String url = "jdbc:mysql://localhost:3306/syussekibo";
	        String id = "ID";
	        String password = "PASSWORD";

	        try {
	            Connection connection = DriverManager.getConnection(url, id, password);
	            Statement statement = connection.createStatement();

	          
	            // データベースに情報を挿入
	            String sql = ("INSERT INTO students VALUES(?,?)",id, password);
	            ((java.sql.Statement) statement).executeUpdate(sql);

	            System.out.println("データベースへの登録が完了しました。");

	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


