package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.J_pdExplainDto;
import com.javalec.dto.J_pdPageDto;

public class J_pdPageDao {
	DataSource dataSource;
	
	
	public J_pdPageDao() {
		// TODO Auto-generated constructor stub
		// DB 연결. 
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop"); 		//context로 가서 디비 로그인 정보 알아냄 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	// 1. 사용자가 선택한 제품 상세정보 페이지 : product 테이블의 모든 데이터 불러와서 상세정보 상단 섹션에 띄우기
	public J_pdPageDto productDetailView(String getPid) {
		J_pdPageDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from product where pid = '" + getPid + "'";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) { 		// db에서 한 줄에 있는 데이터를 열마다 분리해서 할당하는 과정.
				String pid = resultSet.getString("pid");
				String pname = resultSet.getString("pname");
				String pcategory = resultSet.getString("pcategory");
				int pprice = resultSet.getInt("pprice");
				int pstock = resultSet.getInt("pstock");
				int available = resultSet.getInt("available");
				String pthumbnail = resultSet.getString("pthumbnail");
				String pth2 = resultSet.getString("pth2");
				String pth3 = resultSet.getString("pth3");
				
				dto = new J_pdPageDto(pid, pname, pcategory, pprice, pstock, available, pthumbnail, pth2, pth3);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close(); 	// ResultSet이 비면 닫아.
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;
		
	}
	
	// 2. 사용자가 선택한 제품 상세정보 설명 : product 테이블의 모든 데이터 불러와서 상세정보 하단 섹션에 띄우기
	public J_pdExplainDto productExplainView(String getPid) {
		J_pdExplainDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from productimage where pid = '" + getPid + "'";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) { 		// db에서 한 줄에 있는 데이터를 열마다 분리해서 할당하는 과정.
				String pid = resultSet.getString("pid");
				String p_filename = resultSet.getString("p_filename");
				String p_filename2 = resultSet.getString("p_filename2");
				String p_filename3 = resultSet.getString("p_filename3");
				String p_filename4 = resultSet.getString("p_filename4");
				String p_filename5 = resultSet.getString("p_filename5");
				
				dto = new J_pdExplainDto(pid, p_filename, p_filename2, p_filename3, p_filename4, p_filename5);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close(); 	// ResultSet이 비면 닫아.
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;
		
	}
		
		
		

}
