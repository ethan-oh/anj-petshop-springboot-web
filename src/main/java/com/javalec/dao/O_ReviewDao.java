package com.javalec.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class O_ReviewDao {

	DataSource dataSource;

	public O_ReviewDao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
