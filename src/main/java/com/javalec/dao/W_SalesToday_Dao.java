package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.W_SalesToday_Dto;

public class W_SalesToday_Dao {
	DataSource dataSource;
	
	
	
	public W_SalesToday_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public W_SalesToday_Dto salesTable() {
		
		W_SalesToday_Dto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "select count(orderseq), sum(orderprice), sum(usedmileage), (sum(orderprice)-sum(usedmileage)) as total, sum(count) from orders where DATE(orderdate) = CURDATE()";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int count = resultSet.getInt(1);
				int orderprice = resultSet.getInt(2);
				int usedmileage = resultSet.getInt(3);
				int total = resultSet.getInt(4);
				int productcount = resultSet.getInt(5);
				
				dto = new W_SalesToday_Dto(count, orderprice, usedmileage, total, productcount);
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
		return dto;
		
	}
	
public int salesYesterday() {
		
		int yesterday = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "select (sum(orderprice)-sum(usedmileage)) as total from orders where DATE(orderdate) = subdate(CURDATE(),1)";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				yesterday = resultSet.getInt(1);
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
		return yesterday;
		
	}
	
public int salesAvg() {
	
	int avg = 0;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	try {
		connection = dataSource.getConnection();
		String query = "SELECT AVG(orderprice - usedmileage) AS average FROM orders WHERE orderdate >= DATE_SUB(CURDATE(), INTERVAL 7 WEEK) AND orderdate <= CURDATE();";
		preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			avg = resultSet.getInt(1);
			
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
	return avg;
	
}

	
	
	
}
