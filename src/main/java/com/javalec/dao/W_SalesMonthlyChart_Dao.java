package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.W_SalesMothlyChart_Dto;

public class W_SalesMonthlyChart_Dao {
	DataSource dataSource;
	
	
	public W_SalesMonthlyChart_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public ArrayList<W_SalesMothlyChart_Dto> salesMonthlyChart(String day){
	
		ArrayList<W_SalesMothlyChart_Dto> dtos = new ArrayList<>();
		W_SalesMothlyChart_Dto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "SELECT 	sum(o.orderprice - o.usedmileage) as total, DATE_FORMAT(o.orderdate, '%Y-%m-%d') AS orderdate"
					+ " FROM orders o"
					+ " JOIN product p ON o.pid = p.pid"
					+ " WHERE o.orderdate >= ? AND o.orderdate < DATE_ADD(DATE_FORMAT(?, '%Y-%m-%d'), INTERVAL 1 MONTH)"
					+ " group by o.orderdate"
					+ " order by o.orderdate";
			preparedStatement = connection.prepareStatement(query);
			String days = day+"-01";
			preparedStatement.setString(1, days);
			preparedStatement.setString(2, days);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int total = resultSet.getInt(1);
				String date = resultSet.getString(2);
				
				dto = new W_SalesMothlyChart_Dto(total, date);
						
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
