package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.W_SalesDaily_Dto;
import com.javalec.dto.W_SalesMonthly_Dto;
import com.javalec.dto.W_UserList_Dto;

public class W_SalesMonthly_Dao {
	DataSource dataSource;
	
	
	public W_SalesMonthly_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public ArrayList<W_SalesMonthly_Dto> salesMonthly(String day){
		
		ArrayList<W_SalesMonthly_Dto> dtos = new ArrayList<>();
		W_SalesMonthly_Dto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "SELECT 	o.pid, p.pname, p.pprice, o.count, o.orderprice, o.usedmileage, DATE_FORMAT(o.orderdate, '%Y-%m-%d') AS orderdate"
					+ " FROM orders o"
					+ " JOIN product p ON o.pid = p.pid"
					+ " WHERE o.orderdate >= ? AND o.orderdate < DATE_ADD(DATE_FORMAT(?, '%Y-%m-%d'), INTERVAL 1 MONTH)"
					+ " order by o.orderdate";
			preparedStatement = connection.prepareStatement(query);
			String days = day+"-01";
			preparedStatement.setString(1, days);
			preparedStatement.setString(2, days);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String pid = resultSet.getString(1);
				String pname = resultSet.getString(2);
				int pprice = resultSet.getInt(3);
				int count = resultSet.getInt(4);
				int orderprice = resultSet.getInt(5);
				int usedmileage = resultSet.getInt(6);
				String orderdate = resultSet.getString(7);
			
				dto = new W_SalesMonthly_Dto(pid, pname, pprice, count, orderprice, usedmileage, orderdate);
						
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
