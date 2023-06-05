package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.W_ProductList_Dto;

public class W_ProductList_Dao {
	DataSource dataSource;
	
	public W_ProductList_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public ArrayList<W_ProductList_Dto> productList(String pageNum, String pageSize, String selectType, String selectText){
		
		ArrayList<W_ProductList_Dto> dtos = new ArrayList<>();
		W_ProductList_Dto dto = null;
		
		int queryPageSize = Integer.parseInt(pageSize); 
		int queryPageNum = (Integer.parseInt(pageNum)-1)*queryPageSize;
		String querySelectType = selectType;
		String querySelectText = selectText;

		if(querySelectType.equals("available") && querySelectText.equals("Y")){
			querySelectText = "1";
		}else if(querySelectType.equals("available") && querySelectText.equals("N")) {
			querySelectText = "0";
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "select pid, pname, pcategory, pprice, pstock, available, pthumbnail, ( select count(*) from product where "+querySelectType+" like '%"+querySelectText+"%' ) as count from product WHERE "+querySelectType+" LIKE '%"+querySelectText+"%' order by pid limit ?,?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, queryPageNum);
			preparedStatement.setInt(2, queryPageSize);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String pid = resultSet.getString(1);
				String pname = resultSet.getString(2);
				String pcategory = resultSet.getString(3);
				int pprice = resultSet.getInt(4);
				int pstock = resultSet.getInt(5);
				String available = resultSet.getString(6);
				String pthumbnail = resultSet.getString(7);
				int countSQL = resultSet.getInt(8);
				if(available.equals("1")) {
					available = "Y";
				}else {
					available = "N";
				}
				int count =  (int) Math.ceil((double) countSQL / queryPageSize);
				dto = new W_ProductList_Dto(pthumbnail, pid, pname, pcategory, pprice, pstock, available, count);
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
	
	
	public ArrayList<W_ProductList_Dto> productUpdataView(String piddata){
		
		
		ArrayList<W_ProductList_Dto> dtos = new ArrayList<>();
		W_ProductList_Dto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "select pid, pname, pcategory, pprice, pstock, available, pthumbnail, pth2, pth3 from product where pid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, piddata);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String pid = resultSet.getString(1);
				String pname = resultSet.getString(2);
				String pcategory = resultSet.getString(3);
				int pprice = resultSet.getInt(4);
				int pstock = resultSet.getInt(5);
				String available = resultSet.getString(6);
				String pthumbnail = resultSet.getString(7);
				String pth2 = resultSet.getString(8);
				String pth3 = resultSet.getString(9);
				dto = new W_ProductList_Dto(pthumbnail, pid, pname, pcategory, pprice, pstock, available, pth2, pth3);
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
	
	
} // End
