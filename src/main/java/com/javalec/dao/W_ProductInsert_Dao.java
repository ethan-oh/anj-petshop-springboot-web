package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class W_ProductInsert_Dao {
	DataSource dataSource;
	
	
	
	
	 public W_ProductInsert_Dao() {
		 try {
				Context context = new InitialContext();
				dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	 
	 public void productInsertAction(String pid, String pname, String pcategory, int pprice, int pstock, int available, String pthumbnail, String pth2, String pth3){
			
		 
		 pthumbnail = pthumbnail.substring(0, pthumbnail.length()-4);
		 pth2 = pth2.substring(0, pth2.length()-4);
		 pth3 = pth3.substring(0, pth3.length()-4);
		 
			String querypthumbnail = pthumbnail;
			String querypth2 = pth2;
			String querypth3 = pth3;
		 
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				
				
				connection = dataSource.getConnection();
				String query = "insert into product(pid, pname, pcategory, pprice, pstock, available, pthumbnail, pth2, pth3) values(?,?,?,?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, pid);
				preparedStatement.setString(2, pname);
				preparedStatement.setString(3, pcategory);
				preparedStatement.setInt(4, pprice);
				preparedStatement.setInt(5, pstock);
				preparedStatement.setInt(6, available);
				preparedStatement.setString(7, querypthumbnail);
				preparedStatement.setString(8, querypth2);
				preparedStatement.setString(9, querypth3);
				preparedStatement.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					// 생성한 순서의 역순대로 닫아준다! -> 퍼포먼스가 좋아짐.
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
	
	
	
	
}
