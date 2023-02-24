package com.example.demo;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTests2 {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		
		try(Connection con = 
				DriverManager.getConnection(
                // 19g => "jdbc:oracle:thin:@localhost:1521:orcl"
                // 11g => "jdbc:oracle:thin:@localhost:1521:XE"
				
						"jdbc:mysql://host:port/dbname?characterEncoding=utf8",
						"root",
						"1234")){
			System.out.println(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
