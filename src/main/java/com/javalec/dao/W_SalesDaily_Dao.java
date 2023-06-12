package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.W_SalesDaily_Dto;
import com.javalec.dto.W_UserList_Dto;

public class W_SalesDaily_Dao {
	DataSource dataSource;
	
	
	public W_SalesDaily_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public ArrayList<W_SalesDaily_Dto> salesDaily(String day){
		
		ArrayList<W_SalesDaily_Dto> dtos = new ArrayList<>();
		W_SalesDaily_Dto dto = null;
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "SELECT o.pid, p.pname, p.pprice, SUM(o.count) AS total_count, SUM(o.orderprice) AS total_orderprice, SUM(o.usedmileage) AS total_usedmileage"
					+ " FROM orders o"
					+ " JOIN product p ON o.pid = p.pid"
					+ " WHERE DATE(o.orderdate) = ?"
					+ " GROUP BY o.pid, p.pname, p.pprice order by total_orderprice desc";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, day);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String pid = resultSet.getString(1);
				String pname = resultSet.getString(2);
				int pprice = resultSet.getInt(3);
				int count = resultSet.getInt(4);
				int orderprice = resultSet.getInt(5);
				int usedmileage = resultSet.getInt(6);
			
				
				dto = new W_SalesDaily_Dto(pid, pname, pprice, count, orderprice, usedmileage);
						
				dtos.add(dto);
			
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 생성한 순서의 역순대로 닫아준다! -> 퍼포먼스가 좋아짐.
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
		
		
		
		
	}
	
	
	
	
	
}//end
