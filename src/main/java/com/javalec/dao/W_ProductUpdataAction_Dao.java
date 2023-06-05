package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class W_ProductUpdataAction_Dao {
	DataSource dataSource;
	
	public W_ProductUpdataAction_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 public void productUpdataAction(String pid, String pname, String pcategory, int pprice, int pstock, int available, String pthumbnail, String pth2, String pth3){
			
		 
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = dataSource.getConnection();
				String query = "update product set pname = ?, pcategory = ?, pprice = ?, pstock = ?, available = ?, pthumbnail = ?, pth2 = ?, pth3 = ? where pid = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, pname);
				preparedStatement.setString(2, pcategory);
				preparedStatement.setInt(3, pprice);
				preparedStatement.setInt(4, pstock);
				preparedStatement.setInt(5, available);
				preparedStatement.setString(6, pthumbnail);
				preparedStatement.setString(7, pth2);
				preparedStatement.setString(8, pth3);
				preparedStatement.setString(9, pid);
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
